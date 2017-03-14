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
<title>联系客服支付</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<body>
	<header class="header">
		陶学趣
	</header>
	<div class="clearfix mt44 dbb">
		<p>订单编号：${productOrder.orderNo }</p>
	 	<p>课程信息：${activityInfo.activityName } | ${activityInfo.activityNum }</p>
        <p>活动排期：<fmt:formatDate value="${activityInfo.startDate}" type="date" dateStyle="full"/></p>
        <p>支付金额：<span class="price">¥<label>${productOrder.totalAmount }</label></span></p>
    </div>
	<div class="payGrid" style="position: inherit;">
		<div class="payGrid-title">报名支付方式</div>
		<div class="payGrid-content">
			<img src="<%=basePath %>/images/wechat-customer.png"/>
			<p>长按二维码识别，联系客服人员进行支付</p>
			<a href="tel:18027274621"><span>客服热线：18027274621</span></a>
		</div>
	</div>
</body>
</html>