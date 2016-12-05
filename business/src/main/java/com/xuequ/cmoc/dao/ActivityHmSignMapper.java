package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
    /**
     * 判断透明人活动是否报名成功
     * @auther 胡启萌
     * @Date 2016年12月3日
     * @param marineId
     * @param openid
     * @return
     */
    public ActivityHmSign selectForSign(@Param("marineId")Integer marineId, 
    		@Param("openid")String openid);
    
    int updateBindMarine(ActivityHmSign hmSign);
    
    ActivityHmSign selectForMessage(String openid);
}