package com.geek.manager.service.impl;

import com.geek.manager.service.TeamMngService;
import com.geek.mapper.TeamMapper;
import com.geek.model.Team;
import com.geek.utils.FileTools;
import com.geek.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TeamMngServiceImpl implements TeamMngService {

    private String message;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private FileTools fileTools;

    @Override
    public List<Team> findAllTeam() {
        List<Team> teams = teamMapper.mngFindAllTeam();
        if (teams != null){
            message = "获取团队成功";
            return teams;
        }
        message = "获取团队失败";
        return null;
    }

    /**
     * 获取操作信息
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 更新团队
     * @param team
     * @param image
     * @return
     */
    @Override
    public boolean updateTeam(Team team, MultipartFile image) {
        if (!image.isEmpty()){
            boolean flag = fileTools.updateImage(image,team.getImagePath(),Team.class.getSimpleName(),team.getTeamId());
            if (flag){
                team.setImagePath(fileTools.getVirtualPath());
            }else {
                message = "更新团队失败";
                return false;
            }
        }//图片为空或设置路径成功
        int i = teamMapper.updateTeam(team);
        if (i == 1){
            message = "更新团队成功";
            return true;
        }
        message = "更新团队失败";
        return false;
    }

    /**
     * 删除团队
     * @param teamId
     * @return
     */
    @Override
    public boolean deleteTeam(String teamId) {
        String imagePath = findImagePath(teamId);
        fileTools.deleteImage(imagePath);

        boolean flag = hasMember(teamId);
        if (flag){
            message = "该团队有成员,不能删除";
            return false;
        }
        fileTools.deleteImage(findImagePath(teamId));
        int i = teamMapper.deleteTeam(teamId);
        if (i == 1){
            message = "删除团队成功";
            return true;
        }
        message = "删除团队失败";
        return false;
    }

    /**
     * 添加团队
     * @param team
     * @param image
     * @return
     */
    @Override
    public boolean addTeam(Team team,MultipartFile image) {
        boolean flag = findTeam(team.getTeamId());
        if (flag){
            message = "该团队编号已经存在,请重新输入";
            return false;
        }else if (isValidate(team)){
            if (!image.isEmpty()){
                boolean flag1 = fileTools.saveImage(image,Team.class.getSimpleName(),team.getTeamId());
                if (flag1){
                    team.setImagePath(fileTools.getVirtualPath());
                }else {
                    message = "添加团队失败";
                    return false;
                }
            }//图片为空或设置路径成功
            int i = teamMapper.addTeam(team);
            if (i == 1){
                message = "添加团队成功";
                return true;
            }
            message = "添加团队失败";
            return false;
        }else
            return false;
    }

    /**
     * 批量删除
     * @param teamId
     * @return
     */
    @Override
    public boolean deleteTeams(String[] teamId) {
        /*boolean flag = false;
        for (String id:teamId){
            flag = deleteTeam(id);
        }
        message = OperationUtils.getMessage(flag);
        return flag;*/
        List<String> imagePath = teamMapper.findImagePaths(teamId);
        fileTools.deleteImages(imagePath);

        int size = teamId.length;
        int count = teamMapper.deleteTeams(teamId);
        if (size == count){
            message = OperationUtils.getMessage(true);
            return true;
        }else
            message = OperationUtils.getMessage(false);
            return false;
    }

    /**
     * 判断团队是否已经存在
     * @param teamId
     * @return
     */
    @Override
    public boolean findTeam(String teamId){
        Team team = teamMapper.findTeam(teamId);
        if (team != null){
            return true;
        }else
            return false;
    }

    /**
     * 判断团队内是否有成员,有则不予删除
     * @param teamId
     * @return
     */
    @Override
    public boolean hasMember(String teamId){
        int count = teamMapper.getMemberCount(teamId);
        if (count > 0){
            return true;
        }else
            return false;
    }

    /**
     * 输入验证
     * @param team
     * @return
     */
    public boolean isValidate(Team team){
        if (team.getTeamId() == null || team.getTeamId().equals("")){
            message = "团队编号不能为空,请输入1-2为数字";
            return false;
        }
        if (team.getTeamName() == null || team.getTeamName().equals("")){
            message = "为团队起个霸气的名字吧";
            return false;
        }
        if (team.getTeamDesc() == null || team.getTeamDesc().equals("")){
            message = "团队简介好像忘了填呢";
            return false;
        }
        if (!team.getTeamId().matches("\\d{1,2}")){
            message = "团队编号为1-2为数字";
            return false;
        }
        if (!team.getTeamLeader().matches("20\\d{8}")){
            message = "组长的编号有误哟,请输入10位学号";
            return false;
        }
        return true;
    }

    /**
     * 获取图片路径
     * @param teamId
     * @return
     */
    public String findImagePath(String teamId){
        Team team = teamMapper.findTeam(teamId);
        if (team != null){
            return team.getImagePath();
        }
        return "";
    }
}
