<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"       
    pageEncoding="UTF-8"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>家长邦</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta name="msapplication-tap-highlight" content="no" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta name="format-detection" content="telephone=no,email=no" />
	</head>
	<body>
		<jsp:include page="/WEB-INF/page/common/_header.jsp"></jsp:include>
		<div id="banner">
			<img src="<%=basePath %>/images/banner.jpg"/>
		</div>
		<div class="newsList">
			<div class="listHead clearfix">
				<h4>交流专区</h4>
			</div>
			<div class="newsList-content">
				<ul class="clearfix">
					<li>
						<a href="<%=basePath %>/course/detail/2" class="clearfix">
							<img src="<%=basePath %>/images/pic01.jpg"/>
							<dl>
								<dt>如何教育青春期的孩子</dt>
								<dd>青春期的孩子该用怎样的教育方式来教导孩子</dd>
							</dl>
						</a>
					</li>
					<li>
						<a href="<%=basePath %>/course/detail/2" class="clearfix">
							<img src="<%=basePath %>/images/pic01.jpg"/>
							<dl>
								<dt>如何教育青春期的孩子</dt>
								<dd>青春期的孩子该用怎样的教育方式来教导孩子</dd>
							</dl>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<hr>
		<hr class="mt20">
		<div class="videoList">
			<div class="listHead clearfix">
				<h4>精选课程</h4>
				<a class="listMore" href="<%=basePath %>/course/list">更多课程</a>
			</div>
			<div class="videoList-content">
				<ul>
					<li>
						<div class="videoList-pic">
							<a href="<%=basePath %>/course/detail/1">
								<img src="<%=basePath %>/images/pic02.jpg"/>
								<span class="videoList-tag">免费版</span>
								<span class="videoList-stop"></span>
							</a>
						</div>
						<p class="videoList-title">公开课第34讲：如何教育青春期的孩子</p>
					</li>
					<li>
						<div class="videoList-pic">
							<a href="<%=basePath %>/course/detail/1">
								<img src="<%=basePath %>/images/pic02.jpg"/>
								<span class="videoList-tag">免费版</span>
								<span class="videoList-stop"></span>
							</a>
						</div>
						<p class="videoList-title">公开课第34讲：如何教育青春期的孩子</p>
					</li>
					<li>
						<div class="videoList-pic">
							<a href="<%=basePath %>/course/detail/1">
								<img src="<%=basePath %>/images/pic02.jpg"/>
								<span class="videoList-tag">免费版</span>
								<span class="videoList-stop"></span>
							</a>
						</div>
						<p class="videoList-title">公开课第34讲：如何教育青春期的孩子</p>
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>
