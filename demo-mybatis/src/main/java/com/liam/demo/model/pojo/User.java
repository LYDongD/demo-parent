package com.liam.demo.model.pojo;

/**
 *  与tb_user字段一一对应
 */
public class User {

    //主键
    private Integer id;

    //性别，0 男， 1 女
    private Integer sex;

    //用户名
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sex=" + sex +
                ", username='" + username + '\'' +
                '}';
    }
}
