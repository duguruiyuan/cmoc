var courseListUrl = basePath + "/course/json/list/query";
var editShelvesUrl = basePath + "/course/json/edit/shelves";

var dataGrid;
var confirmDialog;
$(function() {
	loadData();
	initCity();
	initCourseType();
});

function loadData() {
	dataGrid = $('#dataGrid').datagrid({
		url : courseListUrl,
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
				if(row.shelves == 0) {
					str += $.formatString('<button  type="button" class="btn btn-info btn-xs" style="margin:4px 4px;" onclick="auditShelves(\'{0}\',1);">审核上架</button>', row.id);
					str += $.formatString('<button  type="button" class="btn btn-danger btn-xs" style="margin:4px 4px;" onclick="auditShelves(\'{0}\',-1);">删除</button>', row.id);
				}else if(row.shelves == 1) {
					str += $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="auditShelves(\'{0}\', 2);">下架</button>', row.id);
				}else if(row.shelves == 2) {
					str += $.formatString('<button  type="button" class="btn btn-info btn-xs" style="margin:4px 4px;" onclick="auditShelves(\'{0}\', 1);">上架</button>', row.id);
				}
				str += $.formatString('<button  type="button" class="btn btn-success btn-xs" style="margin:4px 4px;" onclick="editCourse(\'{0}\');">编辑</button>', row.id);
				return str;
			}
		}, {
			field : 'id',
			title : '编号',
			align : "center",
			resizable : true
		}, {
			field : 'shelves',
			title : '上下架状态',
			align : "center",
			resizable : true,
			formatter : function(value) {
				return shelvesFormat(value);
			}
		}, {
			field : 'courseName',
			title : '课程名称',
			align : "center",
			width : 200,
			resizable : true,
			formatter : function(value) {
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'courseNum',
			title : '课程期数',
			align : "center",
			resizable : true
		}, {
			field : 'courseType',
			title : '课程类型',
			align : "center",
			resizable : true,
			formatter : function(value) {
				return dictDataFormat('course_type', value);
			}
		}, {
			field : 'city',
			title : '城市',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return dictDataFormat('city',v);
			}
		},{
			field : 'addr',
			title : '地址',
			align : "center",
			resizable : true
		},{
			field : 'startDate',
			title : '课程开始时间',
			align : "center",
			resizable : true,
			formatter : function(value) {
				return getTime(value, "yyyy-MM-dd hh:mm");
			}
		}, {
			field : 'endDate',
			title : '课程结束时间',
			align : "center",
			resizable : true,
			formatter : function(value) {
				return getTime(value, "yyyy-MM-dd hh:mm");
			}
		}, {
			field : 'totalPrice',
			title : '总价',
			align : "center",
			resizable : true
		}, {
			field : 'activityPrice',
			title : '优惠价',
			align : "center",
			resizable : true
		}, {
			field : 'resAmount',
			title : '预约金',
			align : "center",
			resizable : true
		}, {
			field : 'coursePeoples',
			title : '课程开团人数',
			align : "center",
			resizable : true
		}, {
			field : 'courseDesc',
			title : '课程简述',
			align : "center",
			width : 200,
			resizable : true,
			formatter : function(value) {
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'createTime',
			title : '创建时间',
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

var auditDialog = function(data){
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0 && data == null) {
		$.messager.alert('系统提示', '请选择数据再进行操作', 'warning');
		return;
	} else {
		confirmDialog = $('#auditDelDiolog').dialog({
			title : "课程审核",
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

function auditShelves(id, type) {
	var tip = "";
	if(type == -1) tip = "删除";
	if(type == 1) tip = "上架";
	if(type == 2) tip = "下架";
	$.messager.confirm('系统提示', '确定'+tip+'吗？', function(r) {
		if (r) {
			$.ajax({
				url : editShelvesUrl,
				type : 'POST',
				dataType:"json",
				data : {
					id: id,
					shelves: type
				},
				error : function() {
					$.messager.alert('系统提示', '提交失败', 'error');
				},
				success : function(data) {
					if (data.code == '000') {
						$.messager.alert('系统提示','提交成功', 'info');
						loadData();
					} else {
						$.messager.alert('系统提示',data.msg, 'warning');
					}
				}
			});
		}
	});
}

function editCourse(id) {
	window.location.href=basePath + "/course/json/edit" + (id != null ? "?id=" + id : "");
}