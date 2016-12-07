<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<title>战队直播[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body id="liveDetail">
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">战队直播</h1>
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
		    <div class="bg-white pb10 mb10">
			    <div class="liveDetail-title">
			    	<input type="hidden" id="marineId" value="${marine.id }"/>
			    	<h1>${marine.marineName }</h1>
			    	<h2>${marine.marineSlogan }</h2>
			    </div>
			    <div class="liveDetail-listNum">
			    	<ul>
			    		<li>
			    			<span class="liveDetail-listNum-title">支持票数</span><br>

			    			<em class="liveDetail-listNum-intro">${marine.votes }</em>
			    		</li>
			    		<li>
			    			<span class="liveDetail-listNum-title">阅读量</span><br>

			    			<em class="liveDetail-listNum-intro">${marine.readnum }</em>
			    		</li>
			    		<li>
			    			<span class="liveDetail-listNum-title">得分</span><br>

			    			<em class="liveDetail-listNum-intro">${marine.score }</em>
			    		</li>
			    	</ul>
			    </div>
			    <div class="liveDetail-award" style="position: relative;">
			    	<img src="<%=basePath%>/images/test2.png"/>
			    	<p class="marine-prize">${marine.marinePrize }</p>
			    </div>
		    </div>
		    <div class="bg-white mb10 pb10">
			    <div class="liveDetail-user">
			    	<ul>
			    		<c:forEach var="itm" items="${familys }">
			    			<li>
			    				<c:choose>
			    					<c:when test="${itm.childImg != null }">
			    						<img src="${config.imgUrl }${itm.childImg}"><br>
			    					</c:when>
			    					<c:otherwise>
			    						<img src="<%=basePath %>/images/user.jpg"><br>
			    					</c:otherwise>
			    				</c:choose>
	
				    			<span class="liveDetail-user-name">${itm.childName }</span><br>
	
				    			<span class="liveDetail-user-intro">${itm.childTitle }</span>
				    		</li>
			    		</c:forEach>
			    	</ul>
			    </div>
		    </div>
		    <div class="bg-white mb10">
			    <div class="liveDetail-list pb10">
			    	<nav class="liveDetail-list-title mui-bar-tab">
			    		<a class="mui-tab-item mui-active" href="#list1" id="panel1">
			    			<span class="mui-tab-label">图片直播</span>
			    			<input type="hidden" name="msgType" value="image">
			    			<input type="hidden" name="pageNo" value="1">
			    		</a>
			    		<a class="mui-tab-item" href="#list2" id="panel2">
			    			<span class="mui-tab-label">视频直播</span>
			    			<input type="hidden" name="msgType" value="shortvideo">
			    			<input type="hidden" name="pageNo" value="1">
			    		</a>
			    	</nav>
			    	<div class="liveDetail-list-content mt10">
			    		<div id="list1" class="mui-control-content mui-active">
				    		<div class="sort">
				    			<span class="sort-icon sort-icon-active" order="desc">最新</span>
				    			<span class="sort-icon" order="asc">开始</span>
				    		</div>
				    		<ul>
				    			
				    		</ul>
				    		
			    			<div class="loader">
			    				<p>玩命加载中<span></span><span></span><span></span></p>
			    			</div>
			    		</div>
			    		
			    		<div id="list2" class="mui-control-content">
				    		<div class="sort">
				    			<span class="sort-icon sort-icon-active" order="desc">最新</span>
				    			<span class="sort-icon" order="asc">开始</span>
				    		</div>
				    		<ul>
				    			
				    		</ul>
				    		
			    			<div class="loader">
			    				<p>玩命加载中<span></span><span></span><span></span></p>
			    			</div>
			    		</div>
			    	</div>
			    	<div class="rightfix">
			    		<span class="mui-icon mui-icon-loop"></span>
			    		<span class="fixTag support">支持</span>
			    	</div>
			    </div>
		    </div>
		</div>
		
		<script type="text/javascript">
			mui.init();
			mui('.footer').on('tap','a',function(){document.location.href=this.href;});
			
			$(function(){
				var picListWrap = $(".liveDetail-list-content");
				var picListTop = $(".liveDetail-list").offset().top;
				var picList = picListWrap.find("img");
				var rightfix = $(".rightfix");
				var picHeight = picList.width();
				picList.css({height:picHeight});
				
				$(".sort-icon").click(function(){
					var obj = $(this);
					obj.addClass("sort-icon-active").siblings().removeClass("sort-icon-active");
					var params = {
						marineId: $("#marineId").val(),
	                   	msgType: $(".liveDetail-list-title .mui-active").find("input[name='msgType']").val(),
	                   	page: 1,
	                   	order: $(this).attr("order")	
					};
					$(".liveDetail-list-title .mui-active").find("input[name='pageNo']").val(1);
					var listObj = $(this.closest(".mui-control-content"));
					listObj.removeClass("list-null");
					loadItem(params, listObj, true);
				});
				initVideoItem();
				$(window).scroll(function(){
					if($(this).scrollTop() >= picListTop-50){
						rightfix.show();
					}else{
						rightfix.hide();
					}
					var listObj = $(".liveDetail-list-content .mui-active");
					if(listObj.hasClass("list-null")>0){
						return false;
					}
				  if ($(window).scrollTop() + $(window).height() + 10 >= $(document).height() && $(window).scrollTop() > 20) {
	                  var msgType = $(".liveDetail-list-title .mui-active").find("input[name='msgType']").val();
					  var pageNo = parseInt($(".liveDetail-list-title .mui-active").find("input[name='pageNo']").val()) + 1;
	                  $.ajax({
	                    type:"POST",
	                    url:'<%=basePath%>/live/marine/resource/query',
	                    dataType:'json',
	                    data: {
	                    	marineId: $("#marineId").val(),
	                    	msgType: msgType,
	                    	page: pageNo,
	                    	order: listObj.find(".sort-icon-active").attr("order")
	                    },
	                    cache:false,
	                    beforeSend:function(){
	                    	listObj.find(".loader").show().html('<p>玩命加载中<span></span><span></span><span></span></p>');
	                    },
	                    success:function(data){
	                    	var str = "";
	                    	if(data.results.length > 0){
	                    		$(".liveDetail-list-title .mui-active").find("input[name='pageNo']").val(pageNo);
	                    		for(var i = 0,len = data.results.length;i < len;i++){
	                    			if(msgType == 'image') {
	                    				str += ' <li>\
		                    				<img src="'+data.results[i].sysUrl+'"/>\
		                    			</li> ';
	                    			}else if(msgType == 'shortvideo') {
	                    				str += '<li><video poster="' + data.results[i].picUrl + '" controls="controls" preload="none">'+
                    					'<source src="' + data.results[i].sysUrl + '" media="only screen and (min-device-width: 360px)">'+
                    					'<source src="' + data.results[i].sysUrl + '" media="only screen and (max-device-width: 960px)">'+
                    					'</video></li>';
	                    			}
	                    		}
	                    		listObj.find("ul").append(str);
	                    	}else {
	                    		listObj.addClass("list-null").find(".loader").html('<p>没有更多了~</p>');
	                    	}
	                    },
	                    error:function(){
	                    	alert("系统异常，请稍后再试！");
	                    },
	                    complete:function(){
	                    	if(!listObj.hasClass("list-null")){
	                    		listObj.find(".loader").hide();
	                    	}
	                    }
	                })
	              }
	            }).trigger("scroll");
				function initImageItem() {
					var params = {
						marineId: $("#marineId").val(),
	                   	msgType: $("#panel1").find("input[name='msgType']").val(),
	                   	page: $("#panel1").find("input[name='pageNo']").val(),
	                   	order: $("#list1").find(".sort-icon-active").attr("order")	
					};
					loadItem(params, $("#list1"), false);
				}
				function initVideoItem() {
					var params = {
						marineId: $("#marineId").val(),
	                   	msgType: $("#panel2").find("input[name='msgType']").val(),
	                   	page: $("#panel2").find("input[name='pageNo']").val(),
	                   	order: $("#list2").find(".sort-icon-active").attr("order")		
					};
					loadItem(params, $("#list2"), false);
				}
				function loadItem(params, listObj, reload) {
	                $.ajax({
	                  type:"POST",
	                  url:'<%=basePath%>/live/marine/resource/query',
	                  dataType:'json',
	                  data: params,
	                  cache:false,
	                  success:function(data){
	                  	var str = "";
	                  	if(data.results.length > 0){
	                  		for(var i = 0,len = data.results.length;i < len;i++){
	                  			if(params.msgType == 'image') {
	                  				str += ' <li>\
		                    				<img src="'+data.results[i].sysUrl+'"/>\
		                    			</li> ';
	                  			}else if(params.msgType == 'shortvideo') {
	                  				str += '<li><video poster="' + data.results[i].picUrl + '" controls="controls" preload="none">'+
                					'<source src="' + data.results[i].sysUrl + '" media="only screen and (min-device-width: 360px)">'+
                					'<source src="' + data.results[i].sysUrl + '" media="only screen and (max-device-width: 960px)">'+
                					'</video></li>';
                    			}
	                  		}
	                  		if(reload) {
	                  			listObj.find("ul").html(str);
	                  		}else {
	                  			listObj.find("ul").append(str);
	                  		}
	                  	}
	                  },
	                  error:function(){
	                  	alert("系统异常，请稍后再试！");
	                  }
	              });
				}
			});
			
		</script>
	</body>

</html>