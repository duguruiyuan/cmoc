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
		
		<script type="text/javascript">
	mui.init();
	mui('.footer').on('tap','a',function(){document.location.href=this.href;});
</script>
	</body>

</html>