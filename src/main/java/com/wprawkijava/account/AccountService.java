package com.wprawkijava.account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountService {

    private IAccountRepository accountsRepository;

    public AccountService(IAccountRepository accountRepository){
        this.accountsRepository = accountRepository;
    }
    List<Account> getAllActiveAccounts(){
        return accountsRepository.getAllAccounts().stream()
                .filter(Account::isActive)
                .collect(Collectors.toList());


    }



}
