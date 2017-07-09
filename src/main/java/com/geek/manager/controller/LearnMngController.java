package com.geek.manager.controller;

import com.geek.manager.service.LearnMngService;
import com.geek.model.Learn;
import com.geek.utils.FileTools;
import com.geek.utils.JSONUtils;
import com.geek.utils.ReturnStatus;
import com.geek.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/management")
public class LearnMngController {

    @Autowired
    private LearnMngService learnMngService;
    @Autowired
    FileTools fileTools;
    private String message;
    private Map resultMap;

    /**
     * 查找所有的学习资源
     * @param response
     */
    @RequestMapping(value = "/findAllLearn.action")
    @Transactional
    public void findAllLearn(HttpServletResponse response) {
        List<Learn> learns = learnMngService.findAllLearn();
        message = learnMngService.getMessage();
        if (learns != null) {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS, message, learns);
        } else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL, message, learns);
        }
        JSONUtils.toJSON(resultMap, response);
    }

    /**
     * 通过学习资源编号
     * @param learnId
     * @param response
     */
    @RequestMapping(value = "/deleteLearn.action")
    @Transactional
    public void deleteLearn(String learnId, HttpServletResponse response) {
        boolean flag = learnMngService.deleteLearn(learnId);
        message = learnMngService.getMessage();
        if (flag) {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS, message, learnId);
        } else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL, message, learnId);
        }
        JSONUtils.toJSON(resultMap, response);
    }


    /**
     * 更新学习资源
     * @param oldId
     * @param newId
     * @param learn
     * @param image
     * @param response
     */
    @RequestMapping(value = "/updateLearn.action")
    @Transactional
    public void updateLearn(String oldId, String newId, Learn learn,
                            MultipartFile image, HttpServletResponse response) {
        boolean flag = learnMngService.updateLearn(oldId,newId,learn,image);
        message = learnMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap, response);
    }

    /**
     * 添加学习资源
     * @param learn
     * @param image
     * @param response
     */
    @RequestMapping(value = "/addLearn.action")
    @Transactional
    public void addLearn(Learn learn, MultipartFile image, HttpServletResponse response) {
        boolean flag = learnMngService.addLearn(learn, image);
        message = learnMngService.getMessage();
        if (flag) {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS, message, learn.getLearnId());
        } else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL, message, learn.getLearnId());
        }
        JSONUtils.toJSON(resultMap, response);
    }

    /**
     * 删除一组学习资源
     *
     * @param learnId
     * @param response
     */
    @RequestMapping(value = "/deleteLearns.action")
    @Transactional
    public void deleteLearns(String learnId[], HttpServletResponse response) {
        boolean flag = learnMngService.deleteLearns(learnId);
        message = learnMngService.getMessage();
        if (flag) {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS, message, null);
        } else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL, message, null);
        }
        JSONUtils.toJSON(resultMap, response);
    }
}
