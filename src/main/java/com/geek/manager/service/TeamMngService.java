package com.geek.manager.service;

import com.geek.model.Team;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeamMngService {

    String getMessage();
    //更新团队(团队编号不能更改)
    boolean updateTeam(Team team, MultipartFile image);
    //通过编号删除团队
    boolean deleteTeam(String teamId);
    //添加团队
    boolean addTeam(Team team, MultipartFile image);
    //批量删除
    boolean deleteTeams(String teamId[]);
    //判断是否存在该团队
    boolean findTeam(String teamId);
    //判断是否还有成员
    boolean hasMember(String teamId);
    //获取所有的成员
    List<Team> findAllTeam();
}
