package com.wprawkijava.order;

import com.wprawkijava.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {


    private OrderStatus orderStatus;
    private List<Meal> meals = new ArrayList<>();

    public void addMealToTheList(Meal meal){
    this.meals.add(meal);
    }
    public void removeMealFromOrder(Meal meal){
        this.meals.remove(meal);

    }
    public List<Meal> getMeals(){
        return meals;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
    int totalPrice(){
        //sum = 0;

        int sum =  this.meals.stream().mapToInt(Meal::getPrice).sum();
        if (sum < 0){
            throw new IllegalStateException("Price limit exceeded!");
        }else{
            return sum;
        }

    }

    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;

    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    void cancel(){
        this.meals.clear();
    }
}
