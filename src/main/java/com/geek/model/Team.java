package com.geek.model;

import org.apache.ibatis.type.Alias;

@Alias("team")
public class Team {

    private String teamId;//团队编号
    private String teamName;//团队名称
    private String teamLeader;//团队负责人
    private String teamDesc;//团队简介
    private String imagePath;//团队照片名

    public Team(){}

    public Team(String teamId, String teamName, String teamLeader, String teamDesc, String imagePath) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamLeader = teamLeader;
        this.teamDesc = teamDesc;
        this.imagePath = imagePath;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
