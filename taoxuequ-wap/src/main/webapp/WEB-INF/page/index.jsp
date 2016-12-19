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
	<title>欢迎光临[陶学趣]</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp" />
<body class="bc-fff">
	<header class="header">淘学趣</header>
	<div id="box1">
		<a href="<%=basePath%>/">
			<div class="bigone1">
				<img src="<%=basePath%>/images/review.png" />
			</div>
			<p>顺德碧桂MYP探究服务结束</p>
		</a>
	</div>
	<div class="h10"></div>
	<div id="box2">
		<div class="bigone2">
			<a href="#"><img src="<%=basePath%>/images/camp-edu.png" /></a>
		</div>
		<div class="bigone3 br-l2 br-b2">
			<div class="small">
				<a href="#"><img src="<%=basePath%>/images/practice.png" /></a>
			</div>
			<div class="small br-l1">
				<a href="#"><img src="<%=basePath%>/images/stem.png" /></a>
			</div>
		</div>
		<div class="bigone3 br-l2">
			<div class="small">
				<a href="#"><img src="<%=basePath%>/images/customize.png" /></a>
			</div>
			<div class="small br-l1">
				<a href="#"><img src="<%=basePath%>/images/explore.png" /></a>
			</div>
		</div>
	</div>
	<div class="h10" style="clear:both;"></div>
	<div id="host-recommend">
		<div class="recommend">
			<div><img src="<%=basePath%>/images/recommend.png" /></div>
			<a href="<%=basePath %>/course">
				<span class="mui-icon mui-icon-arrowright"></span>
				<span class="all">全部</span>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider1.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider2.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider3.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider4.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider3.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider4.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider3.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider4.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider3.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider4.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider3.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
		<div class="recommend_box">
			<a href="#">
				<img src="<%=basePath%>/images/slider4.jpg">
				<p class="bottom-title">小小化学家-中英青少年科学实验营</p>
			</a>
		</div>
	</div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp" />
</body>

</html>