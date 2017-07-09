package com.geek.model;

import org.apache.ibatis.type.Alias;

@Alias("learn")
public class Learn {

    private String learnId;//学习资源编号
    private String learnName;//学习资源名称
    private String learnType;//学习资源类型(书籍|博客|网站)
    private String learnDesc;//学习资源描述
    private String imagePath;//学习资源图片名
    private String learnLink;//学习资源链接



    public Learn() {
    }

    public Learn(String learnId, String learnName, String learnType, String learnDesc, String imagePath, String learnLink) {
        this.learnId = learnId;
        this.learnName = learnName;
        this.learnType = learnType;
        this.learnDesc = learnDesc;
        this.imagePath = imagePath;
        this.learnLink = learnLink;
    }

    public String getLearnLink() {
        return learnLink;
    }

    public void setLearnLink(String learnLink) {
        this.learnLink = learnLink;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLearnId() {
        return learnId;
    }

    public void setLearnId(String learnId) {
        this.learnId = learnId;
    }

    public String getLearnName() {
        return learnName;
    }

    public void setLearnName(String learnName) {
        this.learnName = learnName;
    }

    public String getLearnType() {
        return learnType;
    }

    public void setLearnType(String learnType) {
        this.learnType = learnType;
    }

    public String getLearnDesc() {
        return learnDesc;
    }

    public void setLearnDesc(String learnDesc) {
        this.learnDesc = learnDesc;
    }
}
