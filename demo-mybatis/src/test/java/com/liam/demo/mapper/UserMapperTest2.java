package com.liam.demo.mapper;

import com.liam.demo.model.pojo.User;
import com.liam.demo.model.pojo.UserCustom;
import com.liam.demo.model.pojo.UserQueryVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 使用spring-mybatis整合方式
 */
public class UserMapperTest2 {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception{
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-application.xml");
    }


    @Test
    public void findUserById() throws Exception{

        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findUserById(2);
        System.out.println(user);
    }


}