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
		<title>战队列表-陶学趣</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">战队列表</h1>
		</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div class="mui-content">
			<div class="banner pr">
				<img src="<%=basePath %>/images/slider2.jpg">
				<div class="marines-banner-title">
					<div class="marines-banner-title1">第18期</div>
					<div class="marines-banner-title2">2016-11-12 | 穿越课堂</div>
				</div>
			</div>
		    <div class="pb10 marines-list">
			    <ul class="mui-table-view">
			        <li class="mui-table-view-cell mui-media">
			            <a href="<%=basePath %>/live/marine/detail/1">
			                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text.png">
			                <div class="mui-media-body">
			                    <div class="marines-list-lev1">胜利队</div>
			                    <div class="marines-list-lev2">二年级3号线</div>
			                </div>
			            	<span class="mui-badge mui-badge-inverted">21票</span>
			            </a>
			        </li>
			        <li class="mui-table-view-cell mui-media">
			            <a href="<%=basePath %>/live/marine/detail/2">
			                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text.png">
			                <div class="mui-media-body">
			                    <div class="marines-list-lev1">胜利队</div>
			                    <div class="marines-list-lev2">二年级3号线</div>
			                </div>
			            	<span class="mui-badge mui-badge-inverted">21票</span>
			            </a>
			        </li>
			        <li class="mui-table-view-cell mui-media">
			            <a href="<%=basePath %>/live/marine/detail/3">
			                <img class="mui-media-object mui-pull-left" src="<%=basePath %>/images/text.png">
			                <div class="mui-media-body">
			                    <div class="marines-list-lev1">胜利队</div>
			                    <div class="marines-list-lev2">二年级3号线</div>
			                </div>
			            	<span class="mui-badge mui-badge-inverted">21票</span>
			            </a>
			        </li>
			    </ul>
		    </div>
		</div>
		
		<script type="text/javascript">
			mui.init();
			mui('.footer').on('tap','a',function(){document.location.href=this.href;});
			
		</script>
	</body>

</html>