package com.geek.manager.service.impl;

import com.geek.manager.service.MasterPieceMngService;
import com.geek.mapper.MasterpieceMapper;
import com.geek.model.Masterpiece;
import com.geek.utils.FileTools;
import com.geek.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MasterPieceMngServiceImpl implements MasterPieceMngService {

    @Autowired
    private MasterpieceMapper masterpieceMapper;
    @Autowired
    private FileTools fileTools;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 添加作品
     * @param masterpiece
     * @return
     */
    @Override
    public boolean addMasterpiece(Masterpiece masterpiece, MultipartFile image) {
        boolean flag = findMasterpiece(masterpiece.getMasterId());
        if (flag){
            message = "该作品编号已经存在,请重新输入";
            return false;
        }else if (isValidate(masterpiece)){
            if (!image.isEmpty()){
                boolean flag1 = fileTools.saveImage(image,Masterpiece.class.getSimpleName(),masterpiece.getMasterId());
                if (flag1){
                    masterpiece.setImagePath(fileTools.getVirtualPath());
                }else {
                    message = "添加学习资源失败";
                    return false;
                }
            }//图片为空或设置路径成功
            int i = masterpieceMapper.addMasterpiece(masterpiece);
            if (i == 1){
                message = "添加作品成功";
                return true;
            }
            message = "添加作品失败";
            return false;
        }else
            return false;
    }

    /**
     * 删除作品
     * @param masterId
     * @return
     */
    @Override
    public boolean deleteMasterpiece(String masterId) {
        String imagePath = findImagePath(masterId);
        fileTools.deleteImage(imagePath);

        int i = masterpieceMapper.deleteMasterpiece(masterId);
        if (i == 1){
            message = "删除作品成功";
            return true;
        }
        message = "删除作品失败";
        return false;
    }


    /**
     * 更新作品
     * @param masterpiece
     * @return
     */
    @Override
    public boolean updateMasterpiece(Masterpiece masterpiece,MultipartFile image) {
        if (!image.isEmpty()){
            boolean flag = fileTools.updateImage(image,masterpiece.getImagePath(),Masterpiece.class.getSimpleName(),masterpiece.getMasterId());
            if (flag){
                masterpiece.setImagePath(fileTools.getVirtualPath());
            }else {
                message = "更新作品失败";
                return false;
            }
        }
        int i = masterpieceMapper.updateMasterpiece(masterpiece);
        if (i == 1){
            message = "修改作品成功";
            return true;
        }
        message = "修改作品失败";
        return false;
    }

    /**
     * 批量删除
     * @param masterId
     * @return
     */
    @Override
    public boolean deleteMasterpieces(String[] masterId) {
        /*boolean flag = false;
        for (String id:masterId){
            flag = deleteMasterpiece(id);
        }
        message = OperationUtils.getMessage(flag);
        return flag;*/
        List<String> imagePath = masterpieceMapper.findImagePaths(masterId);
        fileTools.deleteImages(imagePath);

        int size = masterId.length;
        int count = masterpieceMapper.deleteMasterpieces(masterId);
        if (size == count){
            message = OperationUtils.getMessage(true);
            return true;
        }else
            message = OperationUtils.getMessage(false);
            return false;
    }

    /**
     * 判断是否存在该作品
     * @param masterId
     * @return
     */
    @Override
    public boolean findMasterpiece(String masterId) {
        Masterpiece masterpiece = masterpieceMapper.findMasterpiece(masterId);
        if (masterpiece != null){
            return true;
        }else
        return false;
    }

    /**
     * 输入验证
     * @param masterpiece
     * @return
     */
    public boolean isValidate(Masterpiece masterpiece){
        if (masterpiece.getMasterId() == null || masterpiece.getMasterId().equals("")){
            message = "作品编号不能为空,请输入1-4位数字";
            return false;
        }
        if (masterpiece.getMasterDesc() == null || masterpiece.getMasterDesc().equals("")){
            message = "作品简介不能为空";
            return false;
        }
        if (!masterpiece.getMasterId().matches("\\d{1,4}")){
            message = "作品编号为1-4为数字";
            return false;
        }
        return true;
    }

    /**
     * 获取图片路径
     * @param masterId
     * @return
     */
    public String findImagePath(String masterId){
        Masterpiece masterpiece = masterpieceMapper.findMasterpiece(masterId);
        if (masterpiece != null){
            return masterpiece.getImagePath();
        }
        return "";
    }
}
