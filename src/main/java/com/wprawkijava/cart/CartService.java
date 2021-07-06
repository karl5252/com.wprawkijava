package com.wprawkijava.cart;

import com.wprawkijava.order.OrderStatus;

public class CartService {
    private ICartHandler cartHandler;

    public CartService(ICartHandler cartHandler){
        this.cartHandler = cartHandler;
    }
    Cart processCart(Cart cart) {
        if (cartHandler.canHandleCart(cart)) {
            cartHandler.sendToPrepare(cart);
            cart.getOrders().forEach(order -> {
                order.changeOrderStatus(OrderStatus.PREPARING);
            });
            return cart;
        } else {
            cart.getOrders().forEach(order -> {
                order.changeOrderStatus(OrderStatus.REJECTED);
            });
            return cart;


        }
    }
}
