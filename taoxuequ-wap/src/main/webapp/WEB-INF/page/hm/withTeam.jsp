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
		<title>带队记录[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<div class="mui-content">
		    <div class="regTMHeader" style="background-color: #25C26D;">
				<img src="${userInfo.headimgurl }" class="regTMPic"><br>
				<span style="line-height: 20px; color: white;">${hm.hmName }</span><br>
			</div>
			<div style="padding:10px 10px 0px;font-size: 14px;">
	       		<i class="fa fa-list-ol"></i> 带过的队伍列表
	       	</div>
		    <div class="tableList">
			    <c:choose>
			    	<c:when test="${records == null || records.isEmpty()}">
		   				<p style="padding: 10px 20px;">暂无带队记录</p>
		   			</c:when>
		   			<c:otherwise>
		   				<table border="0" cellspacing="0" cellpadding="0">
				    		<thead>
					    		<tr>
					    			<th width="90">时间</th>
					    			<th>活动名称</th>
					    			<th>活动期数</th>
					    			<th>战队名称</th>
					    		</tr>
				    		</thead>
				    		<tbody>
		    					<c:forEach var="item" items="${records }">
		    						<tr>
					    				<td class="mui-text-center"><fmt:formatDate value='${item.startDate}' pattern='yyyy-MM-dd' /></td>
					    				<td class="mui-text-center">${item.activityName }</td>
					    				<td class="mui-text-center">${item.activityNum }</td>
					    				<td class="mui-text-center"><a href="<%=basePath %>/live/marine/detail/${item.id}">${item.marineName }</a></td>
					    			</tr>
		    					</c:forEach>
				    		</tbody>
				    	</table>
		   			</c:otherwise>
		   		</c:choose>
		    </div>
		</div>
	</body>

</html>