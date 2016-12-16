package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.CourseSignVO;
import com.xuequ.cmoc.view.ChildSignView;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.view.CourseListView;

public interface ICourseService {

	List<CourseInfo> selectListByPage(Page<CourseInfo> page);
	
	CourseInfo selectByPrimaryKey(Integer id);
	
	int addAndUpdateCourse(CourseInfo vo, SysUser user);
	
	int updateShelves(Integer shelves, Integer id);
	
	CourseListView selectCourseDetail(Integer id);
	
	/**
	 * 查询上架课程报名情况
	 * @auther 胡启萌
	 * @Date 2016年12月10日
	 * @return
	 */
	List<CourseListView> selectShelvesSignList();
	/**
	 * 查询上架课程
	 * @auther 胡启萌
	 * @Date 2016年12月10日
	 * @return
	 */
	List<CourseInfo> selectShelvesList();
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
	ParentInfo selectByOpenid(String openid);
	/**
	 * 新增修改课程购买客户信息
	 * @auther 胡启萌
	 * @Date 2016年12月10日
	 * @param info
	 * @return
	 */
	CourseSignVO addUPdateOrder(CourseSignVO info);
	/**
	 * 课程预约记录查询
	 * @param page
	 * @return
	 */
	List<ChildSignView> selectCourseSignByPage(Page<ChildSignView> page);
	
	int updateChildSignInfo(ChildSignInfo info);
	
	ChildSignInfo selectChildSignById(Integer id);
}
