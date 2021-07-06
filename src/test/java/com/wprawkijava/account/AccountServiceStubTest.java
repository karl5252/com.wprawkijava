package com.wprawkijava.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceStubTest {

    @Test
    void getAllActiveAccounts() {
        //g
        IAccountRepository repositoryStub = new AccountRepositoryStub();
        AccountService accountService = new AccountService(repositoryStub);

        List<Account> accountList = accountService.getAllActiveAccounts();

        //System.out.println(accountList.toString());
        assertThat(accountList, hasSize(2));

    }
}