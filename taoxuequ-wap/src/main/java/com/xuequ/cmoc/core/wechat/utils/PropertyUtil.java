/*package com.xuequ.cmoc.core.wechat.utils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropertyUtil {
	private static Properties prop = new Properties();  
    static{  
        try {  
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("template.properties"));  
            //转码处理  
            Set<Object> keyset = prop.keySet();  
            Iterator<Object> iter = keyset.iterator();  
            while (iter.hasNext()) {  
                String key = (String) iter.next();  
                String newValue = null;  
                try {
                    //属性配置文件自身的编码  
                    String propertiesFileEncode = "utf-8";  
                    newValue = new String(prop.getProperty(key).getBytes("ISO-8859-1"),propertiesFileEncode);  
                } catch (UnsupportedEncodingException e) {  
                    newValue = prop.getProperty(key);  
                }  
                prop.setProperty(key, newValue);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();
        }  
    } 
}
*/