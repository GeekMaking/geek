package com.geek.user.service;

import com.geek.model.Join;

public interface JoinService {

    //报名
    boolean addJoin(Join join);
    //查看是否已经报名
    boolean findJoin(String joinId);
    String getMessage();
}