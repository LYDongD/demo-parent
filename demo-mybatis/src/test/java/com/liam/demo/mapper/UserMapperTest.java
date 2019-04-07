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

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserMapperTest {

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

        SqlSession sqlSession = sqlSessionFactory.openSession();
        //自动生成mapper动态代理对象，实现了接口
        //根据返回类型内部调用selectOne或selectList
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(2);
        System.out.println(user);
    }

    @Test
    public void findUserList() throws Exception{

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVO userQueryVO = new UserQueryVO();
        UserCustom userCustom = new UserCustom();
        userCustom.setSex(0);
        userQueryVO.setUserCustom(userCustom);
        userQueryVO.setIds(Arrays.asList(1,2,3));
        List<UserCustom> userList = userMapper.findUserList(userQueryVO);
        System.out.println(userList);
    }

    @Test
    public void findUserCount() throws Exception{

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVO userQueryVO = new UserQueryVO();
        UserCustom userCustom = new UserCustom();
        userCustom.setSex(0);
        userCustom.setUsername("am");
        userQueryVO.setUserCustom(userCustom);
        Integer count = userMapper.findUserCount(userQueryVO);
        System.out.println(count);
    }

    @Test
    public void findUserByIdResultMap() throws Exception{

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserByIdResultMap(3);
        System.out.println(user);
    }

}