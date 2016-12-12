var activityQueryUrl = basePath + "/activity/json/hm/query";

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
		columns : [ [  {
			field : 'action',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var str = $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="updateActivity(\'{0}\');">带队记录</button>', row.id);
				return str;
			}
		}, {
			field : 'isActive',
			title : '状态',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return activeFormat(v);
			}
		}, {
			field : 'takeNum',
			title : '带队次数',
			align : "center",
			resizable : true
		},{
			field : 'hmName',
			title : '姓名',
			align : "center",
			resizable : true
		},{
			field : 'hmMobile',
			title : '手机号码',
			align : "center",
			resizable : true
		}, {
			field : 'idCard',
			title : '证件号码',
			align : "center",
			resizable : true
		}, {
			field : 'sex',
			title : '性别',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return sexFormat(v);
			}
		}, {
			field : 'place',
			title : '籍贯',
			align : "center",
			resizable : true
		}, {
			field : 'schoole',
			title : '学校',
			align : "center",
			resizable : true
		}, {
			field : 'grade',
			title : '年级',
			align : "center",
			resizable : true
		}, {
			field : 'activeDate',
			title : '激活日期',
			align : "center",
			resizable : true
		}, {
			field : 'createTime',
			title : '申请时间',
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
	$('#confirmDialog').dialog("close");
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$("#" + formId)[0].reset();
	$(".valid").removeClass("valid");
	$(".error").removeClass("error");
	$("label").find("span").remove();
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



