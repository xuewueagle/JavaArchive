package com.eagle.thread;

public class ThreadSerialDemo2 {
    static ThreadSerialDemo2 serialDemoInstance;
    static Boolean isInit = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            isInit = false;
            serialDemoInstance = null;
            // 线程2
            Thread thread1 = new Thread(() -> {
                synchronized (isInit) {
                    serialDemoInstance = new ThreadSerialDemo2(); // 语句1
                    isInit = true; // 语句2
                }


            });

            // 线程2
            Thread thread2 = new Thread(() -> {
                synchronized (isInit) {
                    if (isInit) {
                        // 因为synchronized 保证了串行执行，指令不会重排序，这里不会再出现serialDemoInstance为null的情况
                        serialDemoInstance.dosomething();
                    }
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
