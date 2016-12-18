var buyRecordListUrl = basePath + "/parent/bind/json/query";

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
			field : 'parentName',
			title : '绑定姓名',
			align : "center",
			resizable : true
		}, {
			field : 'parentMobile',
			title : '绑定号码',
			align : "center",
			resizable : true
		}, {
			field : 'relation',
			title : '与小孩关系',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return dictDataFormat("relation", v);
			}
		}, {
			field : 'openid',
			title : '公众号标识',
			align : "center",
			resizable : true
		}, {
			field : 'familyNo',
			title : '家庭编号',
			align : "center",
			resizable : true
		},{
			field : 'createTime',
			title : '绑定时间',
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
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$('#' + formId).form('clear');
}

