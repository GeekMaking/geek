package com.geek.manager.service;

import com.geek.model.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberMngService {

    String getMessage();
    //添加成员
    boolean addMember(Member member, MultipartFile image);
    //删除成员
    boolean deleteMember(String memberId);
    //更新成员(此时编号没有更新)
    boolean updateMember(String oldId, String newId, Member member, MultipartFile image);
    //更新成员编号
    boolean updateMemberId(String oldId, String newId);
    //批量删除
    boolean deleteMembers(String memberId[]);
    //判断已经存在该成员
    boolean findMember(String memberId);
    //判断是否有该团队
    boolean teamExist(String teamId);
    //查找图片路径,用于删除
    String findImagePath(String memberId);
    //查找所有成员
    List<Member> findAllMember();

}
