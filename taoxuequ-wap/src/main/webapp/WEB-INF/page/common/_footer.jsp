<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<nav class="mui-bar mui-bar-tab footer">
    <a class="foot-tab-item" href="<%=basePath %>/">
        <span class="mui-tab-label">首页</span>
    </a>
    <a class="foot-tab-item" href="<%=basePath %>/course">
        <span class="mui-tab-label">实践课程</span>
    </a>
    <a class="foot-tab-item" href="<%=basePath %>/live">
        <span class="mui-tab-label">直播中心</span>
    </a>
    <a class="foot-tab-item tab-active" href="<%=basePath %>/my">
        <span class="mui-tab-label">个人中心</span>
    </a>
</nav>
<script type="text/javascript">
	$(".footer a").each(function(index, item) {
		if(item.href == window.location.href){
			$(item).addClass("tab-active").siblings().removeClass("tab-active");
		}
	});
</script>