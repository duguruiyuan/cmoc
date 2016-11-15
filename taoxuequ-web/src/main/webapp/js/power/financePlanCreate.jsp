<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<body>
	<jsp:include page="${path}/layout/head.jsp"></jsp:include>
	<jsp:include page="${path}/layout/header.jsp"></jsp:include>
	<div class="container-fluid mt20">
		<div class="row-fluid">
			<div class="col-md-2">
				<jsp:include page="${path}/layout/menu.jsp"></jsp:include>   
			</div>
			<div class="col-md-10">
				<div>
					<jsp:include page="${path}/layout/localinfo.jsp"></jsp:include>
					<div class="span12">
						<form id="fixedForm" class="form-search" method="post">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td class="text-right">债权集编号：</td>
										<td><input class="input-medium search-query search-comp" type="text" id="debtSetNo" name="debtSetNo" placeholder="债权集编号" /></td>
										<td class="text-right">债权集名称：</td>
										<td><input class="input-medium search-query search-comp" type="text" id="debtSetName" name="debtSetName" placeholder="债权集名称" /></td>
										<td class="text-right">总额（元）：</td>
										<td>
											<input class="input-medium search-query search-comp number" style="width: 80px" type="text" id="debtSetAmtStart" name="debtSetAmtStart" /> 至 
											<input class="input-medium search-query search-comp number" style="width: 80px" type="text" id="debtSetAmtEnd" name="debtSetAmtEnd" />
										</td>
									</tr>
									<tr>
										<td class="text-right">创建日期：</td>
										<td colspan="5">
											<input type="text" class="data-icon input-medium search-query search-comp" id="startDate" name="startDate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d',readOnly:true})">  至 
										    <input type="text" class="data-icon input-medium search-query search-comp" id="endDate" name="endDate" onclick="WdatePicker({maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'startDate\')}',readOnly:true})">
										</td>
									</tr>
									<tr>
										<td colspan="6">
											<button type="button" class="btn btn-success btn-xs" onclick="search('fixedForm')" style=" height: 30px; width: 50px">查找</button>
											<button type="button" class="btn btn-warning btn-xs" onclick="cleanFormPanel('fixedForm')" style=" height: 30px; width: 50px">清空</button>
										</td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<div style="height: 400px;margin-top:3px;" id="busiTypeWrapper">
						<table id="setDataGrid"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 分配面板 -->
	<div id="confirmDialog" style="display: none;height: 'auto';">
			<div style="margin: 5px 5px;height: 'auto';" id="createDatagrid">
					<div class="col-md-12">
							<div style="width: 100%; padding: 15px;">
								<form id="addForm" class="row" novalidate="novalidate">
									<div class="form-group col-md-4">
										<label for="debtSetName">债权集名称</label>								
										<input class="form-control" name="debtSetName" id="debtSetName" disabled>
										<input type="hidden" id="debtSetNo" name="debtSetNo"/>
									</div>
									<div class="form-group col-md-4">
										<label for="debtSetName">债权集总额(元)</label>								
										<input class="form-control" id="debtSetAmt" name="debtSetAmt" disabled/>
									</div>
									<div class="form-group col-md-4">
										<label for="productName">理财计划名称</label>								
										<input class="form-control" name="productName" id="productName" placeholder="请输入...">
									</div>
									<div class="form-group col-md-4">
										<label for="totalType">总额类型</label>
										<select class="form-control" name="totalType" id="totalType">
											<option value="1">定额</option>
											<!-- <option value="2">不总额</option> -->
										</select>
									</div>
									<div class="form-group col-md-4">
										<label for="rateType">利率类型</label>
										<select class="form-control" name="rateType" id="rateType">
											<!-- <option value="0">日</option>
											<option value="1">月</option> -->
											<option value="2">年</option>
										</select>
									</div>
									<div class="form-group col-md-4">
										<label for="rate">利率%</label>								
										<input class="form-control" name="rate" id="rate" placeholder="请输入...">
									</div>
									<div class="form-group col-md-4">
										<label for="termType">期数类型</label>
										<select class="form-control" name="termType" id="termType">
											<option value="1">月</option>
											<!-- <option value="2">日</option> -->
										</select>
									</div>
									<div class="form-group col-md-4">
										<label for="fixedTerm">理财期限</label>
										<select class="form-control" name="fixedTerm" id="fixedTerm">
											<option value="">请选择--</option>
											<option value="1">1期</option>
											<option value="2">2期</option>
											<option value="3">3期</option>
											<option value="4">4期</option>
											<option value="5">5期</option>
											<option value="6">6期</option>
											<option value="8">8期</option>
											<option value="9">9期</option>
											<option value="10">10期</option>
											<option value="12">12期</option>
											<option value="24">24期</option>
											<option value="36">36期</option>
										</select>
									</div>
									<div class="form-group col-md-4">
										<label for="effectRule">生效规则</label>
										<select class="form-control" name="effectRule" id="effectRule">
											<option value="1">统一生效</option>
											<!-- <option value="2">即时生效</option> -->
										</select>
									</div>
									<div class="form-group col-md-4">
										<label for="settleType">结算类型</label>
										<select class="form-control" name="settleType" id="settleType">
											<option value="1">手动结算</option>
										</select>
									</div>
									
									<div class="form-group col-md-4">
										<label for="financeAmt">到期处理方式</label>
										<select class="form-control" name="dueTransferType" id="dueTransferType">
											<option value="1">手动</option>
										</select>
									</div>
									<div class="form-group col-md-4">
										<label for="bizFlow">起息方式</label>
										<select class="form-control" name="interestType" id="interestType ">
											<option value="T+1">T+1</option>
										</select>
									</div>
									<div class="form-group col-md-4">
										<label for="isReinvest">是否复投</label>
										<select class="form-control" name="isReinvest" id="isReinvest">
											<option value="">请选择</option>
											<option value="1">复投</option>
											<option value="0">不复投</option>
										</select>
									</div>
									<div class="form-group col-md-4">
										<label for="reinvestType">复投类型</label>
										<select class="form-control" name="reinvestType" id="reinvestType">
											<option value="">请选择</option>
											<option value="1">本息复投</option>
											<option value="2">本金复投</option>
											<option value="0">不复投</option>
										</select>
									</div>
									<div class="form-group col-md-4">
										<label for="buyMinAmt">起投最低限额</label>
										<input class="form-control" name="buyMinAmt" id="buyMinAmt" placeholder="请输入..."/>
									</div>
									<div class="form-group col-md-4">
										<label for="issueAmt">发布金额(元)</label>
										<input type="text" class="form-control" name="issueAmt" id="issueAmt" placeholder="请输入...">
									</div>
									<div class="form-group col-md-4">
										<label for="issueTerm">发布期限(天)</label>
										<input type="text" class="form-control" name="issueTerm" id="issueTerm" placeholder="请输入...">
									</div>
									<div class="form-group col-md-4">
										<label for="matchRuleNo">投资匹配规则</label>
										<select class="form-control" name="matchRuleNo" id="matchRuleNo">
										</select>
									</div>
									<div class="form-group col-md-4">
										<label for="matchRuleNo">回款类型</label>
										<select class="form-control" name="returnType" id="returnType">
											<!-- <option value="" selected="selected">请选择</option>
											<option value="01">等额本息</option>
											<option value="02">等额本金</option>
											<option value="03">先息后本</option> -->
											<option value="04">一次性还本付息</option>	
										</select>
									</div>
									<div class="form-group col-md-8">
										<label for="bizSys">推送系统</label>
										<div id="pushSysList"></div>
										<input type="hidden" id="pushSys" name="pushSys" />
									</div>
									<div class="form-group col-md-12 text-right">
										<button type="button" class="btn btn-warning" onclick="cancelHandle()">取消</button>
										<button type="submit" class="btn btn-success">提交</button>
									</div>
								</form>
							</div>
					</div>
			</div>
	</div>
	<jsp:include page="${path}/layout/footer.jsp"></jsp:include>
	<jsp:include page="${path}/layout/product/originAssetItems.jsp"></jsp:include>
	<script type="text/javascript" src="<%=path%>/js/product/financePlanCreate.js"></script>
</body>
