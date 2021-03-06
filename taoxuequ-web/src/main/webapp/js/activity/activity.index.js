var activityQueryUrl = basePath + "/activity/json/query";
var addUpdateActivityUrl = basePath + "/activity/json/addUpdate";
var queryByIdUrl = basePath + "/activity/json/queryById";
var delByIdUrl = basePath + "/activity/json/delById";

var dataGrid;
$(function() {
	loadData();
	initActivityType();
	initCity();
	initCourse();
});
window.onload = initLoad();
function initLoad(){
	uploadInit1();
	uploadInit("activity1-img-upload", basePath + '/attachment/activity/upload/img', "id", "activity_type");
	$("#activity1-img-upload").on("fileuploaded", function(event, data, previewId, index) {
		if(data.response.code == '000') {
			$('#activityImgUrl').val(data.response.data);
		}
	});
}

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
				var str = "";
				if(row.startDate > (new Date())) {
					str += $.formatString('<button  type="button" class="btn btn-danger btn-xs" style="margin:4px 4px;" onclick="delActivity(\'{0}\');">删除</button>', row.id);
				}
				str += $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="updateActivity(\'{0}\');">编辑</button>', row.id);
				str += $.formatString('<button type="button" class="btn btn-info btn-xs" style="margin:4px 4px;" onclick="uploadNamelist({0});">名单上传</button>', index);
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
		},{
			field : 'courseName',
			title : '课程名称',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return "<span title=\"" + v + "\">" + v + "</span>";
			}
		}, {
			field : 'courseType',
			title : '课程类型',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return dictDataFormat('course_type',v);
			}
		}, {
			field : 'activityImgUrl',
			title : '活动图片',
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
			field : 'activityType',
			title : '活动类型',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return dictDataFormat('activity_type',v);
			}
		}, {
			field : 'activityPeoples',
			title : '活动发布人数',
			align : "center",
			resizable : true
		}, {
			field : 'enterPeoples',
			title : '报名参与人数',
			align : "center",
			resizable : true
		}, {
			field : 'startDate',
			title : '活动开始时间',
			align : "center",
			resizable : true,
			formatter : function(value) {
				return getTime(value, "yyyy-MM-dd hh:mm");
			}
		}, {
			field : 'endDate',
			title : '活动结束时间',
			align : "center",
			resizable : true,
			formatter : function(value) {
				return getTime(value, "yyyy-MM-dd hh:mm");
			}
		},{
			field : 'activityAddr',
			title : '活动地址',
			align : "center",
			resizable : true
		}, {
			field : 'city',
			title : '活动城市',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return dictDataFormat('city',v);
			}
		}, {
			field : 'activityDesc',
			title : '活动描述',
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

function imgOver(thiz) {
	$("#" + thiz.id).tooltip({
	    position: 'right',
	    content: '<span style="color:#fff">This is the tooltip message.</span>',
	    onShow: function(){
	        $(this).tooltip('tip').css({
	            backgroundColor: '#666',
	            borderColor: '#666'
	        });
	    }
	});
}

function search(formId){
	dataGrid.datagrid('load', $.serializeObject($('#' + formId)));
}

function closeFormPanel(formId){
	$('#' + formId)[0].reset();
	$('#' + formId + " #id").val(null);
	$('#confirmDialog').dialog("close");
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$('#' + formId)[0].reset();
	$('#' + formId + " #id").val(null);
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

function delActivity(id) {
	$.messager.confirm('系统提示', '确定删除此活动吗？', function(r) {
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
		 				search("searchForm")
		 			}else {
		 				$.messager.alert('系统提示', data.msg, 'error');
		 			}
		 		},
		 		error : function() {
					$.messager.progress('close');
					$.messager.alert('系统提示', '操作异常', 'error');
				},
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
 			$("#addForm #id").val(data.id);
 			$("#addForm #productId").val(data.productId);
			$("#addForm #activityName").val(data.activityName);
			$("#addForm #activityNum").val(data.activityNum);
			$("#addForm #activityImgUrl").val(data.activityImgUrl);
			$("#addForm #activityPeoples").val(data.activityPeoples);
			$("#addForm #city").val(data.city);
//			$("#addForm .file-drop-zone").html("<img src=\"" + imgUrl+data.activityImgUrl + "\" width=\"100%\" />");
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

function uploadInit1() {
	$("#file-upload").fileinput({
        uploadUrl: basePath + '/activity/namelist/import',
        showRemove : false,
        language : 'zh',
        allowedFileExtensions : ['xls','xlsx'],
        overwriteInitial: false,
        previewFileIcon: '<i class="fa fa-file"></i>',
        //allowedFileTypes: ['image', 'video', 'flash'],
        previewFileIconSettings: {
        	'xls': '<i class="fa fa-file-excel-o text-success"></i>'
        },
        previewFileExtSettings : {
        	'xls': function(ext) {
                return ext.match(/(xls|xlsx)$/i);
            },
        },
        uploadExtraData: function(previewId, index) {
            var obj = {};
        	obj.id = $("#uploadDialog #activity_id").val()
        	return obj;
        },
        slugCallback: function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
	});
	$("#file-upload").on("fileuploaded", function(event, data, previewId, index) {
		if(data.response.code == '000') {
			$.messager.alert('系统提示', data.filenames.toString() + "上传成功!", 'info');
		}else {
			var datamsg = data.response.data == null ? '' : ": " + data.response.data;
			$.messager.alert('系统提示', data.filenames.toString() + "上传" + data.response.msg + datamsg, 'info');
		}
	});
}

var uploadNamelist = function(index) {
	var row = $("#dataGrid").datagrid('getData').rows[index];
	$('#uploadDialog').dialog({
		title : "名单上传",
		modal : true,
		width : 600,
		top : 100,
		draggable : true,
		resizable : true,
		buttons : '#btns',
		onClose : function() {
			$(".fileinput-remove").click();
			loadData();
		},
		onOpen : function() {
			$("#uploadDialog #activity_id").val(row.id);
			$("#uploadDialog #activityName").val(row.activityName);
			$("#uploadDialog #activityNum").val(row.activityNum);
		}
		
	}).show();
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
			productId : {
				required : true
			},
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
				required : true,
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
							$.messager.alert('系统提示', $("#addForm #id").val() == '' ? '活动新增成功' : '活动修改成功', 'info');
							closeFormPanel("addForm");
							loadData();
						} else {
							$.messager.alert('系统提示', $("#addForm #id").val() == '' ? '活动新增失败' : '活动修改失败', 'warning');
						}
					}
				});
		}
	});
}

