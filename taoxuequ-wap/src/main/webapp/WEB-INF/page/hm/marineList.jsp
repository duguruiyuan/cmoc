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
<title>队伍列表[陶学趣]</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<body>
		<div class="regTMHeader" style="height: 140px;">
			<span class="regTMTitle">绑定队伍</span><br>
		</div>
		<div class="regTM-formTitle">队伍列表</div>
		<div class="mui-content ranksList">
			<ul class="mui-table-view">
				<c:forEach var="item" items="${marines }">
					<li class="mui-table-view-cell">
				    	<a class="mui-navigate-right" qrcode-url="${item.bindQrcodeUrl }" mar-name="${item.marineName }">
					    	<div class="ranksList-content">${item.marineName }<span class="ranksList-people">(${item.marineTeam })</span></div>
					    	<div class="ranksList-code">二维码</div>
				    	</a>
				    </li>
				</c:forEach>
			</ul>
		</div>
		
		<script type="text/javascript">
			$(function(){
				$(".ranksList").on("click", ".mui-navigate-right", function(){
					var str = '<div class="code">\
						<div class="code-inner">\
							<div class="code-title">确认绑定队伍：' + $(this).attr("mar-name") + '<p>[陶学趣]长按二维码绑定队伍</p></div>\
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