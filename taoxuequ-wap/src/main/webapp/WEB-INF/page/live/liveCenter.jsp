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
			<div class="mui-card">
				<a href="<%=basePath %>/live/marine/list/12">
					<div class="mui-card-content">
						<img src="<%=basePath %>/images/slider1.jpg" alt="" width="100%" height="154">
					</div>
					<div class="mui-card-footer">
						<div class="courseList-line1">穿越课堂</div>
						<div class="courseList-line2">[城市体验] 第18期 <span class="courseList-state">[进行中]</span></div>
						<div class="courseList-line3"><span class="clock-ico"></span>2016-11-12 | 穿越课堂</div>
						<span class="courseList-text">直播</span>
					</div>
				</a>
			</div>
		
			<div class="mui-card">
				<a href="<%=basePath %>/live/marine/list/12">
					<div class="mui-card-content">
						<img src="<%=basePath %>/images/slider1.jpg" alt="" width="100%" height="154">
					</div>
					<div class="mui-card-footer">
						<div class="courseList-line1">穿越课堂</div>
						<div class="courseList-line2">[城市体验] 第18期 <span class="courseList-state">[进行中]</span></div>
						<div class="courseList-line3"><span class="clock-ico"></span>2016-11-12 | 穿越课堂</div>
						<span class="courseList-text">直播</span>
					</div>
				</a>
			</div>
		</div>
		
		<script type="text/javascript">
			mui.init();
			mui('.footer').on('tap','a',function(){document.location.href=this.href;});
			
			$(function(){
			
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
				
			})
			
			/*
			(function($, doc) {
//				return false;
				$.init();
				$.ready(function() {
					//普通示例
					var userPicker = new $.PopPicker();
					userPicker.setData([{
						value: 'ywj',
						text: '董事长 叶文洁'
					}, {
						value: 'aaa',
						text: '总经理 艾AA'
					}, {
						value: 'lj',
						text: '罗辑'
					}, {
						value: 'ymt',
						text: '云天明'
					}, {
						value: 'shq',
						text: '史强'
					}, {
						value: 'zhbh',
						text: '章北海'
					}, {
						value: 'zhy',
						text: '庄颜'
					}, {
						value: 'gyf',
						text: '关一帆'
					}, {
						value: 'zhz',
						text: '智子'
					}, {
						value: 'gezh', 
						text: '歌者'
					}]);
					$("#selectYear").click(function(){
						userPicker.show(function(items) {
							userResult.innerText = JSON.stringify(items[0]);
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
					})
					
					var showUserPickerButton = doc.getElementById('showUserPicker');
					var userResult = doc.getElementById('userResult');
					showUserPickerButton.addEventListener('tap', function(event) {
						userPicker.show(function(items) {
							userResult.innerText = JSON.stringify(items[0]);
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
					}, false);
					//-----------------------------------------
					//级联示例
					var cityPicker = new $.PopPicker({
						layer: 2
					});
					cityPicker.setData(cityData);
					var showCityPickerButton = doc.getElementById('showCityPicker');
					var cityResult = doc.getElementById('cityResult');
					showCityPickerButton.addEventListener('tap', function(event) {
						cityPicker.show(function(items) {
							cityResult.innerText = "你选择的城市是:" + items[0].text + " " + items[1].text;
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
					}, false);
					//-----------------------------------------
					//					//级联示例
					var cityPicker3 = new $.PopPicker({
						layer: 3
					});
					cityPicker3.setData(cityData3);
					var showCityPickerButton = doc.getElementById('showCityPicker3');
					var cityResult3 = doc.getElementById('cityResult3');
					showCityPickerButton.addEventListener('tap', function(event) {
						cityPicker3.show(function(items) {
							cityResult3.innerText = "你选择的城市是:" + (items[0] || {}).text + " " + (items[1] || {}).text + " " + (items[2] || {}).text;
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
					}, false);
				});
			})(mui, document);
		*/
		</script>
	</body>

</html>