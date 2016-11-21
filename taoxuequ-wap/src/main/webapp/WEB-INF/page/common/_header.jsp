<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<link href="<%=basePath %>/css/mui.min.css" rel="stylesheet" />
<link href="<%=basePath %>/css/mui.picker.css" rel="stylesheet" />
<link href="<%=basePath %>/css/mui.poppicker.css" rel="stylesheet" />
<link  href="<%=basePath %>/css/display.css" rel="stylesheet" type="text/css"/>

<script src="<%=basePath %>/js/plugins/mui/zepto.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/js/plugins/mui/mui.js"></script>
<script src="<%=basePath %>/js/plugins/mui/mui.picker.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/js/plugins/mui/mui.poppicker.js" type="text/javascript" charset="utf-8"></script>
