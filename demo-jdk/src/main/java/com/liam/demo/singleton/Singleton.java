package com.liam.demo.singleton;


/**
 * 1 仅创建一次
 * 2 线程安全
 * 3 全局唯一
 */
public class Singleton {

//    private static final Singleton singleton = new Singleton();


    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }

    private Singleton(){};

    //把实例初始化放在静态内部类中，由于静态内部类只会初始化一次，所以满足1，3，且可以保证线程安全
    private static class SingletonHolder {
        private static final Singleton instance = new Singleton();
    }


}
