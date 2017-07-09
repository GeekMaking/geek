package com.geek.utils;

import java.util.HashMap;
import java.util.Map;

public class ReturnUtils {

    public static Map getReturnMap(boolean flag,String message,Object object){
        Map map = new HashMap();
        map.put("flag",flag);
        map.put("message",message);
        map.put("object",object);
        return map;
    }

}
