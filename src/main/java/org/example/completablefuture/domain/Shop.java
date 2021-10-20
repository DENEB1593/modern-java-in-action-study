package org.example.completablefuture.domain;

import java.util.Random;
import java.util.concurrent.*;

public class Shop {
    private static final Random random = new Random();
    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calculatePrice(String product) {
        randomDelay();
        return random.nextDouble() * product .charAt(0) + product .charAt(1);
    }

    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
