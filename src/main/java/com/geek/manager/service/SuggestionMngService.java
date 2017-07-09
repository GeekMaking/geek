package com.geek.manager.service;

import com.geek.model.Suggestion;

import java.util.Date;
import java.util.List;

public interface SuggestionMngService {

    String getMessage();
    //删除意见
    boolean deleteSuggestion(Date time);
    //获取所有意见
    List<Suggestion> findAllSuggestion();
    //批量删除
    boolean deleteSuggestions(Date time[]);
}
