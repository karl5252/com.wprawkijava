package com.wprawkijava;

import java.util.Objects;

public class Meal {

    private int price;
    public String name;

    public int getQuantity() {
        return quantity;
    }

    public Meal(int price, String name, int quantity) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    private int quantity;

    public Meal(int price){
        this.price = price;
    }

    public Meal(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice(){
        return price;
    }
    public int getDiscountedPrice(int discount){
        if(discount >= this.price){
            throw new IllegalArgumentException("Discount value cannot be higher than price!");
        }
        return this.price - discount;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        if(price != meal.price) return false;
        return name != null ? name.equals(meal.name) : meal.name == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }
}
