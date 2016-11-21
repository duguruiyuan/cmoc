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
		<title>战队直播-陶学趣</title>
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
			    	<h1>战队名称</h1>
			    	<h2>勇往超前，永不放弃，第一名我们来了！</h2>
			    </div>
			    <div class="liveDetail-listNum">
			    	<ul>
			    		<li>
			    			<span class="liveDetail-listNum-title">线路</span><br>

			    			<em class="liveDetail-listNum-intro liveDetail-listNum-intro1">三年级2号线</em>
			    		</li>
			    		<li>
			    			<span class="liveDetail-listNum-title">阅读量</span><br>

			    			<em class="liveDetail-listNum-intro">588</em>
			    		</li>
			    		<li>
			    			<span class="liveDetail-listNum-title">得分</span><br>

			    			<em class="liveDetail-listNum-intro">1000</em>
			    		</li>
			    	</ul>
			    </div>
			    <div class="liveDetail-award">
			    	<img src="<%=basePath %>/images/test2.png">
			    </div>
		    </div>
		    <div class="bg-white mb10 pb10">
			    <div class="liveDetail-user">
			    	<ul>
			    		<li>
			    			<img src="<%=basePath %>/images/user.jpg"><br>

			    			<span class="liveDetail-user-name">大娃</span><br>

			    			<span class="liveDetail-user-intro">老大</span>
			    		</li>
			    		<li>
			    			<img src="<%=basePath %>/images/user.jpg"><br>

			    			<span class="liveDetail-user-name">二娃</span><br>

			    			<span class="liveDetail-user-intro">老二</span>
			    		</li>
			    		<li>
			    			<img src="<%=basePath %>/images/user.jpg"><br>

			    			<span class="liveDetail-user-name">三娃</span><br>

			    			<span class="liveDetail-user-intro">老三</span>
			    		</li>
			    		<li>
			    			<img src="<%=basePath %>/images/user.jpg"><br>

			    			<span class="liveDetail-user-name">四娃</span><br>

			    			<span class="liveDetail-user-intro">老四</span>
			    		</li>
			    		<li>
			    			<img src="<%=basePath %>/images/user.jpg"><br>

			    			<span class="liveDetail-user-name">五娃</span><br>

			    			<span class="liveDetail-user-intro">老五</span>
			    		</li>
			    		<li>
			    			<img src="<%=basePath %>/images/user.jpg"><br>

			    			<span class="liveDetail-user-name">六娃</span><br>

			    			<span class="liveDetail-user-intro">老六</span>
			    		</li>
			    	</ul>
			    </div>
		    </div>
		    <div class="bg-white mb10">
			    <div class="liveDetail-list pb10">
			    	<nav class="liveDetail-list-title mui-bar-tab">
			    		<a class="mui-tab-item  mui-active" href="#list1">
			    			<span class="mui-tab-label">图片直播</span>
			    		</a>
			    		<a class="mui-tab-item" href="#list2">
			    			<span class="mui-tab-label">视频直播</span>
			    		</a>
			    	</nav>
			    	<div class="liveDetail-list-content mt10">
			    		<div id="list1" class="mui-control-content mui-active">
				    		<div class="sort">
				    			<span class="sort-icon sort-icon-active">最新</span>
				    			<span class="sort-icon">开始</span>
				    		</div>
				    		<ul>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    		</ul>
				    		
			    			<div class="loader">
			    				<p>玩命加载中<span></span><span></span><span></span></p>
			    			</div>
			    		</div>
			    		
			    		<div id="list2" class="mui-control-content">
				    		<div class="sort">
				    			<span class="sort-icon sort-icon-active">最新</span>
				    			<span class="sort-icon">开始</span>
				    		</div>
				    		<ul>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
				    			<li>
				    				<img src="<%=basePath %>/images/text.png"/>
				    			</li>
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
				});
				
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
	                $.ajax({
	                    type:"GET",
	                    url:'../test/page9.json',
	                    dataType:'json',
	                    cache:false,
	                    beforeSend:function(){
	                    	listObj.find(".loader").show().html('<p>玩命加载中<span></span><span></span><span></span></p>');
	                    },
	                    success:function(data){
	                    	var str = "";
	                    	if(data.state=="success"){
	                    		for(var i = 0,len = data.result.length;i < len;i++){
	                    			str += ' <li>\
			                    				<img src="'+data.result[i]+'" style="height:'+picHeight+'px"/>\
			                    			</li> ';
	                    		}
	                    		listObj.find("ul").append(str);
	                    		if(data.isLast){
	                    			listObj.addClass("list-null").find(".loader").html('<p>没有更多了~</p>');
	                    		}
	                    	}else{
	                    		alert(data.msg);
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
				
			})
		</script>
	</body>

</html>