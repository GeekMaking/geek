package com.geek.user.service.impl;

import com.geek.mapper.TeamMapper;
import com.geek.model.Team;
import com.geek.user.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private String message;
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<Team> findAllTeam() {
        List<Team> teams = teamMapper.findAllTeam();
        if (teams != null){
            message = "获取团队成功";
            return teams;
        }
        message = "获取团队失败";
        return null;
    }
}
