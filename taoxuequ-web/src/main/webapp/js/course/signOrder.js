var buyRecordListUrl = basePath + "/course/json/signOrder/list";
var orderConfirmPayUrl = basePath + "/course/json/order/confirmPay";

var dataGrid;
$(function() {
	loadData();
	initCourse();
});

function loadData() {
	dataGrid = $('#dataGrid').datagrid({
		url : buyRecordListUrl,
		fit : true,
		fitColumns : true,
		border : true,
		pagination : true,
		idField : 'id',
		pageSize : config.pageSize,
		autoRowHeight : 140,
		pageList : config.pageList,
		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : false,
		nowrap : true,
		striped : true,
		rownumbers : true,
		columns : [ [  {
			field : 'action',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var str = "";
				if(row.orderStatus != '000') {
					str += $.formatString('<button  type="button" class="btn btn-success btn-xs" style="margin:4px 4px;" onclick="orderConfirmPay(\'{0}\');">确认支付</button>', row.orderId);
				}
				return str;
			}
		}, {
			field : 'orderStatus',
			title : '订单状态',
			align : "center",
			resizable : true,
			formatter: function(v) {
				if(v == '000') {
					return "已支付";
				}
				return "<span style='color: red;'>未支付</span>";
			}
		}, {
			field : 'orderNo',
			title : '订单编号',
			align : "center",
			resizable : true
		}, {
			field : 'parentName',
			title : '报名联系人',
			align : "center",
			resizable : true
		}, {
			field : 'parentMobile',
			title : '联系电话',
			align : "center",
			resizable : true
		}, {
			field : 'courseName',
			title : '课程名称',
			align : "center",
			resizable : true
		}, {
			field : 'courseName',
			title : '课程报名方式',
			align : "center",
			resizable : true,
			formatter: function(v) {
				if(v == 1) return "组队";
				return "单人";
			}
		},
		{
			field : 'activityId',
			title : '排期活动信息',
			align : "center",
			resizable : true,
			formatter: function(value, row, index) {
				if(row.activityId != null) {
					return row.activityName + "|" + row.activityNum + "|" + getTime(row.activityStartDate, "yyyy-MM-dd");;
				}
				return "无";
			}
		}, {
			field : 'orderCreateTime',
			title : '报名时间',
			align : "center",
			resizable : true,
			formatter : function(value) {
				return getTime(value, "yyyy-MM-dd hh:mm:ss");
			}
		}] ]
	});
}

function search(formId){
	dataGrid.datagrid('load', $.serializeObject($('#' + formId)));
}

function closeFormPanel(formId){
	cleanFormPanel(formId);
	confirmDialog.dialog("close");
}

function childSignReport(formId){
	$('#'+formId).attr('action', basePath + '/course/json/list/import').submit();
}

function orderConfirmPay(orderId) {
	$.messager.confirm('系统提示', '请再三确认，该订单是否已支付成功？', function(r) {
		if (r) {
			$.ajax({
				url : orderConfirmPayUrl,
				type : 'POST',
				dataType:"json",
				data : {
					orderId: orderId
				},
				error : function() {
					$.messager.alert('系统提示', '确认失败', 'error');
				},
				success : function(data) {
					if (data.code == '000') {
						$.messager.alert('系统提示','确认成功', 'info');
						loadData();
					} else {
						$.messager.alert('系统提示',data.msg, 'warning');
					}
				}
			});
		}
	});
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$('#' + formId)[0].reset();
}

