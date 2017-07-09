package com.geek.manager.service.impl;

import com.geek.manager.service.UserService;
import com.geek.mapper.UserMapper;
import com.geek.model.User;
import com.geek.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    private String message;

    /**
     * 获取操作信息
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 管理员登录
     * @param user
     * @return
     */
    @Override
    public boolean userLogin(User user) {
        boolean flag = findUser(user);
        if (flag) {
            if (isValidate(user)){
                User user1 = userMapper.userLogin(user);
                if (user1 != null) {
                    message = "登录成功";
                    return true;
                }
                message = "用户名或密码错误";
                return false;
            }else {
                return false;
            }
        } else
            message = "用户名或密码错误";
            return false;
    }

    /**
     * 获取所有的管理员
     * @return
     */
    @Override
    public List<User> getAllUser() {
        List<User> users = userMapper.getAllUser();
        if (users != null) {
            message = "获取用户信息成功";
            return users;
        }
        message = "获取用户信息失败";
        return null;
    }

    /**
     * 新增一位管理员
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        boolean flag = findUser(user);
        if (flag) {
            message = "该用户已存在";
            return false;
        } else if (isValidate(user)) {
            int i = userMapper.insertUser(user);
            if (i == 1) {
                message = "添加管理员成功";
                return true;
            } else {
                message = "添加管理员失败";
                return false;
            }
        } else
            return false;
    }

    /**
     * 删除管理员
     * @param userName
     * @return
     */
    @Override
    public boolean deleteUser(String userName) {
        int i = userMapper.deleteUser(userName);
        if (i == 1) {
            message = "删除管理员成功";
            return true;
        }
        message = "删除管理员失败";
        return false;
    }

    /**
     * 更新管理员
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        int i = userMapper.updateUser(user);
        if (i == 1) {
            message = "更新成功";
            return true;
        }
        message = "更新失败";
        return false;
    }

    /**
     * 批量删除
     * @param userName
     * @return
     */
    @Override
    public boolean deleteUsers(String[] userName) {
        /*for (String username : userName) {
            flag = deleteUser(username);
        }*/
        int size = userName.length;
        int count = userMapper.deleteUsers(userName);
        if (size == count){
            message = OperationUtils.getMessage(true);
            return true;
        }
        message = OperationUtils.getMessage(false);
        return false;
    }

    /**
     * 判断用户是否存在
     * @param user
     * @return
     */
    @Override
    public boolean findUser(User user) {
        User user1 = userMapper.findUser(user.getUserName());
        if (user1 != null) {
            return true;
        } else
            return false;
    }

    /**
     * 输入验证
     * @param user
     * @return
     */

    public boolean isValidate(User user) {
        if (user.getUserName() == null || user.getUserName().equals("")) {
            message = "用户名不能为空,请输入6-12为英文或数字组合";
            return false;
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            message = "密码不能为空,请输入6-12位数字、英文或特殊字符";
            return false;
        }
        if (!user.getUserName().matches("[a-zA-Z0-9]{6,12}")) {
            message = "输入的用户名有误,请输入6-12位英文或数字组合";
            return false;
        }
        if (!user.getPassword().matches("[a-zA-Z0-9\\p{Punct}]{6,12}")) {
            message = "输入的密码有误,请输入6-12位数字、英文或特殊字符";
            return false;
        }
        return true;
    }
}
