package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.WechatSendMessage;

public interface WechatSendMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatSendMessage record);

    int insertSelective(WechatSendMessage record);

    WechatSendMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatSendMessage record);

    int updateByPrimaryKey(WechatSendMessage record);
    
    List<WechatSendMessage> selectListByParams(@Param("keyId")Integer keyId, 
    		@Param("keyword")String keyword);
}