package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.view.ChildActRecordView;

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
}