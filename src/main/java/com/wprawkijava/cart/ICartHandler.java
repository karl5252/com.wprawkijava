package com.wprawkijava.cart;

public interface ICartHandler {
    boolean canHandleCart(Cart cart);
    void sendToPrepare(Cart cart);
}
