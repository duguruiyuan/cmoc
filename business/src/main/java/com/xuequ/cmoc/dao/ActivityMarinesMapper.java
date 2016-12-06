package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityMarinesView;

public interface ActivityMarinesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityMarines record);

    int insertSelective(ActivityMarines record);

    ActivityMarines selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityMarines record);

    int updateByPrimaryKey(ActivityMarines record);
    
    List<ActivityMarinesView> selectListByPage(Page<ActivityMarinesView> page);
    
    List<ActivityMarines> selectListByActivityId(Integer activityId);
    
    ActivityMarinesView selectById(Integer id);
    
    int updateMarineImg(@Param("marineImg")String marineImg, 
    		@Param("id")Integer id);
    
    List<ActivityMarinesView> selectMarineTeam(Integer activityId);
    
    ActivityMarinesView selectMarineByHmOpenid(String openid);
}