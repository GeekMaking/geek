package com.geek.user.service.impl;

import com.geek.mapper.MasterpieceMapper;
import com.geek.model.Masterpiece;
import com.geek.user.service.MasterpieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterpieceServiceImpl implements MasterpieceService {

    @Autowired
    private MasterpieceMapper masterpieceMapper;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 查找所有的作品
     * @return
     */
    @Override
    public List<Masterpiece> findAllMasterpiece() {
        List<Masterpiece> masterpieces = masterpieceMapper.findAllMasterpiece();
        if (masterpieces != null){
            message = "查找作品成功";
            return masterpieces;
        }
        message = "查找作品失败";
        return null;
    }
}
