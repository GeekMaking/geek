package com.geek.utils;

public class OperationUtils {

    public static String getMessage(boolean flag){
        String message;
        if (flag){
            message = "批量删除成功";
        }else {
            message = "批量删除失败";
        }
        return message;
    }
}
