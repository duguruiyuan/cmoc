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
<body style="font-size: 15px;">
	<header class="header">
		<a class="mui-icon mui-icon-left-nav mui-pull-left"></a>
		队伍管理
	</header>
	<div class="mt44"></div>
	<c:forEach var="itm" items="${orderList }">
		<div style="margin-top: 10px;">
			<div style="padding: 10px;border-top: 1px solid #E3D7D7;border-bottom: 1px solid #F9DEDE;">
				<div style="float: left;">${itm.activityName } <fmt:formatDate value="${itm.startDate}" type="date" dateStyle="full"/></div>
				<div style="float: right;"><c:if test="${itm.member == 0 }"><a href="<%=basePath %>/course/group/create?orderNo=${itm.orderNo}"><span class="group-but">尚未组队</span></a></c:if></div>
				<div style="clear:both"></div>
			</div>
			<div style="padding: 10px;border-bottom: 1px solid #F9DEDE;">订单编号：${itm.orderNo }</div>
			<div style="padding: 10px;border-bottom: 1px solid #F9DEDE;">实际应付款：${itm.totalPrice }</div>
			<div style="padding: 10px;border-bottom: 1px solid #F9DEDE;">
				<div style="float: left;">总价：${itm.totalPrice }</div>
				<div style="float: right;">
					<c:choose>
						<c:when test="${itm.orderStatus == '000' }"><span class="group-but2">已支付</span></c:when>
						<c:otherwise><span class="group-but1 paySubmit">未付款 (转账支付)</span></c:otherwise>
					</c:choose>
					<a href="<%=basePath%>/course/group/add?oNo=${itm.orderNo}"><span class="group-but2">管理队伍</span></a>
				</div>
				<div style="clear:both"></div>
			</div>
			<div style="height:20px;"></div>
		</div>
	</c:forEach>
	
	<jsp:include page="/WEB-INF/page/common/_footer.jsp" />
	<script type="text/javascript">
		$(function() {
			$(".paySubmit").on("click", function(){
				var str = '<div class="code">\
					<div class="payGrid">\
						<div class="payGrid-title">报名支付方式</div>\
						<div class="payGrid-content">\
							<img src="<%=basePath%>/images/wechat-customer.png"/>\
							<p>长按二维码识别，联系客服人员进行支付</p>\
							<a href="tel:18027274621"><span>客服热线：18027274621</span></a>\
						</div>\
						<div class="payGrid-btn" onclick="closeCode()">关闭</div>\
					</div>\
				</div>';
				$("body").append(str);
			});
		});
	</script>
</body>
</html>