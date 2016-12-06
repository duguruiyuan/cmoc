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
		<div class="rankManager-text">点击上传队伍合照</div>
		<div class="rankManager-headPic" style="z-index: -1;">
			<c:if test="${marine.marineImg != null}">
				<img src="${config.imgUrl}/${marine.marineImg }"/>
			</c:if>
		</div>
	</div>
	<div class="marine-head">
		<div style="float:left">编辑队伍信息</div>
		<div class="marine-setting">未设置</div>
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
				    		<c:when test="${item.childTitle != null }">
				    			<div class="ranksManager-right">未设置</div>
				    		</c:when>
				    		<c:otherwise>
				    			<div class="ranksManager-right">编辑</div>
				    		</c:otherwise>
				    	</c:choose>
			    	</a>
			    </li>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript">
		initSnsToken();	
		mui.init();
		$(function(){
			var str = '<div class="code">\
							<div class="code-inner">\
								<div class="code-title">[穿越部落]长按二维码绑定队伍</div>\
								<div class="code-pic">\
									<img src="../images/code.png">\
								</div>\
								<div class="code-btn">确定</div>\
							</div>\
						</div>';
			
			$(".ranksList").on("click", ".mui-navigate-right", function(){
				$("body").append(str);
			});
			
			$("body").on("click", ".code-btn", function(){
				$(".code").remove();
			})
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
	   function initSnsToken() {
			var snsToken = '${snsToken}';
			setAccessToken(snsToken);
		}
	</script>
</body>
</html>