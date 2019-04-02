package com.liam.demo.thread;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

    private AtomicInteger count = new AtomicInteger(0);

    private CountDownLatch threadCount;

    private static int totalElapsedTime;

    private void testAtomicCountIncrement() throws Exception{

        long start = System.currentTimeMillis();

        //模拟10个线程，每个线程执行10000次，对共享变量count自增，期望最后count的值为100000
        threadCount = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int i = 0; i < 10000; i++) {
                        count.incrementAndGet();
                    }
                    threadCount.countDown();
                }
            });

            thread.start();
        }

        //等待所有线程执行完毕
        threadCount.await();

        //读取共享变量的值
        System.out.println("共享变量count: " + count.get());

        long end = System.currentTimeMillis();
        totalElapsedTime += (end - start);
        System.out.println("单次耗时: " + String.valueOf((end - start)) + "ms");
    }

    public static void main(String[] args) throws Exception{

        int times = 10;

        for (int i = 0; i < times; i++ ){
            AtomicDemo atomicDemo = new AtomicDemo();
            atomicDemo.testAtomicCountIncrement();
        }

        System.out.println("平均耗时: " + String.valueOf((totalElapsedTime / times)) + "ms");
    }

}
