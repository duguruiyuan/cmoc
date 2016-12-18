<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<div id="slider" class="mui-slider" data-slidershowtimer="2">
	<div class="mui-slider-group mui-slider-loop">
  		<c:forEach var="item" items="${topBannerList }" begin="${topBannerList.size() - 1 }">
	  		<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
		    <div class="mui-slider-item mui-slider-item-duplicate">
		      <c:choose>
		      	<c:when test="${item.linkUrl != null && !item.linkUrl.isEmpty() }">
		      		<a href="${item.linkUrl }">
		      	</c:when>
		      	<c:otherwise>
		      		<a href="javascript:void(0)">
		      	</c:otherwise>
		      </c:choose>
		        <img src="${config.imgUrl }${item.imgUrl}" width="400" height="200">
		        <c:if test="${item.title != null && !item.title.isEmpty() }">
		        	<p class="mui-slider-title fff">${item.title }</p>
		        </c:if>
		      </a>
		    </div>
	  </c:forEach>
	  <c:forEach var="item" items="${topBannerList }">
		  	<div class="mui-slider-item">
		      <c:choose>
		      	<c:when test="${item.linkUrl != null && !item.linkUrl.isEmpty() }">
		      		<a href="${item.linkUrl }">
		      	</c:when>
		      	<c:otherwise>
		      		<a href="javascript:void(0)">
		      	</c:otherwise>
		      </c:choose>
		        <img src="${config.imgUrl }${item.imgUrl}" width="400" height="200">
		        <c:if test="${item.title != null && !item.title.isEmpty() }">
		        	<p class="mui-slider-title fff">${item.title }</p>
		        </c:if>
		      </a>
		    </div>
	  </c:forEach>
	  <c:forEach var="item" items="${topBannerList }" begin="0" end="0">
		    <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
		    <div class="mui-slider-item mui-slider-item-duplicate">
		      <c:choose>
		      	<c:when test="${item.linkUrl != null && !item.linkUrl.isEmpty() }">
		      		<a href="${item.linkUrl }">
		      	</c:when>
		      	<c:otherwise>
		      		<a href="javascript:void(0)">
		      	</c:otherwise>
		      </c:choose>
		        <img src="${config.imgUrl }${item.imgUrl}" width="400" height="200">
		        <c:if test="${item.title != null && !item.title.isEmpty() }">
		        	<p class="mui-slider-title fff">${item.title }</p>
		        </c:if>
		      </a>
		    </div>
	  </c:forEach>
   </div>
   <div class="mui-slider-indicator">
   	  <c:forEach var="item" items="${topBannerList }" varStatus="status">
   	  	<div class="mui-indicator <c:if test="${status.index == 0 }">mui-active</c:if>"></div>
   	  </c:forEach>
   </div>
</div>

<script>
	var gallery = mui('.mui-slider');
	gallery.slider({
	  interval:3000//自动轮播周期，若为0则不自动播放，默认为0；
	});
</script>
