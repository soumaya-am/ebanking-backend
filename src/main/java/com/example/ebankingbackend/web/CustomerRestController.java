package com.example.ebankingbackend.web;

import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")

public class CustomerRestController {
    private BankAccountService bankAccountService;
    @RequestMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDTO>  customers(){
        return bankAccountService.listCustomers();
    }
    @GetMapping("/customers/search")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDTO> searchCustomer(@RequestParam(name = "keyword", defaultValue = "") String keyword){

        return bankAccountService.searchCustomers("%"+keyword+"%");
    }
@GetMapping("/customers/{id}")
@PreAuthorize("hasAuthority('SCOPE_USER')")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId){

        return bankAccountService.getCustomer(customerId);
    }
@PostMapping("/customers")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
       return bankAccountService.saveCustomer(customerDTO);
}

@PutMapping("/customers/{customerId}")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
       return bankAccountService.updateCustomer(customerDTO);
}
@DeleteMapping("/customers/{id}")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
}
}
