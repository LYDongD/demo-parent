package com.liam.demo.mapper;

import com.liam.demo.model.pojo.Item;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ItemMapperTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-application.xml");
    }

    @Test
    public void selectByPrimaryKey() {
        ItemMapper itemMapper = (ItemMapper) applicationContext.getBean("itemMapper");
        Item item = itemMapper.selectByPrimaryKey(1);
        System.out.println(item.toString());
    }
}