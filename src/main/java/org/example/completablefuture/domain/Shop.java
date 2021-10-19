package org.example.completablefuture.domain;

import java.util.Random;
import java.util.concurrent.*;

public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice(String product) {
        // 동기 식 방식
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        // completablefuture을 활용하여 비동기 구현
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        Thread thread = new Thread(() -> {
            try {
                futurePrice.complete(calculatePrice(product));
            } catch (Exception e) {
                // 예외 발생 시 작업 종료
                futurePrice.completeExceptionally(e);
            }
        });
        thread.start();
        return futurePrice;
    }

    //supplyAsync 활용
    public Future<Double> getPriceSupplyAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product .charAt(0) + product .charAt(1);
    }

    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
