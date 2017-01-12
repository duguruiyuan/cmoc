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
								 	<td>小孩姓名: <input class="form-control1" type="text" name="childName" id="childName"/></td>
									<td colspan="2">活动时间： <input type="text" class="form-control1 Wdate" id="startDate" name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',readOnly:true})">  至 
										    <input type="text" class="form-control1 Wdate " id="endDate" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',readOnly:true})"></td>
								 </tr>
								 <tr>
									<td colspan="3">
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="search('searchForm')">查找</button>
										<button type="button" style="height: 28px;" class="btn btn-default" onclick="cleanFormPanel('searchForm')">清空</button>
<!-- 										<button class="btn btn-success" type="button" onclick="addRole()">新增</button>
 -->									</td>
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
	<div id="confirmDialog" style="display: none;height: 'auto';">
		<div class="col-md-12 p10">
		  <form id="addForm" novalidate="novalidate">
				<input type="hidden" id="id" name="id">
				<input type="hidden" id="childId" name="childId">
				<div class="form-group col-md-6">
					<label for="files">小队员照片</label>								
					<input type="hidden" name="resourceId" id="resourceId"/>
		            <input type="hidden" name="type" id="type" value="MEMBER"/>
	             	<div class="form-group">
	                    <input id="file-upload" name="files" type="file" accept=".png,.jpg,.gif" multiple class="file" data-overwrite-initial="false" data-max-file-count="1">
	                </div>
				</div>
				<div class="form-group col-md-6">
					<label for="activityName">活动名称</label>								
					<input class="form-control" name="activityName" id="activityName" readonly="readonly">
				</div>
				<div class="form-group col-md-6">
					<label for="activityNum">活动期数</label>								
					<input class="form-control" name="activityNum" id="activityNum" readonly="readonly">
				</div>
				<div class="form-group col-md-6">
					<label for="marineName">战队名称</label>								
					<input class="form-control" name="marineName" id="marineName" readonly="readonly">
				</div>
				<div class="form-group col-md-6">
					<label for="childName">小队员姓名</label>								
					<input class="form-control" name="childName" id="childName">
				</div>
				<div class="form-group col-md-6">
					<label for="childTitle">小队员头衔</label>								
					<input class="form-control" name="childTitle" id="childTitle">
				</div>
				<div class="form-group col-md-12 text-right">
					<button type="submit" class="btn btn-success">保存</button>
					<button class="btn btn-info" type="button"
						onclick="closeFormPanel('addForm')">取消</button>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath%>/js/activity/namelist.js"></script>
</body>
</html>