package com.liam.demo.mapper;

import com.liam.demo.model.pojo.OrderCustom;
import com.liam.demo.model.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public interface OrderMapper {

    /**
     * 查询订单信息，包含订单的用户信息
     *
     * @return 订单用户信息
     */
    public List<OrderCustom> selectOrderUser() throws Exception;

    /**
     * 查询订单及详情
     *
     * @return 订单详情，包含用户信息和商品列表信息
     * @throws Exception
     */
    public List<OrderCustom> selectOrderUserDetail() throws Exception;

    /**
     * 查询订单信息, 延迟加载用户信息
     *
     * @return 订单信息，延迟加载用户新
     * @throws Exception
     */
    public List<OrderCustom> selectOrderUserLazy() throws Exception;

    public static void main(String args[]) throws Exception{
        InputStream resource = Resources.getResourceAsStream("mybatis/mybatisConfig.xml");
        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<OrderCustom> orderCustoms = orderMapper.selectOrderUserLazy();
        for (OrderCustom orderCustom : orderCustoms) {
            User user = orderCustom.getUser();
            System.out.println(user);
        }
    }

}
