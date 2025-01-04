package com.eagle.thread;

public class ThreadVisibilityDemo1 {
    static Boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (flag) { // 主线程将flag设置为false, 子线程这里可以感知到，因为synchronized在解锁后 会将线程中的共享变量副本 同步到主内存的共享变量中
                synchronized (flag) {

                }
                // synchronized 完成解锁
            }
        }).start();

        Thread.sleep(2000);
        // 主线程修改flag
        flag=false;
    }
}
