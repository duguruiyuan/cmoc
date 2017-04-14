package com.xuequ.cmoc.pay.wepay.service;

import org.slf4j.LoggerFactory;

import com.xuequ.cmoc.pay.wepay.common.Configure;
import com.xuequ.cmoc.pay.wepay.common.Log;
import com.xuequ.cmoc.pay.wepay.common.Util;
import com.xuequ.cmoc.pay.wepay.common.report.ReporterFactory;
import com.xuequ.cmoc.pay.wepay.common.report.protocol.ReportReqData;
import com.xuequ.cmoc.pay.wepay.common.report.service.ReportService;
import com.xuequ.cmoc.pay.wepay.protocol.payProtocol.UnifiedOrderReqData;
import com.xuequ.cmoc.pay.wepay.protocol.payProtocol.UnifiedOrderResData;

public class UnifiedOrderService extends BaseService{
	
	private static Log log = new Log(LoggerFactory.getLogger(UnifiedOrderService.class));

    public UnifiedOrderService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.UNIFIED_ORDER_API);
    }

    /**
     * 请求退款服务
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public UnifiedOrderResData request(UnifiedOrderReqData unifiedorderReqData) throws Exception {
    	long costTimeStart = System.currentTimeMillis();
    	
    	String responseString = sendPost(unifiedorderReqData);
    	
    	long costTimeEnd = System.currentTimeMillis();
        long totalTimeCost = costTimeEnd - costTimeStart;
        log.i("api请求总耗时：" + totalTimeCost + "ms");

        log.i(responseString);
        
        try {
        	//将从API返回的XML数据映射到Java对象
        	UnifiedOrderResData unifiedorderResData = (UnifiedOrderResData) Util.getObjectFromXML(responseString, UnifiedOrderResData.class);


            ReportReqData reportReqData = new ReportReqData(
            		unifiedorderResData.getDevice_info(),
                    Configure.UNIFIED_ORDER_API,
                    (int) (totalTimeCost),//本次请求耗时
                    unifiedorderResData.getReturn_code(),
                    unifiedorderResData.getReturn_msg(),
                    unifiedorderResData.getResult_code(),
                    unifiedorderResData.getErr_code(),
                    unifiedorderResData.getErr_code_des(),
                    "",
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
            
            return unifiedorderResData;
		} catch (Exception e) {
			log.i("数据处理异常：" + e);
		} 
        return null;
    }

}
