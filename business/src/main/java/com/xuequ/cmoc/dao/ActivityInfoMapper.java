package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityInfoView;

public interface ActivityInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityInfo record);

    int insertSelective(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityInfo record);

    int updateByPrimaryKey(ActivityInfo record);
    
    List<ActivityInfoView> selectListByPage(Page<ActivityInfoView> page);
}