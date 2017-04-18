package com.xuequ.cmoc.job;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xuequ.cmoc.common.enums.OrderStatusEnum;
import com.xuequ.cmoc.common.enums.RefundStatusEnum;
import com.xuequ.cmoc.common.enums.ResultCode;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.model.ProductRefundOrder;
import com.xuequ.cmoc.pay.wepay.WXPay;
import com.xuequ.cmoc.pay.wepay.common.Configure;
import com.xuequ.cmoc.pay.wepay.protocol.refundQueryProtocol.RefundQueryReqData;
import com.xuequ.cmoc.pay.wepay.protocol.refundQueryProtocol.RefundQueryResData;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.service.IProductRefundOrderService;

@Component
public class OrderScanJob {
	
	private Logger logger = LoggerFactory.getLogger(OrderScanJob.class);
	
	@Autowired
	private IProductOrderService productOrderService;
	@Autowired
	private IProductRefundOrderService productRefundOrderService;

	@Scheduled(cron = "0 0 2 * * ?")
	public void invalidOrderScanJob() {
		productOrderService.updateInvalidOrder();
	}
	
	@Scheduled(cron = "0 */20 * * * ?")
	public void refundQueryScanJob() {
		List<ProductRefundOrder> list = productRefundOrderService.queryRefundPendingOrder();
		if(list != null && list.size() > 0) {
			for(ProductRefundOrder refundOrder : list) {
				RefundQueryReqData reqData = new RefundQueryReqData(
						refundOrder.getTransNo(), 
						refundOrder.getOutTradeNo(), 
						Configure.DEVICE_INFO, 
						refundOrder.getOutRefundNo(), 
						refundOrder.getRefundNo());
				
				try {
					RefundQueryResData resData = WXPay.requestRefundQueryService(reqData);
					if(resData.getReturn_code().equals(ResultCode.SUCCESS.getCode())) {
			        	if(resData.getResult_code().equals(ResultCode.SUCCESS.getCode())) {
			        		refundOrder.setOrderStatus(RefundStatusEnum.SUCCESS.getCode());
			        	}else {
			        		refundOrder.setErrCode(resData.getErr_code());
			        		refundOrder.setErrorReason(resData.getErr_code_des());
			        		refundOrder.setOrderStatus(RefundStatusEnum.FAIL.getCode());
						}
			        }else {
		        		refundOrder.setReturnMsg(resData.getReturn_msg());
		        		refundOrder.setOrderStatus(RefundStatusEnum.FAIL.getCode());
			        }
					refundOrder.setUpdateTime(new Date());
					productRefundOrderService.updateByPrimaryKeySelective(refundOrder);
					if(refundOrder.getOrderStatus().equals(RefundStatusEnum.SUCCESS.getCode())) {
						ProductOrder order = productOrderService.selectByOrderNo(refundOrder.getOutTradeNo());
						order.setOrderStatus(OrderStatusEnum.REFUND.getCode());
						order.setUpdateTime(new Date());
						productOrderService.updateById(order);
					}
				} catch (Exception e) {
					logger.error("--------退款订单回调异常，out_refund_no={}, error={}", refundOrder.getOutRefundNo(), e);
				}
				
			}
		}
	}
}
