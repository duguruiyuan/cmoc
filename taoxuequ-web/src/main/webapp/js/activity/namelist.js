var activityQueryUrl = basePath + "/activity/json/namelist/query";
var addUpdateActivityUrl = basePath + "/activity/json/namelist/addUpdate";
var queryByIdUrl = basePath + "/activity/json/namelist/queryById";
var delByIdUrl = basePath + "/activity/json/namelist/delById";

var dataGrid;
$(function() {
	loadData();
	initActivityType();
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
				var str = $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="updateActivity(\'{0}\');">编辑</button>', row.id);
				if(row.startDate > (new Date())) {
					str += $.formatString('<button  type="button" class="btn btn-danger btn-xs" style="margin:4px 4px;" onclick="delNamelist(\'{0}\');">删除</button>', row.id);
				}
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
				return dictDataFormat('activity_type', v);
			}
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
			field : 'signResource',
			title : '名单来源',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return signResourceFormat(v);
			}
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
			field : 'childIdcard',
			title : '小孩身份证',
			align : "center",
			resizable : true
		}, {
			field : 'childAge',
			title : '小孩年龄',
			align : "center",
			resizable : true
		}, {
			field : 'childSex',
			title : '小孩性别',
			align : "center",
			resizable : true,
			formatter : function(v) {
				return sexFormat(v);
			}
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
			formatter : function(v, row, index) {
				if(v) {
					return "<img style='width:15px;height:15px;' src='" + imgUrl + v + "'>";
				}else {
					return "<span color='gray'>无</span>";
				}
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
	$('#' + formId + " #id").val(null);
	$('#confirmDialog').dialog("close");
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$('#' + formId)[0].reset();
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

function delNamelist(id) {
	$.messager.confirm('系统提示', '确定删除这条名单吗？', function(r) {
		if (r) {
			$.ajax({
		 		url : delByIdUrl,
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
 			$("#addForm #resourceId").val(data.id);
 			$("#addForm #id").val(data.id);
 			$("#addForm #childId").val(data.childId);
			$("#addForm #activityName").val(data.activityName);
			$("#addForm #activityNum").val(data.activityNum);
			$("#addForm #marineName").val(data.marineName);
			$("#addForm #childName").val(data.childName);
			$("#addForm #childTitle").val(data.childTitle);
			$("#addForm #emerName").val(data.emerName);
			$("#addForm #emerMobile").val(data.emerMobile);
			$("#addForm #childIdcard").val(data.childIdcard);
			$("#addForm #childSex").val(data.childSex);
			$("#addForm #childAge").val(data.childAge);
			loadForm(1);
 		}
 	});
}

function returnBack(){
	window.location.reload();
}

var loadForm = function(data){
	confirmDialog = $('#confirmDialog').dialog({
		title : data != null ? "修改名单" : "新增名单",
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
			marineName : {
				required : true,
				maxlength : 40
			},
			emerMobile : {
				required : true,
				isPhone : true
			},
			childName : {
				required : true,
				maxlength : 40
			},
			childIdcard : {
				required : true,
				isIdcard : true
			},
			childAge : {
				number : true
			},
			childTitle : {
				required : true,
				maxlength : 40
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
							$.messager.alert('系统提示', $("#id").val() == '' ? '小队员信息新增成功' : '小队员信息修改成功', 'info');
							closeFormPanel("addForm");
							loadData();
						} else {
							$.messager.alert('系统提示', $("#id").val() == '' ? '小队员信息新增失败' : '小队员信息修改失败', 'warning');
						}
					}
				});
		}
	});
}
