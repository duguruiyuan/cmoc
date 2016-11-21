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
		<title>学习积分-陶学趣</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<header class="mui-bar mui-bar-nav">
		    <h1 class="mui-title">学习积分</h1>
		</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div class="mui-content">
		    <div class="tableList mt10">
		    	<table border="0" cellspacing="0" cellpadding="0">
		    		<thead>
			    		<tr>
			    			<th width="90">时间</th>
			    			<th>项目名称</th>
			    			<th width="40">金额</th>
			    			<th width="80">积分兑换</th>
			    		</tr>
		    		</thead>
		    		<tbody>
		    			<tr>
		    				<td class="mui-text-center">2015/09/01</td>
		    				<td>北京7天夏令营</td>
		    				<td class="mui-text-center">420</td>
		    				<td class="mui-text-center">
		    					<button type="button" class="mui-btn mui-btn-primary">兑换</button>
		    				</td>
		    			</tr>
		    			<tr>
		    				<td class="mui-text-center">2015/09/01</td>
		    				<td>北京7天夏令营</td>
		    				<td class="mui-text-center">420</td>
		    				<td class="mui-text-center">
		    					<button type="button" class="mui-btn mui-btn-primary">兑换</button>
		    				</td>
		    			</tr>
		    		</tbody>
		    	</table>
		    </div>
		</div>
		<script type="text/javascript">
			mui.init();
			mui('.footer').on('tap','a',function(){document.location.href=this.href;});
		</script>
	</body>

</html>