package com.hugo.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ohj
 * @create 2017-10-19 14:22
 **/
public class StringUtil {
    public static String getSubString(String content,int length){
        if(StringUtils.isNotBlank(content)&&content.length()>length){
            return content.substring(0,length)+"......";
        }else
            return content;
    }
}
