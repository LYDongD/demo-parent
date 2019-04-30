package com.liam.demo.ioc.bean;

/**
 * 相机，实现USB接口
 * @author Liam
 * @date 2019/4/30 下午3:55
 */
public class Camera implements IUSB{

    @Override
    public void connect() {
        System.out.println("=======相机连接成功======");
    }
}
