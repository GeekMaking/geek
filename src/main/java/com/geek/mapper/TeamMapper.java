package com.geek.mapper;

import com.geek.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {

    List<Team> findAllTeam();
    int updateTeam(Team team);
    int deleteTeam(String teamId);
    int addTeam(Team team);
    Team findTeam(String teamId);
    int getMemberCount(String teamId);
    List<Team> mngFindAllTeam();
    int deleteTeams(String[] teamId);
    List<String> findImagePaths(String[] teamId);

}
