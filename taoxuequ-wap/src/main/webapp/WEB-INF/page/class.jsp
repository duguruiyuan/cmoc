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
		<title>实践课堂[陶学趣]</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<jsp:include page="/WEB-INF/page/common/_header.jsp" />
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">实践课堂</h1>
		</header>
		<jsp:include page="/WEB-INF/page/common/head.jsp" />
		<div class="mui-content">
		    <div class="mui-card sjktList">
			    <ul class="mui-table-view">
		            <li class="mui-table-view-cell">
		            	<a class="sjktList-item">
			    			<div class="sjktList-content mui-clearfix">
			    				<img src="<%=basePath %>/images/text.png" />
			    			</div>
			    			<div class="sjktList-title">
			    				课程报名：中学MYP跨学科（课程描述）
			    			</div>
		    			</a>
		            </li>
		            <li class="mui-table-view-cell">
		            	<a class="sjktList-item">
			    			<div class="sjktList-content mui-clearfix">
			    				<img src="<%=basePath %>/images/text.png" />
			    			</div>
			    			<div class="sjktList-title">
			    				课程报名：中学MYP跨学科（课程描述）
			    			</div>
		    			</a>
		            </li>
		            <li class="mui-table-view-cell">
		            	<a class="sjktList-item">
			    			<div class="sjktList-content mui-clearfix">
			    				<img src="<%=basePath %>/images/text.png" />
			    			</div>
			    			<div class="sjktList-title">
			    				课程报名：中学MYP跨学科（课程描述）
			    			</div>
		    			</a>
		            </li>
		            <li class="mui-table-view-cell">
		            	<a class="sjktList-item">
			    			<div class="sjktList-content mui-clearfix">
			    				<img src="<%=basePath %>/images/text.png" />
			    			</div>
			    			<div class="sjktList-title">
			    				课程报名：中学MYP跨学科（课程描述）
			    			</div>
		    			</a>
		            </li>
		            <li class="mui-table-view-cell">
		            	<a class="sjktList-item">
			    			<div class="sjktList-content mui-clearfix">
			    				<img src="<%=basePath %>/images/text.png" />
			    			</div>
			    			<div class="sjktList-title">
			    				课程报名：中学MYP跨学科（课程描述）
			    			</div>
		    			</a>
		            </li>
		            <li class="mui-table-view-cell">
		            	<a class="sjktList-item">
			    			<div class="sjktList-content mui-clearfix">
			    				<img src="<%=basePath %>/images/text.png" />
			    			</div>
			    			<div class="sjktList-title">
			    				课程报名：中学MYP跨学科（课程描述）
			    			</div>
		    			</a>
		            </li>
		        </ul>
		    </div>
		</div>
		<jsp:include page="/WEB-INF/page/common/_footer.jsp" />
	</body>

</html>