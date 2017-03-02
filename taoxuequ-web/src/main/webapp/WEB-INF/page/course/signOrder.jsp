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
									<td>课程名称: 
										<select class="form-control1 course" style="width: 173px;" name="courseId" id="courseId">
											<option value="">全部</option>
										</select>
									</td>
									<td>排期活动: 
										<select class="form-control1 actCompent" style="width: 173px;" name="activityId" id="activityId">
											<option value="">全部</option>
										</select>
									</td>
									<td colspan="2">活动排期时间:  <input type="text" class="form-control1 Wdate" id="activityStartDate" name="activityStartDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'activityEndDate\')}',readOnly:true})">  至 
										    <input type="text" class="form-control1 Wdate " id="activityEndDate" name="activityEndDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'activityStartDate\')}',readOnly:true})"></td>
								 </tr>
								 <tr>
								 	<td>订单编号： <input class="form-control1" type="text" name="orderNo" id="orderNo"/></td>
									<td>订单状态: 
										<select class="form-control1" style="width: 173px;" name="orderStatus" id="orderStatus">
											<option value="">全部</option>
											<option value="000">000已支付</option>
											<option value="001">001未支付</option>
										</select>
									</td>
									<td colspan="2">报名时间:  <input type="text" class="form-control1 Wdate" id="orderStartTime" name="orderStartTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'orderEndTime\')}',readOnly:true})">  至 
										    <input type="text" class="form-control1 Wdate " id="orderEndTime" name="orderEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'orderStartTime\')}',readOnly:true})"></td>
								 </tr>
								 <tr>
								 	<td>报名联系人： <input class="form-control1" type="text" name="signName" id="signName"/></td>
									<td>报名联系电话： <input class="form-control1" type="text" name="signPhone" id="signPhone"/></td>
								 </tr>
								 <tr>
									<td colspan="3">
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="search('searchForm')">查找</button>
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="closeFormPanel('searchForm')">清空</button>
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
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath%>/js/course/signOrder.js"></script>
</body>
</html>