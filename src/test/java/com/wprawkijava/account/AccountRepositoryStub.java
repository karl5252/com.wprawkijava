package com.wprawkijava.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements IAccountRepository {
    @Override
    public List<Account> getAllAccounts() {
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
}
