package com.liam.demo.ioc.bean;

/**
 * 收集，实现USB接口
 * @author Liam
 * @date 2019/4/30 下午3:55
 */
public class Phone implements IUSB{

    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public void connect() {
        System.out.println("======手机: " + brand + " 连接成功=====");
    }
}
