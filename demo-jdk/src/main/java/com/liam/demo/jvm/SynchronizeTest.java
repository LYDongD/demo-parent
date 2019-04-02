package com.liam.demo.jvm;


public class SynchronizeTest {
    
    static Lock lock = new Lock();
    static int count = 0;

    public static void foo() {
	synchronized(lock) {
	    count++;
	}
    }

    //仅一个线程执行同步代码，查看偏向锁使用情况
    public static void main(String[] args) throws InterruptedException{
	lock.hashCode();	
	System.identityHashCode(lock);
	for (int i = 0; i < 1000000; i++) {
	    foo();
	}
    }

    static class Lock {
	@Override
	public int hashCode() {
	    return 0;
	}
    }
	
}
