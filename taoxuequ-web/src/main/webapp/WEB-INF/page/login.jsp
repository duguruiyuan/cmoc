<%@ page language="java" contentType="text/html; charset=UTF-8"       
    pageEncoding="UTF-8"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path;
%>         
<!DOCTYPE html>
<html>       
	<head>
		<meta charset="utf-8">
		<title>陶学趣</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<link rel="icon" href="<%=basePath %>/favicon.ico" type="image/x-icon"/>  
		<link rel="shortcut icon" href="<%=basePath %>/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/login.css"/>
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp"></jsp:include>
    <body style="background: #5cb85c;">
	 <div id="particles">
        <div id="intro">
            <div >
                <div class="logo" style="margin-top:20px;"  >
                    <a href="<%=path %>/sys/goIndex">
                        <img src="<%=basePath %>/images/taoxuequ.jpg" class="img img-responsive" style="margin:0 auto;">
                    </a>
                </div>
                <div  class="row" style="padding: 20px 15px;">
                    <form id="loginForm" method="POST">
                        <div class="input-group ">
                            <span class="input-group-addon" ><i class="fa fa-user"></i> 用户名</span>
                            <input type="text" id="j_username" name="j_username" class="form-control input-medium" placeholder="请输入用户名">
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon" ><i class="fa fa-lock"></i> 密&nbsp;码</span>
                            <input type="password" id="j_password" name="j_password" class="form-control input-medium" placeholder="请输入密码">
                        </div>
                        <div class="row" style="margin-top:15px;">
		                	<div class="col-md-9">
		                		<span style="color:green;font-size:10px; border-radius: 15px; border:1px solid green; width:140px;padding:5px 15px;"><i class="fa fa-bell"></i> <span id="msgShow">请输入登录信息！</span></span>
		                	</div>
		                    <div class="col-md-3" align="center">
		                        <button type="submit" class="btn btn-success" style="width:78px;" > <i class="fa fa-sign-in" aria-hidden="true"></i> 登 录</button>
		                    </div>
		                </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
 	<div class="footer">
 		<div class="copyright">
            <p class="cp_txt">版权所有 @ 陶学趣 未经许可不得复制、转载或摘编，违者必究!</p>
        </div>
 	</div>
	</body>	      
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script>
			$(document).ready(function() {
			  $('#particles').particleground({
			    dotColor: '#fff',
			    lineColor: '#fff'
			  });
			  $('.intro').css({
			    'margin-top': -($('.intro').height() / 2)
			  });
			});
	    </script>
	<script type="text/javascript">
		var CONTEXT_PATH = '<%=basePath %>';
		var getFullURL = function(subUrl){
		    var estimatingURL = null;
		    if (!subUrl){
		        estimatingURL = CONTEXT_PATH + "/";
		    }else if (subUrl.substring(0, 1) != "/"){ 
		        estimatingURL = CONTEXT_PATH + "/" + subUrl;
		    }else {
		        estimatingURL = CONTEXT_PATH + subUrl;
		    }
		    return estimatingURL;
		};
	</script>
	<script src="<%=basePath %>/js/login.js"></script>
</html>
