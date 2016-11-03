<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/base.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/style.css"/>
<script src="<%=basePath %>/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/js/adapt.js" type="text/javascript" charset="utf-8"></script>
<header class="clearfix">
	<div class="user-head"><img src="<%=basePath %>/images/head.jpg"></div>
	<div class="nav" id="head_title">
		<a href="<%=basePath %>" class="active">首页</a>
		<a href="<%=basePath %>/course/list" id="courseList">课程列表</a>
		<a href="<%=basePath %>/my/course/list" id="my">我的公开课</a>
	</div>
</header>
