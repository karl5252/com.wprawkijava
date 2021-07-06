package com.wprawkijava.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;



import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AccountTest {

    @Test
    public void givenAccountWhenCreatedThenNotActive(){
        Account newAccount = new Account();

        assertFalse(newAccount.isActive(),"Check if new account is not active");
        assertThat(newAccount.isActive(),equalTo(false));
        assertThat(newAccount.isActive(), is(false));
    }
    @Test
    void givenAccountWhenCreatedThenNotActiveAndActiveAfterActivation(){
        ///given
        Account newAccount = new Account();
        assertFalse(newAccount.isActive(),"Check if new account is not active");
        //when
        newAccount.activate();
        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive(),equalTo(true));
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliverAddressSet(){
        Account account = new Account();

        Address address = account.getDefaultDeliveryAddress();

        assertNull(address);
        assertThat(address,nullValue());
    }
    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){
        Address address = new Address("Krakowska", "523");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);
        Address defaultAddress = account.getDefaultDeliveryAddress();

        assertNotNull(defaultAddress);
        assertThat(defaultAddress, notNullValue());

    }

    @RepeatedTest(5)
    void newAccountWithNotNullAddressShouldBeActive(){
        Address address = new Address("Krakowska", "22");

        Account account = new Account(address);

        assumingThat(address != null, () -> {
                assertTrue(account.isActive());
                });
    }
    @Test
    void invalidEmailShouldThrowException(){
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, ()-> account.setEmail("absadjlkasdjlk") );
    }
    @Test
    void validEmailShouldBeSet(){
        Account account = new Account();
        String email = "12bl!ab_la@googlemail.com";

        account.setEmail(email);

        assertThat(account.getEmail(), is(email));
    }




}
