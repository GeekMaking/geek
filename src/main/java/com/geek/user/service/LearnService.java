package com.geek.user.service;

import com.geek.model.Learn;

import java.util.List;

public interface LearnService {

    //获取操作信息
    String getMessage();
    //通过类型查找学习资源
    List<Learn> findLearnByType(String learnType);

}
