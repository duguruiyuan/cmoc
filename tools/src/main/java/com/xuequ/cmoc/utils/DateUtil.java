package com.xuequ.cmoc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <pre>
 * 日期处理工具类
 * </pre>
 *
 * @author zhangshihai
 * @version $Id: DateUtil.java, 2016年3月27日 下午3:39:04 zhangshihai Exp $
 */
public class DateUtil {
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
}
