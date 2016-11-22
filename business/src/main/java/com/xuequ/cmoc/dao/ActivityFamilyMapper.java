package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.ActivityFamily;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityFamilyView;

public interface ActivityFamilyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityFamily record);

    int insertSelective(ActivityFamily record);

    ActivityFamily selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityFamily record);

    int updateByPrimaryKey(ActivityFamily record);
    
    List<ActivityFamilyView> selectListByPage(Page<ActivityFamilyView> page);
    
    ActivityFamilyView selectById(Integer id);
    
    List<ActivityFamily> selectListByMarineId(Integer marineId);
    
    int updateFamilyImg(@Param("childImg")String childImg, @Param("id")Integer id);
    
}