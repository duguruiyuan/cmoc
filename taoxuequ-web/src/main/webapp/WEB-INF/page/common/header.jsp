<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>  
<header id="top" class="clearfix">
	<a href="/auth/main"><img class="logoIns" /></a>
	<%-- <div class="top-slog mr20 fr">
		<p class="fr mr20"><a href="javascript:void(0)" onclick="addFavorite();" class="collection">收藏系统</a> <a href="<%=basePath %>/logout" class="logout">退出系统</a></p>
	</div> --%>
	<div class="header-right">
        <a href="<%=basePath %>/logout" role="button" data-toggle="modal"><i class="fa fa-sign-out" style="color:#fff;"></i> 退出</a>
        <a id="changeUserPassword" href="#changeUserPasswordBox" role="button" data-toggle="modal"><i class="fa fa-edit" style="color:#fff;"></i> 修改密码</a>
    	<span><i class="fa fa-user" style="color:#fff;"></i> ${appUser.userName }</span>
    </div>
	<nav>
		<ul>
		</ul>
	</nav>
</header>