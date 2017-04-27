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
		<title>直播大厅[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<script type="text/javascript">
		window.param = {
			title: '陶学趣,专注于青少年社会实践教育',
			desc: '我们在直播大厅等你，不见不散 🐳 💝 📷。陶学趣,专注于青少年社会实践教育,欢迎您光临！',
			wZoneTitle: '邀您来看直播 🐳 💝 📷。陶学趣,专注于青少年社会实践教育,欢迎您光临！',
			imgUrl: '<%=basePath %>/images/whead.png' 
		}
	</script>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body id="courseList">
		<header class="mui-bar mui-bar-nav">
		    <ul>
		    	<li id="selectYear">
		    		<span><label id="yearText">选择年份 </label><i class="mui-icon mui-icon-arrowdown" style="color: #000;"></i></span>
		    		<input type="hidden" id="year" />
		    	</li>
		    	<li id="selectMonth">
		    		<span><label id="monthText">选择月份 </label><i class="mui-icon mui-icon-arrowdown" style="color: #000;"></i></span>
		    		<input type="hidden" id="month" />
		    	</li>
		    </ul>
		</header>
		<input type="hidden" id="pageNo" value="0">
		<div class="mui-content mt50">
			<div id="live-list">
			
			</div>
			<div class="loader" style="padding: 50px 0px;">
	 			<p>玩命加载中<span></span><span></span><span></span></p>
	 		</div>
		</div>
		<script id="table-template" type="text/x-handlebars-template">
				{{#if results}}
 				  {{#each results}}
				  <div class="mui-card" style="width: 96%">
					<a href="<%=basePath %>/live/marine/list/{{id}}">
						<div class="mui-card-content">
							<img src="{{activityImgFormat activityImgUrl}}" alt="" width="100%">
						</div>
						<div class="mui-card-footer">
							<div class="courseList-line1">{{activityName}}</div>
							<div class="courseList-line2">[{{activityTypeFormat activityType}}] {{activityNum}} <span class="courseList-state">[{{statusFormat endDate}}]</span></div>
							<div class="courseList-line3"><span class="clock-ico"></span>{{startDateFormat startDate}}</div>
							<span class="courseList-text">直播</span>
						</div>
					</a>
				  </div>
                  {{/each}}
				{{else}}
					<div style="text-align: center;padding-top: 55px;">亲，暂无活动哦</div>
				{{/if}}
		  </script>
		<jsp:include page="/WEB-INF/page/common/_footer.jsp" /> 
		<script type="text/javascript">
			$(function(){
				var picListWrap = $(".mui-content");
				var picListTop = $("#live-list").offset().top;
				var picList = picListWrap.find(".mui-card");
				var picHeight = picList.width();
				picList.css({height:picHeight});
				
				initData();
				$(window).scroll(function(){
				  var listObj = $(".mui-content");
				  if(listObj.hasClass("list-null")>0){
						return false;
				  }
				  if ($(window).scrollTop() + $(window).height() + 10 >= $(document).height() && $(window).scrollTop() > 20) {
					  initData();
	              }
	            }).trigger("scroll");
				
				var yearPicker = new mui.PopPicker();
				var monthPicker = new mui.PopPicker();
				
				var yearList = [{value: "", text: "全部"}];
				var year = parseInt(new Date().getFullYear());
				for(var i = 0; i < (year - 2015); i++) {
					yearList.push({value: year - i, text: year - i});
				}
				
				var monthList = [{
					value: '',
					text: '全部'
				}, {
					value: '1',
					text: '1'
				}, {
					value: '2',
					text: '2'
				}, {
					value: '3',
					text: '3'
				}, {
					value: '4',
					text: '4'
				}, {
					value: '5',
					text: '5'
				}, {
					value: '6',
					text: '6'
				}, {
					value: '7',
					text: '7'
				}, {
					value: '8',
					text: '8'
				}, {
					value: '9',
					text: '9'
				}, {
					value: '10',
					text: '10'
				}, {
					value: '11',
					text: '11'
				}, {
					value: '12',
					text: '12'
				}];
				
				yearPicker.setData(yearList);
				monthPicker.setData(monthList);
				
				$("#selectYear").click(function(){
					var flag = false;
					yearPicker.show(function(items) {
						if(!flag){
							var params = {
						    	page: 1,
						    	year: items[0].value,
						    	month: $("#month").val()
						    }
							$("#yearText").html(items[0].text);
							$("#year").val(items[0].value);
							loadItem(params, true);
							flag = true;
						}
					});
				})
				
				$("#selectMonth").click(function(){
					var flag = false;
					monthPicker.show(function(items) {
						if(!flag){
							flag = true;
							var params = {
						    	page: 1,
						    	month: items[0].value,
						    	year: $("#year").val()
						    }
							$("#monthText").html(items[0].text);
							$("#month").val(items[0].value);
							loadItem(params, true);
						}
					});
				})
				
			});
			
			function initData() {
				var pageNo = parseInt($("#pageNo").val()) + 1;
				var params = {
			    	page: pageNo
			    }
				loadItem(params);
			}
			
			function loadItem(params, reload) {
				var listObj = $(".mui-content");
				params.year = $("#year").val();
				params.month = $("#month").val();
				$.ajax({
				    type: 'post',
				    url: '<%=basePath %>/live/json/activity/query',
				    data: params,
				    dataType: 'json',
				    cache:false,
                    beforeSend:function(){
                    	listObj.find(".loader").show().html('<p>玩命加载中<span></span><span></span><span></span></p>');
                    },
				    success: function(data) {
				    	var myTemplate = Handlebars.compile($("#table-template").html());
				    	
				        Handlebars.registerHelper("startDateFormat", function(startDate) {
				    		return getTime(startDate, 'yyyy-MM-dd');
				    	});
				        
				        Handlebars.registerHelper("statusFormat", function(date) {
				        	if(date > new Date().getTime()) {
				        		return "直播中";
				        	}
				    		return "已结束";
				    	});
				    	Handlebars.registerHelper("activityTypeFormat", function(val) {
				    		return dictDataFormat("activity_type", val);
				    	});
				        Handlebars.registerHelper("activityImgFormat", function(activityImgUrl) {
				        	if(activityImgUrl) {
				        		return imgUrl + activityImgUrl;
				        	}else {
				        		return "<%=basePath %>/images/slider1.jpg";
				        	}
				        });
				        if(reload) {
				        	$('#live-list').html(myTemplate(data));
				        	if(params.page == data.totalPage) {
				        		listObj.addClass("list-null").find(".loader").html('<p>以上是全部活动了~</p>');
				        	}
				        }else {
				        	$('#live-list').append(myTemplate(data));
				        	if(data.results && data.results.length > 0) {
					        	if(params.page == data.totalPage) {
					        		listObj.addClass("list-null").find(".loader").html('<p>以上是全部活动了~</p>');
					        	}else {
					        		$("#pageNo").val(params.page);
					        	}
					        }else {
					        	if(params.page == 1) return;
		                    	listObj.addClass("list-null").find(".loader").html('<p>以上是全部活动了~</p>');
					        }
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