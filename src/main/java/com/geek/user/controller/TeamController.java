package com.geek.user.controller;

import com.geek.model.Team;
import com.geek.user.service.TeamService;
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
public class TeamController {

    @Autowired
    private TeamService teamService;
    private String message;
    private Map resultMap;

    @RequestMapping(value = "/findAllTeam.action")
    @Transactional
    public void findAllTeam(HttpServletResponse response){
        List<Team> teams = teamService.findAllTeam();
        message = teamService.getMessage();
        if (teams != null){
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.SECCESS,message,teams);
        }else {
            resultMap = ReturnUtils.getReturnMap(ReturnStatus.FAIL,message,null);
        }
        JSONUtils.toJSON(resultMap,response);
    }

}
