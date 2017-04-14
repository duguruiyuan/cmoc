package com.xuequ.cmoc.pay.wepay.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.nfunk.jep.function.Str;

public class WechatBridge {

	//公众号id	
	private String appId;
	//时间戳	
	private String timeStamp;
	//随机字符串	
	private String nonceStr;
	//订单详情扩展字符串	
	private String packages;
	//签名方式	
	private String signType;
	//签名	
	private String paySign;
	
	private String orderNo;
	
	public WechatBridge(String appId, Long timeStamp, String nonceStr, 
			String packages, String signType) {
		setAppId(appId);
		setTimeStamp(String.valueOf(timeStamp));
		setNonceStr(nonceStr);
		setPackages(packages);
		setSignType(signType);
		//根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setPaySign(sign);
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPackages() {
		return packages;
	}
	public void setPackages(String packages) {
		this.packages = packages;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                	if("packages".equals(field.getName())) {
                		map.put("package", obj);
                	}else {
                		map.put(field.getName(), obj);
					}
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
