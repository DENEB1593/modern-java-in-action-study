package org.example.stream;

import java.util.List;

public class DishesFactory {
    private static List<Dish> dishes = List.of(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("checken", false, 400, Dish.Type.MEAT),
            new Dish("rice", true, 530, Dish.Type.OTHER),
            new Dish("banana", true, 300, Dish.Type.OTHER),
            new Dish("salmon", false, 450, Dish.Type.FISH),
            new Dish("pizza", true, 650, Dish.Type.OTHER),
            new Dish("shisi", true, 430, Dish.Type.FISH)
    );

    public static List getDishes() {
        return dishes;
    }
}
