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
<!doctype html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户绑定[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">用户绑定</h1>
		</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div class="mui-content">
		    <div>
		    	<c:choose>
		    		<c:when test="${isBind }">
		    			<div class="mui-input-row">
				            <p style="padding: 15px 0px 0px 20px;">您已绑定</p>
				        </div>
				        <div class="mui-input-row">
				        	<div style="padding:10px 20px 0px;font-size: 14px;">
				        		<i class="fa fa-list-ol"></i> 小孩活动体验列表
				        	</div>
				            <div class="tableList mt10">
						    	<table border="0" cellspacing="0" cellpadding="0">
						    		<thead>
							    		<tr>
							    			<th class="mui-text-center">活动名称</th>
							    			<th class="mui-text-center">活动期数</th>
							    			<th class="mui-text-center">活动时间</th>
							    			<th class="mui-text-center">战队名称</th>
							    			<th class="mui-text-center">状态</th>
							    			<th class="mui-text-center">查看直播</th>
							    		</tr>
						    		</thead>
						    		<tbody>
							    		<c:choose>
						    				<c:when test="${list == null || list.isEmpty()}">
						    					<tr><td colspan="6"><p style="padding: 10px 20px;">暂无记录</p></td></tr>
						    				</c:when>
						    				<c:otherwise>
						    					<c:forEach var="item" items="${list }">
								    				<tr>
									    				<td class="mui-text-center">${item.activityName }</td>
									    				<td class="mui-text-center">${item.activityNum }</td>
									    				<td class="mui-text-center"><fmt:formatDate value='${item.startDate}' pattern='yyyy/MM/dd' /></td>
									    				<td class="mui-text-center">${item.marineName }</td>
									    				<td class="mui-text-center">
									    					<c:choose>
									    						<c:when test="${item.status == 2 }">已结束</c:when>
									    						<c:when test="${item.status == 1 }">直播中</c:when>
									    						<c:when test="${item.status == 0 }">未开始</c:when>
									    					</c:choose>
									    				</td>
									    				<td class="mui-text-center"><a href="<%=basePath %>/live/marine/detail/${item.marineId}">查看</a></td>
									    			</tr>
								    			</c:forEach>
						    				</c:otherwise>
						    			</c:choose>
						    		</tbody>
						    	</table>
						    </div>
				        </div>
		    		</c:when>
		    		<c:otherwise>
		    			<form id="bindForm" class="mui-input-group">
			    			<input type="hidden" id="openid" name="openid" value="${openid }"/>
			    			<div class="mui-input-row">
					            <label>与小孩关系</label>
					            <select id="relation" name="relation" class="form-control relation">
					            	<option value="">请选择</option>
					            </select>
					        </div>
			    			<div class="mui-input-row">
					            <label>小孩姓名</label>
					            <input id="childName" name="childName" type="text" class="mui-input-clear" placeholder="小孩姓名">
					        </div>
					        <div class="mui-input-row" style="height: 70px;">
					            <label>手机号</label>
					            <input id="signMobile" name="signMobile" type="text" class="mui-input-clear" maxlength="11" placeholder="手机号">
					        	<p style="bottom: -5px;position: absolute;padding-left: 10px;">填写小朋友报名预留的家长联系电话</p>
					        </div>
					        <div class="mui-button-row">
								<button id="saveBtn" type="button" class="bind-btn" onclick="return false;">确认</button>&nbsp;&nbsp;
							</div>
						</form>
		    		</c:otherwise>
		    	</c:choose>
		    </div>
		</div>
		<jsp:include page="/WEB-INF/page/common/_footer.jsp" />
		
		<script type="text/javascript">
			initRelation();
			//验证手机规则
			function isMobile(str){
				if($.trim(str)==""){
					return false;
				}
				if(str.substr(0,1) == 0){
					return false;
				}
				var mobileReg="^0?(13|15|18|14|17)[0-9]{9}$";
				return new RegExp(mobileReg).test(str);
			}
			
			//验证码倒计时
			function countdown(){
			    if(countdown.timerc > 1){
			        --countdown.timerc; //时间变量自减1
			        
			        $("#phoneCodeTime").text(countdown.timerc);
			    }else{
					$("#phoneCodeTime").text(countdowntime);
			    	window.clearInterval(countdown.InterValObj);
			    	$(".verificationCode").prop("disabled",false).html('获取验证码');
			    }
			};
			
			//获取验证码
			var bRegCode=true;
			var countdowntime = 60;
			$(".verificationCode").click(function(){
				var telObj=$("#signMobile");
				if(!isMobile(telObj.val())){
					mui.toast('请输入正确的手机号');
					return false;
				}
				
				var obj=$(this);
				obj.prop("disabled",true).html('<span id="phoneCodeTime">60</span>s后重新获取');
				
				countdown.timerc = countdowntime;
				countdown.InterValObj = window.setInterval(countdown, 1000);
			});
			
			//确认
			$("#saveBtn").click(function(){
				if($("#childName").val() == ""){
					mui.toast('请输入小孩姓名');
					return false;
				}
				
				if(!isMobile($("#signMobile").val())){
					mui.toast('请输入正确的手机号');
					return false;
				}
				$.ajax({
			 		url : "<%=basePath%>/user/parent/bind",
			 		type : "post",
			 		data : $('#bindForm').serialize(),
			 		dataType : "json",
			 		async : false,
			 		success : function(data) {
			 			if(data.code == '000') {
			 				mui.alert('绑定成功','消息提示');
			 				window.location.reload();
			 			}else {
			 				mui.alert(data.msg,'消息提示');
			 			}
			 		}, error:function(){
			 			mui.alert("系统异常，请联系管理员",'消息提示');
	        		}
			 	});
			})
		</script>
	</body>

</html>