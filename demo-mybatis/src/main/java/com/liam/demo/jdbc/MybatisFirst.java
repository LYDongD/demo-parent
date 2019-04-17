package com.liam.demo.jdbc;


import com.liam.demo.model.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;


public class MybatisFirst {

    //根据id主键查询用户信息
    public void findUserById() throws Exception {

        //加载mybatis全局配置
        InputStream resource = Resources.getResourceAsStream("mybatis/mybatisConfig.xml");

        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //第一个参数是statement的id, 即mapper.xml中的namespace.id
            User user = sqlSession.selectOne("test.findUserById", 2);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }

    public void findUserByUserName() throws Exception {

        //加载mybatis全局配置
        InputStream resource = Resources.getResourceAsStream("mybatis/mybatisConfig.xml");

        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //第一个参数是statement的id, 即mapper.xml中的namespace.id
            //由于返回的是一个列表，使用selectList api
            List<User> userList = sqlSession.selectList("test.findUserByName", "liam");
            System.out.println(userList);
        } finally {
            sqlSession.close();
        }
    }

    public void addUser() throws Exception {

        //加载mybatis全局配置
        InputStream resource = Resources.getResourceAsStream("mybatis/mybatisConfig.xml");

        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            User user = new User();
            user.setUsername("haha");
            user.setSex(0);
            sqlSession.insert("test.addUserByUUID", user);
            System.out.println(user.getId());
            //提及事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteUser() throws Exception {

        //加载mybatis全局配置
        InputStream resource = Resources.getResourceAsStream("mybatis/mybatisConfig.xml");

        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            sqlSession.delete("test.deleteUser", 8);
            //提及事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public void updateUser() throws Exception {

        //加载mybatis全局配置
        InputStream resource = Resources.getResourceAsStream("mybatis/mybatisConfig.xml");

        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {

            User user = new User();
            user.setId(4);
            user.setUsername("xiaofu");
            user.setSex(0);
            sqlSession.update("test.updateUser", user);
            //提及事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public static void main(String[] args) throws Exception {
        MybatisFirst mybatisFirst = new MybatisFirst();
        mybatisFirst.deleteUser();
        mybatisFirst.updateUser();
    }
}
