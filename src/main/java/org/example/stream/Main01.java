package org.example.stream;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * 스트림(Stream)이란?
 * 자바 1.8부터 추가된 API이다.
 * 스트림은 임시 코드대신 선언형 방식을 통해 데이터 처리를 진행할 수 있다.
 *  > 이를 공식문서에서는 widgets과 같다고 한다.
 *  > https://docs.oracle.com/javase/10/docs/api/java/util/stream/Stream.html
 */
public class Main01 {
    public static void main(String[] args) {
        List<Dish> dishes = DishesFactory.getDishes();
        // 아래의 요청사항에 대하여 스트림/non-스트림의 차이점을 살펴보자
        // Q) 칼로리가 400미만인 메뉴 추출 후 칼로리 기준으로 오름차순을 진행하여라
        //  그 후 해당 메뉴의 메뉴명만 추출하여라
        // 자바 7 이전의 방식으로
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : lowCaloricDishes) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }

        // 자바 8이후 스트림 활용
        List<String> lowCaloricNames = dishes.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());

        /*
            후자의 방식이 전자보다 간결하며 추출 진행 과정을 한눈에 알아볼 수 있다
            스트림을 활용하면 아래와 같은 이점을 얻을 수 있다.
            1) 선언형 방식: 간결성, 가독성 향상
                * 단 stream으로 작성한 코드가 무조건적으로 간결함을 제공하는 것은 아니다!
            2) 유연성 향상: 요청사항이 변경되는 경우 중간의 연산블록(filter, map 등..)을 수정하여
            대응이 가능하다
            3) 병렬화
         */
    }
}
