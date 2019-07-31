package com.liam.demo.gc;

/**
 * safe-point对gc pause time的影响
 * 测试参数： -XX:+PrintGC -XX:+PrintGCApplicationStoppedTime -XX:+PrintSafepointStatistics
 * @author Liam(003046)
 * @date 2019/7/31 上午10:05
 */
public class SafePointTest {

    private static double sum = 0;

    /**
     *  单独跑该类也会发生stop-the-world
     */
    public static void foo() {
        for (int i = 0; i < 0x77777777; i++) {
            sum += Math.sqrt(i);
        }
    }

    /**
     *  因内存不足发生GC，导致stop-the-world
     */
    public static void bar() {
        for (int i = 0; i < 50000000; i++) {
            new Object().hashCode();
        }
    }

    /**
     *  1 单独跑foo, bar
     *  2 同时跑foo, bar
     *  观察应用线程暂停的时间
     */
    public static void main(String[] args) {
        new Thread(SafePointTest::foo).start();
        new Thread(SafePointTest::bar).start();
    }
}
