package com.liam.demo.thread;

import java.util.concurrent.CountDownLatch;

public class SynchronizeDemo {

    private int count = 0;

    private CountDownLatch threadCount;

    private final Object lock = new Object();

    private static int totalElapsedTime;

    public void testSynCountIncrement() throws Exception{
        long start = System.currentTimeMillis();

        //模拟10个线程，每个线程执行10000次，对共享变量count自增，期望最后count的值为100000
        threadCount = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int i = 0; i < 10000; i++) {
                        synchronized (lock) { //互斥锁，保证线程安全
                            count++;
                        }
                    }
                    threadCount.countDown();
                }
            });

            thread.start();
        }

        //等待所有线程执行完毕
        threadCount.await();

        //读取共享变量的值
        System.out.println("共享变量count: " + count);

        long end = System.currentTimeMillis();
        System.out.println("单次耗时: " + String.valueOf((end - start)) + "ms");

        totalElapsedTime += (end - start);
    }

    public static void main(String[] args) throws Exception{

        int times = 10;

        for (int i = 0; i < times; i++ ){
            SynchronizeDemo synchronizeDemo = new SynchronizeDemo();
            synchronizeDemo.testSynCountIncrement();
        }

        System.out.println("平均耗时: " + String.valueOf((totalElapsedTime / times)) + "ms");
    }

}
