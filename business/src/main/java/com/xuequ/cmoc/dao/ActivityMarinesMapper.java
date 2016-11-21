package com.xuequ.cmoc.dao;

import java.util.List;

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
}