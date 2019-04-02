package com.liam.demo;

import com.liam.demo.beans.MyTestBean;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.assertEquals;

public class BeanFactoryTest {

    @Test
    public void testBeanLoad() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring/spring-application.xml"));
        MyTestBean myTestBean = (MyTestBean) beanFactory.getBean("myTestBean");
        assertEquals("testStr", myTestBean.getTestStr());
    }

    @Test
    public void testDefaultListBeanFactory() {
        ClassPathResource resource = new ClassPathResource("spring/spring-application.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
    }
}
