<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<title>透明人管理[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<div class="mui-content">
			<div class="regTMHeader" style="background-color: #25C26D;">
				<img src="http://localhost:8060/taoxuequ-wap/images/slider4.jpg" class="regTMPic"><br>
				<span style="line-height: 20px; color: white;">小明</span><br>
			</div>
		    <ul class="mui-table-view mb10">
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/user/bind">
	                   绑定队伍
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/live">
	                     带队记录
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/user/points">
	                     申请带队
	                </a>
	            </li>
	            
	        </ul>
		</div>
		
		<script type="text/javascript">
				mui.init();
				mui('.footer').on('tap','a',function(){document.location.href=this.href;});
		</script>
</body>

</html>
