var hmRegQueryUrl = basePath + "/hm/json/reg/query";
var hmAuditUrl = basePath + "/hm/json/audit";

var dataGrid;
var confirmDialog;
$(function() {
	loadData();
	$("input[name='isActive']").on("change", function() {
		if(this.value == 0) {
			$("#reason").removeClass('hide').addClass("show");
		}else {
			$("#reason").removeClass('show').addClass("hide");
		}
	})
});

function loadData() {
	dataGrid = $('#dataGrid').datagrid({
		url : hmRegQueryUrl,
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
		columns : [ [   {
			field : 'id',
			checkbox : true
		},{
			field : 'action',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var str = $.formatString('<button  type="button" class="btn btn-info btn-xs" style="margin:4px 4px;" onclick="auditOk(\'{0}\');">审核通过</button>', row.id);
				str += $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="auditDel(\'{0}\');">审核不通过</button>', row.id);
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
			field : 'idPhoto',
			title : '证件照',
			align : "center",
			resizable : true,
			formatter : function(v, row, index) {
				if(v) {
					return "<img style='width:15px;height:15px;' src='" + imgUrl + v + "'>";
				}else {
					return "<span color='gray'>无</span>";
				}
			}
		}, {
			field : 'createTime',
			title : '注册时间',
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
	$('#' + formId).form('clear');
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
	var isActive = $("#auditDelForm").find("input[type='radio']:checked").val();
	var reason = $("#auditDelForm").find("input[name='reason']").val().trim();
	if(isActive == null) return;
	if(isActive == 0 && reason.length == 0) {
		$.messager.alert('系统提示', '请输入审核不通过原因', 'warn');
		return;
	}
	$.messager.confirm('系统提示', '确定提交审核吗吗？', function(r) {
		if (r) {
			var rows = dataGrid.datagrid('getSelections');
			var ids=new Array();
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].id);
			}
			submitAudit(ids,isActive,reason);
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
			title : "透明人注册审核",
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

function submitAudit(ids, isActive, reason){
	$.ajax({
		url : hmAuditUrl,
		type : 'POST',
		dataType:"json",
		data : {
			ids: ids,
			isActive: isActive,
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
