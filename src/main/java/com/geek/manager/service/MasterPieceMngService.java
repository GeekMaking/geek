package com.geek.manager.service;

import com.geek.model.Masterpiece;
import org.springframework.web.multipart.MultipartFile;

public interface MasterPieceMngService {

    //获取操作信息
    String getMessage();
    //添加作品
    boolean addMasterpiece(Masterpiece masterpiece, MultipartFile image);
    //删除作品
    boolean deleteMasterpiece(String masterId);
    //更新作品(编号不能更改)
    boolean updateMasterpiece(Masterpiece masterpiece, MultipartFile image);
    //批量删除
    boolean deleteMasterpieces(String masterId[]);
    //判断作品是否存在
    boolean findMasterpiece(String masterId);
}
