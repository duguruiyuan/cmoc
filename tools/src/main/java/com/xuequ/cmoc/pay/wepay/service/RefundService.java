package com.xuequ.cmoc.pay.wepay.service;

import org.slf4j.LoggerFactory;

import com.xuequ.cmoc.pay.wepay.common.Configure;
import com.xuequ.cmoc.pay.wepay.common.Log;
import com.xuequ.cmoc.pay.wepay.common.Util;
import com.xuequ.cmoc.pay.wepay.common.report.ReporterFactory;
import com.xuequ.cmoc.pay.wepay.common.report.protocol.ReportReqData;
import com.xuequ.cmoc.pay.wepay.common.report.service.ReportService;
import com.xuequ.cmoc.pay.wepay.protocol.refundProtocol.RefundReqData;
import com.xuequ.cmoc.pay.wepay.protocol.refundProtocol.RefundResData;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:04
 */
public class RefundService extends BaseService{
	
	private static Log log = new Log(LoggerFactory.getLogger(RefundService.class));

    public RefundService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.REFUND_API);
    }

    /**
     * 请求退款服务
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public RefundResData request(RefundReqData refundReqData) throws Exception {
    	long costTimeStart = System.currentTimeMillis();
    	
    	String responseString = sendCertPost(refundReqData);
    	
    	long costTimeEnd = System.currentTimeMillis();
        long totalTimeCost = costTimeEnd - costTimeStart;
        log.i("api请求总耗时：" + totalTimeCost + "ms");

        log.i(responseString);
        
        try {
        	//将从API返回的XML数据映射到Java对象
            RefundResData refundResData = (RefundResData) Util.getObjectFromXML(responseString, RefundResData.class);


            ReportReqData reportReqData = new ReportReqData(
                    refundResData.getDevice_info(),
                    Configure.REFUND_API,
                    (int) (totalTimeCost),//本次请求耗时
                    refundResData.getReturn_code(),
                    refundResData.getReturn_msg(),
                    refundResData.getResult_code(),
                    refundResData.getErr_code(),
                    refundResData.getErr_code_des(),
                    refundResData.getOut_trade_no(),
                    Configure.SERVER_IP
            );

            long timeAfterReport;
            if(Configure.useThreadToDoReport){
                ReporterFactory.getReporter(reportReqData).run();
                timeAfterReport = System.currentTimeMillis();
                Util.log("pay+report总耗时（异步方式上报）："+(timeAfterReport-costTimeStart) + "ms");
            }else{
                ReportService.request(reportReqData);
                timeAfterReport = System.currentTimeMillis();
                Util.log("pay+report总耗时（同步方式上报）："+(timeAfterReport-costTimeStart) + "ms");
            }
            
            return refundResData;
		} catch (Exception e) {
			log.i("数据处理异常：" + e);
		} 
        return null;
    }

}
