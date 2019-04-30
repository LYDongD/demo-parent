package com.liam.demo.ioc.bean;

/**
 * 电脑主机，依赖USB接口实现数据传输
 * @author Liam
 * @date 2019/4/30 下午3:54
 */
public class Computer {

    private IUSB usb;

    public void setUsb(IUSB usb) {
        this.usb = usb;
    }

    public void run() {
        System.out.println("========启动电脑=======");
        if (usb == null) {
            System.out.println("=====请插入USB设备=====");
        }else {
            usb.connect();
        }
        System.out.println("======数据传输完成=======");
    }
}
