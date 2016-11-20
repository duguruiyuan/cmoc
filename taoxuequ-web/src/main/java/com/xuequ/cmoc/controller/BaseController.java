package com.xuequ.cmoc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
* <p>Title: BaseController </p>
* <p>Description: </p>
* @author pei.wang
* @date 2016年2月25日 下午2:38:10
*/
public class BaseController {
	@Autowired
	protected HttpSession session;

	@Autowired
	protected HttpServletRequest request;
	
	/**
	 * 视图数据绑定模型前将字符串类型时间转型为日期格式
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
