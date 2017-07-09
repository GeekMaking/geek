package com.geek.user.controller;

import com.geek.model.Learn;
import com.geek.user.service.LearnService;
import com.geek.utils.JSONUtils;
import com.geek.utils.ReturnStatus;
import com.geek.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class LearnController {

    @Autowired
    private LearnService learnService;
    private String message;
    private Map resultMap;

    @RequestMapping(value = "/findLearn.action")
    @Transactional
    public void findLearnByType(String learnType, HttpServletResponse response){
        List<Learn> learns = learnService.findLearnByType(learnType);
        message = learnService.getMessage();
        if (learns != null){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,learns);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

}
