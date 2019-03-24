package com.liam.demo.thread;

/**
 *  Thread.join()的底层是Object的wait/notify操作
 */
public class ThreadJoinDemo {

    public static void main(String[] args) throws Exception{


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("tick " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
        thread.join();
        System.out.println("子线程运行结束");
    }
}
