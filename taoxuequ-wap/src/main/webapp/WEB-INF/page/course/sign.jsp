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
<title>透明人注册[陶学趣]</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
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
			   <label><span style="color:red;">*</span>性别</label>
			</div>
			<div class="mui-input-row mui-radio">
			   <label>小帅哥</label>
			   <input name="childSex" type="radio" value="F">
			</div>
			<div class="mui-input-row mui-radio">
			   <label>小美女</label>
			   <input name="childSex" type="radio" value="M">
			</div>
			<div class="mui-input-row">
			    <label><span style="color:red;">*</span>年龄</label>
			    <input id="childAge" name="childAge" type="text" placeholder="请输入小朋友年龄">
			</div>
			<div class="mui-input-row">
			    <label><span style="color:red;">*</span>身份证号码</label>
			    <input id="childIdcard" name="childIdcard" type="text" placeholder="请输入小朋友身份证号码">
			</div>
			<div class="mui-input-row">
			    <label><span style="color:red;">*</span>紧急联系人</label>
			    <input id="emerName" name="emerName" type="text" maxlength="11" placeholder="请输入紧急联系人">
			</div>
			<div class="mui-input-row">
			    <label><span style="color:red;">*</span>关系</label>
				<select id="relation" name="relation" class="form-control relation">
	            	<option value="">请选择</option>
	            </select>
			</div>
			<div class="mui-input-row">
			    <label><span style="color:red;">*</span>联系电话</label>
			    <input id="emerMobile" name="emerMobile" type="text" placeholder="联系电话">
			</div>
		</form>
	</div>
	<div class="sign-btn">
		<button type="button" id="regMT-btn">下一步</button>
	</div>
	<script type="text/javascript">
		initSnsToken();
		mui.init();
		initRelation();
		$(function(){
			$("#regMT-btn").click(function(){
				var arr = ["childName","childSex","childAge","childIdcard","emerName","relation","emerMobile"];
				for(var i = 0, len = arr.length; i < len; i++){
					window[arr[i]] = $("#"+arr[i]).val();
				}
				
				var nameReg = /[\u4E00-\u9FA5]{2,}/g;
				var phoneReg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				
				//验证姓名
				if(!nameReg.test(name)){
					if(name == '') mui.alert('请填写姓名','消息提示');
					else mui.alert('姓名不正确','消息提示');
					return false;
				}
				//验证手机号
				if(!phoneReg.test(mobile)){
					if(mobile == '') mui.alert('请填写手机号码','消息提示');
					else mui.alert('手机号不正确','消息提示');
					return false;
				}
				//验证身份证
				if(!idCardNo(idCard)){
					if(idCard == '') mui.alert('请填写身份证','消息提示');
					else mui.alert('身份证不正确','消息提示');
					return false;
				}
				
				$.ajax({
			 		url : "<%=basePath%>/course/signorder/create",
			 		type : "post",
			 		data : $('#courseSignForm').serialize(),
			 		dataType : "json",
			 		async : false,
			 		success : function(data) {
			 			if(data.code == '000') {
			 				window.location.href=basePath + "/wechatpay/pay?" + data.data;
			 			}else {
			 				mui.alert(data.msg,'消息提示');
			 			}
			 		}, error:function(){
			 			mui.alert("系统异常，请联系管理员",'消息提示');
	        		}
			 	});
			})
		})
		function initSnsToken() {
			var snsToken = '${snsToken}';
			setAccessToken(snsToken);
		}
	</script>
</body>
</html>