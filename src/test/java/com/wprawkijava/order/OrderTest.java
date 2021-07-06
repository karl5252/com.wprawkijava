package com.wprawkijava.order;

import com.wprawkijava.Meal;
import com.wprawkijava.extensions.BeforeAfterExtension;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsEmptyCollection.emptyCollectionOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(BeforeAfterExtension.class)
class OrderTest {
    private Order order;

    @BeforeEach
    void setupOrder(){        order = new Order();    }
    @AfterEach
    void teardownRun(){
        order.cancel();
    }


    @Test
    @Disabled
    void testAssertArrayEquals(){
        int[] ints1 = {1,2,3};
        int[] ints2 = {1,2,3};

        assertArrayEquals(ints1,ints2);
    }
    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder(){
        //Order order = new Order();
        assertThat(order.getMeals(),empty());
        assertThat(order.getMeals().size(),equalTo(0));
        //assertThat(order.getMeals().size(),hasSize(0));
        MatcherAssert.assertThat(order.getMeals(),emptyCollectionOf(Meal.class));
    }
    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){
        Meal meal = new Meal(10, "Gazpacho");
        //Order order = new Order();

        assertThat(order.getMeals().size(),equalTo(0));

        order.addMealToTheList(meal);

        assertThat(order.getMeals().size(),equalTo(1));
        assertThat(order.getMeals(), contains(meal));
    }
    @Test
    void removingMealToOrderShouldDecreaseOrderSize() {
        Meal meal = new Meal(10, "Gazpacho");
        //Order order = new Order();
        order.addMealToTheList(meal);
        order.removeMealFromOrder(meal);

        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), not(contains(meal)));
    }
    @Test
    void mealShouldBeInCorrectOrderAfterAddingThemToOrderObject() {
        Meal meal1 = new Meal(10, "Gazpacho");
        Meal meal2 = new Meal(15, "Pizza");


        //Order order = new Order();
        order.addMealToTheList(meal1);
        order.addMealToTheList(meal2);
        //order.removeMealFromOrder(meal);

        assertThat(order.getMeals().size(), equalTo(2));
        assertThat(order.getMeals(), contains(meal1,meal2));
    }
    @Test
    void testIfTwoOrdersAreSame() {
        Meal meal1 = new Meal(10, "Gazpacho");
        Meal meal2 = new Meal(15, "Pizza");
        Meal meal3 = new Meal(19, "Kebap");

        List<Meal> meals1 = Arrays.asList(meal1, meal2);
        List<Meal> meals2 = Arrays.asList(meal1, meal2);

        assertThat(meals1, is(meals2));
    }
    @Test
    void orderTotalPriceShouldNotExceedIntValue(){
        Meal meal1 = new Meal(Integer.MAX_VALUE, "Gazpacho");
        Meal meal2 = new Meal(Integer.MAX_VALUE, "Pizza");

        order.addMealToTheList(meal1);
        order.addMealToTheList(meal2);

        /*int sum = order.totalPrice();
        System.out.println(sum);*/
        assertThrows(IllegalStateException.class, () -> order.totalPrice());



    }
    @Test
    void emptyOrderTotalPriceShouldEqualZero(){
        //Order is created in BeforeEach

        assertThat(order.totalPrice(), is(0));

    }
    @Test
    void cancelingOrderShouldRemoveAllItemsFromMealsList(){
        Meal meal1 = new Meal(10, "Gazpacho");
        Meal meal2 = new Meal(15, "Pizza");

        order.addMealToTheList(meal1);
        order.addMealToTheList(meal2);
        order.cancel();

        assertThat(order.getMeals().size(),is(0));

    }

}