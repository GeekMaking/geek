package com.geek.model;

import org.apache.ibatis.type.Alias;

@Alias("member")
public class Member {

    private String memberId;//成员学号
    private String memberName;//成员姓名
    private String memberPhone;//成员电话
    private String memberTeam;//成员所属团队编号
    private String memberGrade;//年级
    private String memberGradu;//成员毕业去向
    private String imagePath;//成员照片名
    private String memberDesc;//成员简介

    public String getMemberDesc() {
        return memberDesc;
    }

    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberTeam() {
        return memberTeam;
    }

    public void setMemberTeam(String memberTeam) {
        this.memberTeam = memberTeam;
    }

    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }

    public String getMemberGradu() {
        return memberGradu;
    }

    public void setMemberGradu(String memberGradu) {
        this.memberGradu = memberGradu;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberTeam='" + memberTeam + '\'' +
                ", memberGrade='" + memberGrade + '\'' +
                ", memberGradu='" + memberGradu + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", memberDesc='" + memberDesc + '\'' +
                '}';
    }
}
