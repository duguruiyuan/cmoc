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
									<td colspan="2">活动时间： <input type="text" class="form-control1 Wdate" id="startDate" name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',readOnly:true})">  至 
										    <input type="text" class="form-control1 Wdate " id="endDate" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',readOnly:true})"></td>
								 </tr>
								 <tr>
									<td colspan="2">
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
				</div>
				<div style="height: 330px;">
					<table id="dataGrid"></table>
				</div>
			</div>
			<div id="uploadDialog" style="display: none;height: 'auto';">
			 	 <div class="col-md-12 p15">
				  	<p class="text-left">
				  		活动名称： <input type="text" id="activityName" class="form-control1" readonly="readonly">
				  	</p>
			  	 </div>
			  	 <div class="col-md-12 p15">
				  	<p class="text-left">
				  		活动期数： <input type="text" id="activityName" class="form-control1" readonly="readonly">
				  	</p>
			  	 </div>
			  	 <div class="col-md-12 p15">
			  	 	<form enctype="multipart/form-data">
		             	<div class="form-group">
		                    <input id="file-upload" name="files" type="file" accept=".xls,.xlsx" multiple class="file" data-overwrite-initial="false" data-max-file-count="1">
		                	<input type="hidden" name="activity_id" id="activity_id"/>
		                </div>
	             	</form>
	             </div>
			</div>
			<div id="uploadActivityImgDialog" style="display: none;height: 'auto';">
				<div class="col-md-12 p10">
					<form id="addForm" novalidate="novalidate" enctype="multipart/form-data">
						<div class="form-group col-md-6">
							<label for="activityName">活动名称</label>								
							<input class="form-control" name="activityName" id="activityName">
						</div>
						<div class="form-group col-md-6">
							<label for="activityNum">活动期数</label>								
							<input class="form-control" name="activityNum" id="activityNum">
						</div>
						<div class="col-md-12">
							<label>活动战队、队员图片上传</label>
							<p style="color: red;">注：上传的战队图片名称命名规则是：战队名称.jpg|png|gif(如: 小虎队.jpg,小虎队.png), 队员图片一样
								如果活动中有队员姓名一样，队员名称要带上战队名称（如：小虎队-小明.jpg, 小牛队-小明.jpg)
							</p>
							<input id="activity-img-upload" name="files" type="file" accept=".png,.jpg,.gif" multiple class="file" data-overwrite-initial="false" data-min-file-count="2">
							<input type="hidden" name="activityId" id="activityId"/>
		            		<input type="hidden" name="type" id="type" value="ACTIVITY"/>
						</div>
					</form>
				</div>
			</div>
			<div id="confirmDialog" style="display: none;height: 'auto';">
				<div class="col-md-12 p10">
				  <form id="addForm" novalidate="novalidate">
						<input type="hidden" id="id" name="id">
						<div class="form-group col-md-6">
							<label for="activityName">活动名称</label>								
							<input class="form-control" name="activityName" id="activityName">
						</div>
						<div class="form-group col-md-6">
							<label for="activityNum">活动期数</label>								
							<input class="form-control" name="activityNum" id="activityNum">
						</div>
						<div class="form-group col-md-6">
							<label for="activityType">活动类型</label>								
							<select class="form-control" name="activityType" id="activityType">
								<option value="">请选择</option>
								<option value="1">亲子活动</option>	
								<option value="2">城市体验</option>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="activityPeoples">活动发布人数</label>								
							<input class="form-control" name="activityPeoples" id="activityPeoples">
						</div>
						<div class="form-group col-md-6">
							<label for="city">活动城市</label>								
							<input class="form-control" name="city" id="city">
						</div>
						<div class="form-group col-md-6">
							<label for="strStartDate">活动开始时间</label>								
							<input class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'strEndDate\')}',readOnly:true})" name="strStartDate" id="strStartDate">
						</div>
						<div class="form-group col-md-6">
							<label for="strEndDate">活动结束时间</label>								
							<input class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'strStartDate\')}',readOnly:true})" name="strEndDate" id="strEndDate">
						</div>
						<div class="form-group col-md-6">
							<label for="activityAddr">活动地址</label>								
							<input class="form-control" name="activityAddr" id="activityAddr">
						</div>
						<div class="form-group col-md-6">
							<label for="activityDesc">活动描述</label>	
							<textarea class="form-control" name="activityDesc" id="activityDesc" rows="3"></textarea>							
						</div>
						<div class="form-group col-md-12 text-right">
							<button type="submit" class="btn btn-success">保存</button>
							<button class="btn btn-info" type="button"
								onclick="closeFormPanel('addForm')">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>/js/activity/activity.index.js"></script>
</body>
</html>