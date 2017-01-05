var hmSignAuditQueryUrl = basePath + "/activity/json/hmSignAuditout/query";

var dataGrid;
var confirmDialog;
$(function() {
	loadData();
	initActivityType();
	$("input[name='isEffect']").on("change", function() {
		if(this.value == 0) {
			$("#reason").removeClass('hide').addClass("show");
		}else {
			$("#reason").removeClass('show').addClass("hide");
		}
	})
});

function loadData() {
	dataGrid = $('#dataGrid').datagrid({
		url : hmSignAuditQueryUrl,
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
		queryParams : {
			"isEffect" : 0
		},
        onSelect : function (rowIndex, rowData){
			var oldValue = parseInt($('#checkedNum').html());
			$('#checkedNum').html(oldValue + 1);
		},
		onUnselect : function (rowIndex, rowData){
			var oldValue = parseInt($('#checkedNum').html());
			$('#checkedNum').html(oldValue - 1);
		},
		onSelectAll : function (rows){
			var oldValue = parseInt($('#checkedNum').html());
			oldValue += rows.length;
			$('#checkedNum').html(oldValue);
		},
		onUnselectAll : function (rows){
			var oldValue = parseInt($('#checkedNum').html());
			oldValue -= rows.length;
			$('#checkedNum').html(oldValue);
		},
		columns : [ [ {
			field : 'isEffect',
			title : '状态',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return "审核不通过";
			}
		},{
			field : 'reason',
			title : '原因',
			resizable : true,
			formatter : function(v) {
				if(v == null) return "";
				return "<span title=\"" + v +"\">" + v + "</span>";
			}
		}, {
			field : 'activityName',
			title : '活动名称',
			align : "center",
			resizable : true
		}, {
			field : 'activityNum',
			title : '活动期数',
			align : "center",
			resizable : true
		}, {
			field : 'activityType',
			title : '活动类型',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return dictDataFormat('activity_type',v);
			}
		}, {
			field : 'startDate',
			title : '活动开始时间',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return getTime(v, "yyyy-MM-dd hh:mm");
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
			field : 'effectDate',
			title : '生效日期',
			align : "center",
			resizable : true
		}, {
			field : 'signDate',
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

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$("#" + formId)[0].reset();
}