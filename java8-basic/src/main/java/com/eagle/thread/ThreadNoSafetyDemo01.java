package com.eagle.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadNoSafetyDemo01 {

    public static Integer stock = 1000000;

    static class StockRunnable implements Runnable {
        @Override
        public void run() {
            if (stock > 0) {
                // 多线程下 库存减1 不安全
                stock--; // 等价于 stock = stock - 1， 属于多步操作，非原子性操作
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        StockRunnable task = new StockRunnable();

        for (int i = 0; i < 1000000; i++) {
            threadPool.execute(task);
        }
        threadPool.shutdown();
        threadPool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("剩余库存：" + stock);
    }
}
// 执行结果
// 剩余库存：18427