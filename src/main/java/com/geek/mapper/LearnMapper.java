package com.geek.mapper;

import com.geek.model.Learn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnMapper {

    int addLearn(Learn learn);
    List<Learn> findAllLearn();
    List<Learn> findLearnByType(String learnType);
    int deleteLearn(String learnId);
    int updateLearn(Learn learn);
    int updateLearnId(String oldId, String newId);
    Learn findLearn(String learnId);
    int deleteLearns(String[] learnId);
    List<String> findImagePaths(String[] learnId);

}
