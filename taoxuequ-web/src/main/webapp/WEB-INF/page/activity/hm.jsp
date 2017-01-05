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
										<select class="form-control1 activityType" name="activityType" id="activityType">
											<option value="">全部</option>
										</select>
									</td>
								 </tr>
								 <tr>
								 	<td>战队名称: <input class="form-control1" type="text" name="marineName" id="marineName"/></td>
									<td colspan="2">活动时间： <input type="text" class="form-control1 Wdate" id="startDate" name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',readOnly:true})">  至 
										    <input type="text" class="form-control1 Wdate " id="endDate" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',readOnly:true})"></td>
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
				<div style="height: 330px;">
					<table id="dataGrid"></table>
				</div>
			</div>
		</div>
	</div>
	<div id="judgeDiolog" style="display: none;">
		<form id="judgeForm">
			<input type="hidden" id="id" name="id"/>
			<div class="col-md-12 pt10">
				<div class="col-md-9">
					活动表现：
					<select class="form-control1" id="showed" name="showed">
						<option value="1">差</option>
						<option value="2">良</option>
						<option value="3">优</option>
					</select>
				</div>
			</div>
			<div class="col-md-12 pd10">
				<div class="col-md-9">
					活动评价：
					<input class="easyui-textbox" id="judge" name="judge" data-options="multiline:true" style="width:300px;height:100px">
				</div>
			</div>
		</form>
		<div id="btns" style="display: none;">
			<center><a href="#" class="btn btn-success" onclick="judgeSubmit()">提交</a>
			<a href="#" class="btn btn-warning" onclick="closeFormPanel('judgeForm')">取消</a></center>
		</div>
	</div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath%>/js/activity/activityHm.js"></script>
</body>
</html>