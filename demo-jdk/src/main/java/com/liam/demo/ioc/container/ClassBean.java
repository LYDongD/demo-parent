package com.liam.demo.ioc.container;

import java.util.HashMap;
import java.util.Map;

/**
 * 类对象模型，对应xml中的<bean></bean>
 * @author Liam
 * @date 2019/4/30 下午3:52
 */
public class ClassBean {

    private String id;

    private String className;

    private Map<String, PropertyBean> propMap = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, PropertyBean> getPropMap() {
        return propMap;
    }

    public void setPropMap(Map<String, PropertyBean> propMap) {
        this.propMap = propMap;
    }

    @Override
    public String toString() {
        return "ClassBean{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", propMap=" + propMap +
                '}';
    }
}
