package com.example.ebankingbackend.services;

import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.entities.CurrentAccount;
import com.example.ebankingbackend.entities.SavingAccount;
import com.example.ebankingbackend.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount=bankAccountRepository.findById("29ddd028-1557-4aeb-9ad6-cf2d6597347d").orElse(null);
        if (bankAccount != null){
            System.out.println("******************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if(bankAccount instanceof CurrentAccount){
                System.out.println("Over Draft => " +((CurrentAccount)bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Rate => "+((SavingAccount)bankAccount).getInterestRate());

            }

            bankAccount.getAccountOperations().forEach(op->{

                System.out.println("================================");
                System.out.println(op.getType());
                System.out.println(op.getOperationDate());
                System.out.println(op.getAmount());

            });


        }

    }
}
