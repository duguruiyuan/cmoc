package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.WechatReceiveMessage;

public interface WechatReceiveMessageMapper {
    int deleteByPrimaryKey(String msgid);

    int insert(WechatReceiveMessage record);

    int insertSelective(WechatReceiveMessage record);

    WechatReceiveMessage selectByPrimaryKey(String msgid);

    int updateByPrimaryKeySelective(WechatReceiveMessage record);

    int updateByPrimaryKey(WechatReceiveMessage record);
}