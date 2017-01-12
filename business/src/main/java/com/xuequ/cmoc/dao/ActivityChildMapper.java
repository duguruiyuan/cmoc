package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.ActivityChild;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityChildView;

public interface ActivityChildMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityChild record);

    int insertSelective(ActivityChild record);

    ActivityChild selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityChild record);

    int updateByPrimaryKey(ActivityChild record);
    
    List<ActivityChildView> selectListByPage(Page<ActivityChildView> page);
    
    ActivityChildView selectById(Integer id);
    
    ActivityChildView selectChildByChildId(Integer childId);
    
    List<ActivityChildView> selectListByMarineId(Integer marineId);
}