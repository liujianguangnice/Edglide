package com.ed.edglide.common;

import java.util.List;

public class StringUtil {
    public static String getStringFromList(List<String> list){

        StringBuffer sb = new StringBuffer();
        for(String s:list){
            sb.append(s).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
