package org.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 스트림을 활용하여 피타고라스, 피보나치를 풀이한다.
 */
public class Main04 {
    public static void main(String[] args) {
        /*
        1부터 100까지 정수 중 피타고라스의 경우의 수를 생성한다.
        [3, 4, 5], [9, 12, 15]....
        a^2 + b^2 = c^2
         */
        Stream<int[]> pythagorasStream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(n1 -> {
                    return IntStream.rangeClosed(n1 , 100)
                            .filter(n2 ->
                                    Math.sqrt(n1 * n1 + n2 * n2) % 1 == 0)
                            .mapToObj(n2 ->
                                    new int[] { n1, n2, (int) Math.sqrt(n1 * n1 + n2 * n2) });
                });

        // 위 코드는 제곱근을 2번하기 때문에 연산비용이 존재
        List<double[]> pythagorasList = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(n1 -> IntStream.rangeClosed(n1, 100)
                        .mapToObj(n2 -> new double[] {n1, n2, Math.sqrt(n1*n1 + n2*n2)}))
                .filter(n -> n[2] % 1 == 0) // 정수인지 판별
                .collect(toList());


        // 무한 스트림 생성
        Stream.iterate(0, n -> n + 1)
                .limit(10);
    //            .forEach(System.out::println);

        // 난수 생성
        new Random().ints(10, 0, 10)
                .forEach(System.out::println);
    }
}
