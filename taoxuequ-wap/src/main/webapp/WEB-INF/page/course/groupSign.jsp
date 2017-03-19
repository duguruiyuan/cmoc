<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>课程报名[陶学趣]</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<body>
	<header class="header">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		陶学趣
		<a class="mui-pull-right group-sign-phone" href="tel:18027274621"><span><i class="fa fa-phone"></i></span></a>
	</header>
	<div class="course-img mt44">
		<img src="${config.imgUrl }${course.courseImg }" id="course-img">
	</div>
	<div class="clearfix mt10 dbb">
	 	<p style="font-size: 16px; color: #000;">报名排期：<fmt:formatDate value="${activityInfo.startDate}" type="date" dateStyle="full"/></p>
        <p>活动名称：${activityInfo.activityName }</p>
        <p>支付金额：<span class="price">¥<label>${course.totalPrice }</label> 组/5人</span></p>
    </div>
	<div class="sign-title">我要报名</div>
	<div class="mui-content">
		<form id="courseSignForm" class="mui-form">
			<div class="mui-textfield">
				<input type="text" id="emerName">
				<label>填写报名联系人</label>
			</div>
			<div class="mui-textfield">
				<input type="text" id="emerMobile">
				<label>填写联系电话</label>
			</div>
		</form>
		<div style="display: none;">
			<h3>选择支付方式</h3>
			<div class="pay-type">
				<div class="pay-item">
		            <div class="pay-left">
		                <img src="<%=basePath %>/images/unionPay.png" alt="">
		            </div>
		            <div class="pay-content">
		                <div class="title">银行转账</div>
		                <div class="description"></div>
		            </div>
		            <div class="mui-radio">
		                <input type="radio" class="check" name="payType" value="ChinaPlay" checked="checked" id="js-unionPay">
		            </div>
			    </div> 	
				<div class="pay-item">
		            <div class="pay-left">
		                <img src="<%=basePath %>/images/wechat.png" alt="">
		            </div>
		            <div class="pay-content">
		                <div class="title">微信支付</div>
		                <div class="description">适用于经常使用微信的同学</div>
		            </div>
		            <div class="mui-radio">
		                <input type="radio" class="check" name="payType" value="WeiXin" id="js-wechat">
		            </div>
				</div>
				<div class="pay-item">
		            <div class="pay-left">
		                <img src="<%=basePath %>/images/alipay.png" alt="">
		            </div>
		            <div class="pay-content">
		                <div class="title">支付宝支付</div>
		                <div class="description">适用于经常使用支付宝的同学</div>
		            </div>
		            <div class="mui-radio">
		                <input type="radio" class="check" name="payType" value="ALiPay" id="js-alipay">
		            </div>
			    </div> 	
			</div>
		</div>
		<div style="height: 100px;padding: 10px 20px;">
	            <input type="checkbox" checked="checked" id="checkAgree"/><a id="agreement" href="javascript:void(0)">《穿越广州报名协议》</a>
		</div>
	</div>
	
	<footer class="footer" style="padding:12px 10px;">
        <div>
        	<div class="group-sign-left">合计：<span class="price">¥<label>${course.totalPrice }</label></span></div>
        	<div class="group-sign-right" id="groupSignSubmit">预约报名</div>
    	</div>
    </footer>
</body>
<script type="text/javascript">
		$(function() {
			$("#agreement").on("click", function(){
				var str = '<div class="code">\
					<div class="agreenment">\
						<div class="agreenment-title">家长须知</div>\
						<div class="agreenment-content">\
							<b>一、家长的权利与义务</b><br/>\
						    1、报名时，家长应向主办方承诺其提供的个人及小朋友的身份信息全部为真实的。<b>不得隐瞒参赛小朋友的重大疾病史，否则，由此导致参赛小朋友的人身安全与主办方无关。</b><br/>\
						    2、家长务必在报名时一次性付清参赛费用。参赛费用中，已包含为执行此次活动而产生的资料费、为参赛小朋友购买的活动期间内的意外保险、服装费、餐费及车票费。<br/>\
						    3、报名后，家长原则上不得要求退出。家长如要求退出的，应至迟不得晚于活动日期前7天向主办方提出书面申请，主办方收到该申请后，有权要求该家长推荐另外一名小朋友报名参加，或要求该家长取得本组其它全部家长的书面自愿推迟活动日期的承诺。\
							家长如在活动日期前7天内要求退出的，应向主办方成功推荐另外一名小朋友报名参加，或取得本组其它全部家长的书面自愿推迟活动日期的承诺。否则，主办方有权不予退还参赛费用。\
							活动进行时，家长及参赛小朋友要求退出的，参赛费用不予退还。<br/>\
						    4、家长应在参赛小朋友出发前，避免其携带贵重物品，未委托主办方代管而损坏或丢失的，主办方不承担赔偿责任。<br/>\
							<b>二、主办方的权利与义务</b><br/>\
						    1、主办方不得无故单方面更改参赛日期，但如遇不可抗力或中至暴雨（以天气预报为参考）等恶劣天气的，为保障参赛小朋友们的人身安全，主办方可更改参赛日期，但主办方应提前告知家长。待不可抗力因素消失后，应第一时间通知家长更改后的参赛日期。家长如不同意新的参赛日期要求退赛的，可参照上述第3条第2款的规定执行。<br/>\
							<b>上款所称的不可抗力是指，在活动路线的任何一处或会场地，发生了在报名时主办方不能预见、不能避免并不能克服的客观情况。包括但不限于自然灾害，如台风、冰雹、地震、海啸、洪水、火山爆发、山体滑坡、其它恶劣天气等；政府行为，如征收、征用、法律及政策的变更；社会异常事件，如战争、武装冲突、罢工、骚乱、暴动、流行病等情况。</b><br/>\
						    2、来回路程期间以及参赛期间，主办方将配置摔伤、撞伤、中暑应急救护包，制定走失、食物中毒等各类应急预案等一切措施保障每位参赛小朋友的人身安全，保证活动项目的安全性，同时为每位参赛小朋友购买参赛期间的人身意外保险。<br/>\
						    3、参赛小朋友因自身疾病而无法继续参赛的，主办方应及时通知家长并有义务就近送医治疗。但主办方不负担其医疗费用。<br/>\
							<b>三、其它</b><br/>\
						    1、如家长与主办方发生争议的，双方应友好协商，协商不成的可向主办方所在地人民法院起诉解决。<br/>\
						    2、本报名协议具有合同性质，一式两份，家长及主办方应严格恪守。\
						</div>\
						<div class="agreenment-btn" onclick="closeCode()">确定</div>\
					</div>\
				</div>';
				$("body").append(str);
			});
			$("#checkAgree").change(function() {
				if(this.checked) {
					$("#groupSignSubmit").removeClass("disabled");
				}else {
					$("#groupSignSubmit").addClass("disabled");
				}
			});
			
			$("#groupSignSubmit").click(function() {
				if($("#groupSignSubmit").hasClass("disabled")) return;
				var emerName = $("#emerName").val();
				var emerMobile = $("#emerMobile").val();
				var channel = $("input[name='payType']:checked").val();
				if(!emerName) {
					alert("请填写报名联系人");
					return;
				}
				if(!checkName(emerName)) {
					alert("报名联系人格式不正确");
					return;
				}
				if(!emerMobile) {
					alert("请填写报名联系电话");
					return;
				}
				if(!checkPhone(emerMobile)) {
					alert("报名联系电话格式不正确");
					return;
				}
				$.ajax({
			 		url : basePath + "/course/json/groupOrder/create",
			 		type : "post",
			 		data : {
			 			emerName: emerName,
			 			emerMobile: emerMobile,
			 			channel: channel,
			 			productId: '${course.id}',
			 			activityId: '${activityInfo.id}'
			 		},
			 		dataType : "json",
			 		async : false,
			 		success : function(data) {
			 			if(data.code == '000') {
			 				//window.location.href = basePath + "/course/group/create?" + data.data;
			 				window.location.href = basePath + "/course/group/customerPay?" + data.data;
			 			}else {
			 				mui.alert(data.msg,'消息提示');
			 			}
			 		}, error:function(){
			 			mui.alert("系统异常，请联系管理员",'消息提示');
	        		}
			 	});
			});
		})
	</script>
</html>