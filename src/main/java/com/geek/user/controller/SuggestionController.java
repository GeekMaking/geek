package com.geek.user.controller;

import com.geek.model.Suggestion;
import com.geek.user.service.SuggestionService;
import com.geek.utils.JSONUtils;
import com.geek.utils.ReturnStatus;
import com.geek.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class SuggestionController {

    private String message;
    @Autowired
    private SuggestionService suggestionService;
    private Map resultMap;

    @RequestMapping(value = "/addSuggestion.action")
    public void addSuggestion(Suggestion suggestion, HttpServletResponse response){

        boolean flag = suggestionService.addSuggestion(suggestion);
        message = suggestionService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,suggestion);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }
}
