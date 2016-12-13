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
									<td>课程编号: <input class="form-control1" type="text" name="productId" id="productId"/></td>
									<td>课程名称： <input class="form-control1" type="text" name="courseName" id="courseName"/></td>
									<td>订单编号： <input class="form-control1" type="text" name="orderNo" id="orderNo"/></td>
								 </tr>
								 <tr>
									<td>购买人姓名: <input class="form-control1" type="text" name="name" id="name"/></td>
									<td colspan="2">购买人号码： <input class="form-control1" type="text" name="mobile" id="mobile"/></td>
								 </tr>
								 <tr>
									<td colspan="3">购买时间:  <input type="text" class="form-control1 Wdate" id="startDate" name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',readOnly:true})">  至 
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
	<script type="text/javascript" src="<%=basePath%>/js/course/buyRecord.js"></script>
</body>
</html>