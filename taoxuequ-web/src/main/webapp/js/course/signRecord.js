var buyRecordListUrl = basePath + "/course/json/sign/list";
var courseSignUpdateUrl = basePath + "/course/json/sign/update";
var namelistDelUrl = basePath + "/course/namelist/del";
var nameListUpdateUrl = basePath + "/course/namelist/update";
var namelistQueryByIdUrl = basePath + "/course/namelist/query/";

var dataGrid;
var confirmDialog;
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
				if(row.status == '001') {
					str += $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="nameListUpdate({0});">编辑</button>', row.id);
					str += $.formatString('<button  type="button" class="btn btn-danger btn-xs" style="margin:4px 4px;" onclick="namelistDel({0});">删除</button>', row.id);
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

function namelistDel(id) {
	$.messager.confirm('系统提示', '确定删除这条名单吗？', function(r) {
		if (r) {
			$.ajax({
				url : namelistDelUrl,
				type : 'POST',
				dataType:"json",
				data : {
					id: id
				},
				error : function() {
					$.messager.alert('系统提示', '删除失败', 'error');
				},
				success : function(data) {
					if (data.code == '000') {
						$.messager.alert('系统提示','删除成功', 'info');
						loadData();
					} else {
						$.messager.alert('系统提示',data.msg, 'warning');
					}
				}
			});
		}
	});
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

$("#childUpdateForm #isDisease").change(function() {
	if(this.value == 'N') {
		$("#childUpdateForm #diseaseDesc").val(null);
	} 
})
var nameListUpdate = function(id){
	$.ajax({
		url : namelistQueryByIdUrl + id,
		type : 'GET',
		dataType:"json",
		error : function() {
			$.messager.alert('系统提示', '系统异常', 'error');
		},
		success : function(data) {
			if (data.code == '000') {
				confirmDialog = $('#childDialog').dialog({
					title : "修改名单信息",
					modal : true,
					width : 800,
					top : 100,
					draggable : true,
					resizable : true,
					buttons : '#btns',
					onOpen : function() {
						$("#childUpdateForm #id").val(data.data.id);
						$("#childUpdateForm #childName").val(data.data.childName);
						$("#childUpdateForm #childIdcard").val(data.data.childIdcard);
						$("#childUpdateForm #childSex").val(data.data.childSex);
						$("#childUpdateForm #childAge").val(data.data.childAge);
						$("#childUpdateForm #emerName").val(data.data.emerName);
						$("#childUpdateForm #emerMobile").val(data.data.emerMobile);
						$("#childUpdateForm #isDisease").val(data.data.isDisease);
						$("#childUpdateForm #diseaseDesc").val(data.data.diseaseDesc);
					},
					onClose : function() {
						$("#childUpdateForm #id").val(null);
						$("#childUpdateForm #diseaseDesc").val(null);
						cleanFormPanel("childUpdateForm");
					}
				}).show();
				validator();
			} else {
				$.messager.alert('系统提示',data.msg, 'warning');
			}
		}
	});
}

//绑定表单验证控件
function validator() {
	$("#childUpdateForm").validate({
		errorPlacement : function(error, element) {
			$(element).closest("form").find("label[for='" + element.attr("id") + "']").append(error);
		},
		errorElement : "span",
		rules : {
			childName : {
				required : true
			},
			childIdcard : {
				required : true,
				isIdcard : true,
			},
			childSex : {
				required : true
			},
			childAge : {
				number : true
			},
			emerMobile : {
				required : true,
				isPhone : true
			},
			diseaseDesc : {
				maxlength : 200
			}
		},
		submitHandler : function(form) {
				$.messager.progress({
					title : '系统提示',
					msg : '处理中，请稍候...'
				});

				$.ajax({
					url : nameListUpdateUrl,
					type : 'POST',
					error : function() {
						$.messager.progress('close');
						$.messager.alert('系统提示', '操作异常', 'error');
					},
					data : $('#childUpdateForm').serialize(),
					success : function(data) {
						$.messager.progress('close');
						if (data.code == '000') {
							$.messager.alert('系统提示', '修改名单信息成功', 'info');
							closeFormPanel("childUpdateForm");
							loadData();
						} else {
							$.messager.alert('系统提示', '修改名单信息失败', 'warning');
						}
					}
				});
		}
	});
}


