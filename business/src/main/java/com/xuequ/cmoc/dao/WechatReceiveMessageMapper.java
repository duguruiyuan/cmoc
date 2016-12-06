package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.WechatReceiveMessage;

public interface WechatReceiveMessageMapper {
    int deleteByPrimaryKey(String msgId);

    int insert(WechatReceiveMessage record);

    int insertSelective(WechatReceiveMessage record);

    WechatReceiveMessage selectByPrimaryKey(String msgId);

    int updateByPrimaryKeySelective(WechatReceiveMessage record);

    int updateByPrimaryKey(WechatReceiveMessage record);
}