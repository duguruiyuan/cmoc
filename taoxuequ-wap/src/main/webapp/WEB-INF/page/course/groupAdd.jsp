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
<title>课程报名[陶学趣]</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<script src="<%=basePath %>/js/plugins/wechat-config.js?v=${config.version}" type="text/javascript" charset="utf-8"></script>
<body>
	<header class="header">
		<a class="mui-icon mui-icon-left-nav mui-pull-left" href="<%=basePath%>/course/group/orderList"></a>
		队伍信息管理
	</header>
	<div class="group-add-tips mt44">
		<c:choose>
			<c:when test="${isPayed == 1 }">
				<c:if test="${childList.size() >= 5 }">已经组队完成</c:if>
			</c:when>
			<c:otherwise>
				本队伍尚未完成支付，请完成队伍支付。
				<a id="group-pay">付款</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="group-process">
		<span class="dis">1.组建队伍</span>
		<span> > </span>
		<span class="active">2.邀请队友</span>
		<span> > </span>
		<span class="dis">3.确定支付</span>
	</div>
	<div>
		<div style="width: 100%;text-align: center;margin: 30px 0;">队员资料</div>
		<div class="mui-content">
			<c:forEach var="itm" items="${childList }" varStatus="i">
				<a href="<%=basePath %>/course/group/merber?oNo=${orderNo}&cId=${itm.id}">
					<div class="group-list" data-id="${itm.id }">
						<div class="group-list-left">${i.index + 1 }</div>
						<div class="group-list-right">
							<span>${itm.childName }<span>
							<p>${itm.emerMobile }</p>
						</div>
						<div style="clear:both"></div>
					</div>
				</a>
			</c:forEach>
			<c:forEach var="x" begin="${childList.size() }" end="4" step="1" varStatus="i"> 
			     <a href="<%=basePath %>/course/group/merber?oNo=${orderNo}">
				     <div class="group-list">
						<div class="group-list-left">${i.index + 1 }</div>
						<div class="group-list-right">
							<span>空缺<span>
							<p>队伍尚未组建完成</p>
						</div>
						<div style="clear:both"></div>
					</div>
				</a>
			</c:forEach>
		</div>
		
		<div class="group-wel">
			<a href="<%=basePath%>/course/group/poster?oNo=${orderNo}">
				<div style="float: left; padding-left: 20px;">队伍邀请海报</div>
				<p class="mui-icon mui-icon-arrowright" style="font-size: 15px;float:right;"></p>
			</a>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$("#group-list").click(function() {
				var id = $(this).attr("data-id");
				window.location.href = basePath + "/"
				$.ajax({
			 		url : basePath + "/course/json/group/addMember",
			 		type : "post",
			 		data : {
			 			id: id
			 		},
			 		dataType : "json",
			 		async : false,
			 		success : function(data) {
			 			if(data.code == '000') {
			 				window.location.href = basePath + "/course/group/add?oNo=" + data.data;
			 			}else {
			 				mui.alert(data.msg,'消息提示');
			 			}
			 		}, error:function(){
			 			mui.alert("系统异常，请联系管理员",'消息提示');
	        		}
			 	});
			});
			$("#groupSignSubmit").click(function() {
				
			});
			
		})
	</script>
</body>
</html>