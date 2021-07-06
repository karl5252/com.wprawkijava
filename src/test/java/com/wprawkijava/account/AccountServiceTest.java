package com.wprawkijava.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    private List<Account> preparedAccountData(){
        //return null;
        Address address1 = new Address("testowa", "4/6");
        Account account1 = new Account(address1);
        //account1.setDefaultDeliveryAddress(address1);

        Address address2 = new Address("testowa", "2");
        Account account2 = new Account();
        // account2.setDefaultDeliveryAddress(address2);
        Account account3 = new Account(address2);

        return Arrays.asList(account1,account2,account3);
    }
    @Test
    void getAllActiveAccounts() {
        //g
        List<Account> accounts = preparedAccountData();
        IAccountRepository accountRepository = mock(IAccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);

        when(accountRepository.getAllAccounts()).thenReturn(accounts);
        List<Account> accountList = accountService.getAllActiveAccounts();

        //System.out.println(accountList.toString());
        assertThat(accountList, hasSize(2));

    }
    @Test
    void getNoActiveAccounts(){
        IAccountRepository accountRepository = mock(IAccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(Collections.emptyList());

        List<Account> accountList = accountService.getAllActiveAccounts();

        assertThat(accountList,hasSize(0));

    }

}
