package org.example.completablefuture;

import org.example.completablefuture.domain.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * 모든 상점의 product 가격을 검색하는 기능을 구현한다
 * 일반 stream() : 약 4 sec
 * 병렬 stream() : 약 1 sec
 * async 방식    : 약 1 sec (병렬보다 느림)
 */
public class Main03 {
    static List<Shop> shops = List.of(
            new Shop("BestPrice"), new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"), new Shop("BuyltAll"));

    private static final Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            });

    public static void main(String[] args) {
        long start = System. nanoTime();
        System.out.println(findPrices("doll"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(cores);
    }

    // 각 상점 별 가격 분석
    // String.format("%s : %.2f", shop.getName(), shop.getPrice(product))
    static List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " : "
                                                                        + shop.getPrice(product), executor))
                        .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)   //join() 모든 비동기 작업이 끝날 때까지 대기
                .collect(Collectors.toList());
    }
}
