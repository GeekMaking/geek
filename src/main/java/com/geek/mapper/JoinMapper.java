package com.geek.mapper;

import com.geek.model.Join;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinMapper {
    //填写报名信息
    int insertJoin(Join join);
    //查找报名者是否存在
    Join findJoin(String joinId);
    //删除报名者
    int deleteJoin(String joinId);
    //查找全部报名者
    List<Join> findAllJoin();
    //批量删除
    int deleteJoins(String[] joinId);
}
