package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityHmSignView;

public interface IActivityHmService {

	List<ActivityHmSignView> selectListByPage(Page<ActivityHmSignView> page);
	
	/**
	 * 根据透明人openid获取接收微信消息信息
	 * @param openid
	 * @return
	 */
	ActivityHmSign selectForMessage(String openid);
	
	/**
	 * 查询透明人带队记录
	 * @param openid
	 * @return
	 */
	List<ActivityHmSignView> selectWithTeamRecord(String openid);
	/**
	 * 透明人报名参加活动
	 * @auther 胡启萌
	 * @Date 2016年12月7日
	 * @param activityId
	 * @param openid
	 * @return
	 */
	RspResult addSign(Integer activityId, String openid);
	
	/**
     * 查询活动报名透明人
     * @param page
     * @return
     */
    List<ActivityHmSignView> selectHmSignForAudit(Page<ActivityHmSignView> page);
    
    int updateAuditHmSign(List<Integer> list, Integer isEffect, String reason);
}
