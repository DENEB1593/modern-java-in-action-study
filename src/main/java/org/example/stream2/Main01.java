package org.example.stream2;

import org.example.stream.Dish;
import org.example.stream.DishesFactory;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 *  스트림 컬렉터(Collector)
 *  스트림에서 요소를 어떠한 형태로 도출할지 결정하는 기능이다.
 *  일전에 collect(Collectors.toList()) 가 대표적인 예시이다
 *  컬렉터는 리듀싱, 그룹핑 기능, 컬렉션 결정 기능 등을 가지고 있다.
 */
public class Main01 {
    public static void main(String[] args) {
        List<Dish> dishes = DishesFactory.getDishes();
        // 리듀싱 기능 - counting
        long dishCount = dishes.stream().count();

        // 리듀싱 기능 - maxBy, MinBy
        Optional<Dish> minCaloricDish = dishes.stream()
                .collect(minBy(comparingInt(Dish::getCalories))); // collect(..) -> min으로 깔끔하게 대체 가능

        Optional<Dish> maxCaloricDish = dishes.stream()
                .collect(maxBy(comparingInt(Dish::getCalories))); // collect(..) -> max()로 대체 가능

        // 요약 기능 Collectors.summingInt와 같은 요약 팩토리 매소드를 제공
        int totalCaloric = dishes.stream().collect(summingInt(Dish::getCalories));

        // 이밖에 averagingInt, summarizingInt 등도 존재
        double averageCaloric = dishes.stream().collect(averagingInt(Dish::getCalories));
        IntSummaryStatistics caloricStatistics = dishes.stream().collect(summarizingInt(Dish::getCalories));

        // 리듀싱 연산자 요약하여 사용하기
        int totalCal = dishes.stream().collect(reducing(
                0, Dish::getCalories, Integer::sum));
     

    }
}
