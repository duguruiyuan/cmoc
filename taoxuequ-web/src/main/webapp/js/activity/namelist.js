var activityQueryUrl = basePath + "/activity/json/namelist/query";
var addUpdateActivityUrl = basePath + "/activity/json/addUpdate";
var queryByIdUrl = basePath + "/activity/json/queryById";

var dataGrid;
$(function() {
	loadData();
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
		columns : [ [ {
			field : 'action',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var str = $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="updateActivity(\'{0}\');">编辑</button>', row.id);
				return str;
			}
		}, {
			field : 'id',
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
			field : 'fatherName',
			title : '爸爸姓名',
			align : "center",
			resizable : true
		}, {
			field : 'fatherMobile',
			title : '爸爸手机号码',
			align : "center",
			resizable : true
		}, {
			field : 'motherName',
			title : '妈妈姓名',
			align : "center",
			resizable : true
		},{
			field : 'motherMobile',
			title : '妈妈手机号码',
			align : "center",
			resizable : true
		}, {
			field : 'marineName',
			title : '战队名称',
			align : "center",
			resizable : true
		}, {
			field : 'childName',
			title : '小孩姓名',
			align : "center",
			resizable : true
		}, {
			field : 'childTitle',
			title : '小孩头衔',
			align : "center",
			resizable : true
		}, {
			field : 'childImg',
			title : '小孩相片',
			align : "center",
			resizable : true,
			formatter : function(value) {
				return "<a style='color: red;'>查看</a>";
			}
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

function updateActivity(id) {
	$.ajax({
 		url : queryByIdUrl,
 		type : "post",
 		data : {
 			id : id
 		},
 		dataType : "json",
 		async : false,
 		success : function(data) {
 			$("#addForm #id").val(data.id);
			$("#addForm #activityName").val(data.activityName);
			$("#addForm #activityNum").val(data.activityNum);
			$("#addForm #city").val(data.city);
			$("#addForm #activityType").val(data.activityType);
			$("#addForm #strStartDate").val(getTime(data.startDate, "yyyy-MM-dd hh:mm"));
			$("#addForm #strEndDate").val(getTime(data.endDate, "yyyy-MM-dd hh:mm"));
			$("#addForm #activityAddr").val(data.activityAddr);
			$("#addForm #activityDesc").val(data.activityDesc);
			loadForm(1);
 		}
 	});
}

function returnBack(){
	window.location.reload();
}

var loadForm = function(data){
	confirmDialog = $('#confirmDialog').dialog({
		title : data != null ? "修改活动" : "新增活动",
		modal : true,
		width : 800,
		top : 100,
		draggable : true,
		resizable : true,
		buttons : '#btns',
		onClose : function() {
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
			},
			activityType : {
				required : true
			},
			activityPeoples : {
				required : true,
				number : true
			},
			city : {
				required : true,
				maxlength : 20
			},
			startDate : {
				required : true,
				date:true
			},
			endDate : {
				required : true,
				date:true
			},
			activityAddr : {
				maxlength : 100
			},
			activityDesc : {
				maxlength : 200
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
							$.messager.alert('系统提示', $("#id").val() == '' ? '活动新增成功' : '活动修改成功', 'info');
							returnBack();
						} else {
							$.messager.alert('系统提示', $("#id").val() == '' ? '活动新增失败' : '活动修改失败', 'warning');
						}
					}
				});
		}
	});
}
