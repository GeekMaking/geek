package com.geek.mapper;

import com.geek.model.Masterpiece;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterpieceMapper {

    List<Masterpiece> findAllMasterpiece();
    int addMasterpiece(Masterpiece masterpiece);
    int updateMasterpiece(Masterpiece masterpiece);
    int deleteMasterpiece(String masterId);
    Masterpiece findMasterpiece(String masterId);
    int deleteMasterpieces(String[] masterId);
    List<String> findImagePaths(String[] masterId);

}
