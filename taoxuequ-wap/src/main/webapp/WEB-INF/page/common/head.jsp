<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<nav class="mui-bar mui-bar-tab footer">
    <a class="mui-tab-item" href="<%=basePath %>/">
        <span class="mui-tab-label">热文推荐</span>
    </a>
    <a class="mui-tab-item" href="<%=basePath %>/class">
        <span class="mui-tab-label">实践课堂</span>
    </a>
    <a class="mui-tab-item" href="<%=basePath %>/live">
        <span class="mui-tab-label">直播中心</span>
    </a>
    <a class="mui-tab-item mui-active" href="<%=basePath %>/my">
        <span class="mui-tab-label">个人中心</span>
    </a>
</nav>