package com.geek.manager.controller;

import com.geek.manager.service.TeamMngService;
import com.geek.model.Team;
import com.geek.utils.JSONUtils;
import com.geek.utils.ReturnStatus;
import com.geek.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/management")
public class TeamMngController {

    @Autowired
    private TeamMngService teamMngService;
    private String message;
    private Map resultMap;

    /**
     * 添加团队
     * @param team
     * @param image
     * @param response
     */
    @RequestMapping(value = "/addTeam.action",method = RequestMethod.POST)
    @Transactional
    public void addTeam(Team team, MultipartFile image, HttpServletResponse response){
        boolean flag = teamMngService.addTeam(team,image);
        message = teamMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 删除团队
     * @param teamId
     * @param response
     */
    @RequestMapping(value = "/deleteTeam.action")
    @Transactional
    public void deleteTeam(String teamId,HttpServletResponse response){
        boolean flag = teamMngService.deleteTeam(teamId);
        message = teamMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,teamId);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,teamId);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 更新团队
     * @param team
     * @param image
     * @param response
     */
    @RequestMapping(value = "/updateTeam.action")
    @Transactional
    public void updateTeam(Team team, MultipartFile image, HttpServletResponse response){
        boolean flag = teamMngService.updateTeam(team,image);
        message = teamMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 批量删除
     * @param teamId
     * @param response
     */
    @RequestMapping(value = "/deleteTeams.action")
    @Transactional
    public void deleteTeams(String teamId[],HttpServletResponse response){
        boolean flag = teamMngService.deleteTeams(teamId);
        message = teamMngService.getMessage();
        if (flag){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,null);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

    /**
     * 后台获取团队
     * @param response
     */
    @RequestMapping(value = "/findAllTeam.action")
    @Transactional
    public void findAllTeam(HttpServletResponse response){
        List<Team> teams = teamMngService.findAllTeam();
        message = teamMngService.getMessage();
        if (teams != null){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,teams);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }
}
