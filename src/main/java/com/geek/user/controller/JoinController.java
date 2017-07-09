package com.geek.user.controller;

import com.geek.model.Join;
import com.geek.user.service.JoinService;
import com.geek.utils.JSONUtils;
import com.geek.utils.ReturnStatus;
import com.geek.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;
    private Map resultMap;
    private String message;

    /**
     * 写入报名信息
     * @param join
     * @param response
     */
    @RequestMapping(value = "/addJoin.action")
    @Transactional
    public void addJoin(Join join, HttpServletResponse response){
        boolean flag = joinService.addJoin(join);
        message = joinService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,join);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

}
