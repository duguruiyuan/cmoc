<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>[陶学趣]课程列表</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp" />
<body class="">
		<header class="header">陶学趣</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div class="mui-content">
		    <div class="course-list">
				<div class="z_card">
				  <c:forEach var="item" items="${list}">
				        <div id="card_list" class="parent1">
				            <div class="card">
				                <div class="card_con">
				                    <a href="<%=basePath%>/course/detail/${item.id}">
				                        <img src="${config.imgUrl }${item.courseImg }">
				                    </a>
				                    <button class="small-btn"><span class="courseType">${item.courseType }</span></button>
				                </div>
				                <div class="card_top">
				                    <div class="top_title">
				                        <span class="zhuangtai enable">报名中</span><a class="href" >${item.courseName }</a>
				                    </div>
				                </div>
				                <div class="card_foot">
				                    <div class="jine clearfix">
				                        	上课时间：<i class="time"><fmt:formatDate value='${item.startDate}' pattern='MM月dd日' /></i>
				                        	-<i><fmt:formatDate value='${item.endDate}' pattern='MM月dd日' /></i>
				                        <i class="iconfont icon">&#xe628;</i><span class="city">${item.city }</span>
				                    </div>
				                    <ul class="simple-info" style="border-bottom-style: none; margin-bottom: 0px;">
				                        <li style="margin-bottom: 0px;">
				                            <a href="javascript:;" class="item-link cleardix" style="text-align: left;">
				                                <div class="z-price fl" style="display: inline-block; margin-right: 10px;"><i>课程学费：</i><i class="thjg">¥${item.totalPrice }</i></div>
				                                <div class="jd-point fr" style="display: inline-block; margin-right: 10px;"><i>已报名家长：</i><i class="yuyue">${item.peoples }人</i></div>
				                            </a>
				                        </li>
				                    </ul>
				
				                    <div class="exp_text mt10">
				                        <a href="<%=basePath%>/course/detail/${item.id}"><span class="clearfix"><span>${item.courseDesc }</span><span class="fr more-detail">查看详情<i class="iconfont icon">&#xe689;</i></span></span></a>
				                    </div>
				                </div>
				            </div>
				        </div>
			    	</c:forEach>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/page/common/_footer.jsp" />
		<script type="text/javascript">
			$(function() {
				$(".city").each(function(index, item) {
					var val = item.innerText;
					item.innerText = dictDataFormat("city", val);
				});
				$(".courseType").each(function(index, item) {
					var val = item.innerText;
					item.innerText = dictDataFormat("course_type", val);
				});
			});
		</script>
	</body>
</html>