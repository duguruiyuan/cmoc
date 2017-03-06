var buyRecordListUrl = basePath + "/course/json/sign/list";
var courseSignUpdateUrl = basePath + "/course/json/sign/update";

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
				if(row.isPhoneConfirm == 0) {
					str += $.formatString('<button  type="button" class="btn btn-info btn-xs" style="margin:4px 4px;" onclick="phoneConfirm({0});">确认</button>', row.id);
				}
				str += $.formatString('<button  type="button" class="btn btn-success btn-xs" style="margin:4px 4px;" onclick="printCon(\'{0}\');">打印</button>', row.id);
				return str;
			}
		}, {
			field : 'isPhoneConfirm',
			title : '是否电话确认',
			align : "center",
			resizable : true,
			formatter: function(v) {
				if(v == 0) {
					return "未确认";
				}else if(v == 1) {
					return "已确认";
				}
				return "";
			}
		}, {
			field : 'status',
			title : '是否安排活动',
			align : "center",
			resizable : true,
			formatter: function(v) {
				if(v == '002') {
					return "已安排";
				}else if(v == '001') {
					return "未安排";
				}
				return "";
			}
		}, {
			field : 'orderNo',
			title : '订单编号',
			align : "center",
			resizable : true
		}, {
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
			field : 'emerName',
			title : '紧急联系人',
			align : "center",
			resizable : true
		}, {
			field : 'emerMobile',
			title : '联系电话',
			align : "center",
			resizable : true
		}, {
			field : 'childName',
			title : '小孩姓名',
			align : "center",
			resizable : true
		}, {
			field : 'childIdcard',
			title : '小孩身份证',
			align : "center",
			resizable : true
		}, {
			field : 'childSex',
			title : '小孩性别',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return sexFormat(v);
			}
		}, {
			field : 'childAge',
			title : '小孩年龄',
			align : "center",
			resizable : true
		},{
			field : 'isDisease',
			title : '有无疾病',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return diseaseFormat(v);
			}
		},{
			field : 'diseaseDesc',
			title : '病情描述',
			align : "center",
			resizable : true
		},{
			field : 'createTime',
			title : '报名时间',
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

function phoneConfirm(id) {
	$.messager.confirm('系统提示', '确定电话确认信息了吗？', function(r) {
		if (r) {
			$.ajax({
				url : courseSignUpdateUrl,
				type : 'POST',
				dataType:"json",
				data : {
					id: id,
					isPhoneConfirm: 1
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

function printCon(id) {
	window.open(basePath + "/course/sign/print/" + id);
}

function childSignReport(formId){
	$('#'+formId).attr('action', basePath + '/course/json/list/import').submit();
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$('#' + formId)[0].reset();
}

