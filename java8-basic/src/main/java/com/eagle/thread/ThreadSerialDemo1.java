package com.eagle.thread;

public class ThreadSerialDemo1 {
    static ThreadSerialDemo1 serialDemoInstance;
    static Boolean isInit = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            isInit = false;
            serialDemoInstance = null;
            // 线程2
            Thread thread1 = new Thread(() -> {
                serialDemoInstance = new ThreadSerialDemo1(); // 语句1
                isInit = true; // 语句2


            });

            // 线程2
            Thread thread2 = new Thread(() -> {
                if (isInit) {
                    // 因为指令重排序，这里可能会出现serialDemoInstance为null的情况
                    serialDemoInstance.dosomething();
                }
            });

            thread1.start();
            thread2.start();

            // 保证当前for循环执行完毕
            thread1.join();
            thread2.join();
        }
    }

    void dosomething(){
        System.out.println("doing...");
    }
}
