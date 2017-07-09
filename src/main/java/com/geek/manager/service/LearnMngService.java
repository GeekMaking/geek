package com.geek.manager.service;

import com.geek.model.Learn;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LearnMngService {

    String getMessage();
    //添加学习资源
    boolean addLearn(Learn learn, MultipartFile image);
    //删除学习资源
    boolean deleteLearn(String learnId);
    //更新学习资源
    boolean updateLearn(String oldId, String newId, Learn learn, MultipartFile image);
    //获取全部学习资源
    List<Learn> findAllLearn();
    //更新学习资源编号
    boolean updateLearnId(String oldId, String newId);
    //批量删除
    boolean deleteLearns(String learnId[]);
    //判断资源是否存在
    boolean findLearn(String learnId);
    //查找资源的图片路径
}
