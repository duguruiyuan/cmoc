<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>    
<ul class="breadcrumb">当前位置：
    <li><a href="<%=path %>/auth/main">首页</a></li>
    <c:forEach var="item" items="${appUser.sysMenuList }">
    	<c:if test="${item.idResource == menu.parentResourceId }">
    		<li><span class="divider">${item.resourceName}</span></li>
    		<c:if test="${item.hasChild }">
	    		<c:forEach var="item1" items="${item.subMenuList }">
	    			<c:if test="${item1.idResource == menu.idResource }">
	    				<li><a href="<%=path %>${item1.resourceUrl}">${item1.resourceName}</a></li>
	    			</c:if>
	    		</c:forEach>
    		</c:if>
    	</c:if>
    	
    </c:forEach>
</ul>