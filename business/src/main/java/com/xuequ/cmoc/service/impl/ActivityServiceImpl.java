package com.xuequ.cmoc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.SignResource;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.dao.ActivityChildMapper;
import com.xuequ.cmoc.dao.ActivityHmSignMapper;
import com.xuequ.cmoc.dao.ActivityInfoMapper;
import com.xuequ.cmoc.dao.ActivityMarinesMapper;
import com.xuequ.cmoc.dao.ActivityTeacherMapper;
import com.xuequ.cmoc.dao.ChildSignInfoMapper;
import com.xuequ.cmoc.dao.HollowManInfoMapper;
import com.xuequ.cmoc.dao.SysCommonMapper;
import com.xuequ.cmoc.model.ActivityChild;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.model.ActivityTeacher;
import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.ActivityNamelistVO;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.utils.BeanUtils;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.view.ActivityInfoView;
import com.xuequ.cmoc.view.ActivityResourceTypeView;

@Service("activityService")
public class ActivityServiceImpl implements IActivityService {

	@Autowired
	private ActivityInfoMapper activityInfoMapper;
	@Autowired
	private ActivityMarinesMapper activityMarinesMapper;
	@Autowired
	private ActivityTeacherMapper activityTeacherMapper;
	@Autowired
	private ActivityChildMapper activityChildMapper;
	@Autowired
	private ChildSignInfoMapper childSignInfoMapper;
	@Autowired
	private SysCommonMapper sysCommonMapper;
	
	@Override
	public List<ActivityInfoView> selectListByPage(Page<ActivityInfoView> page) {
		return activityInfoMapper.selectListByPage(page);
	}

	@Override
	public ActivityInfo selectById(Integer id) {
		return activityInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void addAndUpdateActivity(ActivityInfo vo, SysUser sysUser) {
		vo.setYear(DateUtil.getYear(vo.getStartDate()));
		vo.setMonth(DateUtil.getMonth(vo.getStartDate()));
		if(vo.getId() == null) {
			vo.setCreaterUserId(sysUser.getIdUser());
			vo.setCreater(sysUser.getUserName());
			vo.setCreateTime(new Date());
			activityInfoMapper.insertSelective(vo);
		}else {
			vo.setUpdaterUserId(sysUser.getIdUser());
			vo.setUpdater(sysUser.getUserName());
			vo.setUpdaterTime(new Date());
			activityInfoMapper.updateByPrimaryKeySelective(vo);
		}
	}

	@Override
	public RspResult addImportActivityNamelist(List<ActivityNamelistVO> list, SysUser user) {
		ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(list.get(0).getActivityId());
		if(activityInfo == null) {
			return new RspResult(StatusEnum.ACTIVITY_NOT_EXISTS);
		}
		if(activityInfo.getIsDelete().equals("Y")) {
			return new RspResult(StatusEnum.ACTIVITY_INVALID);
		}
		if(activityInfo.getEndDate() != null && 
				DateUtil.compare(new Date(), activityInfo.getEndDate()) >= 0) {
			return new RspResult(StatusEnum.ACTIVITY_OVER);
		}
		ChildSignInfo signInfo = new ChildSignInfo();
		signInfo.setSignResource(SignResource.ONLINE.getCode());
		signInfo.setProductId(activityInfo.getProductId());
		List<ChildSignInfo> onlineSignList = childSignInfoMapper.selectNonStartingList(signInfo);
		List<ActivityChild> childList = new ArrayList<>();
		List<ActivityTeacher> teacherList = new ArrayList<>();
		List<ActivityMarines> marinesList = new ArrayList<>();
		for(ActivityNamelistVO vo : list) {
			if(StringUtils.isNotBlank(vo.getTeacherMobile())) {
				ActivityTeacher teacher = new ActivityTeacher();
				teacher.setActivityId(activityInfo.getId());
				teacher.setName(vo.getTeatherName());
				teacher.setMobile(vo.getTeacherMobile());
				teacher.setCreater(user.getUserName());
				teacher.setCreaterUserId(user.getIdUser());
				teacherList.add(teacher);
			}
			boolean isNew = true;
			for(ActivityMarines marines : marinesList) {
				if(marines.getMarineName().equals(vo.getMarineName())) {
					isNew = false;
					break;
				}
			}
			if(isNew) {
				ActivityMarines marines = new ActivityMarines();
				marines.setActivityId(activityInfo.getId());
				marines.setActivityName(activityInfo.getActivityName());
				marines.setMarineName(vo.getMarineName());
				marines.setCreater(user.getUserName());
				marines.setCreaterUserId(user.getIdUser());
				marinesList.add(marines);
			}
			ChildSignInfo childSign = BeanUtils.copyAs(vo, ChildSignInfo.class);
			childSign.setProductId(activityInfo.getProductId());
			boolean isSchool = true;
			for(ChildSignInfo info : onlineSignList) {
				if(info.getChildIdcard().equals(vo.getChildIdcard()) |
						info.getEmerMobile().equals(vo.getEmerMobile())) {
					isSchool = false;
					childSign.setId(info.getId());
					break;
				}
			}
			if(isSchool) {
				childSign.setStatus("002");
				childSign.setSignResource(SignResource.SCHOOL.getCode());
				childSign.setIsEffect(1);
				String familyNo = childSignInfoMapper.selectFamilyNo(childSign);
				if(StringUtils.isBlank(familyNo)) {
					familyNo = sysCommonMapper.selectFamilyNoSeq();
				}
				childSign.setFamilyNo(familyNo);
				childSignInfoMapper.insertSelective(childSign);
			}
			ActivityChild child = new ActivityChild();
			child.setActivityId(activityInfo.getId());
			child.setMarineName(vo.getMarineName());
			child.setCreater(user.getUserName());
			child.setCreaterUserId(user.getIdUser());
			child.setChildId(childSign.getId());
			childList.add(child);
		}
		for(ActivityMarines marines : marinesList) {
			activityMarinesMapper.insertSelective(marines);
		}
		for(ActivityTeacher teacher : teacherList) {
			for(ActivityMarines marines : marinesList) {
				if(marines.getMarineName().equals(teacher.getMarineName())) {
					teacher.setMarineId(marines.getId());
					break;
				}
			}
			activityTeacherMapper.insertSelective(teacher);
		}
		for(ActivityChild child : childList) {
			for(ActivityMarines marines : marinesList) {
				if(marines.getMarineName().equals(child.getMarineName())) {
					child.setMarineId(marines.getId());
					break;
				}
			}
			activityChildMapper.insertSelective(child);
		}
		return new RspResult(StatusEnum.SUCCESS);
	}

	@Override
	public List<ActivityInfo> selectListByParam(Page<ActivityInfo> page) {
		return activityInfoMapper.selectListByParam(page);
	}

	@Override
	public ActivityResourceTypeView selectForUpload1(Integer activityId, String marineName, String childName) {
		return activityInfoMapper.selectForUpload1(activityId, marineName, childName);
	}

	@Override
	public ActivityResourceTypeView selectForUpload(Integer activityId, String name) {
		return activityInfoMapper.selectForUpload(activityId, name);
	}

	@Override
	public ActivityInfo selectByPrimaryKey(Integer id) {
		return activityInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateActivityImg(String activityImg, Integer id) {
		return activityInfoMapper.updateActivityImg(activityImg, id);
	}

	@Override
	public List<ActivityInfo> selectScheduActivity(ActivityInfo info) {
		return activityInfoMapper.selectScheduActivity(info);
	}

	@Override
	public List<ActivityInfo> selectBeginingActivity(ActivityInfo info) {
		return activityInfoMapper.selectBeginingActivity(info);
	}

	@Override
	public int updateByPrimaryKey(ActivityInfo info) {
		return activityInfoMapper.updateByPrimaryKeySelective(info);
	}

}
