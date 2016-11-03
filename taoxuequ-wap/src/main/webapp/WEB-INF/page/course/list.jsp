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
		<script src="<%=basePath %>/js/course/list.js" type="text/javascript" charset="utf-8"></script>
		<div class="adWrap">
			公告：加入会员以下所有课程免费观看或试听~ <a href="#" class="adLink">加入会员</a>
		</div>
		<div class="listSelect">
			<div class="listSelect-line1 clearfix">
				<span class="listSelect-text">全部</span>
				<span class="listSelect-btn"></span>
			</div>
			<div class="listSelect-line2 clearfix none">
				<div class="listSelect-line2-inner">
					<span>全部</span>
					<span>视频版</span>
					<span>语音版</span>
					<span>推荐</span>
				</div>
			</div>
		</div>
		<div class="videoList">
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
								<span class="videoList-stop2"></span>
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
								<span class="videoList-tj">推荐</span>
							</a>
						</div>
						<p class="videoList-title">公开课第34讲：如何教育青春期的孩子</p>
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>
