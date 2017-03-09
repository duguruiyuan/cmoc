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
										<button type="button" class="btn btn-warning" onclick="childSignReport('searchForm')">报表导出</button>
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
	<div id="childDialog" style="display: none;height: 'auto';">
		<div class="col-md-12 p10">
		  <form id="childUpdateForm" novalidate="novalidate">
				<input type="hidden" id="id" name="id">
				<div class="form-group col-md-6">
					<label for="childName">小孩姓名</label>								
					<input class="form-control" name="childName" id="childName">
					</select>
				</div>
				<div class="form-group col-md-6">
					<label for="childIdcard">小孩身份证</label>								
					<input class="form-control" name="childIdcard" id="childIdcard">
				</div>
				<div class="form-group col-md-6">
					<label for="childSex">小孩性别</label>								
					<select class="form-control" name="childSex" id="childSex">
						<option value="F">男</option>
						<option value="M">女</option>
					</select>
				</div>
				<div class="form-group col-md-6">
					<label for="childAge">小孩年龄</label>								
					<input class="form-control" name="childAge" id="childAge">
				</div>
				<div class="form-group col-md-6">
					<label for="emerName">紧急联系人</label>								
					<input class="form-control" name="emerName" id="emerName">
				</div>
				<div class="form-group col-md-6">
					<label for="emerMobile">紧急联系电话</label>								
					<input class="form-control" name="emerMobile" id="emerMobile">
				</div>
				<div class="form-group col-md-6">
					<label for="isDisease">有无疾病</label>								
					<select class="form-control" name="isDisease" id="isDisease">
						<option value="N" selected>无</option>
						<option value="Y">有</option>
					</select>
				</div>
				<div class="form-group col-md-6">
					<label for="diseaseDesc">病情描述</label>								
					<input class="form-control" name="diseaseDesc" id="diseaseDesc">
				</div>
				<div class="form-group col-md-12 text-right">
					<button type="submit" class="btn btn-success">保存</button>
					<button class="btn btn-info" type="button"
						onclick="closeFormPanel('childUpdateForm')">取消</button>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath%>/js/course/signRecord.js"></script>
</body>
</html>