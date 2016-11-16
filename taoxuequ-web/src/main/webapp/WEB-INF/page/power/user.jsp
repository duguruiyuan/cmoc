<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<jsp:include page="/WEB-INF/page/common/_header.jsp"></jsp:include>
<body>
	<div class="container-fluid" style="padding-top: 20px;">
		<div class="row-fluid">
			<%-- <div class="col-md-2">
				<jsp:include page="${path}/layout/menu.jsp"></jsp:include>
			</div> --%>
			<div class="col-md-10" id="content_div">
				<div>
					<!--begin 搜索栏 -->
					<div class="span12" id="body_div">
					  <form id="searchForm" class="form-search" method="post">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td>用户姓名： <input class="form-control1" type="text" name="userName" id="userName"/></td>
									<td>用户账号： <input class="form-control1" type="text" name="userAccount" id="userAccount"/></td>
									<td>用户状态： 
										<select class="form-control1" name="validFlag" id="validFlag">
											<option value="">全部</option>
											<option value="1">可用</option>
											<option value="0">禁用</option>
										</select>
									</td>
								 </tr>
								 <tr>
									<td colspan="3">
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="search('searchForm')">查找</button>
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="cleanFormPanel('searchForm')">清空</button>
										<button class="btn btn-success" type="button" onclick="addUser()">新增</button>
									</td>
								</tr>
							</tbody>
						</table>
					  </form>
					</div>
					<!--end 搜索栏  -->
				</div>
				<div style="height: 330px;">
					<table id="dataGrid"></table>
				</div>
			</div>
			<div id="confirmDialog" style="display: none;height: 'auto';">
				<div class="col-md-12">
				  <form id="addForm" class="row" novalidate="novalidate">
					<input type="hidden" id="idUser" name="idUser">
					<table class="table table-bordered">
							<col width="20%" align="right">
							<col width="80%">
							<tbody>
								<tr>
									<td>用户姓名：</td>
									<td><input class="form-control" style="width: 250px;display:inline;"
										name="userName" id="userName" />  <span id="userNameTip"></span>
									</td>
								</tr>
								<tr>
									<td>用户账号：</td>
									<td><input class="form-control" style="width: 250px;display:inline;"
										name="userAccount" id="userAccount" />  <span id="userAccountTip"></span>
									</td>
								</tr>
								<tr id="passwordTr">
									<td>登录密码</td>
									<td><input type="password" class="form-control" style="width: 250px;display:inline;"
										name="password" id="password" />  <span id="passwordTip"></span>
									</td>
								</tr>
								<tr>
									<td>有效性：</td>
									<td>
										<select class="form-control"  style="width: 250px;display:inline;" name="validFlag" id="validFlag">
											<option value="1">可用</option>
											<option value="0">禁用</option>
										</select> <span id="validFlagTip"></span>
									</td>
								</tr>
								<tr>
									<td>用户角色：</td>
									<td><span id="checkboxMenu"></span></td>
								</tr>
								<tr>
									<td colspan="2" class="text-center">
									<button class="btn btn-primary" type="button" id="selAll"> 全选</button>
										<button class="btn btn-primary" type="button" id="unselAll">全不选</button>
											<button class="btn btn-primary" type="button" id="reverse">反选</button>
										<button type="submit" class="btn btn-success">保存</button>
										<button class="btn btn-info" type="button"
											onclick="returnBack()">取消</button></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>/js/power/user.js"></script>
</body>
</html>