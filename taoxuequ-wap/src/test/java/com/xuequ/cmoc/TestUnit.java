package com.xuequ.cmoc;

import java.util.Date;

import com.xuequ.cmoc.utils.MD5Util;

public class TestUnit {

	public static void main(String[] args) {
//		Date now = new Date();
//		int hours = now.getHours();
//		int minutes = now.getMinutes();
//        if(hours >= 9 && hours < 18) {
//        	System.out.println("success");
//        }else {
//        	System.out.println("非常抱歉，我们的客服工作时间是：9:00-18:00！别担心，工作时间内我们客服会主动联系您。谢谢您的理解");
//        }
		System.out.println(MD5Util.md5("sibotiyan2016"));
	}

}
