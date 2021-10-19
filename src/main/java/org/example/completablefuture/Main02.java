package org.example.completablefuture;

import org.example.completablefuture.domain.Shop;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 비동기 API 샘플 구현
 * domain - Shop
 */
public class Main02 {
    public static void main(String[] args) {
        Shop shop = new Shop("Best");
        // getPrice()를 이용하여 가격을 검색하는 경우, 검색하는 동안 1초동안 블록이 발생한다.
        // double price = shop.getPrice("doll");

        // 비동기식을 활용하여 검색한다.
        Future<Double> futurePrice = shop.getPriceAsync("doll");
        Future<Double> futurePrice2 = shop.getPriceAsync("table");

        // 계산하는 동안 다른 작업을 진행
        doSomethingElse();
        try {
            System.out.println(futurePrice.get());
            System.out.println(futurePrice2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void doSomethingElse() {
        System.out.println("do Something else");
    }
}
