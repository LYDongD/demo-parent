package com.liam.demo.dubbo_config.api_config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

public class ProviderApplication {

    public static void main(String args[]) throws Exception{

        //api方式添加配置，并暴露服务
        ServiceConfig<DemoServiceImpl> serviceConfig = new ServiceConfig();
        serviceConfig.setApplication(new ApplicationConfig("dubbo-demo-provider"));
        serviceConfig.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(new DemoServiceImpl());
        serviceConfig.export();
        System.in.read();
    }
}
