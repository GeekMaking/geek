package com.geek.manager.controller;

import com.geek.manager.service.MasterPieceMngService;
import com.geek.model.Masterpiece;
import com.geek.utils.JSONUtils;
import com.geek.utils.ReturnStatus;
import com.geek.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping("/management")
@Controller
public class MasterpieceMngController {

    @Autowired
    private MasterPieceMngService masterPieceMngService;
    private Map resultMap;
    private String message;

    /**
     * 更新作品
     * @param masterpiece
     * @param image
     * @param response
     */
    @RequestMapping(value = "/updateMasterpiece.action")
    @Transactional
    public void updateMasterpiece(Masterpiece masterpiece, MultipartFile image, HttpServletResponse response){
        boolean flag = masterPieceMngService.updateMasterpiece(masterpiece,image);
        message = masterPieceMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 删除作品
     * @param masterId
     * @param response
     */
    @RequestMapping(value = "/deleteMasterpiece.action")
    @Transactional
    public void deleteMasterpiece(String masterId,HttpServletResponse response){
        boolean flag = masterPieceMngService.deleteMasterpiece(masterId);
        message = masterPieceMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,masterId);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,masterId);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 添加作品
     * @param masterpiece
     * @param image
     * @param response
     */
    @RequestMapping(value = "/addMasterpiece.action")
    @Transactional
    public void addMasterpiece(Masterpiece masterpiece, MultipartFile image, HttpServletResponse response){
        boolean flag = masterPieceMngService.addMasterpiece(masterpiece,image);
        message = masterPieceMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 批量删除
     * @param masterId
     * @param response
     */
    @RequestMapping(value = "/deleteMasterpieces.action")
    @Transactional
    public void deleteMasterpieces(String masterId[],HttpServletResponse response){
        boolean flag = masterPieceMngService.deleteMasterpieces(masterId);
        message = masterPieceMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

}
