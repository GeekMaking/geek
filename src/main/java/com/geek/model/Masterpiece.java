package com.geek.model;

import org.apache.ibatis.type.Alias;

@Alias("masterpiece")
public class Masterpiece {

    private String masterId;//作品编号
    private String masterName;//作品名称
    private String masterDesc;//作品简介
    private String imagePath;//作品图片名
    private String masterLink;//作品链接

    public Masterpiece() {}

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterDesc() {
        return masterDesc;
    }

    public void setMasterDesc(String masterDesc) {
        this.masterDesc = masterDesc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMasterLink() {
        return masterLink;
    }

    public void setMasterLink(String masterLink) {
        this.masterLink = masterLink;
    }
}
