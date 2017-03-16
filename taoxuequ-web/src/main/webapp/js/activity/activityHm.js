var activityQueryUrl = basePath + "/activity/json/hm/query";
var hmSignJudgeUrl = basePath + "/activity/json/hmSign/judge";
var delActivityHmUrl = basePath + "/activity/json/delActivityHm";

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
				var str = $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="judgeDialog(\'{0}\');">活动评价</button>', index);
				if(row.startDate > (new Date())) {
					str += $.formatString('<button  type="button" class="btn btn-danger btn-xs" style="margin:4px 4px;" onclick="delActivityHm(\'{0}\');">删除</button>', row.id);
				}
				return str;
			}
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
		}, {
			field : 'activityNum',
			title : '活动期数',
			align : "center",
			resizable : true
		}, {
			field : 'startDate',
			title : '活动开始时间',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return getTime(v, "yyyy-MM-dd hh:mm");
			}
		}, {
			field : 'marineId',
			title : '战队编号',
			align : "center",
			resizable : true
		}, {
			field : 'marineName',
			title : '战队名称',
			align : "center",
			resizable : true
		},
		{
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
			field : 'effectDate',
			title : '激活日期',
			align : "center",
			resizable : true
		}, {
			field : 'showed',
			title : '活动表现',
			align : "center",
			resizable : true,
			formatter : function(v) {
				return showedFormat(v);
			}
		}, {
			field : 'judge',
			title : '活动评价',
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
	confirmDialog.dialog("close");
}

function delActivityHm(id) {
	$.messager.confirm('系统提示', '确定删除这个绑定活动透明人吗？', function(r) {
		if (r) {
			$.ajax({
		 		url : delActivityHmUrl,
		 		type : "post",
		 		data : {
		 			id : id
		 		},
		 		dataType : "json",
		 		async : false,
		 		success : function(data) {
		 			if(data.code == '000') {
		 				$.messager.alert('系统提示', '删除成功', 'info');
		 				search("searchForm");
		 			}else {
		 				$.messager.alert('系统提示', '删除失败', 'error');
		 			}
		 		},
		 		error : function() {
					$.messager.progress('close');
					$.messager.alert('系统提示', '操作异常', 'error');
				}
			});
		}
	});
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$("#" + formId)[0].reset();
	$("#" + formId + " #id").val(null);
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

var judgeDialog = function(index){
	var rows = dataGrid.datagrid('getRows');
	var row = rows[index];
	confirmDialog = $('#judgeDiolog').dialog({
		title : "透明人活动评价",
		modal : true,
		width : 600,
		top : 100,
		draggable : true,
		resizable : true,
		buttons : '#btns',
		onClose : function() {
			cleanFormPanel("judgeForm");
		},
		onOpen : function() {
			$("#judgeForm #showed").val(row.showed);
			$("#judgeForm #judge").val(row.judge);
			$("#judgeForm #id").val(row.id);
		}
	}).show();
}

var judgeSubmit = function() {
	$.messager.progress({
		title : '系统提示',
		msg : '处理中，请稍候...'
	});
	$.ajax({
		url : hmSignJudgeUrl,
		type : 'POST',
		error : function() {
			$.messager.progress('close');
			$.messager.alert('系统提示', '操作异常', 'error');
		},
		data : $('#judgeForm').serialize(),
		success : function(data) {
			$.messager.progress('close');
			if (data.code == '000') {
				$.messager.alert('系统提示', '活动评价成功', 'info');
				closeFormPanel("judgeForm");
				loadData();
			} else {
				$.messager.alert('系统提示', '活动评价失败', 'warning');
			}
		}
	});
}

