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
<title>支付结果</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<body>
	<header class="header">
		陶学趣
	</header>
	<div class="clearfix mt44 dbb">
		<p>订单编号：${productOrder.orderNo }</p>
		<c:choose>
			<c:when test="${courseInfo.signWay == 1 }">
				<p>课程信息：${activityInfo.activityName } | ${activityInfo.activityNum }</p>
        		<p>活动排期：<fmt:formatDate value="${activityInfo.startDate}" type="date" dateStyle="full"/></p>
        		<p>预约金：<span class="price">¥<label>${productOrder.resAmount }</label></span></p>
			</c:when>
			<c:otherwise>
				<p>课程信息：${courseInfo.courseName }</p>
				<p>支付金额：<span class="price">¥<label>${productOrder.totalAmount }</label></span></p>
			</c:otherwise>
		</c:choose>
	 	<p>支付时间：<fmt:formatDate value="${productOrder.paySubmitTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></p>
	 	<p><c:choose>
			<c:when test="${productOrder.orderStatus == '000' }">订单状态：<span style="color: green;">支付成功</span></c:when>
			<c:when test="${productOrder.orderStatus == '004' }">订单状态：<span style="color: gray;">失效</span></c:when>
			<c:when test="${productOrder.orderStatus == '005' }">订单状态：<span>退款中</span></c:when>
			<c:when test="${productOrder.orderStatus == '006' }">订单状态：<span style="color: red;">已退款</span></c:when>
		</c:choose></p>
    </div>
	<div class="payGrid" style="position: inherit;">
		<div class="payGrid-content">
			<img src="<%=basePath %>/images/wechat-customer.png"/>
			<p>长按二维码识别，联系客服人员资讯</p>
			<a href="tel:18027274621"><span>客服热线：18027274621</span></a>
		</div>
	</div>
</body>
</html>