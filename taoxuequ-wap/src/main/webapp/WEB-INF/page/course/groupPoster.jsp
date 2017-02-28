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
<img id="imgn1" src="http://img.xue110.top//imgextra//2017/1/mBeIuCDqojWynn70YzIssTtXF6V8Lk2T8N40vK7zrK52enxT2gkFMqVIJaWwLAwl.jpeg"/>
<img id="imgn2" src="${userInfo.headimgurl }"/>
<canvas id="myCanvas" width="642px" height="900px""></canvas>
<script type="text/javascript">
			draw();
			function draw(){
			   var canvas = document.getElementById("myCanvas");
			   if(canvas.getContext){ 
				    var ctx = canvas.getContext("2d");
				    var img = new Image();
				    img.src = basePath + "/images/group_share.png";
				    var img1 = new Image();
				    img1.src = $("#imgn1").attr('src');
				    var img2 = new Image();
				    img2.src = $("#imgn2").attr('src');
				    img.onload = function() {
						ctx.drawImage(img2, 0, 0);  
				        ctx.drawImage(img1, 86, 134, 100, 100);
				        ctx.drawImage(img, 189, 305, 130, 130); 
				        saveAsLocalImage();
			        } 
			   }
			}
			function saveImageInfo(){ 
				var mycanvas = document.getElementById("myCanvas"); 
				var image = mycanvas.toDataURL("image/png"); 
				var w=window.open('about:blank','image from canvas'); 
				w.document.write("<img src='"+image+"' alt='from canvas'/>"); 
			} 
			function saveAsLocalImage(){
				var myCanvas = document.getElementById("myCanvas"); 
				var image = myCanvas.toDataURL("image/png", 0.1).replace("image/png", "image/octet-stream"); 
				$.ajax({
		    		type:"post",
		    		url: "<%=basePath%>/course/group/poster/create",
		    		data: {
		    			orderNo: '${productOrder.orderNo}',
		    			extName: "png",
		    			mkdir: "POSTER",
		    			imgdata: image
		    		},
		    		async: false,
		    		cache: false,
		    		dataType:"json",
		    		success:function(result){
		    			result=result||{};
		    			if(result.code == "000"){
		    				$("#poster").attr("src", imgUrl + result.data);
		    			}else{
		    				mui.alert(result.msg,'消息提示');
		    			}
		    		},
		    		error:function(){
		    			alert("网络错误！");
		    		}
		    	})
			}
		</script>
<c:choose>
	<c:when test="${productOrder.posterImg != null }">
		<img id="poster" src="${config.imgUrl }${productOrder.posterImg }">
	</c:when>
	
	<c:otherwise>
		<canvas id="myCanvas" width="642px" height="900px""></canvas>

		
	</c:otherwise>
</c:choose>

</html>