package com.wprawkijava.cart;

import com.wprawkijava.Meal;
import com.wprawkijava.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Order> orders = new ArrayList<>();

    void addOrderToCart(Order order){
        this.orders.add(order);
    }
    void clearCart(){
        this.orders.clear();
    }

    public List<Order> getOrders() {
        return orders;
    }

    void simulateLargeOrders(){
        for (int i = 0; i < 1_000; i++){
            Meal meal = new Meal(i%10, "Sushi piece " + i);
            Order order = new Order();
            order.addMealToTheList(meal);
            addOrderToCart(order);
        }

    }
}
