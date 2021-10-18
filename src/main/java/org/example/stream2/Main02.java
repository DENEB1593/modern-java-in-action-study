package org.example.stream2;

import org.example.stream.Dish;
import org.example.stream.DishesFactory;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * 스트림 컬렉터의 그룹화(grouping)
 */
public class Main02 {

    enum CaloricLevel {DIET, NORMAL, FAT}

    public static final Map<String, List<String>> dishTags = new HashMap<>();

    static {
        dishTags.put("pork", List.of("greasy", "salty"));
        dishTags.put("beef", List.of("salty", "roasted"));
        dishTags.put("chicken", List.of("fried", "crisp"));
        dishTags.put("french fries", List.of("greasy", "fried"));
        dishTags.put("rice", List.of("light", "natural"));
        dishTags.put("season fruit", List.of("fresh", "natural"));
        dishTags.put("pizza", List.of("tasty", "salty"));
        dishTags.put("prawns", List.of("tasty", "roasted"));
        dishTags.put("salmon", List.of("delicious", "fresh"));
    }

    static Function<Dish, CaloricLevel> caloricLevelGroupingFunction = (dish) -> {
        int caloric = dish.getCalories();
        if (caloric <= 400) return CaloricLevel.DIET;
        else if (caloric <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    };

    public static void main(String[] args) {
        List<Dish> dishes = DishesFactory.getDishes();

        // 1) 타입별 구분
        Map<Dish.Type, List<Dish>> dishesByType = dishes.stream()
                .collect(groupingBy(Dish::getType));

        // 2) 다이어트 등급으로 구분(구분자에 CaloricLevel 구분자를 반환한다)
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = dishes.stream()
                .collect(groupingBy(dish -> {
                    int caloric = dish.getCalories();
                    if (caloric <= 400) return CaloricLevel.DIET;
                    else if (caloric <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));

        // 3) 500 칼로리 이상의 음식을 Dish 타입별로 분류 - Collectors.filtering
        Map<Dish.Type, List<Dish>> dishesByOver400Calories = dishes.stream()
                .collect(groupingBy(Dish::getType,
                        filtering(d -> d.getCalories() >= 500, toList())));

        // 4) 메뉴 타입 별 요리 이름 - Collectors.mapping
        Map<Dish.Type, List<String>> dishNamesByType = dishes.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(Dish::getName, toList())));


        // 5) 요리 별 태그 추출
        /*
        System.out.println(dishTags);
        Map<Dish.Type, Set<String>> dishTagsByType = dishes.stream()
                .collect(groupingBy(Dish::getType,
                            flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet()))
                );
         */

        // 6) 다수준 그룹핑
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeAndCaloricLevel = dishes.stream()
                .collect(groupingBy(Dish::getType,
                            groupingBy(caloricLevelGroupingFunction)));
        // 각 요리 별 NORMAL, DIET, FAT 레벨을 구분 가능함

        // 7) 서브그룹으로 데이터 수집
        Map<Dish.Type, Long> dishCountingByType = dishes.stream()
                .collect(groupingBy(Dish::getType, counting()));

        // maxBy를 통해 Type별 최고 칼로리를 가진 요리 추출하기(Optional)
        Map<Dish.Type, Optional<Dish>> maxDishCountingByType = dishes.stream()
                .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

        // collectAndThen을 통해 Type별 최고 칼로리를 가진 요리 추출하기
        Map<Dish.Type, Dish> mostCaloricByType = dishes.stream()
                .collect(groupingBy(Dish::getType,
                            collectingAndThen(maxBy(comparingInt(Dish::getCalories)), // 감싸인 컬랙터
                                    Optional::get)));   // 변환 함수

        // 타입별 칼로리 총합 구하기
        Map<Dish.Type, Integer> totalCaloriesByType = dishes.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
    }
}
