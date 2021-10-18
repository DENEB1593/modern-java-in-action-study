package org.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

/**
 * 모던자바인액션 177page의 퀴즈 풀이
 */
public class Quiz {
    public static void main(String[] args) {
        List<Transaction> transactions = getTranscations();

        // 1) 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순하시오
        List<Transaction> allTranscationIn2011 = transactions.stream()
                                        .filter(t -> t.getYear() == 2011)
                                        .sorted(comparing(Transaction::getValue))
                                        .collect(toList());

        // 2) 거래자가 근무하는 모든 도시를 중복없이 나열하시오
        List<String> traderCities = transactions.stream()
                                        .map(t -> t.getTrader().getCity())
                                        .distinct()
                                        .collect(toList());
                                        // or toSet 활용 가능

        // 3) 케임브릿지에서 근무하는 모든 거래자를 찾아서 이름순으로 나열하시오
        List<Trader> cambridgeTraders = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(t -> "Cambridge".equals(t.getCity()))
                .sorted(comparing(Trader::getName))
                .collect(toList());
        // distinct의 위치에 따라 연산의 수자 감소할 수 있으니 참고

        // 4) 모든 거래자의 이름을 알파벳순으로 정렬하시오
        List<String> traderNames = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getName)
                .sorted()
                .collect(toList());
        // flatMap으로 변환해보기
        List<String> traderNames2 = transactions.stream()
                .flatMap(transaction -> Stream.of(transaction.getTrader().getName()))
                .distinct().sorted()
                .collect(toList());

        // 5) 밀라노에 거래자가 있는가?
        boolean isTradersInMilan = transactions.stream().anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        // 5-1) 서울에 거래자가 있는가?
        boolean isTradersNotInSeoul = transactions.stream().noneMatch(t -> "Seoul".equals(t.getTrader().getCity()));

        // 6) 케임브릿지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오
        List<Transaction> cambridgeTradersTranscations = transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .collect(toList());

        // 7) 전체 트랜잭션 중 최대값을 얼마인가?
        int maxValueInTranscations = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .orElseThrow(NullPointerException::new);

        // 8) 전체 트랜잭션 중 최소값을 얼마인가?
        int minValueInTranscations = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .orElseThrow(NullPointerException::new);
    }

    static List<Transaction> getTranscations() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        return transactions;
    }
}
