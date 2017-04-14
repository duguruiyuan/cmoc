package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductOrder implements Serializable {
    private Integer id;

    private String orderNo;

    private Integer custId;
    
    private String signName;
    
    private String signPhone;
    
    private String openid;

    private Integer productId;
    
    private String productName;
    
    private Integer activityId;

    private String productType;

    private BigDecimal resAmount;

    private BigDecimal totalAmount;

    private Integer exchangepoints;

    private Integer givePoints;

    private String channel;

    private BigDecimal commision;

    private String commistionRate;

    private String bankCode;

    private String bankNo;

    private String transNo;

    private String bankTransNo;

    private Date paySubmitTime;

    private Date payCallbackTime;

    private String orderStatus;

    private String errorCode;

    private String errorReason;

    private String isDelete;

    private Date createTime;

    private Date updateTime;
    
    private String posterImg;
    
    private String tradeType;
    
    private String tradeState;
    
    private String tradeStateDesc;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getSignPhone() {
		return signPhone;
	}

	public void setSignPhone(String signPhone) {
		this.signPhone = signPhone;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public BigDecimal getResAmount() {
        return resAmount;
    }

    public void setResAmount(BigDecimal resAmount) {
        this.resAmount = resAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getExchangepoints() {
        return exchangepoints;
    }

    public void setExchangepoints(Integer exchangepoints) {
        this.exchangepoints = exchangepoints;
    }

    public Integer getGivePoints() {
        return givePoints;
    }

    public void setGivePoints(Integer givePoints) {
        this.givePoints = givePoints;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public String getCommistionRate() {
        return commistionRate;
    }

    public void setCommistionRate(String commistionRate) {
        this.commistionRate = commistionRate == null ? null : commistionRate.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    public String getBankTransNo() {
        return bankTransNo;
    }

    public void setBankTransNo(String bankTransNo) {
        this.bankTransNo = bankTransNo == null ? null : bankTransNo.trim();
    }

    public Date getPaySubmitTime() {
        return paySubmitTime;
    }

    public void setPaySubmitTime(Date paySubmitTime) {
        this.paySubmitTime = paySubmitTime;
    }

    public Date getPayCallbackTime() {
        return payCallbackTime;
    }

    public void setPayCallbackTime(Date payCallbackTime) {
        this.payCallbackTime = payCallbackTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason == null ? null : errorReason.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getPosterImg() {
		return posterImg;
	}

	public void setPosterImg(String posterImg) {
		this.posterImg = posterImg;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getTradeStateDesc() {
		return tradeStateDesc;
	}

	public void setTradeStateDesc(String tradeStateDesc) {
		this.tradeStateDesc = tradeStateDesc;
	}
    
}