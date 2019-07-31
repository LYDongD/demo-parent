package com.liam.demo.demospringboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.liam.demo.demospringboot.service.UserService;
import org.springframework.stereotype.Component;


/**
 * dubbo提供接口
 * @author Liam
 * @date 2019/5/5 下午4:16
 */
@Component
@Service(interfaceClass = UserService.class, group="dubbo", version="1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "fuck " + name;
    }
}
