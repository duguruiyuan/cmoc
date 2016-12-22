package com.xuequ.cmoc.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.enums.ProductTypeEnum;
import com.xuequ.cmoc.common.enums.SignResource;
import com.xuequ.cmoc.dao.ChildSignInfoMapper;
import com.xuequ.cmoc.dao.CourseInfoMapper;
import com.xuequ.cmoc.dao.ParentInfoMapper;
import com.xuequ.cmoc.dao.ProductOrderMapper;
import com.xuequ.cmoc.dao.SysCommonMapper;
import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.CourseSignVO;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.utils.StringUtil;
import com.xuequ.cmoc.view.ChildSignView;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.view.CourseListView;

@Service("courseService")
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseInfoMapper courseInfoMapper;
	@Autowired
	private ParentInfoMapper parentInfoMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private ChildSignInfoMapper childSignInfoMapper;
	@Autowired
	private SysCommonMapper sysCommonMapper;
	
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
	public List<CourseInfo> selectShelvesList() {
		return courseInfoMapper.selectShelvesList();
	}

	@Override
	public List<CourseListView> selectShelvesSignByPage(Page<CourseListView> page) {
		return courseInfoMapper.selectShelvesSignByPage(page);
	}

	@Override
	public CourseListView selectCourseDetail(Integer id) {
		return courseInfoMapper.selectDetailById(id);
	}

	@Override
	public List<CourseBuyerView> selectCourseBuyerByPage(Page<CourseBuyerView> page) {
		return parentInfoMapper.selectCourseBuyerByPage(page);
	}

	@Override
	public ParentInfo selectByOpenid(String openid) {
		return parentInfoMapper.selectByOpenid(openid);
	}

	@Override
	public CourseSignVO addUPdateOrder(CourseSignVO info) {
		String familyNo = childSignInfoMapper.selectFamilyNo(info);
		if(StringUtils.isBlank(familyNo)) {
			familyNo = sysCommonMapper.selectFamilyNoSeq();
		}
		ParentInfo parentInfo = parentInfoMapper.selectByOpenid(info.getOpenid());
		if(parentInfo == null) {
			parentInfo = new ParentInfo();
			parentInfo.setOpenid(info.getOpenid());
			parentInfo.setParentMobile(info.getEmerMobile());
			parentInfo.setParentName(info.getEmerName());
			parentInfo.setRelation(info.getSignRelation());
			parentInfo.setFamilyNo(familyNo);
			parentInfoMapper.insertSelective(parentInfo);
		}else {
			parentInfo.setParentName(info.getEmerName());
			parentInfo.setParentMobile(info.getEmerMobile());
			parentInfo.setRelation(info.getSignRelation());
			parentInfoMapper.updateByPrimaryKeySelective(parentInfo);
		}
		CourseInfo courseInfo = courseInfoMapper.selectByPrimaryKey(info.getProductId());
		ProductOrder order = new ProductOrder();
		order.setOrderNo(StringUtil.getCourseOrderNum(parentInfo.getId()));
		order.setResAmount(courseInfo.getResAmount());
		order.setCustId(parentInfo.getId());
		order.setProductId(courseInfo.getId());
		order.setProductType(ProductTypeEnum.COURSE.getCode());
		productOrderMapper.insertSelective(order);
		info.setParentId(parentInfo.getId());
		info.setFamilyNo(familyNo);
		info.setSignResource(SignResource.ONLINE.getCode());
		info.setProductId(courseInfo.getId());
		info.setOrderNo(order.getOrderNo());
		childSignInfoMapper.insertSelective(info);
		info.setProductType(order.getProductType());
		return info;
	}

	@Override
	public List<CourseBuyerView> selectBuyRecordByPage(Page<CourseBuyerView> page) {
		return parentInfoMapper.selectBuyRecordByPage(page);
	}

	@Override
	public List<ChildSignView> selectCourseSignByPage(Page<ChildSignView> page) {
		return childSignInfoMapper.selectCourseSignByPage(page);
	}

	@Override
	public int updateChildSignInfo(ChildSignInfo info) {
		return childSignInfoMapper.updateByPrimaryKeySelective(info);
	}

	@Override
	public ChildSignInfo selectChildSignById(Integer id) {
		return childSignInfoMapper.selectByPrimaryKey(id);
	}

}
