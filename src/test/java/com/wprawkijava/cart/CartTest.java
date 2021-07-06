package com.wprawkijava.cart;

import com.wprawkijava.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Test cases for cart")
class CartTest {

    @Test
    @DisplayName("Cart is able to process 1000 orders in 100 ms")
    void simulateLargeOrders() {
        Cart cart = new Cart();

Assertions.assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrders);    }
@Test
void cartShouldNotBeEmptyWhenAddingOrderToCart(){
        Order order = new Order();
        Cart cart = new Cart();

        cart.addOrderToCart(order);

        //assertThat(cart.getOrders(),anyOf(
    assertThat(cart.getOrders(),allOf(
           notNullValue(),
           hasSize(1),
           is(not(empty())),
           is(not(emptyCollectionOf(Order.class)))
        ));

    assertAll(
            () -> assertThat(cart.getOrders(),notNullValue()),
            () -> assertThat(cart.getOrders(), hasSize(1)),
            () -> assertThat(cart.getOrders(), is(not(empty()))),
            () -> assertThat(cart.getOrders().get(0).getMeals(), empty() )
    );

}
}