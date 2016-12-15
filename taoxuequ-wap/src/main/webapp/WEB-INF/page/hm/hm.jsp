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
	<div class="regTMHeader">
		<img src="${userInfo.headimgurl }" class="regTMPic"><br>
		<span class="regTMTitle">注册透明人</span><br>
		<span class="regTMName">陶学趣</span>
	</div>
	<div class="regTM-formTitle">填写透明人资料</div>
	<div class="mui-content">
		<form id="hmRegForm" class="mui-input-group">
			<div class="mui-input-row">
			    <label>姓名</label>
			    <input id="hmName" name="hmName" type="text" placeholder="请输入姓名">
			</div>
			<div class="mui-input-row">
			    <label>手机</label>
			    <input id="hmMobile" name="hmMobile" type="text" maxlength="11" placeholder="请输入手机号码">
			</div>
			<div class="mui-input-row">
			    <label>证件</label>
			    <input type="hidden" id="idType" name="idType" value="01" />
			    <input type="hidden" id="openid" name="openid" value="${userInfo.openid }" />
			    <input id="idCard" name="idCard" type="text" placeholder="请输入身份证号">
			</div>
			<div class="mui-input-row">
			    <label>学校</label>
			    <input id="schoole" name="schoole" type="text" placeholder="请输入学校">
			</div>
			<div class="mui-input-row">
			    <label>籍贯</label>
			    <input id="place" name="place" type="text" placeholder="请输入籍贯">
			</div>
			<div class="mui-input-row pr">
			    <label>年级</label>
			    <select id="grade" name="grade" class="regMT-select pr">
			    	<option value="大一">大一</option>
			    	<option value="大二">大二</option>
			    	<option value="大三">大三</option>
			    	<option value="大四" selected>大四</option>
			    </select>
			    <span class="mui-icon mui-icon-arrowright regMT-select-arrow"></span>
			</div>
			<div class="mui-input-row mui-radio">
			    <label>帅哥</label>
			    <input name="sex" type="radio" value="F" checked>
			</div>
			<div class="mui-input-row mui-radio">
			    <label>美女</label>
			    <input name="sex" type="radio" value="M">
			</div>
			<div class="mui-input-row">
			    <label>上传照片</label>
			</div>
			<div class="mui-input-row regMT-headPic" onclick="wxChooseImage()">
				<div class="pr">
			    	<img src="<%=basePath %>/images/regTM.png" id="regMT-uploadPic" data-state="no">
					<input type="hidden" id="idPhoto" name="idPhoto">
				</div>
			</div>
		</form>
		<div class="regMT-btnWrap">
			<button type="button" id="regMT-btn">确定注册</button>
		</div>
		<div class="regMT-text">
			<p>
				注意事项：<br>
				1、需认可穿越部落活动文化<br>
				2、不限学校、不限专业、不限年级、不限性别<br>
				3、做事细心、耐心、能吃苦、不畏惧困难，工作积极主动、有团队合作精神<br>
				4、具备强烈的责任感和服务意识，逻辑能力强。<br>
				5、有清晰的语言表达能力，为人谦逊、接受能力强。<br>
				6、有文体、摄影等特长或有社会实践经验者、组织参与过大型活动者优先。<br>
				7、在校时间灵活且有市场意识或有过招生经验才优先录用
			</p>
		</div>
	</div>
	<c:if test="${hm != null && hm.isActive == 0 }">
		<div class="code">								
			<div class="code-inner" style="height: 170px;">									
				<div class="code-title">[陶学趣]透明人注册</div>									
				<div class="code-pic">										
					<p style="color: red;">您已经注册成功，等待管理员审核中...</p>								
				</div>									
			</div>							
		</div>
	</c:if>
	<script type="text/javascript">
		initSnsToken();
		$(function(){
			$("#regMT-btn").click(function(){
				var arr = ["hmName","hmMobile","idCard"];
				for(var i = 0, len = arr.length; i < len; i++){
					window[arr[i]] = $("#"+arr[i]).val();
				}
				
				var sex = $("input[name=sex]:checked").val();
				
				var phoneReg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				
				//验证姓名
				if(!/^[\u2E80-\u9FFF]+$/.test(hmName)){
					if(hmName == '') mui.alert('请填写姓名','消息提示');
					else mui.alert('姓名不正确','消息提示');
					return false;
				}
				
				//验证手机号
				if(!phoneReg.test(hmMobile)){
					if(hmMobile == '') mui.alert('请填写手机号码','消息提示');
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
			 		url : "<%=basePath%>/hm/register",
			 		type : "post",
			 		data : $('#hmRegForm').serialize(),
			 		dataType : "json",
			 		async : false,
			 		success : function(data) {
			 			if(data.code == '000') {
			 				mui.alert('注册成功，请等待管理员审核。','消息提示');
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

		//拍照或从手机相册中选图接口
	       function wxChooseImage() {
	           wx.chooseImage({
	               count: 1,
	               needResult: 1,
	               sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	               sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	               success: function (res) {
	               		var localIds = res.localIds[0].toString(); // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	               		wxuploadImage(localIds);
	               },
	               fail: function (res) {
	                   alterShowMessage("操作提示", JSON.stringify(res), "1", "确定", "", "", "");
	               }

	           });
	       }
			function wxuploadImage(localId) {  
	           wx.uploadImage({  
	               localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得  
	               isShowProgressTips: 1, // 默认为1，显示进度提示  
	               success: function (res) {  
	                   mediaId = res.serverId; // 返回图片的服务器端ID  
	                   $("#regMT-uploadPic").attr({"src": localId, "data-state": "yes"});
	                   wechatMediaDownload(mediaId);
	               },  
	               fail: function (error) {  
	                   alert(Json.stringify(error));  
	               }  
	           });  
	       }  
		   function wechatMediaDownload(mediaId) {
			   $.ajax({
			 		url : "<%=basePath%>/attachment/idPhoto/upload",
			 		type : "post",
			 		data : {
			 			mediaId: mediaId
			 		},
			 		dataType : "json",
			 		async : false,
			 		success : function(data) {
			 			if(result.code == "000"){
	        				$("#idPhoto").val(result.data);
			 			}
			 		}, error:function(){
			 			mui.alert("系统异常，请联系管理员",'消息提示');
	       		}
			 });
		   }
	</script>
</body>
</html>