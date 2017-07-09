package com.geek.user.service.impl;

import com.geek.mapper.JoinMapper;
import com.geek.model.Join;
import com.geek.user.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    private JoinMapper joinMapper;
    private String message;

    /**
     * 填写报名信息
     * @param join
     * @return
     */
    @Override
    public boolean addJoin(Join join) {
        boolean flag = findJoin(join.getJoinId());
        if(flag){
            message = "您已经报名,如有疑问,请联系管理员";
            return false;
        }else if (isValidate(join)){
            int i = joinMapper.insertJoin(join);
            if (i == 1){
                message = "提交信息成功";
                return true;
            }else {
                message = "sorry,服务器正在开小差";
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 判断是否已经填写参加信息
     * @param joinId
     * @return
     */
    @Override
    public boolean findJoin(String joinId) {
        Join join = joinMapper.findJoin(joinId);
        if (join != null){
            return true;
        }
        return false;
    }

    /**
     * 获取操作信息
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 验证输入
     * @return
     */
    public boolean isValidate(Join join){

        if (!join.getJoinId().matches("^20\\d{8}")){
            message = "请输入正确的10位学号";
            return false;
        }
        if (!join.getJoinName().matches("^[\\u0391-\\uFFE5]{2,4}")){
            message = "请输入正确的姓名";
            return false;
        }
        if (!join.getJoinPhone().matches("^1[3|4|5|8][0-9]\\d{8}$")){
            message = "请输入正确的手机号码";
            return false;
        }
        if (!join.getJoinEmail().matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$")){
            message = "请输入正确的邮箱";
            return false;
        }
        return true;
    }
}
