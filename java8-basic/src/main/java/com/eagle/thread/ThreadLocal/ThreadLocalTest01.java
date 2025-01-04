package com.eagle.thread.ThreadLocal;

import java.util.Random;

public class ThreadLocalTest01 {

    private static ThreadLocal threadLocal = new ThreadLocal();

    static class A{
        public  void get(){
            int data = (int)threadLocal.get();
            System.out.println("A线程 :  "+Thread.currentThread().getName()+"   data:  "+data);
        }
    }

    static class B{
        public void get(){
            int data = (int)threadLocal.get();
            System.out.println("B线程 :  "+Thread.currentThread().getName()+"   data:  "+data);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i ++) {
            new Thread(() -> {
                int data = new Random().nextInt();
                threadLocal.set(data);
                System.out.println(Thread.currentThread().getName()+"--data:  "+data);

                // 静态类中的非静态方法调用
                new A().get();
                new B().get();
            }).start();
        }
    }
}

//执行结果
//Thread-1--data:  -123969565
//Thread-0--data:  1219861176
//A线程 :  Thread-1   data:  -123969565
//A线程 :  Thread-0   data:  1219861176
//B线程 :  Thread-1   data:  -123969565
//B线程 :  Thread-0   data:  1219861176
