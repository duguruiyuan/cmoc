<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<div class="sign-head">
		<div class="sign-head-left">
			<img src="${config.imgUrl }${course.courseImg }" class="signPic"><br>
		</div>
		<div class="sign-head-right">
			<p class="mui-ellipsis sign-head-title">${course.courseName }</p>
			<p class="mui-ellipsis-2">${course.courseDesc }</p>
		</div>
	</div>
	<div class="sign-title">我要预约</div>
	<div class="mui-content">
		<form id="courseSignForm" class="mui-input-group">
			<input type="hidden" id="openid" name="openid" value="${snsToken.openid }"/>
			<input type="hidden" id="productId" name="productId" value="${course.id }"/>
			<div class="mui-input-row">
			    <label><span style="color:red;">*</span>姓名</label>
			    <input id="childName" name="childName" type="text" placeholder="请输入小朋友姓名">
			</div>
			<div class="mui-input-row mui-radio">
			   <label style="width: 35%;padding: 11px 0px 0px 15px;"><span style="color:red;">*</span>性别</label>
				<label style="width: 30%;padding: 11px 0px 0px 0px;">小帅哥</label>
				<label style="width: 30%;padding: 11px 0px 0px 0px;">小美女</label>
				<input style="width: 40%;" name="childSex" type="radio" value="F">
				<input name="childSex" type="radio" value="M">
			</div>
			<div class="mui-input-row">
			    <label><span style="color:red;">*</span>年龄</label>
			    <input id="childAge" name="childAge" type="text" placeholder="请输入小朋友年龄">
			</div>
			<div class="mui-input-row">
			    <label style="width: 38%;"><span style="color:red;">*</span>身份证号码</label>
			    <input style="width: 62%;" id="childIdcard" name="childIdcard" type="text" placeholder="请输入小朋友身份证号码">
			</div>
			<div class="mui-input-row">
			    <label style="width: 38%;"><span style="color:red;">*</span>紧急联系人</label>
			    <input  style="width: 62%;" id="emerName" name="emerName" type="text" maxlength="11" placeholder="请输入紧急联系人">
			</div>
			<div class="mui-input-row">
			    <label><span style="color:red;">*</span>关系</label>
				<select id="signRelation" name="signRelation" class="form-control relation">
	            	<option value="">请选择</option>
	            </select>
			</div>
			<div class="mui-input-row">
			    <label><span style="color:red;">*</span>联系电话</label>
			    <input id="emerMobile" name="emerMobile" type="text" placeholder="联系电话">
			</div>
			<div class="mui-input-row mui-radio" style="height: 115px;">
			    <label style="width: 45%;padding: 11px 0px 0px 15px;"><span style="color:red;">*</span>有无重大疾病</label>
				<label style="width: 30%;padding: 11px 0px 0px 5px;">无</label>
				<label style="width: 20%;padding: 11px 0px 0px 5px;">有</label>
				<input style="width: 38%;" name="isDisease" type="radio" value="N">
				<input name="isDisease" type="radio" value="Y">
				<div class="mui-input-row" id="disDesc" style="display: none;">
				    <input id="diseaseDesc" name="diseaseDesc" type="text" placeholder="请写下疾病备注">
				</div>
				<p style="bottom: -8px;position: absolute;padding-left: 10px;">（例如：哮喘、心脏病、三个月内做过大型手术等不宜参加户外活动）</p>
			</div>
		</form>
	</div>
	<div class="sign-btn">
		<button type="button" id="regMT-btn">下一步</button>
	</div>
	<script type="text/javascript">
		
	</script>
</body>
</html>