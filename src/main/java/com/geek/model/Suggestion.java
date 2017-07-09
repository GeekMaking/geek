package com.geek.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("suggestion")
public class Suggestion {

    private Date time;  //意见时间
    private String phone;//意见人电话
    private String view;//意见人观点

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
