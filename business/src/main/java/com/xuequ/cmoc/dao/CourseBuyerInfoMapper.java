package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.CourseBuyerInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.CourseBuyerView;

public interface CourseBuyerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseBuyerInfo record);

    int insertSelective(CourseBuyerInfo record);

    CourseBuyerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseBuyerInfo record);

    int updateByPrimaryKey(CourseBuyerInfo record);
    
    List<CourseBuyerView> selectCourseBuyerByPage(Page<CourseBuyerView> page);
    
    CourseBuyerInfo selectByOpenid(String openid);
    
    CourseBuyerView selectRemindOrder(@Param("mobile")String mobile, 
    		@Param("openid")String openid, @Param("courseId")Integer courseId);
}