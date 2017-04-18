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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<div class="mui-content">
			<div class="regTMHeader" style="background-color: #25C26D;">
				<img src="${userInfo.headimgurl }" class="regTMPic"><br>
				<span style="line-height: 20px; color: white;">${hm.hmName }</span><br>
			</div>
		    <ul class="mui-table-view mb10">
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/hm/act/list">
	                   	<i class="fa fa-link fs20" style="color: green;"></i> 绑定队伍
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/hm/withteam">
	                     <i class="fa fa-users fs20" style="color: blue"></i> 带队记录
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/hm/act/sign">
	                     	<i class="fa fa-hand-rock-o" style="color: #C3822C"></i> 申请带队
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/live">
	                     	<i class="fa fa-video-camera" style="color: #2CC33F"></i> 直播中心
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/user/buyRecord">
	                     	<i class="fa fa-opencart" style="color: #f3c90d"></i> 我的订单
	                </a>
	            </li>
	        </ul>
		</div>
		<jsp:include page="/WEB-INF/page/common/_footer.jsp" />
		<script type="text/javascript">
			mui.init();
			mui('.footer').on('tap','a',function(){document.location.href=this.href;});
			var snsToken = '${snsToken}';
			setAccessToken(snsToken);
		</script>
</body>

</html>
