<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<link href="<%=basePath %>/css/font-awesome.min.css" rel="stylesheet">

<div class="add"></div>
<nav class="mui-bar mui-bar-tab">
    <a class="foot-tab-item" href="<%=basePath %>/">
    	<i class="mui-icon fa fa-home"></i>
        <span class="mui-tab-label">首页</span>
    </a>
    <a class="foot-tab-item" href="<%=basePath %>/course">
    	<span class="mui-icon fa fa-book"></span>
        <span class="mui-tab-label">实践课程</span>
    </a>
    <a class="foot-tab-item" href="<%=basePath %>/live">
   		 <span class="mui-icon fa fa-video-camera"></span>
        <span class="mui-tab-label">直播中心</span>
    </a>
    <a class="foot-tab-item" href="<%=basePath %>/my">
    	<span class="mui-icon fa fa-user"></span>
        <span class="mui-tab-label">个人中心</span>
    </a>
</nav>

<script type="text/javascript">
	$(".mui-bar a").each(function(index, item) {
		if(window.location.href.indexOf(item.href) != -1){
			$(item).addClass("tab-active").siblings().removeClass("tab-active");
		}
	});
</script>