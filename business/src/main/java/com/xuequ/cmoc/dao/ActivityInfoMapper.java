package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityInfoView;
import com.xuequ.cmoc.view.ActivityResourceTypeView;

public interface ActivityInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityInfo record);

    int insertSelective(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityInfo record);

    int updateByPrimaryKey(ActivityInfo record);
    
    List<ActivityInfoView> selectListByPage(Page<ActivityInfoView> page);

    List<ActivityInfo> selectListByParam(Page<ActivityInfo> page);
    
    List<ActivityInfo> selectScheduActivity(ActivityInfo info);
    
    ActivityResourceTypeView selectForUpload1(@Param("activityId")Integer activityId,
    		@Param("marineName")String marineName, @Param("childName")String childName);
    
    ActivityResourceTypeView selectForUpload(@Param("activityId")Integer activityId,
    		@Param("name")String name);

    int updateActivityImg(@Param("activityImg")String activityImg, 
    		@Param("id")Integer id);
}