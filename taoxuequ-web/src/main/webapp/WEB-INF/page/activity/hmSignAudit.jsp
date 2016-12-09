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
									<td>活动编号: <input class="form-control1" type="text" name="activityId" id="activityId"/></td>
									<td>活动名称： <input class="form-control1" type="text" name="activityName" id="activityName"/></td>
									<td>活动类型： 
										<select class="form-control1" name="activityType" id="activityType">
											<option value="">全部</option>
											<option value="1">亲子活动</option>
											<option value="2">城市体验</option>
										</select>
									</td>
								 </tr>
								 <tr>
									<td>报名状态： 
										<select class="form-control1" name="isEffect" id="isEffect">
											<option value="">全部</option>
											<option value="0">报名申请中</option>
											<option value="1">已生效</option>
										</select>
									</td>
									<td colspan="2">活动时间： <input type="text" class="form-control1 Wdate" id="startDate" name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',readOnly:true})">  至 
										    <input type="text" class="form-control1 Wdate " id="endDate" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',readOnly:true})"></td>
								 </tr>
								 <tr>
									<td>透明人姓名: <input class="form-control1" type="text" name="hmName" id="hmName"/></td>
									<td>透明人电话: <input class="form-control1" type="text" name="hmMobile" id="hmMobile"/></td>
								 </tr>
								 <tr>
									<td colspan="3">
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="search('searchForm')">查找</button>
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="closeFormPanel('searchForm')">清空</button>
										<button type="button" style="height: 29px;" class="btn btn-danger btn-xs" onclick="auditDialog()">批量审核</button>
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
		</div>
	</div>
	<div id="auditDelDiolog" style="display: none;">
		<form id="auditDelForm">
			<div class="col-md-12">
				<div class="col-md-3 col-md-control">审核结果：</div>
				<div class="col-md-9">
					<input type="radio" name="isEffect" value="1" class="form-control1"/>通过
					<input type="radio" name="isEffect" value="0" class="form-control1"/>不通过
				</div>
			</div>
			<div class="col-md-12 hide" id="reason">
				<div class="col-md-3 col-md-control" >原因：</div>
				<div class="col-md-9">
					<input class="easyui-textbox" name="reason" data-options="multiline:true" style="width:300px;height:100px">
				</div>
			</div>
		</form>
		<div id="btns" style="display: none;">
			<center><a href="#" class="btn btn-success" onclick="batchAudit()">提交</a>
			<a href="#" class="btn btn-warning" onclick="closeFormPanel('auditDelForm')">取消</a></center>
		</div>
	</div>
	<div id="takeNumDiolog" style="display: none;"></div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath%>/js/activity/hmSignAudit.js"></script>
</body>
</html>