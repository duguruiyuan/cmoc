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
<title>活动排期 申请带队[陶学趣]</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<body>
	<div class="regTMHeader" style="height: 170px;">
		<span class="regTMTitle">申请带队</span><br>
	</div>
	<div class="ranksList-title">排期[活动、日期]</div>
	<div class="mui-content ranksList">
		<ul class="mui-table-view">
			<c:if test="${activitys == null || activitys.isEmpty()}">
	    		<p style="padding: 15px 20px;">暂无排期活动</p>
	    	</c:if>
	    	<c:forEach var="item" items="${activitys }">
	    		<li class="mui-table-view-cell">
				    <a class="mui-navigate-right" qrcode-url="${item.qrcodeUrl }" >
				    	<div class="ranksList-content">
				    		<span>${item.activityName }</span>
				    		&nbsp;<span><fmt:formatDate value='${item.startDate}' pattern='yyyy/MM/dd' /> </span>&nbsp;
				    	</div>
				    	<div class="ranksList-code">申请带队</div>
			    	</a>
			    </li>
	    	</c:forEach>
		</ul>
	</div>
		
	<script type="text/javascript">
		mui.init();
		$(function(){
			$(".ranksList").on("click", ".mui-navigate-right", function(){
				var str = '<div class="code">\
					<div class="code-inner">\
						<div class="code-title"><p>[陶学趣]长按二维码报名参与活动</p></div>\
						<div class="code-pic">\
							<img src="' + $(this).attr("qrcode-url") + '">\
						</div>\
						<div class="code-btn">确定</div>\
					</div>\
				</div>';
				$("body").append(str);
			});
			
			$("body").on("click", ".code-btn", function(){
				$(".code").remove();
			})
		})
	</script>
</body>
</html>