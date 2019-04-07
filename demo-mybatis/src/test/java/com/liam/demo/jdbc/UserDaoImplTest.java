package com.liam.demo.jdbc;

import com.liam.demo.model.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception{
        //创建SqlSessionFactory单例
        //加载mybatis全局配置
        InputStream resource = Resources.getResourceAsStream("mybatisConfig.xml");
        //创建会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
    }

    @Test
    public void findUserById() throws Exception{
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.findUserById(1);
        System.out.println(user);
    }
}