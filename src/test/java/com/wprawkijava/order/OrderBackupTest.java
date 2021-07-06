package com.wprawkijava.order;

import com.wprawkijava.Meal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

class OrderBackupTest
{
    private static OrderBackup orderBackup;

    @BeforeAll

    static void setup() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();

    }

    @Test
    void backupOrderWithOneMeal() throws IOException {
        Meal meal = new Meal(7, "fries");
        Order order = new Order();
        order.addMealToTheList(meal);

        orderBackup.backupOrder(order);

        System.out.println(order.toString() + " Backed up");
    }
    @Tag("Zupa")
    @Test
    void backupOrderWithMoreThanOneMeal() throws IOException {
        Meal meal1 = new Meal(7, "zupa");
        Meal meal2 = new Meal(3, "Ketchup");
        Order order = new Order();
        order.addMealToTheList(meal1);
        order.addMealToTheList(meal2);


        orderBackup.backupOrder(order);

        System.out.println(order.toString() + " Backed up");
    }

    @AfterAll
    static  void tearDown() throws IOException {
        orderBackup.closeFile();
    }


}