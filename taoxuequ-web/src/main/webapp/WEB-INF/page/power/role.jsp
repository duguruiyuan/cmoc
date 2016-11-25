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
				<div></div>
			</div>
			<div class="col-md-12" id="body_div" style="display: none;">
				<!--begin 搜索栏 -->
				<div class="span12">
				  <form id="searchForm" class="form-search" method="post">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td>角色名称： <input class="form-control1" type="text" name="roleName" id="roleName"/></td>
							 </tr>
							 <tr>
								<td>
									<button type="button" style="height: 28px;" class="btn btn-default" onclick="search('searchForm')">查找</button>
									<button type="button" style="height: 28px;" class="btn btn-default" onclick="cleanFormPanel('searchForm')">清空</button>
									<button class="btn btn-success" type="button" onclick="addRole()">新增</button>
								</td>
							</tr>
						</tbody>
					</table>
				  </form>
				</div>
				<!--end 搜索栏  -->
				<div style="height: 330px;">
					<table id="dataGrid"></table>
				</div>
			</div>
			
			<div class="col-md-12" style="display: none;" id="role_add">
			  <form id="addForm" novalidate="novalidate">
				<input type="hidden" id="idRole" name="idRole">
				<table class="table table-bordered">
						<col width="15%">
						<col width="85%">
						<tbody>
							<tr>
								<td>角色名称：</td>
								<td><input class="form-control" style="width: 300px;display:inline;"
									name="roleName" id="roleName" />  <span id="roleNameTip"></span>
								</td>
							</tr>
							<tr>
								<td>角色描述：</td>
								<td><input class="form-control" style="width: 300px;display:inline;"
									name="roleDesc" id="roleDesc" /> <span id="roleDescTip"></span>
								</td>
							</tr>
							<tr>
								<td>角色菜单：</td>
								<td><span id="checkboxMenu"></span></td>
							</tr>
							<tr>
								<td colspan="2" class="text-center">
								<button class="btn btn-primary" type="button" id="selAll"> 全选</button>
									<button class="btn btn-primary" type="button" id="unselAll">全不选</button>
										<button class="btn btn-primary" type="button" id="reverse">反选</button>
									<button type="submit" class="btn btn-success">提交</button>
									<button class="btn btn-info" type="button"
										onclick="returnBack()">返回</button></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath%>/js/power/role.js"></script>
</body>
</html>