var userQueryUrl = basePath + "/power/user/json/query";
var queryAllRoleUrl = basePath + "/power/json/queryAllRole";
var addUpdateUserUrl = basePath + "/power/user/json/addUpdate";
var queryUserInfoUrl = basePath + "/power/user/json/queryUserInfo";

var dataGrid;
var confirmDialog;
$(function() {
	loadData();
});

function loadData() {
	dataGrid = $('#dataGrid').datagrid({
		url : userQueryUrl,
		fit : true,
		fitColumns : true,
		border : true,
		pagination : true,
		idField : 'idRole',
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
				var str = $.formatString('<button  type="button" class="btn btn-info btn-xs" style="margin:4px 4px; width:85px;"  onclick="updateUser(\'{0}\');">用户权限</button>', row.idUser);
				return str;
			}
		}, {
			field : 'userName',
			title : '用户姓名',
			align : "center",
			resizable : true
		},{
			field : 'userAccount',
			title : '用户账号',
			align : "center",
			resizable : true
		},{
			field : 'validFlag',
			title : '用户状态',
			align : "center",
			resizable : true,
			formatter : function(value) {
				if(value == 0) {
					return "禁用";
				}else if(value == 1) {
					return "可用";
				}
			}
		}, {
			field : 'creator',
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

function cleanFormPanel(formId){
	$("#" + formId)[0].reset();
	$('form .textbox-value').val('');
}

//取消选中
var cancelSelect = function(){
	if (dataGrid != undefined){
		dataGrid.datagrid("clearSelections");
	}
}

//债权转让申请
var assetSorting = function() {
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('系统提示', '请选择资产再进行操作', 'warning');
		return;
	} else {
		$.messager.confirm('系统提示', '确定分拣选择的资产吗？', function(r) {
			if (r) {
				var ids=new Array();
				for(var i=0;i<rows.length;i++){
					ids.push(rows[i].id);
				}
				submitAssetSorting(ids);
			}
		});
	}
};


//债权转让申请
function submitAssetSorting(ids){
	$.ajax({
		url : submitAssetSortingUrl,
		type : 'POST',
		dataType:"json",
		data : {
			'ids' :ids
		},
		error : function() {
			$.messager.alert('系统提示', '资产分拣失败', 'error');
		},
		success : function(data) {
			if (data.success == true ||data.success == 'true') {
				$.messager.alert('系统提示','资产分拣成功', 'info');
			} else {
				$.messager.alert('系统提示',data.msg, 'warning');
			}
			search("assetform");
		}
	});
}

function addUser() {
	loadForm(null);
}

function updateUser(idUser) {
	$.ajax({
 		url : queryUserInfoUrl,
 		type : "post",
 		data : {
 			idUser : idUser
 		},
 		dataType : "json",
 		async : false,
 		success : function(data) {
			loadForm(data);
 		}
 	});
}

function loadItems(data) {
	var roleIds = ",";
	if(data) {
		$("#addForm #idUser").val(data.idUser);
		$("#addForm #userName").val(data.userName);
		$("#addForm #userAccount").val(data.userAccount);
		$("#addForm #validFlag").val(data.validFlag);
		$.each(data.relList,function(i, v) {
			roleIds += v.idRole + ","
		});
	}
	$.ajax({
 		url : queryAllRoleUrl,
 		type : "post",
 		dataType : "json",
 		async : false,
 		success : function(data) {
 			showMenu(data, roleIds);	
 		}
 	});
}

function showMenu(obj, roleIds){//一级菜单
	var str = "";
	for(var i = 0; i < obj.length; i++){
		var checked = "";
		if(roleIds.indexOf(","+obj[i].idRole+",") > -1){
			checked = "checked='checked'";
		}
		if(i == 0) str += "<div id='" + obj[i].idRole + "Tip'></div><tr class='f16'>";
		str += "<td class='chbox'><input type='checkbox' onclick='boxcheck(this)' id='" + obj[i].idRole + "' name='roleIds' value='" + obj[i].idRole + "'" + checked + "/>&nbsp;" + obj[i].roleName +"</td>";
	}
	str += "</tr>";
	$("#checkboxMenu").html("<table id=\"tab_checkbox\">"+str+"</table>");
}

function checkParentNode(id,checkboxFlag){//操作父级菜单
	$(":checkbox").each(function(){
		if($(this).attr("id") == id){
			if(checkboxFlag == true){//选中
				$(this).prop("checked",checkboxFlag);
				if($(this).attr("parent_id")!="0"){//如果父级菜单不为0，则继续调用
					checkParentNode($(this).attr("parent_id"),checkboxFlag);
				}
			}
			else{
				var i = checkboxNum(id);
				if(i == 0){
					$(this).prop("checked",checkboxFlag);
					if($(this).attr("parent_id")!="0"){//如果父级菜单不为0，则继续调用
						checkParentNode($(this).attr("parent_id"),checkboxFlag);
					}
				}
			}
		}
	});
}
	
function checkboxNum(pid){//查询同级菜单选中有几个
	var num = 0;
	$(":checkbox").each(function(){
		if($(this).attr("parent_id") == pid && $(this).prop("checked") == true){
			num = num+1;
		}
	});
	return num;
}

function boxcheck(obj) {
  	 var pid = $(obj).attr("parent_id");
  	 var checkboxFlag = $(obj).prop("checked");
  	 checkParentNode(pid,checkboxFlag);
}
$("#selAll").click(function () { //":checked"匹配所有的复选框
    $("#tab_checkbox :checkbox").prop("checked", true); //"#div1 :checked"之间必须有空格checked是设置选中状态。如果为true则是选中fo否则false为不选中
});
$("#unselAll").click(function () {
    $("#tab_checkbox :checkbox").prop("checked", false);
});

//理解用迭代原理each（function(){}）
$("#reverse").click(function () {
	 $(":checkbox").each(function(){
		 if($(this).css("display")!="none"){
			 if($(this).prop("checked")==true){
				 $(this).prop("checked",false);
				 checkParentNode($(this).attr("parent_id"),false);
			 }
			 else{
				 $(this).prop("checked",true);
				 checkParentNode($(this).attr("parent_id"),true);
			 }
		 }
	 });
});

function changeSonCheck(obj)
{
	var checkboxFlag=$(obj).prop("checked");
	var id=$(obj).attr("id");
	$(":checkbox").each(function(){
		if($(this).attr("parent_id") == id){
				$(this).prop("checked",checkboxFlag);
				if($(this).attr("parent_id")!="0"){//如果父级菜单不为0，则继续调用
					checkParentNode($(this).attr("id"),checkboxFlag);
				}
		}
	});
}

function returnBack(){
	window.location.reload();
}

//绑定表单验证控件
function validator(data) {
	if(data == null) {
		$("#passwordTr").show()
	}else {
		$("#passwordTr").hide();
	}
	$("#addForm").validate({
		errorPlacement : function(error, element) {
			$(element).closest("form").find("#" + element.attr("id") + "Tip").append(error);
		},
		errorElement : "span",
		rules : {
			userName : {
				required : true,
				maxlength : 40,
			},
			userAccount : {
				required : true,
				maxlength : 50
			},
			password : {
				required : data == null ? true : false,
				maxlength : 50
			},
			validFlag: {
				required : true
			},
			roleIds : {
				required : true
			}
		},
		submitHandler : function(form) {
				$.messager.progress({
					title : '系统提示',
					msg : '处理中，请稍候...'
				});

				$.ajax({
					url : addUpdateUserUrl,
					type : 'POST',
					error : function() {
						$.messager.progress('close');
						$.messager.alert('系统提示', '操作异常', 'error');
					},
					data : $('#addForm').serialize(),
					success : function(data) {
						$.messager.progress('close');
						if (data.code == '000') {
							$.messager.alert('系统提示', $("#idUser").val() == '' ? '用户新增成功' : '用户权限修改成功', 'info');
							cancelHandle("addForm");
							loadData();
						} else {
							if(data.code == '1021') {
								$("#addForm #userAccount").val(null);
								$("#addForm #userAccount").focus();
								$.messager.alert('系统提示', data.msg, 'warning');
								return;
							}
							$.messager.alert('系统提示', $("#idRole").val() == '' ? '用户新增失败' : '用户权限修改失败', 'warning');
						}
					}
				});
		}
	});
}

function getMenuIds() {
	var menuList = $("input[name='menuId']:checked");
	var menuIds = [];
	menuList.each(function(i, v) {
		menuIds.push(v.value);
	})
	$("#menuIds").val(menuIds);
}

var loadForm = function(data){
	confirmDialog = $('#confirmDialog').dialog({
		title : data != null ? "修改用户" : "创建用户",
		modal : true,
		width : 600,
		top : 100,
		draggable : true,
		resizable : true,
		buttons : '#btns',
		onClose : function() {
			cleanFormPanel("addForm");
		}
	}).show();
	validator(data);
	loadItems(data);
}

var cancelHandle = function(form){
	cleanFormPanel(form);
	$('#confirmDialog').dialog("close");
};
