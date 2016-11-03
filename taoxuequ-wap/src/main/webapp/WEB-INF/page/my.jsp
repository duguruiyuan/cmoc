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
		<script src="<%=basePath %>/js/my.js" type="text/javascript" charset="utf-8"></script>
		<div class="listSelect">
			<div class="listSelect-line1 clearfix">
				<span class="listSelect-text2">公开课</span>
				<span class="listSelect-btn"></span>
			</div>
		</div>
		<hr>
		<div class="videoList">
			<div class="videoList-content">
				<ul class="clearfix">
					<li>
						<div class="videoList-pic">
							<a href="#">
								<img src="<%=basePath %>/images/pic02.jpg"/>
								<span class="videoList-tag">免费版</span>
								<span class="videoList-stop"></span>
							</a>
						</div>
						<p class="videoList-title">公开课第34讲：如何教育青春期的孩子</p>
						<p class="videoList-intro">1.青春期孩子的教育方式  2.青春期孩子的教育方式  3.青春期孩子的教育方式 </p>
					</li>
					<li>
						<div class="videoList-pic">
							<a href="#">
								<img src="<%=basePath %>/images/pic02.jpg"/>
								<span class="videoList-tag">免费版</span>
								<span class="videoList-stop"></span>
							</a>
						</div>
						<p class="videoList-title">公开课第34讲：如何教育青春期的孩子</p>
						<p class="videoList-intro">1.青春期孩子的教育方式  2.青春期孩子的教育方式  3.青春期孩子的教育方式 </p>
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>
