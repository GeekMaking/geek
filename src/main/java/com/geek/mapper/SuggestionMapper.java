package com.geek.mapper;

import com.geek.model.Suggestion;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SuggestionMapper {

    //添加意见
    int insertSuggestion(Suggestion suggestion);
    //删除意见
    int deleteSuggestion(Date time);
    //查找所有意见
    List<Suggestion> findAllSuggestion();
    //批量删除
    int deleteSuggestions(Date[] time);

}
