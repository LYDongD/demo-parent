package com.liam.demo.dubbo_config.api_config;

public class DemoServiceImpl implements DemoService{
    @Override
    public String sayHello(String name) {
        System.out.println("hello world");
        return name;
    }
}
