package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.HollowManInfoView;
import com.xuequ.cmoc.view.HollowManTakeView;

public interface HollowManInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HollowManInfo record);

    int insertSelective(HollowManInfo record);

    HollowManInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HollowManInfo record);

    int updateByPrimaryKey(HollowManInfo record);
    
    HollowManInfo selectByNameMobile(@Param("hmName")String hmName, 
    		@Param("hmMobile")String hmMobile);
    
    List<HollowManInfoView> selectByPage(Page<HollowManInfoView> page);
    
    HollowManInfo selectByOpenid(String openid);
    
    int selectCountByOpenid(String openid);
    
    int updateAuditDeleteHm(@Param("list")List<Integer> list, @Param("reason")String reason);
    
    int updateAuditActiveHm(@Param("list")List<Integer> ids, @Param("reason")String reason);
    
    List<HollowManTakeView> selectHmTakeListByHmId(Integer hmId);
    
    List<HollowManInfo> selectListByIds(List<Integer> ids);
    
    List<HollowManTakeView> selectHmTakeListByPage(Page<HollowManTakeView> page);
}