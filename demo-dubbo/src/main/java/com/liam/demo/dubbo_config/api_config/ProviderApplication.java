package com.liam.demo.dubbo_config.api_config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.liam.demo.dubbo_config.service.DemoService;

public class ProviderApplication {

    public static void main(String args[]) throws Exception{

        //api方式添加配置，并暴露服务
        ServiceConfig<DemoServiceImpl> serviceConfig = new ServiceConfig();
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-demo-provider");
        serviceConfig.setApplication(applicationConfig);
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setVersion("2.1.1");
        serviceConfig.setProvider(providerConfig);

//        serviceConfig.setVersion("2.1.1");
        serviceConfig.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(new DemoServiceImpl());
        serviceConfig.setGeneric("true");
        serviceConfig.export();
        System.in.read();
    }
}
