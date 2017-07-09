package com.geek.model;

import org.apache.ibatis.type.Alias;

@Alias("join")
public class Join {

    private String joinId;//参加人学号
    private String joinName;//参加人姓名
    private String joinPhone;//参加人电话
    private String joinEmail;//参加人邮箱

    public String getJoinId() {
        return joinId;
    }

    public void setJoinId(String joinId) {
        this.joinId = joinId;
    }

    public String getJoinName() {
        return joinName;
    }

    public void setJoinName(String joinName) {
        this.joinName = joinName;
    }

    public String getJoinPhone() {
        return joinPhone;
    }

    public void setJoinPhone(String joinPhone) {
        this.joinPhone = joinPhone;
    }

    public String getJoinEmail() {
        return joinEmail;
    }

    public void setJoinEmail(String joinEmail) {
        this.joinEmail = joinEmail;
    }
}
