package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.CourseBuyerInfo;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.view.CourseListView;

public interface ICourseService {

	List<CourseInfo> selectListByPage(Page<CourseInfo> page);
	
	CourseInfo selectByPrimaryKey(Integer id);
	
	int addAndUpdateCourse(CourseInfo vo, SysUser user);
	
	int updateShelves(Integer shelves, Integer id);
	
	CourseListView selectCourseDetail(Integer id);
	
	/**
	 * 查询上架课程
	 * @auther 胡启萌
	 * @Date 2016年12月10日
	 * @return
	 */
	List<CourseListView> selectShelvesList();
	/**
	 * 分页查询课程购买人信息
	 * @auther 胡启萌
	 * @Date 2016年12月10日
	 * @param page
	 * @return
	 */
	List<CourseBuyerView> selectCourseBuyerByPage(Page<CourseBuyerView> page);
	/**
	 * 分页查询课程购买记录
	 * @auther 胡启萌
	 * @Date 2016年12月13日
	 * @param page
	 * @return
	 */
	List<CourseBuyerView> selectBuyRecordByPage(Page<CourseBuyerView> page);
	/**
	 * 根据openid查询课程购买人信息
	 * @auther 胡启萌
	 * @Date 2016年12月10日
	 * @param openid
	 * @return
	 */
	CourseBuyerInfo selectByOpenid(String openid);
	/**
	 * 新增修改课程购买客户信息
	 * @auther 胡启萌
	 * @Date 2016年12月10日
	 * @param info
	 * @return
	 */
	CourseBuyerView addUPdateOrder(CourseBuyerView info);
}
