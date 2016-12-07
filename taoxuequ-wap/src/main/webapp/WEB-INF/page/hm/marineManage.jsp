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
<title>活动排期 绑定队伍[陶学趣]</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<body>
	<input type="hidden" id="activityId" value="${marine.activityId }" />
	<div class="ranksManager-head" id="${marine.id }" onclick="wxChooseImage('MARINE', ${marine.id})" >
		<c:choose>
			<c:when test="${marine.marineImg != null }">
				<img src="${config.imgUrl}/${marine.marineImg }"/>
			</c:when>
			<c:otherwise>
				<div class="rankManager-text">点击上传队伍合照</div>
				<div class="rankManager-headPic"></div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="marine-head">
		<div style="float:left">编辑队伍信息</div>
		<div class="marine-setting editInfo" id="${marine.id }" ed-type="MARINE">编辑</div>
	</div>
	<div class="ranksManager-listHead">编辑队员信息</div>
	<div class="mui-content ranksManager-list">
		<ul class="mui-table-view">
			<c:forEach var="item" items="${families }">
				<li class="mui-table-view-cell">
			    	<a class="mui-navigate-right ranksManager-line">
				    	<div class="ranksManager-img" id="${item.id }" onclick="wxChooseImage('MEMBER',${item.id})">
				    		<c:choose>
				    			<c:when test="${item.childImg != null }">
				    				<img src="${config.imgUrl}/${item.childImg }"/>
				    			</c:when>
				    			<c:otherwise>
				    				<img src="<%=basePath %>/images/uploadPic-bg.png">
				    			</c:otherwise>
				    		</c:choose>
				    	</div>
				    	<div class="ranksManager-name">${item.childName }</div>
				    	<c:choose>
				    		<c:when test="${item.childTitle == null }">
				    			<div class="ranksManager-right editInfo" id="${item.id }" ed-type="MEMBER">未设置</div>
				    		</c:when>
				    		<c:otherwise>
				    			<div class="ranksManager-right editInfo" id="${item.id }" ed-type="MEMBER">编辑</div>
				    		</c:otherwise>
				    	</c:choose>
			    	</a>
			    </li>
			</c:forEach>
		</ul>
	</div>
	<div id="memberEdit" class="code" style="display: none;">
		<div class="mui-content">
			<div class="regTM-formTitle">编辑队员信息
				<span class="marine-close" onclick="closeEdit('memberEdit')">
				 <i class="fa fa-times-circle-o" aria-hidden="true"></i>
				</span>
			</div>
			<form id="memberForm" class="mui-input-group">
				<input type="hidden" id="id" name="id" />
				<input type="hidden" id="type" name="type" />
				<div class="mui-input-row">
				    <label>队员</label>
				    <input id="childName" type="text" maxlength="11" readonly value="小明">
				</div>
				<div class="mui-input-row">
				    <label>队员头衔</label>
				    <input id="childTitle" name="childTitle" type="text" placeholder="队员头衔">
				</div>
				<div class="mui-input-row textarea-row">
				    <label>评语</label>
				    <textarea id="childComment" name="childComment" rows="3" cols="40" placeholder="请填写评语" style="height: 190px;"></textarea>
					<p style="bottom: -5px;position: absolute;padding-left: 10px;">（可不填）请透明人任务填写评语，不要输入图片表情</p>
				</div>
			</form>
			<div class="regMT-btnWrap">
				<button type="button" id="member-btn" class="zdy-btn">保存</button>
			</div>
		</div>
	</div>
	<div id="marineEdit" class="code" style="display: none;">
		<div class="mui-content">
			<div class="regTM-formTitle">编辑战队信息
				<span class="marine-close" onclick="closeEdit('marineEdit')">
				 <i class="fa fa-times-circle-o" aria-hidden="true"></i>
				</span>
			</div>
			<form id="marineForm" class="mui-input-group">
				<input type="hidden" id="id" name="id" />
				<input type="hidden" id="type" name="type" />
				<div class="mui-input-row">
				    <label>队名</label>
				    <input id="marineName" name="marineName" type="text" maxlength="11" placeholder="请输入队名">
				</div>
				<div class="mui-input-row">
				    <label>口号</label>
				    <input id="marineSlogan" name="marineSlogan" type="text" placeholder="请输入口号">
				</div>
				<div class="mui-input-row">
				    <label>得分</label>
				    <input id="score" name="score" class="number" type="text" placeholder="请输入得分">
				</div>
				<div class="mui-input-row">
				    <label>奖项</label>
				    <input id="marinePrize" name="marinePrize" type="text" placeholder="请输入奖项">
				</div>
				<div class="mui-input-row textarea-row">
				    <label>评语</label>
				    <textarea id="comment" name="comment" rows="3" cols="40" placeholder="请填写评语" style="height: 190px;"></textarea>
					<p style="bottom: -5px;position: absolute;padding-left: 10px;">请透明人任务填写评语，不要输入图片表情</p>
				</div>
			</form>
			<div class="regMT-btnWrap">
				<button type="button" id="marine-btn" class="zdy-btn">保存</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		initSnsToken();	
		mui.init();
		$(function(){
			$(".editInfo").on("click", function() {
				var thiz = $(this);
				var id = thiz.attr("id");
				var type = thiz.attr("ed-type");
				marineEditQuery(id, type);
			})
			
			$("#member-btn").click(function(){
				var childTitle = $("#memberForm #childTitle").val();
				if(!childTitle) {
					alert("队员头衔不能为空");
					return;
				}
				$.ajax({
			 		url : "<%=basePath%>/hm/marine/edit/submit",
			 		type : "post",
			 		data : $('#memberForm').serialize(),
			 		dataType : "json",
			 		async : false,
			 		success : function(data) {
			 			if(data.code == '000') {
			 				$("#memberEdit").attr("style", "display:none");
			 				mui.alert('队员信息编辑成功','消息提示');
			 			}else {
			 				mui.alert(data.msg,'消息提示');
			 			}
			 		}, error:function(){
			 			mui.alert("系统异常，请联系管理员",'消息提示');
	        		}
			 	});
			});
			
			$("#marine-btn").click(function(){
				var childTitle = $("#marineForm #marineName").val();
				if(!childTitle) {
					alert("队名不能为空");
					return;
				}
				var childTitle = $("#marineForm #marineSlogan").val();
				if(!childTitle) {
					alert("战队口号不能为空");
					return;
				}
				$.ajax({
			 		url : "<%=basePath%>/hm/marine/edit/submit",
			 		type : "post",
			 		data : $('#marineForm').serialize(),
			 		dataType : "json",
			 		async : false,
			 		success : function(data) {
			 			if(data.code == '000') {
			 				$("#marineEdit").attr("style", "display:none");
			 				mui.alert('战队信息编辑成功','消息提示');
			 			}else {
			 				mui.alert(data.msg,'消息提示');
			 			}
			 		}, error:function(){
			 			mui.alert("系统异常，请联系管理员",'消息提示');
	        		}
			 	});
			});
			$(".number").blur(function() {
				var value = this.value;
				if(value) {
					if(!positiveValidate(value)) {
						mui.alert('请输入正确格式','系统提示');
						this.value = null;
						this.focus();
					}
				}
			});
		})
		
		//拍照或从手机相册中选图接口
       function wxChooseImage(imgType, id) {
           wx.chooseImage({
               count: 1,
               needResult: 1,
               sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
               sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
               success: function (res) {
               		var localIds = res.localIds[0].toString(); // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
               		wxuploadImage(imgType, id, localIds);
               },
               fail: function (res) {
                   alterShowMessage("操作提示", JSON.stringify(res), "1", "确定", "", "", "");
               }

           });
       }
		function wxuploadImage(imgType, id, localId) {  
           wx.uploadImage({  
               localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得  
               isShowProgressTips: 1, // 默认为1，显示进度提示  
               success: function (res) {  
                   mediaId = res.serverId; // 返回图片的服务器端ID  
                   $("#" + id).html("<img src='" + localId + "'/>");
                   wechatMediaDownload(imgType, id, mediaId);
               },  
               fail: function (error) {  
                   alert(Json.stringify(error));  
               }  
           });  
       }  
	   function wechatMediaDownload(imgType, id, mediaId) {
		   $.ajax({
		 		url : "<%=basePath%>/attachment/media/download",
		 		type : "post",
		 		data : {
		 			mediaId: mediaId,
		 			activityId: $("#activityId").val(),
		 			imgType: imgType,
		 			id: id
		 		},
		 		dataType : "json",
		 		async : false,
		 		success : function(data) {
		 		}, error:function(){
		 			mui.alert("系统异常，请联系管理员",'消息提示');
       		}
		 });
	   }
	   function marineEditQuery(id, type) {
		   $.ajax({
		 		url : "<%=basePath%>/hm/marine/edit/query",
		 		type : "post",
		 		data : {
		 			id: id,
		 			type: type
		 		},
		 		dataType : "json",
		 		async : false,
		 		success : function(data) {
		 			var obj = data.data;
		 			if(data.code == '000') {
		 				if(type == 'MARINE') {
		 					$("#marineEdit").attr("style", "display:block");
		 					$("#marineForm #id").val(obj.id);
		 					$("#marineForm #type").val(type);
		 					$("#marineForm #marineName").val(obj.marineName);
		 					$("#marineForm #marineSlogan").val(obj.marineSlogan);
		 					$("#marineForm #score").val(obj.score);
		 					$("#marineForm #marinePrize").val(obj.marinePrize);
		 					$("#marineForm #comment").val(obj.comment);
		 				}else if(type == 'MEMBER') {
		 					$("#memberEdit").attr("style", "display:block");
		 					$("#memberForm #id").val(obj.id);
		 					$("#memberForm #type").val(type);
		 					$("#memberForm #childName").val(obj.childName);
		 					$("#memberForm #childTitle").val(obj.childTitle);
		 					$("#memberForm #childComment").val(obj.childComment);
		 				}
		 			}else {
		 				alert(data.msg);
		 			}
		 		}, error:function(){
		 			mui.alert("系统异常，请联系管理员",'消息提示');
		 		}
		 	});
       	}
	   
	   function initSnsToken() {
			var snsToken = '${snsToken}';
			setAccessToken(snsToken);
		}
	   
	   function closeEdit(id) {
		   $("#" + id).attr("style", "display:none");
	   }
	</script>
</body>
</html>