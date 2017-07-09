package com.geek.user.service;

import com.geek.model.Suggestion;

public interface SuggestionService {

    String getMessage();
    //添加建议
    boolean addSuggestion(Suggestion suggestion);
}
