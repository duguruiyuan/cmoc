<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"       
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
		<title>热文推荐[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<header class="mui-bar mui-bar-nav">
		    <h1 class="mui-title">热文推荐</h1>
		</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div class="mui-content">
		    <div class="mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted mt10">
		        <div class="mui-scroll">
		            <a class="mui-control-item mui-active">
		            	校辅实践
		            </a>
		            <a class="mui-control-item">
		              	跨学科
		            </a>
		            <a class="mui-control-item">
		               	营天下
		            </a>
		            <a class="mui-control-item">
		               	教育杂谈
		            </a>
		            <a class="mui-control-item">
		               	实践基地
		            </a>
		        </div>
		    </div>
		    <ul class="mui-table-view mb10">
		        <li class="mui-table-view-cell mui-media">
		            <a href="javascript:;">
		                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text2.png" width="40" height="30">
		                <div class="mui-media-body">
		                   	1幸福
		                    <p class="mui-ellipsis">能和心爱的人一起睡觉，是件幸福的事情；可是，打呼噜怎么办？</p>
		                </div>
		            </a>
		        </li>
		        <li class="mui-table-view-cell mui-media">
		            <a href="javascript:;">
		                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text2.png" width="40" height="30">
		                <div class="mui-media-body">
		                    2木屋
		                    <p class="mui-ellipsis">想要这样一间小木屋，夏天挫冰吃瓜，冬天围炉取暖.</p>
		                </div>
		            </a>
		        </li>
		        <li class="mui-table-view-cell mui-media">
		            <a href="javascript:;">
		                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text2.png" width="40" height="30">
		                <div class="mui-media-body">
		                    3CBD
		                    <p class="mui-ellipsis">烤炉模式的城，到黄昏，如同打翻的调色盘一般.</p>
		                </div>
		            </a>
		        </li>
		        <li class="mui-table-view-cell mui-media">
		            <a href="javascript:;">
		                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text2.png" width="40" height="30">
		                <div class="mui-media-body">
		                    3CBD
		                    <p class="mui-ellipsis">烤炉模式的城，到黄昏，如同打翻的调色盘一般.</p>
		                </div>
		            </a>
		        </li>
		        <li class="mui-table-view-cell mui-media">
		            <a href="javascript:;">
		                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text2.png" width="40" height="30">
		                <div class="mui-media-body">
		                    3CBD
		                    <p class="mui-ellipsis">烤炉模式的城，到黄昏，如同打翻的调色盘一般.</p>
		                </div>
		            </a>
		        </li>
		        <li class="mui-table-view-cell mui-media">
		            <a href="javascript:;">
		                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text2.png" width="40" height="30">
		                <div class="mui-media-body">
		                    3CBD
		                    <p class="mui-ellipsis">烤炉模式的城，到黄昏，如同打翻的调色盘一般.</p>
		                </div>
		            </a>
		        </li>
		        <li class="mui-table-view-cell mui-media">
		            <a href="javascript:;">
		                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text2.png" width="40" height="30">
		                <div class="mui-media-body">
		                    3CBD
		                    <p class="mui-ellipsis">烤炉模式的城，到黄昏，如同打翻的调色盘一般.</p>
		                </div>
		            </a>
		        </li>
		        <li class="mui-table-view-cell mui-media">
		            <a href="javascript:;">
		                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text2.png" width="40" height="30">
		                <div class="mui-media-body">
		                    3CBD
		                    <p class="mui-ellipsis">烤炉模式的城，到黄昏，如同打翻的调色盘一般.</p>
		                </div>
		            </a>
		        </li>
		    </ul>
		
		</div>
		<jsp:include page="/WEB-INF/page/common/_footer.jsp" />
	</body>
</html>