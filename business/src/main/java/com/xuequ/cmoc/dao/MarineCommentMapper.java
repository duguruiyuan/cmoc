package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.MarineComment;
import com.xuequ.cmoc.page.Page;

public interface MarineCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MarineComment record);

    int insertSelective(MarineComment record);

    MarineComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MarineComment record);

    int updateByPrimaryKey(MarineComment record);
    
    List<MarineComment> selectListByPage(Page<MarineComment> page);
}