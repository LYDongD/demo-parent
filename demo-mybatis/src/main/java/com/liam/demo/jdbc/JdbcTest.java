package com.liam.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *  完成一次数据库查询操作
 *  1 每次操作都建立连接和释放连接，重IO操作，性能开销大，且无法限制资源的使用 -》使用连接池管理连接
 *  2 sql和占位符硬编码，每次修改sql都要重新编译 -> 使用xml单独维护
 *  3 每次都遍历结果集，对象和数据库实体之间的映射关系缺乏自动化 -> 使用xml定义对象和数据实体之间的映射
 */
public class JdbcTest {

    public static void main(String[] args) {

        Connection connection = null;
        //预编译sql，缓存，避免重复编译
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://47.106.84.7/db_test?characterEncoding=utf-8", "root", "pss123er");
            String sql = "select * from tb_user where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            //根据占位符设置参数值
            preparedStatement.setString(1, "pony");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "  " + resultSet.getString("username")
                        + " " + resultSet.getInt("sex"));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}