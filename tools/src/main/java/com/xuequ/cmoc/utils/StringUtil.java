package com.xuequ.cmoc.utils;

import java.util.Collection;
import java.util.Date;

public class StringUtil {
	
	public static boolean isNullOrEmpty(Collection c){
        return c==null||c.isEmpty();
    }

	public static String getCourseOrderNum(Integer userId) {
		Date date = new Date();
		String dateTime = date.getTime()+"";
		String str = 1 + DateUtil.dateToStr(date,"MMdd") + dateTime.substring(dateTime.length()-8, dateTime.length()) + userId;
		return str;
	}
}
