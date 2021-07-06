package com.wprawkijava.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Fabryczna, 10","Armii krajowej, 57/11", "Sałaciana, 35", "'Papcia Chmiela, Tytusa, Romka i Atomka', 16" })
    void givenAddressShouldNotBEEmptyAndHaveProperNames(String streetName, String streetNumber){
        assertThat(streetName, notNullValue());
        assertThat(streetName, containsString("a"));

        assertThat(streetNumber, notNullValue());
        assertThat(streetNumber.length(), lessThan(8));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/adresses.csv")
    void givenAddressFromFileShouldNotBeEmptyAndHaveProperNames(String streetName, String streetNumber){
        assertThat(streetName, notNullValue());
        assertThat(streetName, containsString("a"));

        assertThat(streetNumber, notNullValue());
        assertThat(streetNumber.length(), lessThan(8));
    }

}