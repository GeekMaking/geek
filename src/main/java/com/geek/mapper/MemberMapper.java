package com.geek.mapper;

import com.geek.model.Member;
import com.geek.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMapper {

    List<Member> mngFindAllMember();
    List<Member> findAllMember();
    List<Member> findMemberByTeam(String memberTeam);
    int addMember(Member member);
    int deleteMember(String memberId);
    int updateMemberId(String oldId, String newId);
    int updateMember(Member member);
    Member findMember(String memberId);
    Team teamExist(String teamId);
    int deleteMembers(String[] memberId);
    List<String> findImagePaths(String[] memberId);
}
