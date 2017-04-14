package com.xuequ.cmoc.pay.wepay.service;

import org.slf4j.LoggerFactory;

import com.xuequ.cmoc.pay.wepay.common.Configure;
import com.xuequ.cmoc.pay.wepay.common.Log;
import com.xuequ.cmoc.pay.wepay.common.Util;
import com.xuequ.cmoc.pay.wepay.common.report.ReporterFactory;
import com.xuequ.cmoc.pay.wepay.common.report.protocol.ReportReqData;
import com.xuequ.cmoc.pay.wepay.common.report.service.ReportService;
import com.xuequ.cmoc.pay.wepay.protocol.closeResProtocol.CloseReqData;
import com.xuequ.cmoc.pay.wepay.protocol.closeResProtocol.CloseResData;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:04
 */
public class CloseService extends BaseService{
	
	private static Log log = new Log(LoggerFactory.getLogger(CloseService.class));

    public CloseService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.CLOSE_ORDER_API);
    }

    /**
     * 请求撤销服务
     * @param closeReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public CloseResData request(CloseReqData closeReqData) throws Exception {

    	long costTimeStart = System.currentTimeMillis();
    	
    	String responseString = sendPost(closeReqData);
    	
    	long costTimeEnd = System.currentTimeMillis();
        long totalTimeCost = costTimeEnd - costTimeStart;
        log.i("api请求总耗时：" + totalTimeCost + "ms");

        log.i(responseString);
        
        try {
        	//将从API返回的XML数据映射到Java对象
        	CloseResData resData = (CloseResData) Util.getObjectFromXML(responseString, CloseResData.class);


            ReportReqData reportReqData = new ReportReqData(
            		"",
                    Configure.CLOSE_ORDER_API,
                    (int) (totalTimeCost),//本次请求耗时
                    resData.getReturn_code(),
                    resData.getReturn_msg(),
                    resData.getResult_code(),
                    resData.getErr_code(),
                    resData.getErr_code_des(),
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
            
            return resData;
		} catch (Exception e) {
			log.i("数据处理异常：" + e);
		} 
        return null;
    }

}
