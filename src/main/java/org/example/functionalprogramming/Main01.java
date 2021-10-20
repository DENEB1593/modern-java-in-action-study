package org.example.functionalprogramming;

import org.example.stream.Transaction;

import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

/**
 * 함수형 프로그램을 사용하는 이유
 * : 자바 8을 기점으로 자바는 타 프로그래밍 언어에서 제공되는 함수형 프로그래밍 기법(스트림, 람다 등..)을 지원하기 시작했다.
 * 기존의 방식보다 함수형 프로그래밍 사용하면 어떠한 장점을 가지는지 확인한다.
 */
public class Main01 {
    private static List<Number> list = new ArrayList<>();

    public static void main(String[] args) throws IllegalAccessException {
        for (int i = 1; i <= 10; i++)
            list.add(i);

        /*
            위와 같이 공유 데이터(list)가 있다고 가정하자
            위 공유 데이터는 여러 클래스가 사용하고 여러 클래스에 의해 읽기/쓰기를 반복할 것이다.
            공유 데이터는 [자료구조를 고치거나 필드 값 임의 변경], [예외 발생], [파일에 쓰기 등의 I/O 동작] 등 문제를 유발한다.

            이를 방지하고자 불변객체를 사용할 수 있으나,
                Collections.singletonMap(),
                Collections.unmodifiableList() 와 같은 방법을 사용한다.
         */
        List<Number> unmodifiedList = Collections.unmodifiableList(list);
        // unmodifiedList.add(1);  UnsupportedOperationException 발생

        /*
            선언형 프로그래밍이란?
            : 고전적인 객체지향 프로그래밍에서는 명령형(할당, 조건문, 분기문, 루프 등) 코드 위주로 코드를 작성하였다.
            아래의 예시와 같이 가장 비싼 트랜잭션을 구하는 기능을 구현이 있다.
         */
        List<Transaction> transactions = List.of();
        Transaction mostExpensive = transactions.get(0);
        if (mostExpensive == null) {
            throw new IllegalStateException("Transaction Empty");
        }

        for (Transaction t : transactions.subList(1, transactions.size())) {
            if (t.getValue() > mostExpensive.getValue())
                mostExpensive = t;
        }

        /* 위 코드를 함수형으로 작성 시 간결하게 표현할 수 있다 */
        Transaction mostExpensive2 = transactions.stream()
                .max(comparingInt(Transaction::getValue))
                .orElseThrow(() -> new IllegalStateException("No Transaction Exception"));

        /*
            선언형 프로그래밍의 장점은 달성하고자 하는 목표를 명확하게 표현할 수 있다는 장점이다.
            유지보수를 하는 개발자 입장에서는 해당 코드가 어떠한 목표로 구현되어 있는지 확인하기 쉽다.
            함수형 프로그래밍은 이러한 선언형 프로그래밍을 따르면서 앞서 설명한 2가지 부작용을 최소화 하여 프로그램 유지보수성을 향상 시킨다.
            자바 8에서는 함수형 프로그래밍을 위한 다양한 기능들을 지원한다.
         */
    }
}
