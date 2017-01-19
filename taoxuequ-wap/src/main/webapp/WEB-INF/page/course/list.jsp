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
<title>[陶学趣]课程列表</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp" />
<body class="">
		<header class="header">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" style="color: #fff;"></a>
			陶学趣
		</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div class="mui-content">
			<input type="hidden" id="pageNo" value="0"/>
		    <div class="course-list" id="course-list">
			    
			</div>
			<div class="loader" style="padding: 20px 0px;display: none;">
	 			<p>玩命加载中<span></span><span></span><span></span></p>
	 		</div>
		</div>
		<script id="table-template" type="text/x-handlebars-template">	
					{{#if results}}
 				    {{#each results}}
					<div class="z_card">
						<div id="card_list" class="parent1">
				            <div class="card">
				                <div class="card_con">
				                    <a href="<%=basePath%>/course/detail/{{id}}">
				                        <img src="{{courseImgFormat courseImg }}">
				                    </a>
				                    <button class="small-btn"><span>{{courseTypeFormat courseType }}</span></button>
				                </div>
				                <div class="card_top">
				                    <div class="top_title">
				                        <span class="zhuangtai enable">报名中</span><a class="href" >{{courseName }}</a>
				                    </div>
				                </div>
				                <div class="card_foot">
				                    <div class="jine clearfix">
				                        	上课时间：{{courseDateFormat startDate endDate}}
				                        <i class="iconfont icon">&#xe628;</i><span>{{cityFormat city }}</span>
				                    </div>
				                    <ul class="simple-info" style="border-bottom-style: none; margin-bottom: 0px;">
				                        <li style="margin-bottom: 0px;">
				                            <a href="javascript:;" class="item-link cleardix" style="text-align: left;">
				                                <div class="z-price fl" style="display: inline-block; margin-right: 10px;"><i>课程学费：</i><i class="thjg">¥{{totalPrice }}</i></div>
				                                <div class="jd-point fr" style="display: inline-block; margin-right: 10px;"><i>已报名家长：</i><i class="yuyue">{{peoples }}人</i></div>
				                            </a>
				                        </li>
				                    </ul>
				
				                    <div class="exp_text mt10">
				                        <a href="<%=basePath%>/course/detail/{{id}}"><span class="clearfix"><span>{{courseDesc }}</span><span class="fr more-detail">查看详情<i class="iconfont icon">&#xe689;</i></span></span></a>
				                    </div>
				                </div>
				            </div>
				        </div>
					</div>
					{{/each}}
					{{else}}
						<div style="text-align: center;padding-top: 35px;">亲，暂无课程哦</div>
					{{/if}}
				</script>
		<script type="text/javascript">
		   window.param = {
				title: '陶学趣,专注青少年社会实践教育',
				desc: '精彩课程这里，邀你来趣学学。陶学趣,专注于青少年社会实践教育,欢迎您光临！',
				wZoneTitle: '精彩课程这里，邀你来趣学学。陶学趣,专注于青少年社会实践教育,欢迎您光临！',
				imgUrl: '<%=basePath %>/images/whead.png' 
			}
		</script>
		<jsp:include page="/WEB-INF/page/common/_footer.jsp" />
		<script type="text/javascript">
			$(function() {
				loadItem();
				$(window).scroll(function(){
				  var listObj = $(".mui-content");
				  if(listObj.hasClass("list-null")>0){
						return false;
				  }
				  if ($(window).scrollTop() + $(window).height() + 10 >= $(document).height() && $(window).scrollTop() > 20) {
					  loadItem();
	              }
	            }).trigger("scroll");
			});
			
			function loadItem() {
				var pageNo = parseInt($("#pageNo").val()) + 1;
				var listObj = $(".mui-content");
				$.ajax({
				    type: 'post',
				    url: '<%=basePath %>/course/list/query',
				    data: {
				    	page: pageNo
				    },
				    dataType: 'json',
				    cache:false,
                    beforeSend:function(){
                    	listObj.find(".loader").show().html('<p>玩命加载中<span></span><span></span><span></span></p>');
                    },
				    success: function(data) {
				    	var myTemplate = Handlebars.compile($("#table-template").html());
				    	
				        Handlebars.registerHelper("courseTypeFormat", function(val) {
				    		return dictDataFormat("course_type", val);
				    	});
				        
				        Handlebars.registerHelper("cityFormat", function(val) {
				    		return dictDataFormat("city", val);
				    	});
				        
				        Handlebars.registerHelper("courseDateFormat", function(startDate, endDate) {
				        	var str = "";
				        	if(startDate) {
				        		str += "<i>" + getTime(startDate, 'MM月dd日') + "</i>";
				        	}
				        	if(endDate) {
				        		str += "-<i>" + getTime(endDate, 'MM月dd日') + "</i>";
				        	}
				    		return new Handlebars.SafeString(str);
				    	});
				        Handlebars.registerHelper("courseImgFormat", function(val) {
				        	if(val) {
				        		return imgUrl + val;
				        	}else {
				        		return "<%=basePath %>/images/slider1.jpg";
				        	}
				        });
				        $('#course-list').append(myTemplate(data));
			        	if(data.results && data.results.length > 0) {
				        	if(pageNo == data.totalPage) {
				        		listObj.addClass("list-null").find(".loader").html('<p>以上是全部活动了~</p>');
				        	}else {
				        		$("#pageNo").val(pageNo);
				        	}
				        }else {
				        	if(pageNo == 1) return;
				        	listObj.addClass("list-null").find(".loader").html('<p>以上是全部活动了~</p>');
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
				});
			}
		</script>
	</body>
</html>