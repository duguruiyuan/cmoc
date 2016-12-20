package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.MarineStudentImpression;

public interface MarineStudentImpressionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MarineStudentImpression record);

    int insertSelective(MarineStudentImpression record);

    MarineStudentImpression selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MarineStudentImpression record);

    int updateByPrimaryKey(MarineStudentImpression record);
    
    int insertByMarineId(Integer marineId);
    
    List<MarineStudentImpression> selectByMarineId(Integer marineId);
    
    int addStuImpVotes(Integer id);
}