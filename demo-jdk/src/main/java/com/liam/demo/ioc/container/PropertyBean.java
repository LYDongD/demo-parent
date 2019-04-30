package com.liam.demo.ioc.container;

/**
 * 属性bean模型，对应xml中的<property></property>
 * @author Liam
 * @date 2019/4/30 下午3:52
 */
public class PropertyBean {

    private String name;

    private String value;

    //指向另一个bean的id
    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "PropertyBean{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", ref='" + ref + '\'' +
                '}';
    }
}
