package com.liam.demo;

/**
 * 线程局部变量，只能被当前线程访问
 */
public class ThreadLocalDemo {

    public void testThreadLocal(){
        Thread thread = new Thread(){
            ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> Thread.currentThread().getName());

            @Override
            public void run(){
                super.run();
                stringThreadLocal.set("test string");
                String var = stringThreadLocal.get();
                System.out.println(var);
            }
        };

        thread.start();
    }

    public static void main(String args[]) {
        new ThreadLocalDemo().testThreadLocal();
        System.out.println("hah");
    }

}
