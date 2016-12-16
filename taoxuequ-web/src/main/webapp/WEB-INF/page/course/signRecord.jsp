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
									<select class="form-control1 course" style="width: 173px;" name="productId" id="productId">
										<option value="">全部</option>
									</select>
									<td>订单编号： <input class="form-control1" type="text" name="orderNo" id="orderNo"/></td>
								 	<td>是否安排活动：
									 	<select class="form-control1" name="status" id="status">
											<option value="">全部</option>
											<option value="002">已安排</option>
											<option value="001">未安排</option>
										</select>
								 	</td>
								 </tr>
								 <tr>
									<td>购买人姓名: <input class="form-control1" type="text" name="name" id="name"/></td>
									<td>购买人号码： <input class="form-control1" type="text" name="mobile" id="mobile"/></td>
									<td>是否电话确认： 
										<select class="form-control1" name="isPhoneConfirm" id="isPhoneConfirm">
											<option value="">全部</option>
											<option value="1">已确认</option>
											<option value="0">未确认</option>
										</select>
									</td>
								 </tr>
								 <tr>
									<td colspan="3">报名时间:  <input type="text" class="form-control1 Wdate" id="startDate" name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',readOnly:true})">  至 
										    <input type="text" class="form-control1 Wdate " id="endDate" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',readOnly:true})"></td>
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
	<script type="text/javascript" src="<%=basePath%>/js/course/signRecord.js"></script>
</body>
</html>