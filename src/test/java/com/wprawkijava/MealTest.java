package com.wprawkijava;

import com.wprawkijava.extensions.IAExceptionIgnoreExtension;
import com.wprawkijava.order.Order;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldGetPriceReturnNotNullValue(){
        int price  = 25;
        Meal meal = new Meal(price);

        assertEquals(meal.getPrice(),25);
        assertThat(meal.getPrice(),equalTo(25));

    }

    @Test
    void givenPriceWhenDiscountAppliedThenReducePriceByDiscount() {
        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28,discountedPrice);
        assertThat(discountedPrice,equalTo(28));

    }
    @Test
    void referencesToSameObjectShouldBeEqual(){
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;//new Meal(20);

        assertSame(meal1,meal2);
        assertThat(meal1,sameInstance(meal2));
    }
    @Test
    void referencesToDifferentObjectShouldNotBeEqual(){
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

        assertNotSame(meal1,meal2);
        assertThat(meal1, not(sameInstance(meal2)));
    }
    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){

        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");

        assertEquals(meal1,meal2, "Checking if two meals are equal");

    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherOrEqualThanPrice(){
        Meal meal = new Meal(8, "Pizza");
        //meal.getDiscountedPrice(8);

        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(8));
    }

    @ParameterizedTest
    @ValueSource(ints = {5,10,15,18})
    void mealPricesShouldBeLowerThanTwenty(int price){
        assertThat(price,lessThan(20));

    }
    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price){
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheesburger", 12)
        );
    }
    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNameShouldEndWithCake(String name){
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }
    private static Stream<String> createCakeNames(){
        List<String> cakeNames = Arrays.asList("Cheescake", "Pancake", "Cupcake");
        return cakeNames.stream();
    }
    @ExtendWith(IAExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {1,3,5,8})
    void mealPricesShouldBeLowerThanTen(int price){
       // assertThat(price,lessThan(10));
        if(price > 5){
            throw new IllegalArgumentException();
        }
        assertThat(price,lessThan(10));

    }
    @Tag("Zupa")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices(){
        Order order = new Order();
        order.addMealToTheList(new Meal(2, "Zupa z testera", 5));
        order.addMealToTheList(new Meal(12, "Zupa z Kraba", 5));
        order.addMealToTheList(new Meal(11, "Zupa z żółwia", 9));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        for (int i = 0; i < order.getMeals().size(); i++){
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
              assertThat(calculatePrice(price,quantity), lessThan(100));

            };
            String name = "Test name : "+ i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name,executable);
            dynamicTests.add(dynamicTest);


            }
        return dynamicTests;


        }



    private int calculatePrice(int price, int quantity){
        return price * quantity;
    }

}