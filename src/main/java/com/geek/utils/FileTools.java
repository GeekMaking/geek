package com.geek.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class FileTools {
    private String message;     //信息

    //private final String realDir = "E:"+File.separator+"picture" + File.separator + "geek";
    private final String realDir = "/srv/www/geek";
    private final String virtualDir = File.separator + "file"; //虚拟磁盘目录
    private String extension;   //文件后缀

    private String fileName;    //文件名
    private String className;   //类名
    private long timeStamp;     //时间戳

    private String realPath;    //真实路径
    private String virtualPath; //虚拟路径

    /**
     * 将文件存入磁盘中
     *
     * @param file      文件
     * @param clazzName 全类名
     * @param id        主码
     * @return
     */
    public boolean saveImage(MultipartFile file, String clazzName, String id) {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        if (file.getSize() > 5242880) {
            message = "文件过大,请重新选择";
            return false;
        } else {
            timeStamp = System.currentTimeMillis();
            className = clazzName;
            fileName = file.getOriginalFilename();
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);

            createFilePath(id);

            File file1 = new File(getRealPath());   //创建文件
            try {
                fileOutputStream = new FileOutputStream(file1);
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                bufferedOutputStream.write(file.getBytes());
                if (file1.exists()) {
                    return true;
                } else
                    return false;
            } catch (FileNotFoundException e) {
                message = "存储图片出现异常";
                e.printStackTrace();
            } catch (IOException e) {
                message = "存储图片出现异常";
                e.printStackTrace();
            }finally {
                if (bufferedOutputStream != null){
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
    /**
     * 更新图片
     *
     * @param image
     * @param oldImagePath
     * @param clazzName
     * @param id
     * @return
     */
    public boolean updateImage(MultipartFile image, String oldImagePath, String clazzName, String id){
        //先删除原图片
        deleteImage(oldImagePath);
        //存新图片
        boolean flag = saveImage(image,clazzName,id);
        return flag;
    }

    /**
     * 删除图片
     * @param imagePath
     */
    public void deleteImage(String imagePath){
        if (imagePath != null && !imagePath.equals("")){
            File file = new File(imagePath.replace(virtualDir,realDir));
            if (file.exists()) {
                try {
                    FileUtils.forceDelete(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 批量删除图片
     * @param imagePaths
     */
    public void deleteImages(List<String> imagePaths){
        for (String imagePath:imagePaths){
            deleteImage(imagePath);
        }
    }

    /**
     * 返回操作消息
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 创建文件路径
     *
     * @param id
     */
    public void createFilePath(String id) {
        realPath = realDir + File.separator + className + "_" + timeStamp + "_" + id + "." + extension;
        virtualPath = virtualDir + File.separator + className + "_" + timeStamp + "_" + id + "." + extension;
    }

    /**
     * 返回文件的虚拟路径
     *
     * @return
     */
    public String getVirtualPath() {
        return virtualPath;
    }

    /**
     * 返回文件的真实路径
     *
     * @return
     */
    public String getRealPath() {
        return realPath;
    }
}
