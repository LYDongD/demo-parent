package com.liam.demo.mapper;

import com.liam.demo.model.pojo.OrderCustom;
import com.liam.demo.model.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class OrderMapperTest extends BaseTest{

    @Test
    public void selectOrderUser() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<OrderCustom> orderCustoms = orderMapper.selectOrderUser();
        System.out.println(orderCustoms);
    }

    @Test
    public void selectOrderUserDetail() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<OrderCustom> orderCustoms = orderMapper.selectOrderUserDetail();
        System.out.println(orderCustoms);
    }

    @Test
    public void selectOrderUserLazy() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        //延迟加载
        List<OrderCustom> orderCustoms = orderMapper.selectOrderUserLazy();
        for (OrderCustom orderCustom : orderCustoms) {
            User user = orderCustom.getUser();
            System.out.println(user);
        }
    }
}


