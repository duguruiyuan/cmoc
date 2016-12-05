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
		<title>个人中心[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<header class="mui-bar mui-bar-nav">
		    <h1 class="mui-title">个人中心</h1>
		</header>
		<div class="mui-content">
		    <div id="slider" class="mui-slider">
		      <div class="mui-slider-group mui-slider-loop">
		        <div class="mui-slider-item mui-slider-item-duplicate">
		          <a href="#">
		            <img src="<%=basePath %>/images/slider4.jpg" width="400" height="200">
		            <p class="mui-slider-title">文字说明4</p>
		          </a>
		        </div>
		      </div>
		    </div>
		    <ul class="mui-table-view mt10 mb10">
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/user/bind">
	                   用户绑定
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/live">
	                     直播中心
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/user/points">
	                     学习积分
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/user/buyRecord">
	                     购买记录
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/user/collection">
	                     个人收藏
	                </a>
	            </li>
	            <li class="mui-table-view-cell">
	                <a class="mui-navigate-right" href="<%=basePath%>/about">
	                     关于我们
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
