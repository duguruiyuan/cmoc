var buyRecordListUrl = basePath + "/course/json/buyRecord/list";

var dataGrid;
$(function() {
	loadData();
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
			field : 'productId',
			title : '课程编号',
			align : "center",
			resizable : true
		}, {
			field : 'courseName',
			title : '课程名称',
			align : "center",
			resizable : true
		}, {
			field : 'courseType',
			title : '课程类型',
			align : "center",
			resizable : true,
			formatter : function(value) {
				return dictDataFormat("course_type", value);
			}
		}, {
			field : 'orderNo',
			title : '订单编号',
			align : "center",
			resizable : true
		}, {
			field : 'name',
			title : '购买人姓名',
			align : "center",
			resizable : true
		}, {
			field : 'mobile',
			title : '购买人号码',
			align : "center",
			resizable : true
		},{
			field : 'resAmount',
			title : '预约金',
			align : "center",
			resizable : true
		},{
			field : 'createTime',
			title : '购买时间',
			align : "center",
			resizable : true,
			formatter : function(value) {
				return getTime(value, "yyyy-MM-dd hh:mm");
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

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$('#' + formId).form('clear');
}

