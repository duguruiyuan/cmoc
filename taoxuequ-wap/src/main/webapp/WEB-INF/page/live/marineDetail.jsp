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
		<header class="header">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" style="color: #fff;"></a>
		    	战队直播
		</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div class="mui-content">
		    <div class="bg-white pbb10">
			    <div class="liveDetail-title">
			    	<input type="hidden" id="marineId" value="${marine.id }"/>
			    	<h1>${marine.marineName }</h1>
			    	<h2>${marine.marineSlogan }</h2>
			    </div>
			    <div class="liveDetail-listNum">
			    	<ul>
			    		<li>
			    			<span class="liveDetail-listNum-title">支持票数</span><br>

			    			<em class="liveDetail-listNum-intro" style="color: #6ac562;">${marine.votes }</em>
			    		</li>
			    		<li>
			    			<span class="liveDetail-listNum-title">阅读量</span><br>

			    			<em class="liveDetail-listNum-intro" style="color: #8cb2f8;">${marine.readnum }</em>
			    		</li>
			    		<li>
			    			<span class="liveDetail-listNum-title">得  分</span><br>

			    			<em class="liveDetail-listNum-intro" style="color: #e96747;">${marine.score }</em>
			    		</li>
			    	</ul>
			    </div>
			    <div class="liveDetail-award" style="position: relative;">
			    	<img src="<%=basePath%>/images/prize.png"/>
			    	<p class="marine-prize">
			    		<c:choose>
			    			<c:when test="${marine.marinePrize != null && !marine.marinePrize.isEmpty() }">
			    				${marine.marinePrize }
			    			</c:when>
			    			<c:otherwise>
			    				暂未颁奖
			    			</c:otherwise>
			    		</c:choose>
			    	</p>
			    </div>
		    </div>
		    <div class="bg-white pbb10">
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
		    <div class="bg-white pbb10">
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
			    		<a class="mui-tab-item" href="#list3" id="panel3">
			    			<span class="mui-tab-label">小组评价</span>
			    			<input type="hidden" name="msgType" value="comment">
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
			    		
			    		<div id="list3" class="mui-control-content">
				    		<div class="sort">
				    			<span class="sort-icon sort-icon-active" order="desc">最新</span>
				    			<span class="sort-icon" order="asc">开始</span>
				    		</div>
				    		<ul>
				    			
				    		</ul>
				    		
			    			<div class="loader">
			    				<p>加载中...<span></span><span></span><span></span></p>
			    			</div>
			    		</div>
			    	</div>
			    	<div class="rightfix">
			    		<span class="mui-icon mui-icon-loop"></span>
			    		<span class="fixTag support">支持</span>
			    	</div>
			    </div>
		    </div>
		    <div class="bg-white pbb10">
		    	<div class="more-item">学生印象</div>
		    	<div class="messageebox">
					<div class="midbox">
						<div class="textbox">
							<div class="text_meg"><span>思路清晰</span><em>1456</em></div>
						</div>
						<div class="textbox">
							<div class="text_meg"><span>态度热情</span><em>1212</em></div>
						</div>
						<div class="textbox">
							<div class="text_meg"><span>板书美观</span><em>1515</em></div>
						</div>
						<div class="textbox">
							<div class="text_meg"><span>知识渊博</span><em>4512</em></div>
						</div>
						<div class="textbox">
							<div class="text_meg"><span>方法独特</span><em>4545</em></div>
						</div>
						<div class="textbox">
							<div class="text_meg"><span>讲解细致</span><em>2222</em></div>
						</div>
					</div>
				</div>
		    </div>
		    <div class="bg-white pbb10">
		    	<div class="more-item">活动留言</div>
		    	<div class="pms">
					<div class="pms_box">
						<div class="headlog">
							<img src="<%=basePath %>/images/t01f91640b40600714f.jpg" alt="">
						</div>
						<div class="pmstext">勇敢，团结，机智的小组勇敢，团结，机智的小组勇敢，团结，机智的小组勇敢，团结，机智的小组勇敢，团结，机智的小组勇敢，团结，机智的小组勇敢，团结，机智的小组。</div>
					</div>
					<div class="pms_box">
						<div class="headlog">
							<img src="<%=basePath %>/images/t01f91640b40600714f.jpg" alt="">
						</div>
						<div class="pmstext">勇敢，团结，机智的小组勇敢，团结，机智的小组勇敢</div>
					</div>
					<div class="pms_box">
						<div class="headlog">
							<img src="<%=basePath %>/images/t01f91640b40600714f.jpg" alt="">
						</div>
						<div class="pmstext">勇敢，团结，机智的小组勇敢</div>
					</div>
				</div>
				<div class="input_box"><input placeholder="" type="text"> <button>留言</button> </div>
		    </div>
		    <div class="bg-white pbb10" style="height: 3em;"></div>
		</div>
		<footer class="footer" style="padding:12px 10px;">
	        <div class="btn-wrap clearfix" id="footerInner">
	            <a href="<%=basePath %>/course/list" class="wbl-btn btn-border " id="newest">
	               	 更多课程
	            </a>
	            <a href="<%=basePath %>/course/detail/${marine.productId}" class="wbl-btn btn-go" id="submit" ms-class-disabled="submit_enabled"><label>预约报名</label><i class="iconfont icon"></i></a>
	        </div>
	    </footer>
		<script type="text/javascript">
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