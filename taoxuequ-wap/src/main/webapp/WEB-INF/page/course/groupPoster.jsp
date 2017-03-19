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
<body>
	<img style="width:100%;" src="${config.imgUrl }${productOrder.posterImg }">
	
	<script type="text/javascript">
    	window.param = {
			title:'我的孩子参加了陶学趣${activityInfo.activityName}活动，城邀你加入我的队伍',
			desc: '点击进入活动页面，并长按设别二维码识别。',
			wZoneTitle: '我的孩子参加了陶学趣${activityInfo.activityName}活动，城邀你加入我的队伍。点击进入活动页面，并长按设别二维码识别。',
			imgUrl: $("#course-img").attr("src") 
		}
	</script>
</body>
</html>