package com.liam.demo.ioc.container;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IOC容器，负责解析xml, 加载bean以及实现bean依赖注入
 * 1 构造容器时完成bean加载和依赖注入
 * 2 缓存bean实例，可通过bean id 获取bean实例
 *
 * @author Liam
 * @date 2019/4/30 下午3:51
 */
public class ApplicationContext {

    private String filePath;

    private Map<String, ClassBean> classBeanMap = new HashMap<>();

    private Map<String, Object> beanMap = new HashMap<>();

    public ApplicationContext(String filePath) {
        this.filePath = filePath;

        //整个过程基于classBeanMap
        try {
            //1 解析xml,写入classBeanMap
            parseXml();

            //2 遍历classBeanMap，并初始化bean, 写入beanMap
            instanceBean();

            //3 遍历classBeanMap，取出property, 依赖注入beanMap
            beanToProperty();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String beanId) {
        return beanMap.get(beanId);
    }

    /**
     * 解析xml，加载bean到classBeanMap,这一步不需要实现注入
     */
    private void parseXml() {
        //文件IO，用普通io，面向流
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(filePath));

            //获取beans
            Element root = document.getRootElement();

            //解析<bean>
            List<Element> beanElementList = root.elements("bean");
            for (Element element : beanElementList) {
                ClassBean classBean = new ClassBean();
                String classBeanId = element.attributeValue("id");
                classBean.setId(classBeanId);
                classBean.setClassName(element.attributeValue("class"));

                Map<String, PropertyBean> propertyBeanMap = new HashMap<>();
                classBean.setPropMap(propertyBeanMap);

                classBeanMap.put(classBeanId, classBean);

                //解析<property>
                List<Element> propertyElementList = element.elements("property");
                for (Element propertyElement : propertyElementList) {
                    PropertyBean propertyBean = new PropertyBean();
                    String propertyName = propertyElement.attributeValue("name");
                    propertyBean.setName(propertyName);
                    propertyBean.setValue(propertyElement.attributeValue("value"));
                    propertyBean.setRef(propertyElement.attributeValue("ref"));
                    propertyBeanMap.put(propertyName, propertyBean);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  遍历classBeanMap, 实例化bean，写入beanMap
     *  这一步不需要实现注入
     */
    private void instanceBean() {
        for (String classBeanId : classBeanMap.keySet()) {
            ClassBean classBean = classBeanMap.get(classBeanId);

            //反射：根据类全限定名构造实例
            try {
                Object bean = Class.forName(classBean.getClassName()).newInstance();
                beanMap.put(classBeanId, bean);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    /**
     * bean的依赖注入
     * 反射：查找类对象的setXX方法，动态调用，实现依赖注入
     */
    private void beanToProperty() throws Exception{

        for (String classBeanId : classBeanMap.keySet()) {
            //取出classBean和bean
            ClassBean classBean = classBeanMap.get(classBeanId);
            Object bean = beanMap.get(classBeanId);

            //从classBean中取出所有属性，实现注入
            for (Map.Entry<String, PropertyBean> propertyBeanEntry : classBean.getPropMap().entrySet()) {

                //获取要注入的属性名和属性值，如果是ref，则需要从beanMap中获取bean进行注入
                String propertyName = propertyBeanEntry.getKey();
                PropertyBean propertyBean = propertyBeanEntry.getValue();
                String value = propertyBean.getValue();
                String ref = propertyBean.getRef();

                //反射，调用setField方法实现注入
                try {
                    Field property = bean.getClass().getDeclaredField(propertyName);
                    String setterName = "set" + propertyName.substring(0,1).toUpperCase() + propertyName.substring(1);
                    Method setFieldMethod = bean.getClass().getMethod(setterName, property.getType());

                    //区分注入ref还是填充value
                    if (ref != null) {
                        setFieldMethod.invoke(bean, beanMap.get(ref));
                    }else {
                        Class propertyType = property.getType();

                        //普通属性，根据类型注入,因为value是String类型
                        if (propertyType == int.class || propertyType == Integer.class) {
                            setFieldMethod.invoke(bean, Integer.parseInt(value));
                        }else if (propertyType == String.class) {
                            setFieldMethod.invoke(bean, value);
                        }else if (propertyType == float.class || propertyType == Float.class) {
                            setFieldMethod.invoke(bean, Float.parseFloat(value));
                        }else {
                            throw new RuntimeException("property type not support error when dependency injecting");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }






}
