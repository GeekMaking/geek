package com.geek.manager.controller;

import com.geek.manager.service.UserService;
import com.geek.model.User;
import com.geek.utils.JSONUtils;
import com.geek.utils.ReturnStatus;
import com.geek.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private String message;
    private Map resultMap;

    /**
     * 管理员登录
     * @param user
     * @param request
     * @param response
     */
    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    @Transactional
    public void userLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("登录一波,很舒服! ");
        boolean flag = this.userService.userLogin(user);
        message = userService.getMessage();
        if (flag) {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS, message, null);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        System.out.println("登录两波,很帅气!");
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 注销
     * @param session
     * @param response
     */
    @RequestMapping(value = "/management/logout.action")
    public void userLogout(HttpSession session,HttpServletResponse response){
        session.invalidate();
        resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,"注销成功",null);
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 查找所有管理员
     * @param response
     */
    @RequestMapping(value = "/management/findAllUser.action")
    @Transactional
    public void findAllUser(HttpServletResponse response) {
        List<User> users = this.userService.getAllUser();
        message = userService.getMessage();
        if (users != null){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,users);
        }else{
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,users);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 更新管理员
     * @param user
     * @param response
     */
    @RequestMapping(value = "/management/updateUser.action")
    @Transactional
    public void updateUser(User user,HttpServletResponse response){
        boolean flag = userService.updateUser(user);
        message = userService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,user);
        }else{
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,user);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 增加一位管理员
     * @param user
     * @param response
     */
    @RequestMapping(value = "/management/addUser.action")
    @Transactional
    public void addUser(User user,HttpServletResponse response){
        boolean flag = userService.addUser(user);
        message = userService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,user);
        }else{
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,user);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 删除一位用户
     * @param userName
     * @param response
     */
    @RequestMapping(value = "/management/deleteUser.action")
    @Transactional
    public void deleteUser(String userName,HttpServletResponse response){
        boolean flag = userService.deleteUser(userName);
        message = userService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,userName);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,userName);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    @RequestMapping(value = "/management/deleteUsers.action")
    @Transactional
    public void deleteUsers(@RequestParam("userName") String[] userName, HttpServletResponse response){
        boolean flag = userService.deleteUsers(userName);
        message = userService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,userName);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,userName);
        }
        JSONUtils.toJSON(resultMap,response);
    }
}
