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
<!doctype html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>学习积分[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">学习积分</h1>
		</header>
		
		<div class="mui-content">
			<div style="height: 234px; position: relative;">
				<img src="<%=basePath %>/images/slider1.jpg" width="100%">
				<p class="point-tip">您目前有 0 积分</p>
			</div>
		    <div class="tableList">
		   		<table border="0" cellspacing="0" cellpadding="0">
		    		<thead>
			    		<tr>
			    			<th class="mui-text-center">时间</th>
			    			<th class="mui-text-center">项目名称</th>
			    			<th class="mui-text-center">金额</th>
			    			<th class="mui-text-center">积分兑换</th>
			    		</tr>
		    		</thead>
		    		<tbody>
		    			<c:choose>
				    		<c:when test="${list == null || list.isEmpty }">
				    			<tr><td colspan="4"><p style="padding: 10px 10px;">暂无可兑换课程，敬请期待...</p></td></tr>
				    		</c:when>
				    		<c:otherwise>
				    			<tr>
				    				<td class="mui-text-center">2015/09/01</td>
				    				<td class="mui-text-center">北京7天夏令营</td>
				    				<td class="mui-text-center">420</td>
				    				<td class="mui-text-center">
				    					<button type="button" class="mui-btn mui-btn-primary">兑换</button>
				    				</td>
				    			</tr>
				    		</c:otherwise>
				    	</c:choose>
		    		</tbody>
		    	</table>
		    </div>
		</div>
</body>

</html>