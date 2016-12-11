<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>透明人注册[陶学趣]</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<body>
	<div class="header">
		<c:choose>
			<c:when test="${error != null && !error.isEmpty() }">
				<p style="color:#f30606">${error }</p>
			</c:when>
			<c:otherwise>
			    <div class="money">
			        <span class="description">预约金</span>
			        <span class="money-sum" id="resAmount">${order.resAmount }</span>
			    </div>
			    <div class="description" id="about">
			        <p>人数过半即包养成功，课程余款在封班后统一支付。若不成功，预约金原路足额返还。</p>
			    </div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="header-bottom">
	
	</div>
	<h3>选择支付方式</h3>
	<div class="pay-type">
		<div class="pay-item">
            <div class="pay-left">
                <img src="<%=basePath %>/images/wechat.png" alt="">
            </div>
            <div class="pay-content">
                <div class="title">微信支付</div>
                <div class="description">适用于经常使用微信的同学</div>
            </div>
            <div class="mui-radio">
                <input type="radio" class="check" name="payType" checked="checked" id="js-wechat">
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
                <input type="radio" class="check" name="payType" id="js-alipay">
            </div>
	    </div> 	
	</div>
	
	<div class="sign-btn">
		<button type="button" id="regMT-btn">确认支付</button>
	</div>
	<script type="text/javascript">
		mui.init();
	</script>
</body>
</html>