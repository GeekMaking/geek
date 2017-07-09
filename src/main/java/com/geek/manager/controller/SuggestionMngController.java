package com.geek.manager.controller;

import com.geek.manager.service.SuggestionMngService;
import com.geek.model.Suggestion;
import com.geek.utils.JSONUtils;
import com.geek.utils.ReturnStatus;
import com.geek.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/management")
public class SuggestionMngController {

    @Autowired
    private SuggestionMngService suggestionMngService;
    private String message;
    private Map resultMap;
    private SimpleDateFormat dateFormat;

    /**
     * 删除一条意见
     * @param time
     * @param response
     */
    @RequestMapping(value = "/deleteSuggestion.action")
    @Transactional
    public void deleteSuggestion(String time, HttpServletResponse response) throws ParseException {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean flag = suggestionMngService.deleteSuggestion(dateFormat.parse(time));
        message = suggestionMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 返回所有的意见
     * @param response
     */
    @RequestMapping(value = "/findSuggestion.action")
    @Transactional
    public void findAllSuggestion(HttpServletResponse response){
        List<Suggestion> suggestions = suggestionMngService.findAllSuggestion();
        message = suggestionMngService.getMessage();
        if (suggestions != null){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,suggestions);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,suggestions);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 批量删除
     * @param time
     * @param response
     */
    @RequestMapping(value = "/deleteSuggestions.action")
    @Transactional
    public void deleteSuggestions(String time[],HttpServletResponse response){
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date[] dates = new Date[time.length];
        for (int i = 0;i < time.length;i ++){
            try {
                dates[i] = dateFormat.parse(time[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        boolean flag = suggestionMngService.deleteSuggestions(dates);
        message = suggestionMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

}
