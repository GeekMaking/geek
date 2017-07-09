package com.geek.user.service;

import com.geek.model.Masterpiece;

import java.util.List;

public interface MasterpieceService {

    String getMessage();
    //查找所有的作品
    List<Masterpiece> findAllMasterpiece();

}
