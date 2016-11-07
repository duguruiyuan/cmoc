<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.xuequ.cmoc.auth.AppUser" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"       
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>   
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	AppUser user = (AppUser)request.getAttribute("appUser");
	String menuList = JSONArray.toJSONString(user.getSysMenuList());
%>     
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>支付清算管理系统</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<link rel="icon" href="<%=basePath %>/favicon.ico" type="image/x-icon"/>  
		<link rel="shortcut icon" href="<%=basePath %>/favicon.ico" type="image/x-icon" />
		<link href="<%=basePath %>/animated_favicon.gif" rel="icon" type="image/gif" />  
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/main.css"/>
		<link href="//cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
		<link href="<%=basePath %>/css/bootstrap.css" rel="stylesheet">
		<!--[if lt IE 9]><script src="<%=basePath %>/js/html5.js"></script><![endif]-->
		<script src="<%=basePath %>/js/plugin/jquery-2.1.4.min.js"></script>		
		<script src="<%=basePath %>/js/main.js"></script>
	    <script src="<%=basePath %>/js/common.js"></script>	
	</head>
	<body>
		<header id="top" class="clearfix">
			<a href="/auth/main"><img class="logoIns" /></a>
			<%-- <div class="top-slog mr20 fr">
				<p class="fr mr20"><a href="javascript:void(0)" onclick="addFavorite();" class="collection">收藏系统</a> <a href="<%=basePath %>/logout" class="logout">退出系统</a></p>
			</div> --%>
			<div class="header-right">
		        <a href="<%=basePath %>/logout" role="button" data-toggle="modal"><i class="fa fa-sign-out" style="color:#fff;"></i> 退出</a>
		        <a id="changeUserPassword" href="#changeUserPasswordBox" role="button" data-toggle="modal"><i class="fa fa-edit" style="color:#fff;"></i> 修改密码</a>
		    	<a href="javascript:void(0)"><i class="fa fa-user" style="color:#fff;"></i> ${appUser.userName }</a>
		    </div>
			<nav>
				<ul>
				</ul>
			</nav>
		</header>
		<section id="wrap" class="clearfix">
			<aside id="sider" class="left">
				<div class="sdmenu">
					<c:forEach var="item" items="${appUser.sysMenuList }">
						<div class="collapsed">
               				<span class="menuLev1">${item.resourceName }</span>
               				<c:if test="${item.hasChild }">
								<c:forEach var="user" items="${item.subMenuList }" varStatus="status">
									<a onclick="mainShow('${user.resourceName }','<%=basePath %>${user.resourceUrl }','${user.parentResourceId }-${user.idResource }',780,'${user.isSystemConfMenu }')">${user.resourceName }</a>
								</c:forEach>
							</c:if>
          				</div>
					</c:forEach>
				</div>
			</aside>
			<section id="mainWrap">
				<div id="tabBar">
					<ul>
						<li class="tabItem tabOn" id="tabItem-00-00" data-order="00-00" first-page="Y" onclick="tabItem('00-00',this)"><div class="tabCenter"><div class="tabRight"><div class="tabLeft"><span class="tab-ico"></span> <span class="tab-text">首页</span></div></div></div></li>
					</ul>
					<div id="tabBar-leftBtn"><</div>
					<div id="tabBar-rightBtn">></div>
				</div>
				<div id="main">
					<div class="webContain main-00-00" id="main-00-00" style="padding:0 0 0 4px;">
						<iframe id="iframe-00-00" src="<%=basePath %>/common/home" width="100%" frameborder="0" scrolling="no" onload="iFrameHeight('iframe-00-00')"></iframe>
					</div>
				</div>
			</section>
		</section>
		<div id="siderBtn" class="none"></div>
		<script>
			var menuList = '<%=menuList %>';
			var basePath = '<%=basePath %>';
		</script>
	</body>
</html>