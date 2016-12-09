var hmSignAuditQueryUrl = basePath + "/activity/json/hmSignAudit/query";
var hmSignAuditUrl = basePath + "/activity/json/hmSignAudit";

var dataGrid;
var confirmDialog;
$(function() {
	loadData();
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
		columns : [ [  {
			field : 'id',
			checkbox : true
		},{
			field : 'action',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var str = "";
				if(row.takeNum > 0) {
					str += $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="getTakeRecord(\'{0}\');">带队记录</button>', row.hmId);
				}
				if(row.isEffect == 0) {
					str += $.formatString('<button  type="button" class="btn btn-info btn-xs" style="margin:4px 4px;" onclick="auditOk(\'{0}\');">审核通过</button>', row.id);
					str += $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="auditDel(\'{0}\');">审核不通过</button>', row.id);
				}
				return str;
			}
		}, {
			field : 'isEffect',
			title : '状态',
			align : "center",
			resizable : true,
			formatter: function(v) {
				var formatValue = effectFormat(v);
				if(v == 0) {
					return '<span style="color: red;">' + formatValue + '</span>';
				}
				return formatValue;
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

function closeFormPanel(formId){
	cleanFormPanel(formId);
	confirmDialog.dialog("close");
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	initAuditDialog(0);
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

var auditOk = function(id) {
	$.messager.confirm('系统提示', '确定审核通过吗？', function(r) {
		if (r) {
			var ids=new Array();
			ids.push(id);
			submitAudit(ids, 1, null);
		}
	});
}

var auditDel = function(id) {
	initAuditDialog(1);
	auditDialog(id);
}

var initAuditDialog = function(type) {
	if(type == 0) {
		$("#reason").removeClass('show').addClass("hide");
		$("input[type='radio']:checked").attr("checked",false);
		$("input[type='radio']").attr("disabled", false);
	}else {
		$("#reason").removeClass('hide').addClass("show");
		$("input[type='radio']").attr("disabled", true);
		$("input[type='radio']").eq(1).attr("checked","checked");
	}
}

var batchAudit = function() {
	var isEffect = $("#auditDelForm").find("input[type='radio']:checked").val();
	var reason = $("#auditDelForm").find("input[name='reason']").val().trim();
	if(isEffect == null) return;
	if(isEffect == 0 && reason.length == 0) {
		$.messager.alert('系统提示', '请输入审核不通过原因', 'warn');
		return;
	}
	$.messager.confirm('系统提示', '确定提交审核吗？', function(r) {
		if (r) {
			var rows = dataGrid.datagrid('getSelections');
			var ids=new Array();
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].id);
			}
			submitAudit(ids,isEffect,reason);
		}
	});
}

var auditDialog = function(data){
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0 && data == null) {
		$.messager.alert('系统提示', '请选择数据再进行操作', 'warning');
		return;
	} else {
		confirmDialog = $('#auditDelDiolog').dialog({
			title : "透明人报名审核",
			modal : true,
			width : 600,
			top : 100,
			draggable : true,
			resizable : true,
			buttons : '#btns',
			onClose : function() {
				cleanFormPanel("auditDelForm");
			}
		}).show();
	}
}

function submitAudit(ids, isEffect, reason){
	$.ajax({
		url : hmSignAuditUrl,
		type : 'POST',
		dataType:"json",
		data : {
			ids: ids,
			isEffect: isEffect,
			reason: reason
		},
		error : function() {
			$.messager.alert('系统提示', '审核失败', 'error');
		},
		success : function(data) {
			if (data.code == '000') {
				$.messager.alert('系统提示','审核成功', 'info');
				loadData();
				closeFormPanel("auditDelForm");
			} else {
				$.messager.alert('系统提示',data.msg, 'warning');
			}
		}
	});
}