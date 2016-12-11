package com.xuequ.cmoc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.enums.ProductTypeEnum;
import com.xuequ.cmoc.dao.CourseBuyerInfoMapper;
import com.xuequ.cmoc.dao.CourseInfoMapper;
import com.xuequ.cmoc.model.CourseBuyerInfo;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.utils.BeanUtils;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.StringUtil;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.view.CourseListView;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.dao.ProductOrderMapper;

@Service("courseService")
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseInfoMapper courseInfoMapper;
	@Autowired
	private CourseBuyerInfoMapper courseBuyerInfoMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	
	@Override
	public List<CourseInfo> selectListByPage(Page<CourseInfo> page) {
		return courseInfoMapper.selectListByPage(page);
	}

	@Override
	public CourseInfo selectByPrimaryKey(Integer id) {
		return courseInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addAndUpdateCourse(CourseInfo vo, SysUser user) {
		if(vo.getId() == null) {
			vo.setCreaterId(user.getIdUser());
			vo.setCreater(user.getUserName());
			vo.setCreateTime(new Date());
			courseInfoMapper.insertSelective(vo);
		}else {
			vo.setUpdaterId(user.getIdUser());
			vo.setUpdater(user.getUserName());
			vo.setUpdateTime(new Date());
			courseInfoMapper.updateByPrimaryKeyWithBLOBs(vo);
		}
		return 1;
	}

	@Override
	public int updateShelves(Integer shelves, Integer id) {
		if(shelves == -1) {
			return courseInfoMapper.delCourse(id);
		}
		return courseInfoMapper.updateShelves(shelves, id);
	}

	@Override
	public List<CourseListView> selectShelvesList() {
		return courseInfoMapper.selectShelvesList();
	}

	@Override
	public CourseListView selectCourseDetail(Integer id) {
		return courseInfoMapper.selectDetailById(id);
	}

	@Override
	public List<CourseBuyerView> selectCourseBuyerByPage(Page<CourseBuyerView> page) {
		return courseBuyerInfoMapper.selectCourseBuyerByPage(page);
	}

	@Override
	public CourseBuyerInfo selectByOpenid(String openid) {
		return courseBuyerInfoMapper.selectByOpenid(openid);
	}

	@Override
	public CourseBuyerView addUPdateOrder(CourseBuyerView info) {
		CourseBuyerView buyerInfo = courseBuyerInfoMapper.selectRemindOrder(info.getMobile(), 
				info.getOpenid(), info.getCourseId());
		if(buyerInfo == null) {
			CourseInfo courseInfo = courseInfoMapper.selectByPrimaryKey(info.getCourseId());
			info.setCreater(Const.SYS_USER);
			info.setCreateTime(new Date());
			courseBuyerInfoMapper.insertSelective(info);
			ProductOrder order = new ProductOrder();
			order.setOrderNo(StringUtil.getCourseOrderNum(info.getId()));
			order.setResAmount(courseInfo.getResAmount());
			order.setCustId(info.getId());
			order.setProductId(courseInfo.getId());
			order.setProductType(ProductTypeEnum.COURSE.getCode());
			order.setCreater(Const.SYS_USER);
			productOrderMapper.insertSelective(order);
			info.setProductType(order.getProductType());
			info.setOrderNo(order.getOrderNo());
		}else {
			info.setId(buyerInfo.getId());
			courseBuyerInfoMapper.updateByPrimaryKeySelective(info);
			return buyerInfo;
		}
		return info;
	}

}
