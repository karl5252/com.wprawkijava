package com.wprawkijava.cart;

import com.wprawkijava.account.Account;
import com.wprawkijava.account.Address;
import com.wprawkijava.order.Order;
import com.wprawkijava.order.OrderStatus;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class CartServiceTest {

    /*private List<Account> preparedAccountData(){
        //return null;
        Address address1 = new Address("testowa", "4/6");
        Account account1 = new Account(address1);
        //account1.setDefaultDeliveryAddress(address1);

        Address address2 = new Address("testowa", "2");
        Account account2 = new Account();
        // account2.setDefaultDeliveryAddress(address2);
        Account account3 = new Account(address2);

        return Arrays.asList(account1,account2,account3);
    }*/

    @Test
    void processCartShouldSendToPrepare() {
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        ICartHandler cartHandler = mock(ICartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        Cart resultCart = cartService.processCart(cart);

        verify(cartHandler).sendToPrepare(cart);
        verify(cartHandler, times(1)).sendToPrepare(cart);
        verify(cartHandler,atLeastOnce()).sendToPrepare(cart);

        verify(cartHandler).sendToPrepare(cart);

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);


        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));


    }
    @Test
    void processEmptyCartShouldNotSendToPrepare() {
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        ICartHandler cartHandler = mock(ICartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        Cart resultCart = cartService.processCart(cart);

       verify(cartHandler,never()).sendToPrepare(cart);
       assertThat(resultCart.getOrders(), hasSize(1));
       assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));


    }
    @Test
    void processEmptyCartShouldNotSendToPrepareWithArgumentMatchers() {
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        ICartHandler cartHandler = mock(ICartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(any())).willReturn(false);

        Cart resultCart = cartService.processCart(cart);

        verify(cartHandler,never()).sendToPrepare(any(Cart.class));
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));


    }
    @Test
    void canHandleCartShouldReturnMultipleValues(){
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        ICartHandler cartHandler = mock(ICartHandler.class);

        given(cartHandler.canHandleCart(cart)).willReturn(true,false,false,true);

        assertThat(cartHandler.canHandleCart(cart),equalTo(true));
        assertThat(cartHandler.canHandleCart(cart),equalTo(false));
        assertThat(cartHandler.canHandleCart(cart),equalTo(false));
        assertThat(cartHandler.canHandleCart(cart),equalTo(true));

    }
}