package com.xuequ.cmoc.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.enums.PayType;
import com.xuequ.cmoc.common.enums.ProductTypeEnum;
import com.xuequ.cmoc.common.enums.SignResource;
import com.xuequ.cmoc.common.enums.TradeType;
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
import com.xuequ.cmoc.view.CourseGroupOrderView;
import com.xuequ.cmoc.view.CourseListView;
import com.xuequ.cmoc.view.CourseSchduleActivityView;

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
	public ProductOrder addUPdateOrder(CourseSignVO info) {
		ProductOrder productOrder = null;
		if(StringUtils.isNotBlank(info.getOrderNo())) {
			productOrder = productOrderMapper.selectByOrderNo(info.getOrderNo());
			if(!(productOrder != null && productOrder.getOpenid().equals(info.getOpenid()))) {
				productOrder = null;
			}
		}
		CourseInfo courseInfo = courseInfoMapper.selectByPrimaryKey(info.getProductId());
		if(productOrder == null) {
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
				parentInfo.setCity(info.getCity());
				parentInfo.setHeadImg(info.getHeadImg());
				parentInfo.setNickName(info.getNickName());
				parentInfoMapper.insertSelective(parentInfo);
			}else {
				parentInfo.setRelation(info.getSignRelation());
				parentInfo.setCity(info.getCity());
				parentInfo.setHeadImg(info.getHeadImg());
				parentInfoMapper.updateByPrimaryKeySelective(parentInfo);
			}
			productOrder = new ProductOrder();
			productOrder.setSignName(info.getEmerName());
			productOrder.setSignPhone(info.getEmerMobile());
			productOrder.setOpenid(info.getOpenid());
			productOrder.setOrderNo(StringUtil.getCourseOrderNum(parentInfo.getId()));
			productOrder.setResAmount(courseInfo.getResAmount());
			productOrder.setTotalAmount(courseInfo.getTotalPrice());
			productOrder.setCustId(parentInfo.getId());
			productOrder.setProductId(courseInfo.getId());
			productOrder.setProductType(ProductTypeEnum.COURSE.getCode());
			productOrder.setChannel(info.getChannel());
			productOrder.setActivityId(info.getActivityId());
			productOrder.setPaySubmitTime(new Date());
			if(StringUtils.isNotBlank(info.getChannel()) && info.getChannel().equals(PayType.WEIXIN.getCode())) {
				productOrder.setTradeType(TradeType.JSAPI.getCode());
			}
			productOrderMapper.insertSelective(productOrder);
			info.setParentId(parentInfo.getId());
			info.setFamilyNo(familyNo);
			info.setSignResource(SignResource.ONLINE.getCode());
			info.setProductId(courseInfo.getId());
			info.setOrderNo(productOrder.getOrderNo());
			if(courseInfo.getSignWay() == 0) {
				childSignInfoMapper.insertSelective(info);
			}
		}else {
			productOrder.setSignName(info.getEmerName());
			productOrder.setSignPhone(info.getEmerMobile());
			if(StringUtils.isNotBlank(info.getChannel()) && info.getChannel().equals(PayType.WEIXIN.getCode())) {
				productOrder.setTradeType(TradeType.JSAPI.getCode());
			}
			productOrder.setUpdateTime(new Date());
			productOrderMapper.updateByPrimaryKeySelective(productOrder);
		}
		productOrder.setProductName(courseInfo.getCourseName());
		return productOrder;
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

	@Override
	public List<CourseSchduleActivityView> selectScheduActivityInfoByCourseId(Integer courseId) {
		return courseInfoMapper.selectScheduActivityInfoByCourseId(courseId);
	}

	@Override
	public List<CourseGroupOrderView> selectCourseGroupOrder(String openid, String orderNo) {
		return courseInfoMapper.selectCourseGroupOrder(openid, orderNo);
	}

}
