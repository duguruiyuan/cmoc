package com.xuequ.cmoc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.dao.ActivityChildMapper;
import com.xuequ.cmoc.dao.ActivityFamilyMapper;
import com.xuequ.cmoc.dao.ActivityHmSignMapper;
import com.xuequ.cmoc.dao.ActivityInfoMapper;
import com.xuequ.cmoc.dao.ActivityMarinesMapper;
import com.xuequ.cmoc.dao.ActivityTeacherMapper;
import com.xuequ.cmoc.dao.HollowManInfoMapper;
import com.xuequ.cmoc.model.ActivityFamily;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.model.ActivityTeacher;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.ActivityNamelistVO;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.utils.BeanUtils;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.view.ActivityHmSignView;
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
	private ActivityHmSignMapper activityHmSignMapper;
	@Autowired
	private ActivityChildMapper activityChildMapper;
	@Autowired
	private HollowManInfoMapper hollowManInfoMapper;
	
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
		List<ActivityFamily> familyList = new ArrayList<>();
		List<ActivityHmSign> hmSignList = new ArrayList<>();
		List<ActivityTeacher> teacherList = new ArrayList<>();
		List<ActivityMarines> marinesList = new ArrayList<>();
		for(ActivityNamelistVO vo : list) {
			if(StringUtils.isNotBlank(vo.getTeacherMobile())) {
				ActivityTeacher teacher = new ActivityTeacher();
				teacher.setActivityId(activityInfo.getId());
				teacher.setActivityName(activityInfo.getActivityName());
				teacher.setMarineName(vo.getMarineName());
				teacher.setName(vo.getTeatherName());
				teacher.setMobile(vo.getTeacherMobile());
				teacher.setCreater(user.getUserName());
				teacher.setCreaterUserId(user.getIdUser());
				teacherList.add(teacher);
			}
			if(StringUtils.isNotBlank(vo.getHmMobile())) {
				ActivityHmSign hmSign = new ActivityHmSign();
				hmSign.setActivityId(activityInfo.getId());
				hmSign.setActivityName(activityInfo.getActivityName());
				hmSign.setMarineName(vo.getMarineName());
				hmSign.setHmMobile(vo.getHmMobile());
				hmSign.setHmName(vo.getHmName());
				hmSign.setSignDate(DateUtil.getDate(new Date()));
				hmSign.setIsEffect(1);
				hmSign.setEffectDate(DateUtil.getDate(new Date()));
				hmSign.setCreater(user.getUserName());
				hmSign.setCreaterUserId(user.getIdUser());
				hmSignList.add(hmSign);
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
			ActivityFamily family = BeanUtils.copyAs(vo, ActivityFamily.class);
			family.setActivityId(activityInfo.getId());
			family.setActivityName(activityInfo.getActivityName());
			family.setCreater(user.getUserName());
			family.setCreaterUserId(user.getIdUser());
			familyList.add(family);
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
		for(ActivityHmSign hmSign : hmSignList) {
			for(ActivityMarines marines : marinesList) {
				if(marines.getMarineName().equals(hmSign.getMarineName())) {
					hmSign.setMarineId(marines.getId());
					break;
				}
			}
			HollowManInfo man = hollowManInfoMapper.selectByNameMobile(hmSign.getHmName(), hmSign.getHmMobile());
			if(man == null) {
				man = BeanUtils.copyAs(hmSign, HollowManInfo.class);
				man.setIsActive(1);
				man.setActiveDate(DateUtil.getDate(new Date()));
				man.setCreater(user.getUserName());
				hollowManInfoMapper.insertSelective(man);
			}
			hmSign.setHmId(man.getId());
			activityHmSignMapper.insertSelective(hmSign);
		}
		for(ActivityFamily family : familyList) {
			for(ActivityMarines marines : marinesList) {
				if(marines.getMarineName().equals(family.getMarineName())) {
					family.setMarineId(marines.getId());
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
		return activityInfoMapper.updateByPrimaryKey(info);
	}

}
