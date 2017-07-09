package com.geek.user.service;

import com.geek.model.Member;

import java.util.List;

public interface MemberService {

    String getMessage();
    //查找所有的成员
    List<Member> findAllMember();
    //通过团队查找成员
    List<Member> findMemberByTeam(String memberTeam);

}
