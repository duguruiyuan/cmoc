<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<title>用户绑定-陶学趣</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<header class="mui-bar mui-bar-nav">
		    <h1 class="mui-title">用户绑定</h1>
		</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div class="mui-content">
		    <div id="slider" class="mui-slider">
		      <div class="mui-slider-group mui-slider-loop">
		        <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
		        <div class="mui-slider-item mui-slider-item-duplicate">
		          <a href="#">
		            <img src="<%=basePath %>/images/slider4.jpg" width="400" height="200">
		            <p class="mui-slider-title">文字说明4</p>
		          </a>
		        </div>
		        <!-- 第一张 -->
		        <div class="mui-slider-item">
		          <a href="#">
		            <img src="<%=basePath %>/images/slider1.jpg" width="400" height="200">
		            <p class="mui-slider-title">文字说明1</p>
		          </a >
		        </div>
		        <!-- 第二张 -->
		        <div class="mui-slider-item">
		          <a href="#">
		            <img src="<%=basePath %>/images/slider2.jpg" width="400" height="200">
		            <p class="mui-slider-title">文字说明2</p>
		          </a>
		        </div>
		        <!-- 第三张 -->
		        <div class="mui-slider-item">
		          <a href="#">
		            <img src="<%=basePath %>/images/slider3.jpg" width="400" height="200">
		            <p class="mui-slider-title">文字说明3</p>
		          </a>
		        </div>
		        <!-- 第四张 -->
		        <div class="mui-slider-item">
		          <a href="#">
		            <img src="<%=basePath %>/images/slider4.jpg" width="400" height="200">
		            <p class="mui-slider-title">文字说明4</p>
		          </a>
		        </div>
		        <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
		        <div class="mui-slider-item mui-slider-item-duplicate">
		          <a href="#">
		            <img src="<%=basePath %>/images/slider1.jpg" width="400" height="200">
		            <p class="mui-slider-title">文字说明1</p>
		          </a>
		        </div>
		      </div>
		      <div class="mui-slider-indicator">
		        <div class="mui-indicator mui-active"></div>
		        <div class="mui-indicator"></div>
		        <div class="mui-indicator"></div>
		        <div class="mui-indicator"></div>
		      </div>
		    </div>
		    <form class="mui-input-group mt10">
		        <div class="mui-input-row">
		            <label>姓名</label>
		            <input id="name" type="text" class="mui-input-clear" placeholder="名称">
		        </div>
		        <div class="mui-input-row">
		            <label>手机号</label>
		            <input id="phone" type="text" class="mui-input-clear" maxlength="11" placeholder="手机号">
		        </div>
		        <div class="mui-input-row">
		            <label>验证码</label>
		            <input id="validCode" type="text" maxlength="10" placeholder="验证码">
		            <button type="button" class="mui-btn verificationCode">获取验证码</button>
		        </div>
		        <div class="mui-button-row">
					<button id="saveBtn" type="button" class="mui-btn mui-btn-green" onclick="return false;">确认</button>&nbsp;&nbsp;
					<button type="reset" class="mui-btn">取消</button>
				</div>
		    </form>
		</div>
		
		
		<script type="text/javascript">
			mui.init();
			mui('.footer').on('tap','a',function(){document.location.href=this.href;});
			
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
				var telObj=$("#phone");
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
				if($("#name").val() == ""){
					mui.toast('请输入姓名');
					return false;
				}
				
				if(!isMobile($("#phone").val())){
					mui.toast('请输入正确的手机号');
					return false;
				}
				
				if($("#validCode").val() == ""){
					mui.toast('请输入验证码');
					return false;
				}
			})
		</script>
	</body>

</html>