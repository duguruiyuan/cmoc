package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityHmSignView;

public interface ActivityHmSignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityHmSign record);

    int insertSelective(ActivityHmSign record);

    ActivityHmSign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityHmSign record);

    int updateByPrimaryKey(ActivityHmSign record);
    
    public List<ActivityHmSignView> selectListByPage(Page<ActivityHmSignView> page);
}