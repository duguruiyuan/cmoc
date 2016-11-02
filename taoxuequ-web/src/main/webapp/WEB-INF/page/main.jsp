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
		<!--[if lt IE 9]><script src="<%=basePath %>/js/html5.js"></script><![endif]-->
		<script src="<%=basePath %>/js/jquery.min.js"></script>		
		<script src="<%=basePath %>/js/main.js"></script>
		<script src="<%=basePath %>/js/plugin/artDialog/artDialog.source.js?skin=chrome"></script>
	    <script src="<%=basePath %>/js/plugin/artDialog/plugins/iframeTools.source.js"></script>
	    <script src="<%=basePath %>/js/common.js"></script>	
	</head>
	<body class="image">
		<header id="top" class="clearfix">
			<div id="logoArea">
				<div class="logoPic fl"><img src="<%=basePath %>/image/logo.png"></div>
				<%-- 
				<div class="logoTextWrap fl ml15">
					<h3 class="logoText-ch">客服工单管理系统</h3>
				</div>
				--%>
			</div>
			<div class="top-slog mr20 fr">
				<p class="fr mr20"><a href="javascript:void(0)" onclick="addFavorite();" class="collection">收藏系统</a> <a href="<%=basePath %>/logout" class="logout">退出系统</a></p>
			</div>
			<nav>
				<ul>
					<c:forEach var="item" items="${appUser.sysChannelList }" varStatus="status">
						<c:choose>
							<c:when test="${status.index == 0 }">
								<li onclick="channelShow('N',this,'${item.idChannel}')" id="channelConf" ><div class="navCenter"><div class="navRight"><div class="navLeft">${item.channelName}</div></div></div></li>
							</c:when>
							<c:otherwise>
								<li onclick="channelShow('N',this,'${item.idChannel}')" ><div class="navCenter"><div class="navRight"><div class="navLeft">${item.channelName}</div></div></div></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<li onclick="channelShow('Y',this)" id="sysConf" data-order="00-01" class="navOn"><div class="navCenter"><div class="navRight"><div class="navLeft">系统配置</div></div></div></li>
				</ul>
			</nav>
			<%--
			<div id="top-searchWrap">
				<div class="top-searchKefu"></div>
				<div class="top-searchForm">
				<div class="top-searchRight">
				<div class="top-searchLeft">
					 <div class="top-searchSelect fl pr">
					 	<div class="top-searchType"><span class="top-searchTypeText"></span></div>
					 	<ul class="top-searchTypeList">
					 		<li>分类二一</li>
					 		<li>分类二一</li>
					 		<li>分类二一</li>
					 	</ul>
					 </div>
					 <div class="top-searchInputWrap fl pr">
					 	<input type="text" class="top-searchInput">
					 	<ul class="top-searchInputList">
					 		<li>分类二一</li>
					 		<li>分类二一</li>
					 		<li>分类二一</li>
					 	</ul>
					 </div>
					 <div class="top-searchBtn fr"></div>
				</div>
				</div>
				</div>
			</div>
			--%>
		</header>
		<section id="wrap" class="clearfix">
			<aside id="sider">
				<div id="siderInner">
					<div class="siderWelcome"><div class="siderWelcomeInner">欢迎您，${appUser.userName }</div></div>
					<%-- 
					<div class="ucpWrap"></div>
					--%>
					<ul id="siderMenu" class="clearfix">
						<c:forEach var="item" items="${appUser.sysMenuList }">
							<c:if test="${item.isSystemConfMenu == 'Y' }">
								<li class="menuItem menu-01">
									<dl class="menuItemInner clearfix">
										<dt class="menuLev1"><div class="menuLev1Inner">${item.resourceName }</div></dt>
										<dd class="menuLev2Wrap">
											<ul class="clearfix">
												<c:if test="${item.hasChild }">
													<c:forEach var="user" items="${item.subMenuList }" varStatus="status">
														<c:if test="${user.isSystemConfMenu == 'Y' }">
															<li class="menuLev2 menu-01-0${status.index }" onclick="mainShow('${user.resourceName }','<%=basePath %>${user.resourceUrl }','${user.parentResourceId }-${user.idResource }',780,'${user.isSystemConfMenu }')"><div class="menuLev2Inner">${user.resourceName }</div></li>
														</c:if>
													</c:forEach>
												</c:if>
											</ul>
										</dd>
									</dl>
								</li>
							</c:if>
						</c:forEach>
					</ul>
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