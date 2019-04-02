package com.liam.demo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;
import java.lang.Thread;

class Account {
    private int balance;
    private final Lock lock = new ReentrantLock();

    public void setBalance(int balance) {
	this.balance = balance;
    }

    public int getBalance() {
	return this.balance;
    }

    void transfer(Account tar, int amt) throws Exception{
	boolean flag = false;
	while(true) {
	     if (this.lock.tryLock()) {
		try {
		    if (tar.lock.tryLock()) {
			try {
			    this.balance -= amt;
			    tar.balance += amt;
			    //Thread.currentThread().sleep(2000);
			}finally {
			    flag = true;
			    tar.lock.unlock();
			}
		    }else {
			System.out.println("account get lock fail inside");
		    }
		}finally{
		    this.lock.unlock();
		    if (flag) {
			return;
		    }
		}
	    }else {
		System.out.println("account get lock fail outside: " + Thread.currentThread().getId());
	    }
	}

    }

    public static void main(String[] args) throws Exception {
	Account A = new Account();
	A.setBalance(100);
	Account B = new Account();
	B.setBalance(100);

	CountDownLatch contDown = new CountDownLatch(10);

	for (int i = 0; i < 10; i++) {
	    final int index = i;
	    Thread thread = new Thread(new Runnable(){

		@Override
		public void run() {

		try{
		    if (index % 2 == 0) {
			A.transfer(B, 50);
		    }else {
			B.transfer(A, 50);
		    }
		     contDown.countDown();
		  }catch(Exception e) {
		    e.printStackTrace();
		  }
		}
	    });

	    thread.start();
	}

	contDown.await();
	System.out.println("A: " + A.getBalance());
	System.out.println("B: " + B.getBalance());

    }
}
