<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<title>个人收藏[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">个人收藏</h1>
		</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div class="mui-content">
		    <ul class="mui-table-view" style="margin-top: 0px;">
		    	<c:choose>
		    		<c:when test="${list == null || list.isEmpty }">
		    			<p style="padding: 10px 10px;">暂无收藏记录</p>
		    		</c:when>
		    		<c:otherwise>
		    			<li class="mui-table-view-cell">
			                <a class="mui-navigate-right">
			                    课程报名：中学MYP跨学科（课程描述）
			                </a>
			            </li>
		    		</c:otherwise>
		    	</c:choose>
	        </ul>
		</div>
		<jsp:include page="/WEB-INF/page/common/_footer.jsp" />
	</body>

</html>