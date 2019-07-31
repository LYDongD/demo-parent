package com.liam.demo.demospringboot.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.liam.demo.demospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liam
 * @date 2019/4/25 下午8:23
 */
@RestController
public class HelloController {

    @Reference(group = "dubbo", interfaceClass=UserService.class, version="1.0.0")
    private UserService userService;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Object hello(String name) {
        return userService.sayHello(name);
    }
}
