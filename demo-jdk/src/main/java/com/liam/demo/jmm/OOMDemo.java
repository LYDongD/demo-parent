package com.liam.demo.jmm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class OOMDemo {

    private static int index = 0;

    //无限递归造成栈溢出
    private void stackOverFlow() {
        index++;
        stackOverFlow();
    }

    //无限创建字节数组，造成堆溢出，设置堆的最大值为16M，-Xmx16m
    private void heapOverFlow() {
        List<byte[]> list = new ArrayList<>();
        boolean exit = false;
        while(!exit) {
            try {
                index++;
                list.add(new byte[1024 * 1024]); //每次创建1m，预计14次将oom
            }catch (Throwable e) {
                System.out.println("执行次数：" + index);
                exit = true;
                e.printStackTrace();
            }
        }
    }

    //无限记载类，造成方法区(元数据区溢出), -XXMaxMetaspaceSize=3m
    private void metaspaceOverFlow() {
        List<ClassLoader> classLoaderList = new ArrayList<>();
        try {
            URL url = new File("/Users/lee/demo-parent/demo-jdk/src/main/java/com/liam/demo").toURI().toURL();
            URL[] urls = {url};
            ClassLoader classLoader = new URLClassLoader(urls);
            while (true) {
                classLoader.loadClass("com.liam.demo.App");
            }

        }catch (Throwable e) {
            e.printStackTrace();
        }


    }

    public static void main(String args[]) {
//        try {
//            new OOMDemo().stackOverFlow();
//        }catch (Throwable e) {
//            System.out.println("当前栈深度: " + index);
//            e.printStackTrace();
//        }

//        new OOMDemo().heapOverFlow();

        new OOMDemo().metaspaceOverFlow();
    }


}
