package com.geek.user.service;

import com.geek.model.Team;

import java.util.List;

public interface TeamService {

    String getMessage();
    //查找所有团队
    List<Team> findAllTeam();

}
