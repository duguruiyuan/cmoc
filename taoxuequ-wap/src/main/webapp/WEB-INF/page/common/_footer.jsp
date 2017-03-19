<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<div class="add"></div>
<nav class="mui-bar mui-bar-tab" id="mui-bar">
    <a class="foot-tab-item" id="my-index" href="<%=basePath %>/index">
    	<i class="mui-icon fa fa-eye"></i>
        <span class="mui-tab-label">趣看看</span>
    </a>
    <a class="foot-tab-item" href="<%=basePath %>/course">
    	<span class="mui-icon fa fa-book"></span>
        <span class="mui-tab-label">趣学学</span>
    </a>
    <a class="foot-tab-item" href="<%=basePath %>/live">
    	<span class="mui-icon fa fa-video-camera"></span>
        <span class="mui-tab-label">趣直播</span>
    </a>
    <a class="foot-tab-item" id="qu-my" href="<%=basePath %>/my">
    	<span class="mui-icon fa fa-user"></span>
        <span class="mui-tab-label">趣我的</span>
    </a>
</nav>

<script type="text/javascript">
	$(function() {
		var isFind = false;
		$("#mui-bar a").each(function(index, item) {
			if(window.location.href.indexOf(item.href) != -1){
				$(item).addClass("tab-active").siblings().removeClass("tab-active");
				isFind = true;
			}
		});
		if(!isFind) {
			$("#my-index").each(function(i,it){
				if(it.href.indexOf(window.location.href) != -1) {
					$("#my-index").addClass("tab-active").siblings().removeClass("tab-active");
				}else {
					$("#qu-my").addClass("tab-active").siblings().removeClass("tab-active");
				}
			});
		}
	})
</script>