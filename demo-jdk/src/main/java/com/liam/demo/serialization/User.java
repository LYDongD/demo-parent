package com.liam.demo.serialization;

import java.io.Serializable;

/**
 * 用于测试jvm的序列化与反序列化
 * @author Liam
 * @date 2019/5/1 上午11:35
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3974974861271534445L;

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
