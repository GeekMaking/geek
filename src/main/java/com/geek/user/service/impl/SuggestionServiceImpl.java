package com.geek.user.service.impl;

import com.geek.mapper.SuggestionMapper;
import com.geek.model.Suggestion;
import com.geek.user.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    private SuggestionMapper suggestionMapper;
    private String message;

    /**
     * 填写意见
     * @param suggestion
     * @return
     */
    @Override
    public boolean addSuggestion(Suggestion suggestion) {
        if (isValidate(suggestion)){
            suggestion.setTime(new Date());
            int i = suggestionMapper.insertSuggestion(suggestion);
            if (i == 1){
                message = "意见提交成功";
                return true;
            }
            message = "提交意见失败";
            return false;
        }else
            return false;
    }

    /**
     * 获取操作信息
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 输入验证
     * @param suggestion
     * @return
     */
    public boolean isValidate(Suggestion suggestion){
        if (suggestion.getView() == null || suggestion.getView().equals("")){
            message = "您的意见还没填写哟~";
            return false;
        }
        if (!suggestion.getPhone().matches("^1[3|4|5|8][0-9]\\d{8}$")){
            message = "请输入正确的电话号码";
            return false;
        }
        return true;
    }
}
