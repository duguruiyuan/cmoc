package com.xuequ.cmoc.pay.wepay.protocol.payQueryProtocol;

import java.math.BigDecimal;

import javax.print.DocFlavor.STRING;

/**
 * User: rizenguo
 * Date: 2014/10/25
 * Time: 13:54
 */
public class OrderQueryResData {

    //返回状态码 SUCCESS/FAIL
    private String return_code = "";
    //返回信息
    private String return_msg = "";

    //协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
    private String appid = "";
    private String mch_id = "";
    private String nonce_str = "";
    private String sign = "";
    //业务结果
    private String result_code = "";
    //错误代码
    private String err_code = "";
    //错误代码描述
    private String err_code_des = "";

    //以下字段在return_code 和result_code 都为SUCCESS 的时候有返回
    private String trade_state = "";

    //trade_state的几种可能取值：
    //    SUCCESS--支付成功
    //    REFUND--转入退款
    //    NOTPAY--未支付
    //    CLOSED--已关闭
    //    REVOKED--已撤销
    //    USERPAYING--用户支付中
    //    NOPAY--未支付(确认支付超时)
    //    PAYERROR--支付失败(其他原因，
    //            如银行返回失败)

    //以下字段在trade_state 为SUCCESS 或者REFUND 的时候有返回
    //微信支付分配的终端设备号
    private String device_info = "";
    //用户在商户appid下的唯一标识
    private String openid = "";
    //是否关注公众账号
    private String is_subscribe = "";
    //交易类型
    private String trade_type = "";
    //付款银行
    private String bank_type = "";
    //标价金额
    private BigDecimal total_fee;
    //应结订单金额
    private BigDecimal settlement_total_fee;
    //标价币种
    private String fee_type = "";
    //现金支付金额
    private BigDecimal cash_fee;
    //现金支付币种
    private String cash_fee_type = "";
    //代金券金额
    private BigDecimal coupon_fee;
    //代金券使用数量
    private String coupon_count = "";
    //微信支付订单号
    private String transaction_id = "";
    //商户订单号
    private String out_trade_no = "";
    //附加数据
    private String attach = "";
    //支付完成时间
    private String time_end = "";
    //交易状态描述
    private String trade_state_desc = "";

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }


    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		this.total_fee = total_fee;
	}

	public BigDecimal getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(BigDecimal settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}

	public BigDecimal getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(BigDecimal cash_fee) {
		this.cash_fee = cash_fee;
	}

	public BigDecimal getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(BigDecimal coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public String getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(String coupon_count) {
		this.coupon_count = coupon_count;
	}

	public String getTrade_state_desc() {
		return trade_state_desc;
	}

	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}

}
