package com.geek.user.controller;

import com.geek.model.Member;
import com.geek.user.service.MemberService;
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
public class MemberController {

    @Autowired
    MemberService memberService;
    private String message;
    private Map resultMap;

    /**
     * 前台获取所有的成员
     * @param response
     */
    @RequestMapping(value = "/findAllMember.action")
    @Transactional
    public void findAllMember(HttpServletResponse response){
        List<Member> members = memberService.findAllMember();
        message = memberService.getMessage();
        if (members != null){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,members);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 通过团队查找成员
     * @param memberTeam
     * @param response
     */
    @RequestMapping(value = "/findMember.action")
    @Transactional
    public void findMemberByTeam(String memberTeam,HttpServletResponse response){
        List<Member> members = memberService.findMemberByTeam(memberTeam);
        message = memberService.getMessage();
        if (members != null){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,members);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,members);
        }
        JSONUtils.toJSON(resultMap,response);
    }

}
