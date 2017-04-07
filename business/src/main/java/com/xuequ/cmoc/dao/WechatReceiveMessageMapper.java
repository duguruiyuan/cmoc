package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.page.Page;

public interface WechatReceiveMessageMapper {
    int deleteByPrimaryKey(String msgId);

    int insert(WechatReceiveMessage record);

    int insertSelective(WechatReceiveMessage record);

    WechatReceiveMessage selectByPrimaryKey(String msgId);

    int updateByPrimaryKeySelective(WechatReceiveMessage record);

    int updateByPrimaryKey(WechatReceiveMessage record);
    
    List<WechatReceiveMessage> selectListByPage(Page<WechatReceiveMessage> page);
    
    int selectCountByParam(WechatReceiveMessage message);
}