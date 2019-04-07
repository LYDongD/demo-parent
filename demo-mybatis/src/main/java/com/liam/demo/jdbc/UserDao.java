package com.liam.demo.jdbc;

import com.liam.demo.model.pojo.User;

import java.util.List;

public interface UserDao {

    public User findUserById(int id) throws Exception;

    public List<User> findUserByName(String name) throws Exception;

    public void addUser(User user) throws Exception;

    public void deleteUser(int id) throws Exception;
}
