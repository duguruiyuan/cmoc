package com.xuequ.cmoc.core.wechat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.common.enums.OrderStatusEnum;
import com.xuequ.cmoc.common.enums.PayType;
import com.xuequ.cmoc.common.enums.RefundStatusEnum;
import com.xuequ.cmoc.common.enums.ResultCode;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.common.enums.TradeState;
import com.xuequ.cmoc.controller.BaseController;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.template.Data_Add;
import com.xuequ.cmoc.core.wechat.template.Data_Clazz;
import com.xuequ.cmoc.core.wechat.template.Data_First;
import com.xuequ.cmoc.core.wechat.template.Data_Keyword;
import com.xuequ.cmoc.core.wechat.template.Data_Remark;
import com.xuequ.cmoc.core.wechat.template.Data_Time;
import com.xuequ.cmoc.core.wechat.template.OutputTemateData;
import com.xuequ.cmoc.core.wechat.template.TemplateDate;
import com.xuequ.cmoc.core.wechat.utils.TemplateUtil;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.model.AuditReqVO;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.model.ProductRefundOrder;
import com.xuequ.cmoc.pay.wepay.WXPay;
import com.xuequ.cmoc.pay.wepay.common.Configure;
import com.xuequ.cmoc.pay.wepay.protocol.refundProtocol.RefundReqData;
import com.xuequ.cmoc.pay.wepay.protocol.refundProtocol.RefundResData;
import com.xuequ.cmoc.service.IActivityHmService;
import com.xuequ.cmoc.service.IHollowManService;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.service.IProductRefundOrderService;
import com.xuequ.cmoc.utils.AmountArithUtil;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.PropertiesUtil;
import com.xuequ.cmoc.utils.RequestUtil;
import com.xuequ.cmoc.utils.StringUtil;
import com.xuequ.cmoc.utils.TextUtil;
import com.xuequ.cmoc.view.ActivityHmSignView;
import com.xuequ.cmoc.view.CourseSignOrderView;

@RequestMapping("wechatmsg")
@Controller
public class WechatMsgController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(WechatMsgController.class);

	@Autowired
	private IHollowManService hollowManService;
	@Autowired
	private IActivityHmService activityHmService;
	@Autowired
	private IProductOrderService productOrderService;
	@Autowired
	private IProductRefundOrderService productRefundOrderService;
	
	/**
	 * 订单退款
	 * @auther 胡启萌
	 * @Date 2017年4月17日
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="orderRefundMsg", method = RequestMethod.POST)  
    @ResponseBody Object orderRefund(@RequestBody AuditReqVO vo) { 
		try {
			if(vo != null) {
				ProductOrder productOrder = productOrderService.selectById(Integer.valueOf(vo.getIds()));
				if(productOrder != null) {
					ProductRefundOrder refundOrder = new ProductRefundOrder();
					refundOrder.setOutRefundNo(StringUtil.getCourseOrderNum(productOrder.getCustId()));
	        		refundOrder.setOutTradeNo(productOrder.getOrderNo());
	        		refundOrder.setTransNo(productOrder.getTransNo());
	        		refundOrder.setTotalFee(productOrder.getTotalAmount());
	        		refundOrder.setRefundFee(productOrder.getResAmount());
	        		refundOrder.setOrderStatus(RefundStatusEnum.HANDING.getCode());
	        		refundOrder.setRefundSubmitTime(new Date());
	        		productRefundOrderService.insertSelective(refundOrder);
					RefundReqData reqData = new RefundReqData(
							refundOrder.getTransNo(), 
							refundOrder.getOutTradeNo(), 
							Configure.DEVICE_INFO, 
							refundOrder.getOutRefundNo(), 
							AmountArithUtil.muiFloor(refundOrder.getTotalFee(), 100), 
							AmountArithUtil.muiFloor(refundOrder.getRefundFee(), 100));
					
					RefundResData resData = WXPay.requestRefundService(reqData);
					
					refundOrder.setRefundCallbackTime(new Date());
					refundOrder.setReturnCode(resData.getResult_code());
					if(resData.getReturn_code().equals(ResultCode.SUCCESS.getCode())) {
			        	if(resData.getResult_code().equals(ResultCode.SUCCESS.getCode())) {
			        		refundOrder.setRefundNo(resData.getRefund_id());
			        	}else {
			        		refundOrder.setErrCode(resData.getErr_code());
			        		refundOrder.setErrorReason(resData.getErr_code_des());
			        		refundOrder.setOrderStatus(RefundStatusEnum.FAIL.getCode());
						}
			        }else {
		        		refundOrder.setReturnMsg(resData.getReturn_msg());
		        		refundOrder.setOrderStatus(RefundStatusEnum.FAIL.getCode());
			        }
					productRefundOrderService.updateByPrimaryKeySelective(refundOrder);
					if(refundOrder.getOrderStatus().equals(RefundStatusEnum.HANDING.getCode())) {
						ProductOrder order = productOrderService.selectByOrderNo(refundOrder.getOutTradeNo());
						order.setOrderStatus(OrderStatusEnum.REFUND_HANDING.getCode());
						order.setUpdateTime(new Date());
						productOrderService.updateById(order);
						//退款通知模板
						TemplateUtil.orderRefundApplyMsg(order.getOrderNo(), order.getOpenid(), refundOrder.getRefundFee());
					}
					return new RspResult(StatusEnum.SUCCESS, resData);
				}
			}
		} catch (Exception e) {
			logger.error("--orderRefund, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	/**
	 * 订单支付成功提醒
	 * @auther 胡启萌
	 * @Date 2017年4月17日
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="paySucessMsg", method = RequestMethod.POST)
	@ResponseBody Object paySucessMsg(@RequestBody CourseSignOrderView vo) {
		try {
			CourseSignOrderView view = productOrderService.selectCourseSignOrderByOrderId(vo.getOrderId());
			if(view != null) {
				if(view.getSignWay() == 1) {
					TemplateUtil.courseOrderPaySucessMsg(vo.getPaySubmitTime(), vo.getOrderNo(), 
							vo.getOpenid(), vo.getSignName(), vo.getActivityName(), 
							vo.getActivityNum(), vo.getActivityStartDate(), vo.getTotalPrice());
				}else {
					TemplateUtil.courseOrderPaySucessMsg(vo.getPaySubmitTime(), vo.getOrderNo(), 
							vo.getOpenid(), vo.getSignName(), vo.getCourseName(), 
							null, null, vo.getResAmount());
				}
			}
		} catch (Exception e) {
			logger.error("--paySucessMsg, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	/**
	 * 透明人注册成功提醒
	 * @auther 胡启萌
	 * @Date 2017年4月17日
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="hmRegMsg", method = RequestMethod.POST)  
    @ResponseBody Object hmRegMsg(@RequestBody AuditReqVO vo) { 
		try {
			if(vo != null) {
				List<Integer> list = new ArrayList<>();
				for(String string : vo.getIds().split(",")) {
					list.add(Integer.valueOf(string));
				}
				List<HollowManInfo> hmList = hollowManService.selectListByIds(list);
				if(!StringUtil.isNullOrEmpty(hmList)) {
					hmRegMsgSend(hmList, vo.getStatus());
				}
				return new RspResult(StatusEnum.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("--hmRegMsg, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	/**
	 * 透明人报名成功提醒
	 * @auther 胡启萌
	 * @Date 2017年4月17日
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="hmSignMsg", method = RequestMethod.POST)
	@ResponseBody Object hmSignMsg(@RequestBody AuditReqVO vo) {
		try {
			if(vo != null) {
				List<Integer> list = new ArrayList<>();
				for(String string : vo.getIds().split(",")) {
					list.add(Integer.valueOf(string));
				}
				List<ActivityHmSignView> hmList = activityHmService.selectListByIds(list);
				if(!StringUtil.isNullOrEmpty(hmList)) {
					hmSignMsgSend(hmList, vo.getStatus());
				}
				return new RspResult(StatusEnum.SUCCESS);
			}
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--hmSignMsg, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	private static void hmSignMsgSend(List<ActivityHmSignView> list, Integer status) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		for(ActivityHmSignView info : list) {
			OutputTemateData outputData = new OutputTemateData();
			outputData.setTouser(info.getOpenid());
			outputData.setTemplate_id(PropertiesUtil.getProperty("sign_template_id"));
			TemplateDate templateDate = new TemplateDate();
			Data_First first = new Data_First();
			if(status == 1) {
				first.setValue(info.getHmName() + "恭喜你,报名成功!");
			}else {
				first.setValue(info.getHmName() + "很抱歉,报名失败!\n原因：" + info.getReason());
			}
			Data_Clazz clazz  = new Data_Clazz();
			clazz.setValue(info.getActivityName());
			Data_Time time = new Data_Time();
			time.setValue(DateUtil.dateToStr(info.getStartDate(), DateUtil.DATE_MD_FORMAT) + "-" + 
					DateUtil.dateToStr(info.getEndDate(), DateUtil.DATE_MD_FORMAT));
			Data_Add add = new Data_Add();
			add.setValue(info.getActivityAddr() + "\n");
			outputData.setData(templateDate);
			Data_Remark remark = new Data_Remark();
			if(status == 1) {
				remark.setValue("活动开始后点击详情进入个人中心绑定队伍吧\n\n欢迎使用陶学趣公众号");
				outputData.setUrl(Constants.BASEPATH + "/my");
			}else {
				remark.setValue("点击详情去报名其他排期活动吧\n\n欢迎使用陶学趣公众号");
				outputData.setUrl(Constants.BASEPATH + "/hm/act/sign");
			}
			templateDate.setClazz(clazz);
			templateDate.setFirst(first);
			templateDate.setTime(time);
			templateDate.setRemark(remark);
			templateDate.setAdd(add);
			try {
				String json = JSONObject.toJSONString(outputData);
				json = json.replace("clazz", "class");
				HttpClientUtils.postStringJosn(url, json);
			} catch (Exception e) {
				logger.error("--hm reg send, id={}, error={}", info.getId(), e);
			}
		}
	}
	
	
	/**
	 * 透明人注册提醒
	 * @param list
	 */
	private static void hmRegMsgSend(List<HollowManInfo> list, Integer status) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		for(HollowManInfo info : list) {
			OutputTemateData outputData = new OutputTemateData();
			outputData.setTouser(info.getOpenid());
			outputData.setTemplate_id(PropertiesUtil.getProperty("reg_template_id"));
			TemplateDate templateDate = new TemplateDate();
			Data_First first = new Data_First();
			if(status == 1) {
				first.setValue(info.getHmName() + ",你好！恭喜你注册成功\n");
			}else {
				first.setValue(info.getHmName() + ",抱歉！注册失败\n原因：" + info.getReason());
			}
			Data_Keyword keyword1 = new Data_Keyword();
			keyword1.setValue(info.getHmName()+"\n");
			Data_Keyword keyword2 = new Data_Keyword();
			keyword2.setValue(info.getHmMobile() + "\n");
			Data_Remark remark = new Data_Remark();
			if(status == 1) {
				remark.setValue("点击详情进入个人中心申请带队吧\n\n欢迎使用陶学趣公众号");
				outputData.setUrl(Constants.BASEPATH + "/my");
			}else {
				remark.setValue("\n\n欢迎使用陶学趣公众号");
			}
			templateDate.setFirst(first);
			templateDate.setKeyword1(keyword1);
			templateDate.setKeyword2(keyword2);
			templateDate.setRemark(remark);
			outputData.setData(templateDate);
			try {
				HttpClientUtils.postJson(url, outputData);
			} catch (Exception e) {
				logger.error("--hm reg send, id={}, error={}", info.getId(), e);
			}
		}
	}
}
