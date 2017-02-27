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


<canvas id="myCanvas" width="642px" height="900px">

</canvas>

<script type="text/javascript">
	window.onload = function() { 
		draw(); 
	}
	function draw(){
	   var canvas = document.getElementById("myCanvas");
	   if(canvas.getContext){ 
		    var ctx = canvas.getContext("2d");
		    var img = new Image();
		    img.src = basePath + "/images/group_share.png";
		    var img1 = new Image();
		    img1.src = "http://wx.qlogo.cn/mmopen/PLVuSzicWvZrYB7LJMoo7k11T7Qd6vTiaibTgvGRTlRR6sANRVfHqBXY9EBKUe3eF25eA1pZAwvLTWibdfLQgicTC9kxbhKeZ8PMX/0";
		    var img2 = new Image();
		    img2.src = "http://wx.qlogo.cn/mmopen/PLVuSzicWvZrYB7LJMoo7k11T7Qd6vTiaibTgvGRTlRR6sANRVfHqBXY9EBKUe3eF25eA1pZAwvLTWibdfLQgicTC9kxbhKeZ8PMX/0";
		    img.onload = function() {
				ctx.drawImage(img, 0, 0);  
		        ctx.drawImage(img1, 86, 134, 100, 100);
		        ctx.drawImage(img2, 189, 305, 130, 130);   
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
		var image = myCanvas.toDataURL("image/png").replace("image/png", "image/octet-stream"); 
		window.location.href=image; 
	}
</script>
</html>