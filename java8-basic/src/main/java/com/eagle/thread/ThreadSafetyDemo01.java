package com.eagle.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSafetyDemo01 {

    public static Integer stock = 1000000;

    static class StockRunnable implements Runnable {
        @Override
        public synchronized void run() {
            if (stock > 0) {
                // 使用synchronized 同步锁保证了原子性操作，实现了线程安全
                stock--;
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
// 剩余库存：0