package com.liam.demo.thread;

public class ThreadCount {

    public static void main(String args[]) {
        System.out.println("hello world");

        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        while (group != null) {
            topGroup = group;
            group = topGroup.getParent();
        }

        int threadCount = topGroup.activeCount();
        Thread[] threads = new Thread[threadCount];
        topGroup.enumerate(threads);
        for (int i = 0; i < threadCount; i++) {
            System.out.println(threads[i].getName());
        }
    }
}
