package com.xuequ.cmoc.core.wechat.utils;

import java.io.Writer;
import java.lang.reflect.Field;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.xuequ.cmoc.core.wechat.message.ArticleItem;
import com.xuequ.cmoc.core.wechat.message.XStreamCDATA;
import com.xuequ.cmoc.core.wechat.message.XStreamSub;

public class SerializeXmlUtil {

	public static XStream createXstream() {  
        return new XStream(new XppDriver() {  
            @Override  
            public HierarchicalStreamWriter createWriter(Writer out) {  
                return new PrettyPrintWriter(out) {  
                    boolean cdata = false;  
                    Class<?> targetClass = null;
                    Class<?> subTargetClass = null;
  
					@Override  
                    public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {  
                        super.startNode(name, clazz);  
                        // 业务处理，对于用XStreamCDATA标记的Field，需要加上CDATA标签  
                        if (!name.equals("xml")) {  
                        	if(XStreamSub.class.isAssignableFrom(clazz)) {
                        		subTargetClass = clazz;
                        	}
                        	if(isExistsField(subTargetClass, name)) {
                        		cdata = needCDATA(subTargetClass, name);
                        	}else {
                        		cdata = needCDATA(targetClass, name);
							}
                        } else {  
                            targetClass = clazz;  
                        }  
                    }  
  
                    @Override  
                    protected void writeText(QuickWriter writer, String text) {  
                        if (cdata) {  
                            writer.write("<![CDATA[");  
                            writer.write(text);  
                            writer.write("]]>");  
                        } else {  
                            writer.write(text);  
                        }  
                    }  
                };  
            }  
        });  
    }  
	
	private static boolean isExistsField(Class<?> clazz, String fieldAlias) {
		if(clazz == null) return false;
		Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {  
        	if(fieldAlias.equals(field.getName())) {
        		return true;
        	}
        }
        return false;
	}
  
    private static boolean needCDATA(Class<?> targetClass, String fieldAlias) {  
        boolean cdata = false;  
        // first, scan self  
        cdata = existsCDATA(targetClass, fieldAlias);  
        if (cdata)  
            return cdata;  
        // if cdata is false, scan supperClass until java.lang.Object  
        Class<?> superClass = targetClass.getSuperclass();  
        while (!superClass.equals(Object.class)) {  
            cdata = existsCDATA(superClass, fieldAlias);  
            if (cdata)  
                return cdata;  
            superClass = superClass.getClass().getSuperclass();  
        }  
        return false;  
    }  
  
    private static boolean existsCDATA(Class<?> clazz, String fieldAlias) {  
        if ("MediaId".equals(fieldAlias)) {  
            return true; // 特例添加 morning99  
        }  
        // scan fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {  
            // 1. exists XStreamCDATA  
            if (field.getAnnotation(XStreamCDATA.class) != null) {  
                XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);  
                // 2. exists XStreamAlias  
                if (null != xStreamAlias) {  
                    if (fieldAlias.equals(xStreamAlias.value()))// matched  
                        return true;  
                } else {// not exists XStreamAlias  
                    if (fieldAlias.equals(field.getName()))  
                        return true;  
                }  
            }  
        }
        return false;  
    }  
    
}
