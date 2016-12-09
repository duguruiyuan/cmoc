<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<jsp:include page="/WEB-INF/page/common/_header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/page/common/header.jsp"></jsp:include>
	<div id="middle">
		<jsp:include page="/WEB-INF/page/common/menu.jsp"></jsp:include>
		<div id="right">
		    <jsp:include page="/WEB-INF/page/common/localinfo.jsp" />
			<div class="col-md-12" id="content_div">
				<div>
					<!--begin 搜索栏 -->
					<div class="span12" id="body_div">
					  <form id="searchForm" class="form-search" method="post">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td>课程编号: <input class="form-control1" type="text" name="activityId" id="activityId"/></td>
									<td>课程名称： <input class="form-control1" type="text" name="activityName" id="activityName"/></td>
									<td>课程状态： 
										<select class="form-control1" name="shelves" id="shelves">
											<option value="">全部</option>
											<option value="1">上架</option>
											<option value="2">下架</option>
										</select>
									</td>
								 </tr>
								 <tr>
									<td colspan="3">
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="search('searchForm')">查找</button>
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="closeFormPanel('searchForm')">清空</button>
										<button type="button" style="height: 29px;width: 80px;" class="btn btn-info btn-xs" onclick="addCourse()">新增课程</button>
									</td>
								</tr>
							</tbody>
						</table>
					  </form>
					</div>
					<!--end 搜索栏  -->
				</div>
				<div style="height: 360px;">
					<table id="dataGrid"></table>
				</div>
			</div>
		</div>
	</div>
	<div id="takeNumDiolog" style="display: none;"></div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath%>/js/course/course.index.js"></script>
</body>
</html>