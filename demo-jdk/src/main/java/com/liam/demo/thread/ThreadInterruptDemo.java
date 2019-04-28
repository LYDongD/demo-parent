package com.liam.demo.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * 线程中断：优雅的停止线程
 * A 线程while循环，B 线程去终止A线程
 *
 * thread.interrupt() 中断一个线程，设置线程的中断标志位为true
 *
 * 一般而言，如果希望线程能够响应中断：
 *
 * 1 在执行核心逻辑前，先判断线程是否被中断，是则退出
 * 2 如果线程可等待，则捕获线程中断异常，必要时需要重置线程中断状态
 * 3 多数情况1和2配合使用
 *
 * 线程感知中断的两种方式
 *
 * 1 waiting的线程或阻塞在io的runnable线程：抛出InterruptedException异常
 * 2 正常runnable的线程： 通过主动调用thread.isInterrupted()探测
 *
 */
public class ThreadInterruptDemo {

    public static void main(String args[]) throws Exception{

        List<Thread> threadList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {

                while (true) {
                    //if thread is interrupted, end the logic
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!!");
                        System.out.println(Thread.currentThread().isInterrupted());
                        break;
                    }

                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException ie) {
                        ie.printStackTrace();
                        Thread.currentThread().interrupt();
                    }

                    //some logic


                    System.out.println("hello world");
                }
            });

            thread.start();
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.interrupt();
        }
    }
}
