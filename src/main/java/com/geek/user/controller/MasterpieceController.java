package com.geek.user.controller;

import com.geek.model.Masterpiece;
import com.geek.user.service.MasterpieceService;
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
public class MasterpieceController {

    @Autowired
    private MasterpieceService masterpieceService;
    private String message;
    private Map resultMap;

    @RequestMapping(value = "/findAllMasterpiece.action")
    @Transactional
    public void findAllMasterpiece(HttpServletResponse response){
        List<Masterpiece> masterpieces = masterpieceService.findAllMasterpiece();
        message = masterpieceService.getMessage();
        if (masterpieces != null){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,masterpieces);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

}

