package org.example.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 *  스트림 활용(필터링)
 */
public class Main03 {
    public static void main(String[] args) {
        List<Dish> dishes = DishesFactory.getDishes();

        // 1) 중복 제거
        List<Integer> numbers = List.of(1, 2, 1, 3, 5, 5, 4, 6);
        numbers.stream().distinct().forEach(System.out::println);

        // 2) 채식 여부 확인
        List<Dish> vegeterians = dishes.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());

        // 3) Java 9 이후 (takewhile, dropwhile)
        // 아래의 링크에서 확인
        // https://blog.indrek.io/articles/whats-new-in-java-9-streams/
        /*
            takewhile (Predicate가 false인 순간 작업 중단)
            filter는 Predicate에 관계 없이 모든 요소를 순환하지만
            takewhile은 false가 나오면 기존의 요소들을 반환함
         */
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .takeWhile(n -> n % 2 == 0)
                .forEach(System.out::println);
        /*
            dropwhile (Predicate가 최초로 false가 되는 순간 작업 중단 후 반환)
            takewhile과의 차이점은 predicate가 true이면 해당 요소를 버림
         */
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .dropWhile(n -> n % 2 == 0)
                .forEach(System.out::println);
        /*
            takeWhile, dropWhile 사용 시 주의할 점은 연산 중간에 중단되기 때문에
            정렬 후 사용하는 것을 추천한다
         */

        // 4) 요소 축소
        List<Dish> threeDishes = dishes.stream()
                .limit(3)
                .collect(toList());
        // 별도의 정렬을 진행하지 않으면 기존의 요소 순서대로 나오는 것을 확인할 수 있다

        // 5) 요소 건너뛰기
        List<Dish> skipTwoDishes = dishes.stream()
                .skip(2)
                .collect(toList());
        // port, beef를 건너뛰기한다

        // Q) 스트림을 활용하여 [처음 두 고기 요리]를 건너뛰기 한 요리목록을 추출
        List<Dish> skipTwoMeatDishes = dishes.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .skip(2)
                .collect(toList());

        // 6) 매핑 (map, flatMap)
        List<String> dishNames = dishes.stream()
                .map(Dish::getName) // Stream<String> 반환
                .collect(toList());

        List<Integer> dishNameLengths = dishes.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());

        // 아래와 같이 컬랙션 내부에 컬랙션이 존재하는 경우
        List<List<String>> words = List.of(
                List.of("Hello"),
                List.of("My"),
                List.of("Name"),
                List.of("Is"),
                List.of("Lee") );

        // 평면화 진행
        List<String> greeting = words.stream()
                .flatMap(Collection::stream)
                .collect(toList());

        // join을 추가하여 한문장으로도 추출할 수 있다
        String myName = words.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.joining(" ", "", ""));

        // flatMap의 활용을 극대화해보자
        // 아래 두개의 숫자 리스트가 있으며, 각 숫자 쌍을 배열로 반환하여라
        // ex) [1,4], [1,5], [2,4]....
        List<Integer> numbers1 = List.of(1, 2, 3);
        List<Integer> numbers2 = List.of(4, 5);
        List<int []> pairList = numbers1.stream()
                                .flatMap(n1 ->     // flatMap 내부에는 Stream<int[]>를 제공해야한다
                                    numbers2.stream().map(n2 -> new int[] {n1, n2})
                                )
                                .collect(toList());

        // 7) 검색과 매칭
        // 1. anyMatch 하나 이상이라도 일치하면 true 반환
        boolean isVegeterianExist = dishes.stream().anyMatch(Dish::isVegetarian);

        // 2. allMatch 모든 요소가 일치하면 true 반횐
        boolean isAllDishOverThousandCaloric = dishes.stream().allMatch(dish -> dish.getCalories() > 1000);
        System.out.println(isAllDishOverThousandCaloric);

        boolean isNotAllDishOverThousandCaloric = dishes.stream().noneMatch(dish -> dish.getCalories() > 1000);
        System.out.println(isNotAllDishOverThousandCaloric);
        /*
            allMatch, noneMatch는 쇼트서킷 방식이다
            쇼트서킷은 연산 중 하나라도 false가 나오면 결과 반환과 동시에 연산을 중단한다
         */

        // 3. findFirst : 조건에 맞는 첫번째 요소를 반환(반환타입 Optional)
        List<Integer> myNumbers = List.of(3, 1, 4, 5, 100, 990, 300, 600);
        Optional<Integer> numOpt = myNumbers.stream()
                                        .filter(n -> n > 300)
                                        .findFirst(); // 990


        // 8) 리듀스(reduce) : 스트림의 모든 요소들을 처리하여 하나의 결과로 도출하고자 할 때 사용
        // 자바 7 이전
        int totalCaloric = 0;
        for (Dish dish : dishes) {
            int caloric = dish.getCalories();
            totalCaloric += caloric;
        }

        // 스트림 활용
        int totalCaloricWithStream = dishes
                .stream()
                .map(Dish::getCalories)
                .reduce(0, (a, b) -> a + b);


        // 최소값 도출
        // reduce의 초기값을 선언하지 않을 경우 reduce는 Optional 타입을 반환한디
        int minCaloric = dishes.stream()
                .map(Dish::getCalories)
                .reduce(Integer::min).orElse(0);
        // Integer::max로 최대값도 도출가능하다
        System.out.println(minCaloric);
    }
}
