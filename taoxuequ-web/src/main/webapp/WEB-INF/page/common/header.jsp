<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>  
<header id="top" class="clearfix">
	<a href="<%=basePath %>/auth/main"><img class="logoIns" /></a>
	<div class="header-right">
		<a href="javascript:void(0)" onclick="addFavorite();" role="button" data-toggle="modal"><i class="fa fa-star-half-empty" style="color:#fff;"></i> 收藏系统</a>
        <a href="javascript:void(0)" onclick="logout()" role="button" data-toggle="modal"><i class="fa fa-sign-out" style="color:#fff;"></i> 退出</a>
        <a href="javascript:void(0)" onclick="updatePwdForm()" role="button" data-toggle="modal"><i class="fa fa-edit" style="color:#fff;"></i> 修改密码</a>
    	<span><i class="fa fa-user" style="color:#fff;"></i> ${appUser.userName }</span>
    </div>
	<nav>
		<ul>
		</ul>
	</nav>
</header>
<div id="updatePwdDialog" style="display: none;height: 'auto';">
	<div class="col-md-12">
	  <form id="updatePwdForm" novalidate="novalidate">
		<input type="hidden" id="idUser" name="idUser">
		<table class="table table1 table-bordered">
				<col width="30%" align="right">
				<col width="70%">
				<tbody>
					<tr>
						<td>登录账号：</td>
						<td><input class="form-control" name="userAccount" id="userAccount" value="${appUser.userAccount }"/> <span id="userAccountTip"></span>
						</td>
					</tr>
					<tr>
						<td>原始密码：</td>
						<td><input type="password" class="form-control" name="oldPwd" id="oldPwd" /> <span id="oldPwdTip"></span>
						</td>
					</tr>
					<tr>
						<td>新密码：</td>
						<td><input type="password" class="form-control" name="newPwd" id="newPwd" /> <span id="newPwdTip"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<button type="submit" class="btn btn-success">保存</button>
							<button class="btn btn-info" type="button" onclick="closeFormPanel('updatePwdForm')">取消</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>