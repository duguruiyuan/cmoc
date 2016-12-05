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
<title>活动排期 绑定队伍[陶学趣]</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<body>
	<div class="regTMHeader" style="height: 170px;">
		<span class="regTMTitle">绑定队伍</span><br>
	</div>
	<div class="ranksList-title">排期[活动、日期、队伍]</div>
	<div class="mui-content ranksList">
		<ul class="mui-table-view">
			<c:if test="${activitys == null || activitys.isEmpty()}">
	    		<p style="padding: 15px 20px;">暂无排期活动</p>
	    	</c:if>
	    	<c:forEach var="item" items="${activitys }">
	    		<li class="mui-table-view-cell">
				    <a class="mui-navigate-right" href="<%=basePath %>/hm/act/${item.id}">
				    	<div class="ranksList-content">
				    		<span>${item.activityName }</span>
				    		&nbsp;<span class="ranksList-time"><fmt:formatDate value='${item.startDate}' pattern='MM月dd日' /> </span>&nbsp;
				    		<span class="ranksList-state">准备中</span>
				    	</div>
				    	<div class="ranksList-code">绑定队伍</div>
			    	</a>
			    </li>
	    	</c:forEach>
		</ul>
	</div>
		
	<script type="text/javascript">
		mui.init();
		 /*$(function(){
			var str = '<div class="code">\
							<div class="code-inner">\
								<div class="code-title">[穿越部落]长按二维码绑定队伍</div>\
								<div class="code-pic">\
									<img src="../images/code.png">\
								</div>\
								<div class="code-btn">确定</div>\
							</div>\
						</div>';
			
			$(".ranksList").on("click", ".mui-navigate-right", function(){
				$("body").append(str);
			});
			
			$("body").on("click", ".code-btn", function(){
				$(".code").remove();
			})
		}) */
	</script>
</body>
</html>