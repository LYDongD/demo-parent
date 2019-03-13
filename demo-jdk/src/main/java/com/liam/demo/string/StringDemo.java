package com.liam.demo.string;

public class StringDemo {
//
    public static void strTest01(){
        String s = "11";
        String s2 = new String("11");
        System.out.println(s == s2);
    }

    public static void strTest02(){
        String s = "11";
        String s2 = "1" + "1";
        System.out.println(s == s2);
    }

    public static void main(String args[]) {
        strTest02();
    }
}
