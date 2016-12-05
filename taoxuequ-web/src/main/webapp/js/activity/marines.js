var activityQueryUrl = basePath + "/activity/json/marines/query";
var addUpdateActivityUrl = basePath + "/activity/json/marines/addUpdate";
var queryByIdUrl = basePath + "/activity/json/marines/queryById";

var dataGrid;
$(function() {
	loadData();
});
window.onload = uploadInit("file-upload", basePath + '/attachment/activity/upload/img');
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
			field : 'action',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var str = $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="updateMarine(\'{0}\');">编辑</button>', row.id);
				return str;
			}
		}, {
			field : 'activityId',
			title : '活动编号',
			align : "center",
			resizable : true
		},{
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
				return activityTypeFormat(v);
			}
		}, {
			field : 'marineName',
			title : '战队名称',
			align : "center",
			resizable : true
		}, {
			field : 'marineSlogan',
			title : '战队口号',
			align : "center",
			resizable : true
		}, {
			field : 'marinePrize',
			title : '战队奖项',
			align : "center",
			resizable : true
		}, {
			field : 'marineImg',
			title : '战队照片',
			align : "center",
			resizable : true,
			formatter : function(v, row, index) {
				if(v) {
					return "<img style='width:15px;height:15px;' src='" + imgUrl + v + "'>";
				}else {
					return "<span color='gray'>无</span>";
				}
			}
		},{
			field : 'votes',
			title : '支持票数',
			align : "center",
			resizable : true
		}, {
			field : 'readnum',
			title : '阅读量',
			align : "center",
			resizable : true
		}, {
			field : 'score',
			title : '得分',
			align : "center",
			resizable : true
		}, {
			field : 'creater',
			title : '创建人',
			align : "center",
			resizable : true
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

function addRole() {
	loadForm(null);
}

function updateMarine(id) {
	$.ajax({
 		url : queryByIdUrl,
 		type : "post",
 		data : {
 			id : id
 		},
 		dataType : "json",
 		async : false,
 		success : function(data) {
 			$("#addForm #resourceId").val(data.id);
 			$("#addForm #id").val(data.id);
			$("#addForm #activityName").val(data.activityName);
			$("#addForm #activityNum").val(data.activityNum);
			$("#addForm #marineName").val(data.marineName);
			$("#addForm #marineSlogan").val(data.marineSlogan);
			loadForm(1);
 		}
 	});
}

function returnBack(){
	window.location.reload();
}

var loadForm = function(data){
	confirmDialog = $('#confirmDialog').dialog({
		title : data != null ? "修改战队" : "新增战队",
		modal : true,
		width : 800,
		top : 100,
		draggable : true,
		resizable : true,
		buttons : '#btns',
		onClose : function() {
			$(".fileinput-remove").click();
			cleanFormPanel("addForm");
		}
	}).show();
	validator();
}

//绑定表单验证控件
function validator() {
	$("#addForm").validate({
		errorPlacement : function(error, element) {
			$(element).closest("form").find("label[for='" + element.attr("id") + "']").append(error);
		},
		errorElement : "span",
		rules : {
			activityName : {
				required : true,
				maxlength : 40,
			},
			activityNum : {
				required : true,
				maxlength : 40
			},marineName : {
				required : true,
				maxlength : 40
			},
			marineSlogan : {
				required : true,
				maxlength : 100
			}
		},
		submitHandler : function(form) {
				$.messager.progress({
					title : '系统提示',
					msg : '处理中，请稍候...'
				});

				$.ajax({
					url : addUpdateActivityUrl,
					type : 'POST',
					error : function() {
						$.messager.progress('close');
						$.messager.alert('系统提示', '操作异常', 'error');
					},
					data : $('#addForm').serialize(),
					success : function(data) {
						$.messager.progress('close');
						if (data.code == '000') {
							$.messager.alert('系统提示', $("#id").val() == '' ? '战队信息新增成功' : '战队信息修改成功', 'info');
							closeFormPanel("addForm");
							loadData();
						} else {
							$.messager.alert('系统提示', $("#id").val() == '' ? '战队信息新增失败' : '战队信息修改失败', 'warning');
						}
					}
				});
		}
	});
}


