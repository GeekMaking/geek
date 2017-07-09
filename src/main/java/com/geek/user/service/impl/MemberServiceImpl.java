package com.geek.user.service.impl;

import com.geek.mapper.MemberMapper;
import com.geek.model.Member;
import com.geek.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<Member> findAllMember() {
        List<Member> members = memberMapper.findAllMember();
        if (members != null){
            message = "获取成员成功";
            return members;
        }
        message = "获取成员失败";
        return null;
    }

    @Override
    public List<Member> findMemberByTeam(String memberTeam) {
        List<Member> members = memberMapper.findMemberByTeam(memberTeam);
        if (members != null){
            message = "获取成员成功";
            return members;
        }
        message = "获取成员失败";
        return null;
    }
}
