package com.eagle.thread;

public class ThreadVisibilityDemo3 {
    static volatile Boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (flag) { // 主线程将flag设置为false, 子线程这里可以感知到，因为volatile实现多线程中共享变量可见性

            }
        }).start();

        Thread.sleep(2000);
        // 主线程修改flag
        flag=false;
    }
}
