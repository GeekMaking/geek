package com.geek.manager.service.impl;

import com.geek.manager.service.MemberMngService;
import com.geek.mapper.MemberMapper;
import com.geek.model.Member;
import com.geek.model.Team;
import com.geek.utils.FileTools;
import com.geek.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MemberMngServiceImpl implements MemberMngService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private FileTools fileTools;
    private String message;


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean addMember(Member member,MultipartFile image) {
        boolean flag = findMember(member.getMemberId());
        if (flag){
            message = "该学号已经存在,请重新添加";
            return false;
        }else if (isValidate(member)){
            if (!image.isEmpty()){
                boolean flag1 = fileTools.saveImage(image,Member.class.getSimpleName(),member.getMemberId());
                if (flag1){
                    member.setImagePath(fileTools.getVirtualPath());
                }else {
                    message = "成员添加失败";
                    return false;
                }
            }//图片为空或添加路径成功
            boolean flag1 = teamExist(member.getMemberTeam());
            if (flag1){
                int i = memberMapper.addMember(member);
                if (i == 1){
                    message = "成员添加成功";
                    return true;
                }
                message = "成员添加失败";
                return false;
            }
            return false;
        }else
            return false;
    }

    /**
     * 删除成员
     * @param memberId
     * @return
     */
    @Override
    public boolean deleteMember(String memberId) {
        String imagePath = findImagePath(memberId);
        fileTools.deleteImage(imagePath);
        int i = memberMapper.deleteMember(memberId);
        if (i == 1){
            message = "删除成员成功";
            return true;
        }
        message = "删除成员失败";
        return false;
    }

    /**
     * 更新成员
     * @param oldId
     * @param newId
     * @param member
     * @param image
     * @return
     */
    @Override
    public boolean updateMember(String oldId,String newId,Member member, MultipartFile image) {
        if (oldId.equals(newId)){
            updateMemberId(oldId,newId);
        }
        member.setMemberId(newId);
        if (!image.isEmpty()){
            boolean flag = fileTools.updateImage(image,member.getImagePath(),Member.class.getSimpleName(),member.getMemberId());
            if (flag){
                member.setImagePath(fileTools.getVirtualPath());
            }else {
                message = "成员修改失败";
                return false;
            }
        }//图片为空或路径保存成功
        int i = memberMapper.updateMember(member);
        if (i == 1){
            message = "成员修改成功";
            return true;
        }
        message = "成员修改失败";
        return false;
    }

    /**
     * 更新成员学号
     * @param oldId
     * @param newId
     * @return
     */
    @Override
    public boolean updateMemberId(String oldId, String newId) {
        int i = memberMapper.updateMemberId(oldId,newId);
        if (i == 1){
            message = "成员学号修改成功";
            return true;
        }
        message = "成员学号修改失败";
        return false;
    }

    /**
     * 批量删除
     * @param memberId
     * @return
     */
    @Override
    public boolean deleteMembers(String[] memberId) {
        /*boolean flag = false;
        for (String id:memberId){
            flag = deleteMember(id);
        }
        message = OperationUtils.getMessage(flag);
        return flag;*/
        List<String> imagePath = memberMapper.findImagePaths(memberId);
        fileTools.deleteImages(imagePath);

        int size = memberId.length;
        int count = memberMapper.deleteMembers(memberId);
        if (size == count){
            message = OperationUtils.getMessage(true);
            return true;
        }else
            message = OperationUtils.getMessage(false);
            return false;
    }

    /**
     * 判断成员是否存在
     * @param memberId
     * @return
     */
    @Override
    public boolean findMember(String memberId){
        Member member = memberMapper.findMember(memberId);
        if (member!= null){
            return true;
        }else
            return false;
    }

    /**
     * 判断所添加的团队是否存在
     * @param teamId
     * @return
     */
    @Override
    public boolean teamExist(String teamId) {
        Team team = memberMapper.teamExist(teamId);
        if (team != null){
            return true;
        }else
            message = "该团队编号不存在,您可以先添加团队";
            return false;
    }

    /**
     * 查询图片路径用于删除
     * @param memberId
     * @return
     */
    @Override
    public String findImagePath(String memberId){
        Member member = memberMapper.findMember(memberId);
        if (member != null){
            return member.getImagePath();
        }
        return "";
    }

    @Override
    public List<Member> findAllMember() {
        List<Member> members = memberMapper.mngFindAllMember();
        if (members != null){
            message = "获取成员成功";
            return members;
        }
        message = "获取成员失败";
        return null;
    }

    /**
     * 输入验证
     * @param member
     * @return
     */
    public boolean isValidate(Member member){
        if (member.getMemberId() == null || member.getMemberId().equals("")){
            message = "学号不能为空,请输入10位学号";
            return false;
        }
        if (member.getMemberName() == null || member.getMemberName().equals("")){
            message = "姓名不能为空";
            return false;
        }
        if (member.getMemberGrade() == null || member.getMemberGrade().equals("")){
            message = "年级不能为空";
            return false;
        }
        if (member.getMemberTeam() == null || member.getMemberTeam().equals("")){
            message = "所在团队不能为空";
            return false;
        }
        if (member.getMemberDesc() == null || member.getMemberDesc().equals("")){
            message = "成员简介不能为空";
            return false;
        }
        if (!member.getMemberId().matches("20\\d{8}")){
            message = "请输入正确的10位学号";
            return false;
        }
        if (!member.getMemberGrade().matches("\\d{4}")){
            message = "年级为4位数字,如'2015'";
            return false;
        }
        if (!member.getMemberPhone().matches("^1[3|4|5|8][0-9]\\d{8}$")){
            message = "请输入正确的电话号码";
            return false;
        }
        return true;
    }
}
