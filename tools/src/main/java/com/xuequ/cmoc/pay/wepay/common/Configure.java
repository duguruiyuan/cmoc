package com.xuequ.cmoc.pay.wepay.common;

import com.xuequ.cmoc.common.Configuration;
import com.xuequ.cmoc.common.WechatConfigure;

public final class Configure {
	
	//API秘钥
	public static String KEY = "20170413taoxuequ888888schooledus";
	//公众账号ID
	public static String APPID = WechatConfigure.getInstance().getAppid();
	//商户号
	public static final String MCH_ID = "1422485502";
	//设备号
	public static final String DEVICE_INFO = "WEB";
	//交易类型
	public static final String TRADE_TYPE = "JSAPI";
	
	public static final String SERVER_IP = Configuration.getInstance().getServerIp();
	
	public static final String HttpsRequestClassName = "com.xuequ.cmoc.pay.wepay.common.HttpsRequest";
	
	public static final boolean useThreadToDoReport = true;
	
	//1）统一下单接口
	public static final String UNIFIED_ORDER_API ="https://api.mch.weixin.qq.com/pay/unifiedorder";
	//2）订单查询接口
	public static final String ORDER_QUERY_API= "https://api.mch.weixin.qq.com/pay/orderquery";
	//3）订单关闭接口
	public static final String CLOSE_ORDER_API = "https://api.mch.weixin.qq.com/pay/closeorder";
	//4}退款订单API
	public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//5）退款查询API
	public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";
	//6）下载对账单API
	public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";
	//7) 统计上报API
	public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

}
