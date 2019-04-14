package com.liam.demo.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.InputStream;

public class BaseTest {

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws Exception{
        //创建SqlSessionFactory单例
        //加载mybatis全局配置
        InputStream resource = Resources.getResourceAsStream("mybatisConfig.xml");
        //创建会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
    }
}
