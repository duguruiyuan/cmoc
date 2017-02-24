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
<title>[陶学趣]课程详情</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp" />
<body class="course-detail">
		<header class="header">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" href="<%=basePath%>/course"></a>
			陶学趣
		</header>
		<div class="course-img mt44">
			<img src="${config.imgUrl }${course.courseImg }" id="course-img">
		</div>
		<div class="course-title" id="course-name">
			${course.courseName }
		</div>
		<c:choose>
        	<c:when test="${course.signWay == 1 }">
        	 <div class="pl10">
        		 	课程价格：<span class="price">¥<label>${course.totalPrice }</label> 组/5人</span>
          	 </div>
          	 <div class="clearfix mt10 dbb">
          	 	 <p><i class="fa fa-universal-access" style="font-size: 17px;color: #E95800;"></i> 适合7~12岁儿童参加</p>
                  <p><i class="fa fa-clock-o" style="font-size: 18px;color: #E95800;"></i>
	                  <c:choose>
	                  	<c:when test="${schuduList.size() == 0 }">暂无场次可选</c:when>
	                  	<c:otherwise>有${schuduList.size()}个场次可选</c:otherwise>
	                  </c:choose>
                  </p>
                  <p><i class="fa fa-map-marker" style="font-size: 18px;color: #E95800;"></i> ${course.addr }</p>
              </div>
              <div class="dbb" id="cal-change">
              	<div class="fem12 lh25"><i class="fa fa-list-ol"></i> 选期参与</div>
	                <c:if test="${schuduList.size() == 0 }">
	                	<div class="cal-none">暂无排期活动</div>
	                </c:if>
              		<c:forEach var="itm" items="${schuduList }">
              			<div class="cal-content" data-pid="${course.id }" data-aid="${itm.activityId }">
		              		<div class="cal-left">
		              			<span>${itm.activityNum } 【${itm.activityName }】</span><br/>
								<span><fmt:formatDate value="${itm.startDate}" type="date" dateStyle="full"/></span><br/>
								<span>已报名${itm.buyCount }组</span>
		              		</div>
		              		<div class="cal-right">
		              			<span>[剩余<fmt:parseNumber integerOnly="true" value="${itm.activityPeoples/5 + (itm.activityPeoples%5 == 0 ? 0 : 1) - itm.buyCount }" />队]</span><br/>
		              			<span></span><br/>
		              			<span></span>
		              		</div>
	              		</div>
              		</c:forEach>
              </div>
        	</c:when>
        	<c:otherwise>
	         	<div class="wrapper">
		          	<div class="box">
			            <div class="f_le">
			               	 课程价格：<span class="price">¥<label>${course.totalPrice }</label><c:if test="${course.signWay == 1 }"> 组/5人</c:if></span>
			            </div>
			            <div class="clear"></div>
			        </div>
			        <div class="barT">
			          	<ul>
			              	<li>
			                   <span class="price_gray">¥<label>${course.totalPrice }</label></span>
			                   <br> 课程价格
			                   <i class="split"></i>
			               </li>
			             		<li>
			                   <span class="price_gray">¥<label>${course.resAmount }</label></span>
			                   <br> 预约金
			                   <i class="split"></i>
			               </li>
			               <li>
			                   <span class="price_gray"><label>${course.peoples }</label>人</span>
			                   <br> 已预约家长
			                   <i class="split"></i>
			               </li>
			             </ul>
			         </div>
	       		</div>
        	</c:otherwise>
        </c:choose>
		<div class="course-tab">
			<ul>
				<li class="cur">课程详情</li>
				<li>已预约家长</li>
				<li>往期直播</li>
			</ul>
		</div>
		<div class="course-content">
			<div class="course-content-detail" id="courseDetail">
				${desc }
			</div>
			<div class="course-content-stu" style="display: none;">
				<ul>
				</ul>
				<p class="more" id="more">查看更多</p>
				<input type="hidden" id="pageNo" value="0"/>
			</div>
			<div class="course-content-live" style="display: none;">
				<ul>
				</ul>
				<p class="more" id="more">查看更多课程</p>
			</div>
		</div>
		<footer class="footer" style="padding:12px 10px;">
	        <div class="btn-wrap clearfix" id="footerInner">
	            <a href="<%=basePath %>/course/list" class="wbl-btn btn-border " id="newest">
	               	 更多课程
	            </a>
	            <c:choose>
        			<c:when test="${course.signWay == 1 }">
        				<a href="javascript:void(0)" class="wbl-btn btn-go" id="submit"><label>预约报名</label><i class="iconfont icon"></i></a>
        			</c:when>
        			<c:otherwise>
        				<a href="<%=basePath %>/course/sign/${course.id}" class="wbl-btn btn-go" id="submit" ms-class-disabled="submit_enabled"><label>预约报名</label><i class="iconfont icon"></i></a>
        			</c:otherwise>
	            </c:choose>
	        </div>
	    </footer>
	    <script type="text/javascript">
	    	window.param = {
				title: $("#course-name").html() + ', 点我来了解我的精彩',
				desc: '精彩课程这里，邀你来趣学学。陶学趣,专注于青少年社会实践教育,欢迎您光临！',
				wZoneTitle: '精彩课程这里，邀你来趣学学。陶学趣,专注于青少年社会实践教育,欢迎您光临！',
				imgUrl: $("#course-img").attr("src") 
			}
		</script>
		<script src="<%=basePath %>/js/plugins/wechat-config.js?v=${config.version}" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			mui.init();
			
			$(function(){
				loadBuyer();
				$(".course-tab li").click(function(){
					var index = $(this).index();
					var tabContent = $(".course-content > div");
					var tabCourse = $(".course-tab > ul li");
					tabContent.hide().eq(index).show();
					tabCourse.removeClass("cur").eq(index).addClass("cur");
				});
				
				$(".more").click(function(){
					loadBuyer();
				});
				
				$(".cal-content").click(function() {
					$(this).addClass("cal-content-active").siblings().removeClass("cal-content-active");
				});
				
				$("#submit").click(function() {
					if($("#cal-change").find(".cal-content-active").length == 0) {
						window.location.hash = "#cal-change";
					}else {
						var obj = $("#cal-change").find(".cal-content-active");
						window.location.href = basePath + "/course/sign/" + obj.attr("data-pid") + "/" + obj.attr("data-aid");
						return;
					}	
				});
			})
			
			function loadBuyer() {
				 var pageNo = parseInt($("#pageNo").val()) + 1;
				 $.ajax({
	                  type:"POST",
	                  url:'<%=basePath%>/course/json/buyer',
	                  dataType:'json',
	                  data: {
	                	 page: pageNo,
	                	 productId: parseInt('${course.id}')
	                  },
	                  cache:false,
	                  success:function(data){
	                  	var str = "";
	                  	var result = data.results;
	                  	if(result.length > 0){
	                  		$("#pageNo").val(pageNo);
	                  		if(data.totalPage == pageNo) {
	                  			$("#more").removeClass("more");
	                  			$("#more").html("以上为报名此课程的所有家长");
	                  		}
	                  		for(var i = 0; i < result.length; i++){
	    						str += '<li style="display: list-item;">'+
	    			                        '<img src="${config.imgUrl}'+result[i].headImg +  
	    			                        '"/><h4 class="nick-name">'+result[i].name+
	    			                        '<span class="type pl15" style="display: inline;padding-left:10px;">成功预约</span></h4>'+
	    			                        '<span class="price">¥<label style="display: inline;">'+result[i].resAmount+
	    			                        '</label></span></li>';
	    					}
	    					$(".course-content-stu ul").append(str);
	                  	}else {
	                  		if(pageNo == 1) $(".course-content-stu .more").hide();
	                  	}
	                  }
				  });
			 }
			
		</script>
</body>
</html>