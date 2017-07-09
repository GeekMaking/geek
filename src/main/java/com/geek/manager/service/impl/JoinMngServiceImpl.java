package com.geek.manager.service.impl;

import com.geek.manager.service.JoinMngService;
import com.geek.mapper.JoinMapper;
import com.geek.model.Join;
import com.geek.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinMngServiceImpl implements JoinMngService {

    @Autowired
    JoinMapper joinMapper;
    private String message;

    /**
     * 删除报名者
     * @param joinId
     * @return
     */
    @Override
    public boolean deleteJoin(String joinId) {
        boolean flag = findJoin(joinId);
        if (flag) {
            int i = joinMapper.deleteJoin(joinId);
            if (i == 1) {
                message = "删除成功";
                return true;
            }
            message = "删除失败";
            return false;
        }else {
            message = "所删除的报名者不存在,请重新选择";
            return false;
        }
    }

    /**
     * 批量删除
     * @param joinId
     * @return
     */
    @Override
    public boolean deleteJoins(String[] joinId) {
        /*boolean flag = false;
        for (String id:joinId){
            flag = deleteJoin(id);
        }
        message = OperationUtils.getMessage(flag);
        return flag;*/
        int size = joinId.length;
        int count = joinMapper.deleteJoins(joinId);
        if (size == count){
            message = OperationUtils.getMessage(true);
            return true;
        }else
            message = OperationUtils.getMessage(false);
            return false;
    }

    /**
     * 查找所有的报名者
     * @return
     */
    @Override
    public List<Join> findAllJoin() {
        List<Join> joins = joinMapper.findAllJoin();
        if (joins != null){
            message = "获取所有报名者成功";
            return joins;
        }
        message = "获取报名者失败";
        return null;
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
     * 判断是否存在
     * @param joinId
     * @return
     */
    @Override
    public boolean findJoin(String joinId){
        Join join = joinMapper.findJoin(joinId);
        if (join != null){
            return true;
        }else
            return false;
    }

}
