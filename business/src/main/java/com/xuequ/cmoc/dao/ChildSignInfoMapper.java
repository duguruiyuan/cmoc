package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ChildSignView;

public interface ChildSignInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChildSignInfo record);

    int insertSelective(ChildSignInfo record);

    ChildSignInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChildSignInfo record);

    int updateByPrimaryKey(ChildSignInfo record);
    
    int selectHasChild(String childName, String emerMobile);
    
    List<ChildSignInfo> selectNonStartingList(ChildSignInfo info);
    
    ChildSignInfo selectByChildNameMobile(@Param("childName")String childName, 
    		@Param("emerMobile")String emerMobile);
    String selectFamilyNoByOrderNo(String orderNo);
    
    String selectFamilyNo(ChildSignInfo record);
    
    List<ChildSignView> selectCourseSignByPage(Page<ChildSignView> page);
    
    int selectCountByOrderNo(String orderNo);
    
    List<ChildSignInfo> selectListByOrderNo(String orderNo);
}