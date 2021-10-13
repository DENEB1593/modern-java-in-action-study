package org.example.stream;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  스트림의 특징은 아래와 같다.
 *
 *  1) 연속된 요소(element) : 스트림 내부에는 같은 성질을 가진 요소가 연속되어 존재하며,
 *  해당 요소들의 집합에 대한 처리 기능을 제공한다.
 *    컬렉션(Collection)과의 차이점?
 *    컬렉션: 데이터의 관리, 접근을 목적이 주
 *    스트림: 데이터에 대한 처리가 주
 *
 *  2) 소스(source) : 컬렉션, 배열, I/O등으로 부터 소스를 얻는다.
 *  스트림은 이들의 정렬을 그대로 유지한채 스트림으로 변환시켜준다.
 *
 *  3) 데이터 처리 연산
 */
public class Main02 {
    public static void main(String[] args) {
        List<Dish> dishes = DishesFactory.getDishes();
        /**
         * 위의 설명은 아래의 예시로 확인 가능하다.
         * 여기서 소스는 스트림을 제공한 dishes이며,
         * dishes는 연속된 요소를 스트림에 제공한다.
         * 이후 데이터처리여산(filter, map, limit)으로 데이터 처리 후
         * collect를 통해 처리 결과를 반환한다
         */
        List<String> threeHighCaloricDishNames =
                dishes.stream()
                        .filter(dish -> dish.getCalories() < 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(Collectors.toList());
    }
}
