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
			    	<input type="hidden" id="marineImg" value="${config.imgUrl }${marine.marineImg }"/>
			    	<input type="hidden" id="supportQrcodeUrl" value="${marine.supportQrcodeUrl }" />
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

			    			<em class="liveDetail-listNum-intro" style="color: #8cb2f8;" id="readNum">${marine.readnum }</em>
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
			    <div class="liveDetail-list">
			    	<nav class="liveDetail-list-title mui-bar-tab">
			    		<a class="mui-tab-item mui-active" href="#list1" id="panel1">
			    			<span class="mui-tab-label">图片直播</span>
			    			<input type="hidden" name="msgType" value="image">
			    			<input type="hidden" name="pageNo" value="0">
			    		</a>
			    		<a class="mui-tab-item" href="#list2" id="panel2">
			    			<span class="mui-tab-label">视频直播</span>
			    			<input type="hidden" name="msgType" value="shortvideo">
			    			<input type="hidden" name="pageNo" value="0">
			    		</a>
			    		<a class="mui-tab-item" href="#list3" id="panel3">
			    			<span class="mui-tab-label">小组评价</span>
			    		</a>
			    	</nav>
			    	<div class="liveDetail-list-content mt10">
			    		<div id="list1" class="mui-control-content mui-active">
				    		<ul>
				    			
				    		</ul>
				    		
				    		<div class="btn-more loader res-more">
								<span>查看更多</span>
							</div>
			    		</div>
			    		
			    		<div id="list2" class="mui-control-content">
				    		<ul>
				    			
				    		</ul>
				    		
			    			<div class="btn-more loader res-more">
								<span>查看更多</span>
							</div>
			    		</div>
			    		
			    		<div id="list3" class="mui-control-content">
			    			<div class="mar-comment">${marine.comment }</div>
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
					<div class="midbox" id="midbox">
						
					</div>
				</div>
		    </div>
		    <div class="bg-white pbb10">
		    	<div class="more-item">活动留言</div>
		    	<input type="hidden" id="commentPageNo" value="0" />
		    	<div class="pms" id="pmsComment">
				
				</div>
				<div class="btn-more" id="comment-more">
					<span>查看更多</span>
				</div>
				<div class="input_box"><input placeholder="留下想对我们说的话吧" type="text" id="comment"> <button id="subComment">留言</button> </div>
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
				initSnsToken();
				var picListWrap = $(".liveDetail-list-content");
				var picListTop = $(".liveDetail-list").offset().top;
				var picList = picListWrap.find("img");
				var rightfix = $(".rightfix");
				var picHeight = picList.width();
				picList.css({height:picHeight});
				
				initImageItem();
				initVideoItem();
				readnum();
				loadStuImp();
				loadComment();
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
	                  /* var msgType = $(".liveDetail-list-title .mui-active").find("input[name='msgType']").val();
					  var pageNo = parseInt($(".liveDetail-list-title .mui-active").find("input[name='pageNo']").val()) + 1;
					  var params = {
							marineId: $("#marineId").val(),
		                   	msgType: msgType,
		                   	page: pageNo
					  };
					  loadItem(params, listObj) */
	              }
	            }).trigger("scroll");
				
				$("#subComment").click(function() {
					var comment = $("#comment").val();
					if(comment.length == 0 ) return;
					submitComment();
				});
				
				$("#comment-more").click(function() {
					if($(this).hasClass("list-null")>0){
						return false;
					}
					loadComment();
				});
				$(".res-more").click(function() {
					moreResItem();
				})
				$(".mui-icon-loop").click(function() {
					window.location.reload();
				});
				
				$("#midbox").on("click", ".textbox", function() {
					addStuimp(this);
				});
				
				$(".support").on("click", function(){
					var str = '<div class="code">\
						<div class="code-inner" style="height: 450px;width: 280px;">\
							<div class="code-title" style="height: 40px;line-height: 40px;">支持我们</div><div class="code-pic">\
							<img style="width: 100%" src="' + $("#marineImg").val() + '"/></div>\
							<div class="qr-group">\
								<img style="width: 50%;float:left;padding: 10px 0px 10px 20px;" src="' + $("#supportQrcodeUrl").val()+ '">\
							</div>\
							<div class="qr-desc">[陶学趣]长按二维码支持我们小战队</div>\
							<div class="code-btn" onclick="closeCode()">确定</div>\
						</div>\
					</div>';
					$("body").append(str);
				});
				function closeCode() {
					$(".code").remove();
				}
				$("body").on("click", ".code-btn", function(){
					$(".code").remove();
				})
			});
			
				function moreResItem() {
				  var listObj = $(".liveDetail-list-content .mui-active");
				  var msgType = $(".liveDetail-list-title .mui-active").find("input[name='msgType']").val();
				  var pageNo = parseInt($(".liveDetail-list-title .mui-active").find("input[name='pageNo']").val()) + 1;
				  var params = {
						marineId: $("#marineId").val(),
	                   	msgType: msgType,
	                   	page: pageNo,
	                   	rows: 48
				  };
				  loadItem(params, listObj);	
				}
				
				function initImageItem() {
					var params = {
						marineId: $("#marineId").val(),
	                   	msgType: $("#panel1").find("input[name='msgType']").val(),
	                   	page: $("#panel1").find("input[name='pageNo']").val(),
	                   	rows: 9
					};
					loadItem(params, $("#list1"));
				}
				function initVideoItem() {
					var params = {
						marineId: $("#marineId").val(),
	                   	msgType: $("#panel2").find("input[name='msgType']").val(),
	                   	page: $("#panel2").find("input[name='pageNo']").val(),
	                   	rows: 9
					};
					loadItem(params, $("#list2"));
				}
				function loadItem(params, listObj, isMore) {
					var currPage = params.page;
					if(currPage == 0) params.page = 1;
	                $.ajax({
	                  type:"POST",
	                  url:'<%=basePath%>/live/marine/resource/query',
	                  dataType:'json',
	                  data: params,
	                  cache:false,
	                  beforeSend:function(){
                    	listObj.find(".res-more").removeClass("btn-more").addClass("loader").show().html('<p>玩命加载中<span></span><span></span><span></span></p>');
                      },
	                  success:function(data){
	                  	var str = "";
	                  	if(data.results.length > 0){
                    		if(currPage > 0) $(".liveDetail-list-title .mui-active").find("input[name='pageNo']").val(params.page);
                    		for(var i = 0,len = data.results.length;i < len;i++){
                    			if(params.msgType == 'image') {
                    				str += ' <li><a class="detail-a" href="javascript:;">\
	                    				<img src="'+imgUrl+data.results[i].sysUrl+'"/>\
		                            </a></li> ';
		                        }else if(params.msgType == 'shortvideo') {
		                            str += '<li style="margin: 0 2px 5px;"><a class="detail-a" href="javascript:;"><video poster="' + imgUrl + data.results[i].picUrl + '" controls="controls" preload="none">'+
		                          '<source src="' + imgUrl + data.results[i].sysUrl + '" media="only screen and (min-device-width: 360px)">'+
		                          '<source src="' + imgUrl + data.results[i].sysUrl + '" media="only screen and (max-device-width: 960px)">'+
		                          '</video></a></li>';
		                        }
                    		}
                    		if(currPage == 1) {
                    			listObj.find("ul").html(str);
                    		}else {
                    			listObj.find("ul").append(str);
                    		}
                    		if(params.page == data.totalPage) {
                    			listObj.addClass("list-null").find(".res-more").removeClass("btn-more").addClass("loader").html('<p>没有更多了~</p>');
                    		}
                    	}else {
                    		listObj.addClass("list-null").find(".res-more").removeClass("btn-more").addClass("loader").html('<p>没有更多了~</p>');
                    	}
	                  },
	                  error:function(){
	                  	alert("系统异常，请稍后再试！");
	                  },
                      complete:function(){
                    	if(!listObj.hasClass("list-null")){
                    		listObj.find(".res-more").removeClass("loader").addClass("btn-more").show().html("<span>查看更多</span>");
                    	}
                      }
	              });
				}
				
				function readnum() {
					$.ajax({
	                  type:"POST",
	                  url:'<%=basePath%>/live/marine/read',
	                  dataType:'json',
	                  data: {
	                	  marineId: $("#marineId").val()
	                  },
	                  cache:false,
	                  success:function(data){
	                	  var readNum = parseInt($("#readNum").innerHTML);
	                	  $("#readNum").innerHTML = readNum+1;
	                  }
					});      
				}
			function loadStuImp() {
				$.ajax({
                  type:"POST",
                  url:'<%=basePath%>/live/marine/stuimp',
                  dataType:'json',
                  data: {
                	  marineId: $("#marineId").val()
                  },
                  cache:false,
                  success:function(data){
                	 var html = "";
                	 for(var i = 0; i < data.length; i++) {
                		 html += '<div class="textbox" id="'+ data[i].id +'">' + 
                		 '<div class="text_meg"><span>'+ dictDataFormat("student_imp", data[i].impression) +'</span>  <em class="stuVotes">'+ data[i].votes +'</em></div></div>';
                	 }
                	 $("#midbox").html(html);
                  }
				});      
			}
			function submitComment() {
				var params = {
                	  marineId: $("#marineId").val(),
                	  comment: $("#comment").val()
                  };
				$.ajax({
                  type:"POST",
                  url:'<%=basePath%>/live/marine/comment',
                  dataType:'json',
                  data: params,
                  cache:false,
                  success:function(data){
                	  if(data.code == '000') {
                		  $("#comment").blur();
                    	  var html = "";
                    	  html+= '<div class="pms_box"><div class="headlog"><img src="'+ data.data +'">' + 
    							'</div><div class="pmstext">' + params.comment + '</div></div>';
                    	  $("#pmsComment").before(html);
                    	  $("#comment").val(null);
                    	  $("#comment-more").html("");
                	  }
                  }
				});      
			}
			function loadComment() {
				var pageNo = parseInt($("#commentPageNo").val()) + 1;
                $.ajax({
                  type:"POST",
                  url:'<%=basePath%>/live/marine/comment/query',
                  dataType:'json',
                  data: {
                	pageNo: pageNo,
                	marineId: $("#marineId").val()
                  },
                  cache:false,
                  success:function(data){
                  	if(data.results && data.results.length > 0){
                  		var html = "";
                  		for(var i = 0,len = data.results.length;i < len;i++){
                  			html+= '<div class="pms_box"><div class="headlog"><img src="'+data.results[i].headUrl+'">' + 
							'</div><div class="pmstext">' + data.results[i].comment + '</div></div>';
                  		}
                  		$("#pmsComment").append(html);
                  		$("#commentPageNo").val(pageNo);
						if(data.totalPage == pageNo) {
                  			$("#comment-more").addClass("list-null");
                  			$("#comment-more").html("已显示全部留言");
                  		}
                  	}else {
                  		$("#comment-more").addClass("list-null");
              			$("#comment-more").html(pageNo == 1 ? "暂无留言，空位为你而留" : "已显示全部留言");
                  	}
                  },
                  error:function(){
                  	alert("系统异常，请稍后再试！");
                  }
              });
			}
			
			function addStuimp(thiz) {
                $.ajax({
                  type:"POST",
                  url:'<%=basePath%>/live/marine/stuimp/add',
                  dataType:'json',
                  data: {
                	id: thiz.id,
                  },
                  cache:false,
                  success:function(data){
                	var val = $(thiz).find(".stuVotes").html()
                  	$(thiz).find(".stuVotes").html(parseInt(val) + 1);
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