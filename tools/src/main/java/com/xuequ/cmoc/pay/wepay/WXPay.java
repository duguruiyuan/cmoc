package com.xuequ.cmoc.pay.wepay;

import com.xuequ.cmoc.pay.wepay.protocol.closeResProtocol.CloseReqData;
import com.xuequ.cmoc.pay.wepay.protocol.closeResProtocol.CloseResData;
import com.xuequ.cmoc.pay.wepay.protocol.payProtocol.UnifiedOrderReqData;
import com.xuequ.cmoc.pay.wepay.protocol.payProtocol.UnifiedOrderResData;
import com.xuequ.cmoc.pay.wepay.protocol.refundProtocol.RefundReqData;
import com.xuequ.cmoc.pay.wepay.protocol.refundProtocol.RefundResData;
import com.xuequ.cmoc.pay.wepay.protocol.refundQueryProtocol.RefundQueryReqData;
import com.xuequ.cmoc.pay.wepay.protocol.refundQueryProtocol.RefundQueryResData;
import com.xuequ.cmoc.pay.wepay.service.CloseService;
import com.xuequ.cmoc.pay.wepay.service.RefundQueryService;
import com.xuequ.cmoc.pay.wepay.service.RefundService;
import com.xuequ.cmoc.pay.wepay.service.UnifiedOrderService;

/**
 * SDK总入口
 */
public class WXPay {

    /**
     * 请求退款服务
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public static RefundResData requestRefundService(RefundReqData refundReqData) throws Exception{
        return new RefundService().request(refundReqData);
    }

    /**
     * 请求退款查询服务
     * @param refundQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
	public static RefundQueryResData requestRefundQueryService(RefundQueryReqData refundQueryReqData) throws Exception{
		return new RefundQueryService().request(refundQueryReqData);
	}
	
	public static CloseResData requestCloseResService(CloseReqData closeReqData) throws Exception {
		return new CloseService().request(closeReqData);
	}
	
	public static UnifiedOrderResData requestUnifiedOrderService(UnifiedOrderReqData unifiedOrderReqData) throws Exception {
		return new UnifiedOrderService().request(unifiedOrderReqData);
	}

}
