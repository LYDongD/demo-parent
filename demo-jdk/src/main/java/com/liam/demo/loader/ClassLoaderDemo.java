package com.liam.demo.loader;

public class ClassLoaderDemo {

    public static void main(String args[]) {
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println();
    }
}
