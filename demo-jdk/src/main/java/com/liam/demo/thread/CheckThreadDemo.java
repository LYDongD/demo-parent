package com.liam.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建10个线程并执行，通过top -Hp [pid] 和 jstack [pid] 查看线程资源消耗和线程执行栈
 * 线程执行栈：线程状态，线程栈执行代码行等
 *
 * pstree -p [pid] 进程和线程关系
 * top -Hp [pid] 线程资源使用
 * jstack [pid] 线程状态 -> thead dump
 *
 * "i am thread 8" #18 prio=5 os_prio=31 tid=0x00007fd95288b800 nid=0x5e03 runnable [0x0000700004f07000]
 *    java.lang.Thread.State: RUNNABLE
 * 	at com.liam.demo.thread.CheckThreadDemo$1.run(CheckThreadDemo.java:14)
 * 	at java.lang.Thread.run(Thread.java:745)
 *
 *
 * waiting to lock xxx  等待锁，阻塞在某个锁
 * locked xxx 获得锁xx
 * waiting on xxx 等待在某个对象上， 通常是调用了Object.wait()
 *
 */
public class CheckThreadDemo {

    public static void main(String args[]) {

        final ReentrantLock lock = new ReentrantLock();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            Thread thread = new Thread(() -> {
                if (index % 2 == 0 ) {
                    synchronized (lock) {
                        try {
                            lock.wait();
                        }catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                }else {
                    while (true){}
                }

            });

            thread.setName("i am thread " + i);

            thread.start();
        }
    }
}
