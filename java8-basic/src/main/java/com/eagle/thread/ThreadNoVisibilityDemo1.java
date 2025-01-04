package com.eagle.thread;

public class ThreadNoVisibilityDemo1 {
    static Boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (flag) { // 这里会一直循环， 主线程将flag设置为false, 子线程这里感知不到

            }
        }).start();

        Thread.sleep(2000);
        // 主线程修改flag
        flag=false;
    }
}
