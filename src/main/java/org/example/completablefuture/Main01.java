package org.example.completablefuture;

import java.util.concurrent.*;

/**
 *
 */
public class Main01 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(() -> {
            return doSomethingWithFuture(100);
        });

        doSomethingElseWhileTasking();

        try {
            // TimeUnit.SECONDS 기준으로 1초
            System.out.printf("Future result : %d\n", future.get(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static int doSomethingWithFuture(int value) {
        int result = 100 + value;
        System.out.printf("result : %d\n", result);
        return result;
    }

    public static void doSomethingElseWhileTasking() {
        System.out.println("do Something else");
    }
}
