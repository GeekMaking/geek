package com.geek.manager.service.impl;

import com.geek.manager.service.SuggestionMngService;
import com.geek.mapper.SuggestionMapper;
import com.geek.model.Suggestion;
import com.geek.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SuggestionMngServiceImpl implements SuggestionMngService {

    @Autowired
    private SuggestionMapper suggestionMapper;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 删除单个意见
     * @param time
     * @return
     */
    @Override
    public boolean deleteSuggestion(Date time) {
        int i = suggestionMapper.deleteSuggestion(time);
        if (i == 1){
            message = "删除成功";
            return true;
        }
        message = "删除失败";
        return false;
    }

    /**
     * 查找所有的意见
     * @return
     */
    @Override
    public List<Suggestion> findAllSuggestion() {
        List<Suggestion> suggestions = suggestionMapper.findAllSuggestion();
        if(suggestions != null){
            message = "查找所有意见成功";
            return suggestions;
        }
        message = "查找意见失败";
        return null;
    }

    /**
     * 批量删除
     * @param time
     * @return
     */
    @Override
    public boolean deleteSuggestions(Date[] time) {
        /*boolean flag = false;
        for (Date t:time){
            flag = deleteSuggestion(t);
        }
        message = OperationUtils.getMessage(flag);
        return flag;*/
        int size = time.length;
        int count = suggestionMapper.deleteSuggestions(time);
        if (size == count){
            message = OperationUtils.getMessage(true);
            return true;
        }else
            message = OperationUtils.getMessage(false);
            return false;
    }

}
