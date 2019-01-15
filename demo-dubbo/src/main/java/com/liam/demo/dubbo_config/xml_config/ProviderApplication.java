package com.liam.demo.dubbo_config.xml_config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.liam.demo.dubbo_config.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderApplication {

    public static void main(String args[]) throws Exception{
        //读取classpath下的配置文件，xml格式，并创建容器上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/dubbo-provider.xml"});
        //lifeCycle方法，用于传递一个容器启动信号,发布ioc容器启动事件：context.start()
        context.start();
        //阻塞方法，从stdin中读取字节
        System.in.read(); // press any key to exit
    }
}
