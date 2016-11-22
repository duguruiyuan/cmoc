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
		<title>直播大厅-陶学趣</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body id="courseList">
		<header class="mui-bar mui-bar-nav">
		    <ul>
		    	<li id="selectYear">
		    		<span>选择年份 <i class="mui-icon mui-icon-arrowdown"></i></span>
		    	</li>
		    	<li id="selectMonth">
		    		<span>选择月份 <i class="mui-icon mui-icon-arrowdown"></i></span>
		    	</li>
		    </ul>
		</header>
		
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		
		<div class="mui-content">
		</div>
		<script id="table-template" type="text/x-handlebars-template">
				{{#if rows}}
 				  {{#each rows}}
				  <div class="mui-card">
					<a href="<%=basePath %>/live/marine/list/{{id}}">
						<div class="mui-card-content">
							<img src="<%=basePath %>/images/slider1.jpg" alt="" width="100%" height="154">
						</div>
						<div class="mui-card-footer">
							<div class="courseList-line1">{{activityName}}</div>
							<div class="courseList-line2">[{{activityTypeValue}}] {{activityNum}} <span class="courseList-state">[{{statusFormat endDate}}]</span></div>
							<div class="courseList-line3"><span class="clock-ico"></span>{{startDateFormat startDate}} | {{activityName}}</div>
							<span class="courseList-text">直播</span>
						</div>
					</a>
				  </div>
                  {{/each}}
				{{else}}
					<div style="text-align: center;padding-top: 20px;">亲，暂无数据哦</div>
				{{/if}}
		  </script>
		<script type="text/javascript">
			mui.init();
			mui('.footer').on('tap','a',function(){document.location.href=this.href;});
			
			$(function(){
				initData();
				var yearPicker = new mui.PopPicker();
				var monthPicker = new mui.PopPicker();
				
				var yearList = [{
					value: '2015',
					text: '2015'
				}, {
					value: '2016',
					text: '2016'
				}];
				
				var monthList = [{
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
							flag = true;
							console.log(JSON.stringify(items[0]));
						}
					});
				})
				
				$("#selectMonth").click(function(){
					var flag = false;
					monthPicker.show(function(items) {
						if(!flag){
							flag = true;
							console.log(JSON.stringify(items[0]));
						}
					});
				})
				
			});
			
			function initData() {
				$.ajax({
				    type: 'post',
				    url: '<%=basePath %>/live/json/activity/query',
				    data: {},
				    dataType: 'json',
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
				        $('.mui-content').html(myTemplate(data));
				    }
				});
			}
		</script>
	</body>

</html>