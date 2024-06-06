package ru.otus.pro.kovaleva;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(25);
        AtomicInteger value = new AtomicInteger();
        for (int i = 0; i < 1000; i++) {
            int task = i;
            pool.execute(() -> System.out.println(Thread.currentThread().getName() + " processed the task " + task
                    + " with the result " + value.addAndGet(1)));
        }
        pool.shutdown();
    }
}
