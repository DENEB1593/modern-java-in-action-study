package org.example.completablefuture.domain;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Discount {
    private static final Random random = new Random();

    public enum Code {
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    private static double apply(double price, Code code) { //기존가격에 할인가격 적용
        randomDelay(); //서비스 응답지연 흉내
        return (price * (100 - code.percentage) / 100);
    }

    private static void delay() {
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
