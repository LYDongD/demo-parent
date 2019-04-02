package com.liam.demo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

class LockDemo {
    private final Lock r1 = new ReentrantLock();
    private int value;

    public void addOne() {
	r1.lock();
	try {
	    System.out.println("value: " + value);
	    value += 1;
	}finally {
	    r1.unlock();
	}
    }

    public static void main(String[] args) throws Exception {
	
	final LockDemo demo = new LockDemo();
	CountDownLatch countDown = new CountDownLatch(10);
	for(int i = 0; i < 10; i++) {
	    Thread thread = new Thread(new Runnable(){
		@Override
		public void run() {
		    demo.addOne();
		    countDown.countDown();
		}
	    });

	    thread.start();
	}

	countDown.await();
	
    }
}
