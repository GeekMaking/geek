package com.geek.manager.service;

import com.geek.model.User;

import java.util.List;

public interface UserService {
    //返回相关信息
    String getMessage();
    //用户登录
    boolean userLogin(User user);
    //查找单个用户
    boolean findUser(User user);
    //查找全部用户并显示
    List<User> getAllUser();
    //添加一位管理员
    boolean addUser(User user);
    //删除一位管理员
    boolean deleteUser(String userName);
    //更新一位管理员
    boolean updateUser(User user);
    //删除一组管理员
    boolean deleteUsers(String userName[]);
}
