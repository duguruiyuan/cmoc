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
									<td>姓名: <input class="form-control1" type="text" name="hmName" id="hmName"/></td>
									<td>手机号码： <input class="form-control1" type="text" name="hmMobile" id="hmMobile"/></td>
									<td>证件号码： <input class="form-control1" type="text" name="idCard" id="idCard"/></td>
								 </tr>
								 <tr>
									<td>性别: 
										<select class="form-control1" id="sex" name="sex">
											<option value="">全部</option>
											<option value="F">帅哥</option>
											<option value="M">美女</option>
										</select>
									</td>
									<td>状态： 
										<select class="form-control1" id="isActive" name="isActive">
											<option value="">全部</option>
											<option value="0">注册申请中</option>
											<option value="1">已激活</option>
										</select>
								 </tr>
								 <tr>
									<td colspan="3">
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="search('searchForm')">查找</button>
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="cleanFormPanel('searchForm')">清空</button>
									</td>
								</tr>
							</tbody>
						</table>
					  </form>
					</div>
					<!--end 搜索栏  -->
				</div>
				<div style="height: 380px;">
					<table id="dataGrid"></table>
				</div>
			</div>
		</div>
	</div>
	<div id="takeNumDiolog" style="display: none;"></div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath%>/js/hm/hm.js"></script>
</body>
</html>