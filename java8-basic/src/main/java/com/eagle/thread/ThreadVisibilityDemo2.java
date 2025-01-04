package com.eagle.thread;

public class ThreadVisibilityDemo2 {
    static Boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (flag) { // 主线程将flag设置为false, 子线程这里可以感知到，因为println底层也使用synchronized了
               System.out.println("执行完，会同步从主内存中刷新flag的值");
            }
        }).start();

        Thread.sleep(2000);
        // 主线程修改flag
        flag=false;
    }
}
