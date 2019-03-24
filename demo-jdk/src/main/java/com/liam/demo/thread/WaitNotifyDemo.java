package com.liam.demo.thread;


/**
 * object.wait(), object.notify机制
 * 不满足保护条件时，让对象的线程等待，通知对象的等待线程
 *
 * 1 wait和notify必须是同一个对象
 * 2 wait和notify必须在临界区内操作，以保证数据一致
 * 3 wait必须放在while循环块内，并判断保护条件，以免信号丢失或欺骗性唤醒
 * 4 notofyAll 可以解决信号丢失问题，但是可能会导致过早唤醒
 */
public class WaitNotifyDemo{

    //共享变量
    public static boolean runTask;

    public static class Task implements Runnable {
        @Override
        public void run() {

            synchronized (this) {
                while (!runTask) {
                    try {
                        this.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("子线程: " + Thread.currentThread() + " 正在运行");
            }
        }
    }


    public static void main(String[] args) throws Exception{

        Task task = new Task();
        Thread thread1 = new Thread(task);
        thread1.start();

        Thread thread2 = new Thread(task);
        thread2.start();

        Thread.sleep(5000);

        synchronized (task) {
            System.out.println("主线程运行");
            WaitNotifyDemo.runTask = true;
            task.notifyAll();
        }
    }
}
