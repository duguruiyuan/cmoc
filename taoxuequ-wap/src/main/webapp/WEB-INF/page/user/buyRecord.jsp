<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>购买记录[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">购买记录</h1>
		</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div>
		    <div class="tableList">
		    	<table border="0" cellspacing="0" cellpadding="0">
		    		<thead>
			    		<tr>
			    			<th class="mui-text-center">时间</th>
			    			<th class="mui-text-center">项目名称</th>
			    			<th class="mui-text-center">金额</th>
			    		</tr>
		    		</thead>
		    		<tbody>
		    			<c:choose>
				    		<c:when test="${list == null || list.isEmpty }">
				    			<tr><td colspan="3"><p style="padding: 10px 10px;">暂无购买记录</p></td></tr>
				    		</c:when>
				    		<c:otherwise>
				    			<tr>
				    				<td class="mui-text-center">2015/09/01</td>
				    				<td class="mui-text-center">北京7天夏令营</td>
				    				<td class="mui-text-center">420</td>
				    			</tr>
				    		</c:otherwise>
				    	</c:choose>
		    		</tbody>
		    	</table>
		    </div>
		</div>
		<jsp:include page="/WEB-INF/page/common/_footer.jsp" />
	</body>

</html>