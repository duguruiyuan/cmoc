package com.xuequ.cmoc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * 日期处理工具类
 * </pre>
 *
 * @author zhangshihai
 * @version $Id: DateUtil.java, 2016年3月27日 下午3:39:04 zhangshihai Exp $
 */
public class DateUtil {
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_DATE_FORMAT1 = "yyyy-MM-dd HH:mm";
	public static final String DEFAULT_DATE_FORMAT2 = "yyyy年MM月dd日 HH:mm";
	public static final String DEFAULT_DATE_FORMAT3="yyyyMMddHHmmss";
	public static final String DATE_FORMAT = "yyyyMMdd";
	public static final String DATE_FORMAT1 = "yyyy/MM/dd";
	public static final String DATE_MD_FORMAT = "MM月dd日";
	
	/**
	 * 
	 * <pre>
	 * 获取两个日期(yyyyMMddHHmmss格式)相隔月数，如果在同一个月，则返回1
	 * </pre>
	 *
	 * @author zhangshihai
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static int getMonthSpace(String date1, String date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));
		
		int result = 0;
		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		
		return result == 0 ? 1 : Math.abs(result);
	}
	
	public static String getDate(Date date) {
		if(date != null) {
			return new SimpleDateFormat(DATE_FORMAT).format(date);
		}
		return null;
	}
	
	public static Integer getYear(Date date) {
		if(date == null) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	public static Integer getMonth(Date date) {
		if(date == null) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	public static Date strToDate(String strDate, String pattern) {
		try {
			return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(  
			        pattern).parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return null;
	}
	
	public static String dateToStr(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 两个时间比较
	 * date1 > date2 = 1
	 * date1 = date2 = 0
	 * date1 < date2 = -1
	 * @auther 胡启萌
	 * @Date 2016年11月20日
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compare(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime()) {
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            return -1;
        } else {
            return 0;
        }
	}
	
	public static String addDayDateToStr(Date date, int day, String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return new SimpleDateFormat(pattern).format(calendar.getTime());
	}
	
	public static void main(String[] args) {
		System.out.println(addDayDateToStr(new Date(), 3, DateUtil.DEFAULT_DATE_FORMAT3));
	}
}
