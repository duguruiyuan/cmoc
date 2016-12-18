package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ChildActRecordView;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.view.ParentInfoView;

public interface ParentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParentInfo record);

    int insertSelective(ParentInfo record);

    ParentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParentInfo record);

    int updateByPrimaryKey(ParentInfo record);
    
    ParentInfo selectByOpenid(String openid);
    
    int selectCountByOpenid(String openid);
    
    List<ChildActRecordView> selectChildActRecord(String openid);
    
    List<CourseBuyerView> selectCourseBuyerByPage(Page<CourseBuyerView> page);
    
    List<CourseBuyerView> selectBuyRecordByPage(Page<CourseBuyerView> page);
    
    List<ParentInfo> selectListByPage(Page<ParentInfo> page);
    
    List<ParentInfoView> selectBuyTotalByPage(Page<ParentInfoView> page);
    
    CourseBuyerView selectRemindOrder(@Param("mobile")String mobile, 
    		@Param("openid")String openid, @Param("courseId")Integer courseId);
}