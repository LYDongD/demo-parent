package com.liam.demo.mapper;

import com.liam.demo.model.pojo.User;
import com.liam.demo.model.pojo.UserCustom;
import com.liam.demo.model.pojo.UserQueryVO;

import java.util.List;

/**
 *  用mapper动态代理的方式实现dao操作，避免直接操作SqlSession
 *  如何确保动态代理正确生成？需要遵循以下规范：
 *  1 namespace = mapper interface address
 *  2 sql statement id = method name
 *  3 参数类型 = parameter Type
 *  4 返回类型 = result type or result map
 *  动态代理实现模板方法:
 *  1,2 确保可以生成 SqlSession api 操作的 mapper statement id,
 *  3,4 确保正确传入SqlSession api 需要的参数
 */
public interface UserMapper {

    public User findUserById(int id) throws Exception;

    public List<User> findUserByName(String name) throws Exception;

    //综合查询: 处理复杂参数和返回值的情况，使用包装类型
    public List<UserCustom> findUserList(UserQueryVO userQueryVO) throws Exception;

    //综合查询总数，用于实现分页
    public Integer findUserCount(UserQueryVO userQueryVO) throws Exception;

    //使用ResultMap实现输出映射
    public User findUserByIdResultMap(int id) throws Exception;

    public List<UserCustom> findUserItems() throws Exception;


}
