package com.geek.user.service.impl;

import com.geek.mapper.LearnMapper;
import com.geek.model.Learn;
import com.geek.user.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnServiceImpl implements LearnService {

    @Autowired
    LearnMapper learnMapper;
    private String message;

    /**
     * 通过类型查找不同的学习资源
     * @param learnType
     * @return
     */
    @Override
    public List<Learn> findLearnByType(String learnType) {
        List<Learn> learns = learnMapper.findLearnByType(learnType);
        if (learns != null){
            message = "获取学习资源成功";
            return learns;
        } else {
            message = "获取学习资源失败";
            return null;
        }
    }

    /**
     * 获取操作信息
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }
}
