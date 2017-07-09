package com.geek.mapper;

import com.geek.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    //用户登录
    User userLogin(User user);
    //查找单个用户
    User findUser(String userName);
    //查找全部用户并显示
    List<User> getAllUser();
    //添加一位管理员
    int insertUser(User user);
    //删除一位管理员
    int deleteUser(String userName);
    //更新一位管理员
    int updateUser(User user);
    ////批量删除
    int deleteUsers(String[] userName);
}
