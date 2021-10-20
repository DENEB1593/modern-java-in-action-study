package org.example.functionalprogramming;

import java.util.stream.IntStream;

public class Main02 {
    public static void main(String[] args) {
        /**
         * 함수형 프로그래밍이란?
         * : 말 그대로 함수를 이용한 프로그래밍이다.
         *
         * 그럼 함수는 무엇인가?
         * : 매개변수를 받아서 기능 수행 후 결과를 반환한다. (메서드(method)와 동일)
         * 일반적인 함수는 부작용을 가지고 있다.
         *      ex) someFunction()을 이용하여 A 객체의 필드를 변경하거나, B 객체의 필드를 변경하는 경우
         *
         * 하지만 함수형 프로그래밍에서의 함수는 부작용 없이 동작하는 것을 지향한다.
         *
         */

        long start = System.nanoTime();
        long result = factorialIterative(100);
        long duration = System.nanoTime() - start;
        System.out.printf("%d : %d\n", duration, result);

        start = System.nanoTime();
        result = factorialStream(100);
        duration = System.nanoTime() - start;
        System.out.printf("%d : %d\n", duration, result);

        System.out.println(factorialTailRecursive(10));
    }
    /* 반복방식의 팩토리얼 구현 */
    static long factorialIterative(long n) {
        long result = 1;
        for (long i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /* 재귀방식의 팩토리얼 구현 */
    /*
        함수를 호출하는 경우 새로운 스택 공간이 생성된다. 재귀 함수는 많은 스택공간을 생성할 수 있어서
        StackOverFlowException 유발할 수 있는 단점이 있다.
    */
    static int factorialRecursive(int n) {
        return n == 1 ? n : n * factorialRecursive(n - 1);
    }

    /* 스트림방식의 팩토리얼 */
    static int factorialStream(int n) {
        return IntStream.rangeClosed(1, n).reduce(1 , (a, b) -> a * b);
    }

    /* 꼬리호출 기법을 통한 재귀호출 최적화(스택 공간 최적화) */
    static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    static long factorialHelper(long start, long n) {
        return n == 1 ? start : factorialHelper(start * n, n - 1);
    }

}
