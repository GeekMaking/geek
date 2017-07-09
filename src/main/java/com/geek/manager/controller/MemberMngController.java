package com.geek.manager.controller;

import com.geek.manager.service.MemberMngService;
import com.geek.model.Member;
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
public class MemberMngController {

    @Autowired
    private MemberMngService memberMngService;
    private String message;
    private Map resultMap;

    /**
     * 添加成员
     * @param member
     * @param image
     * @param response
     */
    @RequestMapping(value = "/addMember.action")
    @Transactional
    public void addMember(Member member, MultipartFile image, HttpServletResponse response){
        boolean flag = memberMngService.addMember(member,image);
        message = memberMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 前台获取所有的成员
     * @param response
     */
    @RequestMapping(value = "/findAllMember.action")
    @Transactional
    public void findAllMember(HttpServletResponse response){
        List<Member> members = memberMngService.findAllMember();
        message = memberMngService.getMessage();
        if (members != null){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,members);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 删除成员
     * @param memberId
     * @param response
     */
    @RequestMapping(value = "/deleteMember.action")
    @Transactional
    public void deleteMember(String memberId,HttpServletResponse response){
        boolean flag = memberMngService.deleteMember(memberId);
        message = memberMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,memberId);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,memberId);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 更新成员
     * @param member
     * @param oldId
     * @param newId
     * @param image
     * @param response
     */
    @RequestMapping(value = "/updateMember.action")
    @Transactional
    public void updateMember(Member member, String oldId, String newId, MultipartFile image, HttpServletResponse response){
        boolean flag = memberMngService.updateMember(oldId,newId,member,image);
        message = memberMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 批量删除
     * @param memberId
     * @param response
     */
    @RequestMapping(value = "/deleteMembers.action")
    @Transactional
    public void deleteMembers(String memberId[],HttpServletResponse response){
        boolean flag = memberMngService.deleteMembers(memberId);
        message = memberMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

}
