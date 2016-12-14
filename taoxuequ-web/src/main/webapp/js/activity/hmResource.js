var activityQueryUrl = basePath + "/activity/hm/resource/total";

var dataGrid;
$(function() {
	loadData();
	initActivityType();
});

function loadData() {
	dataGrid = $('#dataGrid').datagrid({
		url : activityQueryUrl,
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
		columns : [ [ {
			field : 'hmName',
			title : '透明人姓名',
			align : "center",
			resizable : true
		}, {
			field : 'hmMobile',
			title : '透明人号码',
			align : "center",
			resizable : true
		}, {
			field : 'activityId',
			title : '活动编号',
			align : "center",
			resizable : true
		}, {
			field : 'activityName',
			title : '活动名称',
			align : "center",
			resizable : true
		},{
			field : 'activityType',
			title : '活动类型',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return dictDataFormat('activity_type', v);
			}
		}, {
			field : 'startDate',
			title : '活动开始时间',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return getTime(v, "yyyy-MM-dd");
			}
		}, {
			field : 'imageNum',
			title : '图片数量',
			align : "center",
			resizable : true
		}, {
			field : 'videoNum',
			title : '视频数量',
			align : "center",
			resizable : true
		}] ]
	});
}

function search(formId){
	dataGrid.datagrid('load', $.serializeObject($('#' + formId)));
}

function closeFormPanel(formId){
	cleanFormPanel(formId);
	$('#confirmDialog').dialog("close");
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$("#" + formId)[0].reset();
}

//取消选中
var cancelSelect = function(){
	if (dataGrid != undefined){
		dataGrid.datagrid("clearSelections");
	}
}


function returnBack(){
	window.location.reload();
}
