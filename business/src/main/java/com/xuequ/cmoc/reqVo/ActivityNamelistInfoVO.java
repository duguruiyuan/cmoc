package com.xuequ.cmoc.reqVo;

import java.io.Serializable;
import java.util.List;

import com.xuequ.cmoc.model.ActivityFamily;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.model.ActivityTeacher;

public class ActivityNamelistInfoVO implements Serializable {
	
	private static final long serialVersionUID = 3008853849317175568L;

	private Integer activityId;
	
	private List<ActivityFamily> familyList;
	
	private List<ActivityHmSign> hmSignList;
	
	private List<ActivityTeacher> teacherList;
	
	private List<ActivityMarines> marinesList;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public List<ActivityFamily> getFamilyList() {
		return familyList;
	}

	public void setFamilyList(List<ActivityFamily> familyList) {
		this.familyList = familyList;
	}

	public List<ActivityHmSign> getHmSignList() {
		return hmSignList;
	}

	public void setHmSignList(List<ActivityHmSign> hmSignList) {
		this.hmSignList = hmSignList;
	}

	public List<ActivityTeacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<ActivityTeacher> teacherList) {
		this.teacherList = teacherList;
	}

	public List<ActivityMarines> getMarinesList() {
		return marinesList;
	}

	public void setMarinesList(List<ActivityMarines> marinesList) {
		this.marinesList = marinesList;
	}

}
