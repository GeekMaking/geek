package com.geek.manager.service;

import com.geek.model.Join;

import java.util.List;

public interface JoinMngService {
    //获取所有的报名者
    List<Join> findAllJoin();
    //删除报名者
    boolean deleteJoin(String joinId);
    //批量删除
    boolean deleteJoins(String joinId[]);
    //获取操作信息
    String getMessage();
    //判断是否存在
    boolean findJoin(String joinId);
}
