package com.geek.manager.service.impl;

import com.geek.manager.service.LearnMngService;
import com.geek.mapper.LearnMapper;
import com.geek.model.Learn;
import com.geek.utils.FileTools;
import com.geek.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
class LearnMngServiceImpl implements LearnMngService {

    private String message;
    @Autowired
    private LearnMapper learnMapper;
    @Autowired
    private FileTools fileTools;

    /**
     * 获取所有的资源
     * @return
     */
    @Override
    public List<Learn> findAllLearn() {
        List<Learn> learns = learnMapper.findAllLearn();
        if (learns != null){
            message = "学习资源查找成功";
            return learns;
        }
        message = "学习资源查找失败";
        return null;
    }

    /**
     * 添加资源
     * @param learn
     * @return
     */
    @Override
    public boolean addLearn(Learn learn,MultipartFile image) {
        boolean flag = findLearn(learn.getLearnId());//判断是否存在
        if (flag){
            message = "该资源编号已经存在,请重新输入";
            return false;
        }else if (isValidate(learn)){
            if (!image.isEmpty()){//图片不为空
                boolean flag1 = fileTools.saveImage(image,Learn.class.getSimpleName(),learn.getLearnId());
                if (flag1){//设置图片路径
                    learn.setImagePath(fileTools.getVirtualPath());
                }else {
                    message = "添加学习资源失败";
                    return false;
                }
            }//图片为空或设置路径完成
            int i = learnMapper.addLearn(learn);
            if (i == 1){
                message = "添加学习资源成功";
                return true;
            }else {
                message = "添加学习资源失败";
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 删除资源
     * @param learnId
     * @return
     */
    @Override
    public boolean deleteLearn(String learnId) {
        String imagePath = findImagePath(learnId);
        fileTools.deleteImage(imagePath);

        int i = learnMapper.deleteLearn(learnId);
        if(i == 1){
            message = "删除成功";
            return true;
        }
        message = "删除失败";
        return false;
    }


    /**
     * 更新资源
     * @param learn
     * @return
     */
    @Override
    public boolean updateLearn(String oldId,String newId,Learn learn, MultipartFile image) {
        if (!oldId.equals(newId)){
            updateLearnId(oldId,newId);
        }
        learn.setLearnId(newId);//总是设置成新Id
        if(!image.isEmpty()){
            boolean flag = fileTools.updateImage(image,learn.getImagePath(),Learn.class.getSimpleName(),newId);
            if (flag){
                learn.setImagePath(fileTools.getVirtualPath());
            }else {
                message = "更新学习资源失败";
                return false;
            }
        }//图片为空或设置路径成功
        int i = learnMapper.updateLearn(learn);
        if (i == 1){
            message = "修改成功";
            return true;
        }
        message = "修改失败";
        return false;
    }

    /**
     * 更新编号
     * @param oldId
     * @param newId
     * @return
     */
    @Override
    public boolean updateLearnId(String oldId, String newId) {
        int i = learnMapper.updateLearnId(oldId,newId);
        if (i == 1){
            message = "修改资源编号成功";
            return true;
        }
        message = "修改资源编号失败";
        return false;
    }

    /**
     * 批量删除
     * @param learnId
     * @return
     */
    @Override
    public boolean deleteLearns(String[] learnId) {
        /*boolean flag = false;
        for (String id:learnId){
            flag = deleteLearn(id);
        }
        message = OperationUtils.getMessage(flag);
        return flag;*/
        List<String> imagePath = learnMapper.findImagePaths(learnId);
        fileTools.deleteImages(imagePath);

        int size = learnId.length;
        int count = learnMapper.deleteLearns(learnId);
        if (size == count){
            message = OperationUtils.getMessage(true);
            return true;
        }else
            message = OperationUtils.getMessage(false);
            return false;
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
     * 判断资源是否存在
     * @param learnId
     * @return
     */
    @Override
    public boolean findLearn(String learnId){
        Learn learn = learnMapper.findLearn(learnId);
        if (learn != null){
            return true;
        }else
            return false;
    }

    /**
     * 查找资源的图片路径
     * @param learnId
     * @return
     */
    public String findImagePath(String learnId) {
        Learn learn = learnMapper.findLearn(learnId);
        if (learn != null){
            return learn.getImagePath();
        }
        return "";
    }

    public String[] findImagePaths(String[] learnId){
        List<Learn> learns = learnMapper.findAllLearn();
        return null;
    }

    /**
     * 验证输入
     * @param learn
     * @return
     */
    public boolean isValidate(Learn learn){
        if (learn.getLearnId() == null || learn.getLearnId().equals("")){
            message = "资源编号不能为空,请输入1-4位数字";
            return false;
        }
        if (learn.getLearnName() == null || learn.getLearnName().equals("")){
            message = "资源名字不能为空";
            return false;
        }
        if (learn.getLearnDesc() == null || learn.getLearnDesc().equals("")){
            message = "介绍不能为空";
            return false;
        }
        if (!learn.getLearnId().matches("^\\d{1,4}")){
            message = "资源编号为1-4位数字";
            return false;
        }
        return true;
    }
}
