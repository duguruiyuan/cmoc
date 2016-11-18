<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
	String curUrl = request.getRequestURI();
%>
<div id="left">
	<div class="sdmenu">
		<c:forEach var="item" items="${appUser.sysMenuList }">
			<div <c:if test="${item.idResource != menu.parentResourceId }">class="collapsed"</c:if> >
        			<span class="menuLev1">${item.resourceName }</span>
        			<c:if test="${item.hasChild }">
						<c:forEach var="user" items="${item.subMenuList }" varStatus="status">
							<a href="<%=basePath %>${user.resourceUrl }" <c:if test="${user.idResource == menu.idResource }"> class="btn-info active" </c:if> >${user.resourceName }</a>
						</c:forEach>
					</c:if>
       		</div>
		</c:forEach>
	</div>
</div>
<div class="switch"></div>