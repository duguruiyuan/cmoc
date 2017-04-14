package com.xuequ.cmoc.utils;

import java.util.Date;

import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.utils.SignUtil;
import com.xuequ.cmoc.core.wechat.utils.WechatModel;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.pay.wepay.WXPay;
import com.xuequ.cmoc.pay.wepay.common.WechatBridge;
import com.xuequ.cmoc.pay.wepay.common.Configure;
import com.xuequ.cmoc.pay.wepay.common.RandomStringGenerator;
import com.xuequ.cmoc.pay.wepay.protocol.payProtocol.UnifiedOrderReqData;
import com.xuequ.cmoc.pay.wepay.protocol.payProtocol.UnifiedOrderResData;

public class PayUtils {

	/**
	 * 微信统一下单
	 * @auther 胡启萌
	 * @Date 2017年4月12日
	 * @param productName 产品名称
	 * @param productId 产品编号
	 * @param orderNo 订单号
	 * @param totalAmount 支付金额
	 * @param tradeType 交易类型
	 * @param openid 
	 * @param submitDate 支付提交时间
	 * @return
	 * @throws Exception
	 */
	public static WechatBridge wechatUnifiedOrder(String productName, 
			String productId, String orderNo, int totalAmount, 
			String tradeType, String openid) throws Exception {
		Date submitDate = new Date();
		UnifiedOrderReqData unifiedOrderReqData = new UnifiedOrderReqData(
				Configure.DEVICE_INFO, 
				productName, 
				"", 
				orderNo, 
				totalAmount, 
				DateUtil.dateToStr(submitDate , DateUtil.DEFAULT_DATE_FORMAT3), 
				DateUtil.addDayDateToStr(submitDate, 3, DateUtil.DEFAULT_DATE_FORMAT3), 
				"", 
				Constants.BASEPATH + "/wechatpay/callback",
				tradeType, 
				String.valueOf(productId), 
				openid);
		UnifiedOrderResData unifiedOrderResData = WXPay.requestUnifiedOrderService(unifiedOrderReqData);
		
		WechatBridge bridge = new WechatBridge(WechatConfigure.getInstance().getAppid(), 
				new Date().getTime(), RandomStringGenerator.getRandomStringByLength(32), 
				"prepay_id=" + unifiedOrderResData.getPrepay_id(), "MD5");
		
		return bridge;
	}
}
