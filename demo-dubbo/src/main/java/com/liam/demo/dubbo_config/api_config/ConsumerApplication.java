package com.liam.demo.dubbo_config.api_config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.liam.demo.dubbo_config.service.DemoService;

public class ConsumerApplication {

    public static void main(String args[]) {
        //通过api方式添加配置，并获取一个服务引用
        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("dubbo-demo-cosumer"));
        referenceConfig.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setGeneric(true);

        //泛化调用，替代所有接口，在未引入接口的情况下进行调用
        GenericService demoService = (GenericService) referenceConfig.get();
        String name = (String) demoService.$invoke("sayHello", new String[]{"".getClass().getName()}, new Object[]{"pony"});

//        DemoService demoService = referenceConfig.get();
        referenceConfig.setVersion("2.1.1");
        DemoService demoService = referenceConfig.get();
        //rpc调用
//        String name = demoService.sayHello("liam");
        System.out.println("rpc call success: " + name);

    }
}
