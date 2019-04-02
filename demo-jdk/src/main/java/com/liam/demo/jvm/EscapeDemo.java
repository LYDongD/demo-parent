package com.liam.demo.jvm;

/**
 *  逃逸分析优化demo
 */
public class EscapeDemo {

    //数组对象未逃逸, 如果开启逃逸分析，对象将在栈中分配，方法结束后被回收
    //-XX:-DoEscapeAnalysis关闭逃逸分析，默认开启
    //逃逸分析基于标量替换，
    public static void alloc() {
        byte[] bytes = new byte[2];
        synchronized (bytes) {
            bytes[0] = 1;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            alloc();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时: " + String.valueOf(end - start) + "ms");
    }
}
