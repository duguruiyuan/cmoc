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
<script src="<%=basePath %>/js/plugins/wechat-config.js?v=${config.version}" type="text/javascript" charset="utf-8"></script>
<body>
	<header class="header">
		<a class="mui-icon mui-icon-left-nav mui-pull-left" href="javascript:void(0)"></a>
		陶学趣
		<a class="mui-pull-right group-sign-phone" href="tel:18675840368"><span><i class="fa fa-phone"></i></span></a>
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
		<div style="height: 100px;padding: 10px 20px;">
	            <input type="checkbox" checked="checked"/><a id="agreement" href="javascript:void(0)">《穿越广州报名协议》</a>
		</div>
	</div>
	
	<footer class="footer" style="padding:12px 10px;">
        <div>
        	<div class="group-sign-left">合计：<span class="price">¥<label>${course.totalPrice }</label></span></div>
        	<div class="group-sign-right" id="groupSignSubmit">预约报名</div>
    	</div>
    </footer>
    <div id="all-body" style="width: 100%;height:100%;color:grey;"></div>
	<script type="text/javascript">
		$(function() {
			$("#agreement").on("click", function(){
				var str = '<div class="agreenment">\
					<div class="code-inner">\
						<div class="code-title">家长须知</div>\
						<div class="code-pic">\
							<b>一、家长的权利与义务</b>\
							<span>1、报名时，家长应向主办方承诺其提供的个人及小朋友的身份信息全部为真实的。<b>不得隐瞒参赛小朋友的重大疾病史，否则，由此导致参赛小朋友的人身安全与主办方无关。<b></span><br/>\
						</div>\
						<div class="code-btn" onclick="closeCode()">确定</div>\
					</div>\
				</div>';
				$("#all-body").append(str);
			});
			function closeCode() {
				$(".agreenment").remove();
			}
			$("#groupSignSubmit").click(function() {
				var emerName = $("#emerName").val();
				var emerMobile = $("#emerMobile").val();
				var channel = $("input[name='payType']:checked").val();
				if(!emerName) {
					alert("请填写报名联系人");
					return;
				}
				if(!emerMobile) {
					alert("请填写联系电话");
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
			 				window.location.href = basePath + "/pay/wechatpay?" + data.data;
			 				window.location.href = basePath + "/course/group/create?" + data.data;
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
</body>
</html>