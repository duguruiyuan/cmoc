var roleQueryUrl = basePath + "/power/role/json/query";
var queryAllMenuUrl = basePath + "/power/json/queryAllMenu";
var addUpdateRoleUrl = basePath + "/power/role/json/addUpdate";
var queryRoleInfoUrl = basePath + "/power/role/json/queryRoleInfo";

var dataGrid;
$(function() {
	loadData();
});

function loadData() {
	dataGrid = $('#dataGrid').datagrid({
		url : roleQueryUrl,
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
				var str = $.formatString('<button  type="button" class="btn btn-warning btn-xs" onclick="updateRole(\'{0}\');">编辑</button>', row.idRole);
				return str;
			}
		}, {
			field : 'roleName',
			title : '角色名称',
			align : "center",
			resizable : true
		},{
			field : 'roleDesc',
			title : '角色描述',
			align : "center",
			resizable : true
		}, {
			field : 'creator',
			title : '创建人',
			align : "center",
			resizable : true
		}, {
			field : 'createTime',
			title : '创建时间',
			align : "center",
			resizable : true
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

function addRole() {
	$("#content_div").replaceWith($('#role_add'));
	$("#role_add").show();
	loadItems("");
}

function updateRole(idRole) {
	$.ajax({
 		url : queryRoleInfoUrl,
 		type : "post",
 		data : {
 			idRole : idRole
 		},
 		dataType : "json",
 		async : false,
 		success : function(data) {
 			$("#addForm #idRole").val(data.idRole);
			$("#addForm #roleName").val(data.roleName);
			$("#addForm #roleDesc").val(data.roleDesc);
			var menuIds = ",";
			$.each(data.relList,function(i, v) {
				menuIds += v.idResource + ","
			});
			$("#content_div").replaceWith($('#role_add'));
			$("#role_add").show();
			loadItems(menuIds);
 		}
 	});
}

function loadItems(menuIds) {
	$.ajax({
 		url : queryAllMenuUrl,
 		type : "post",
 		dataType : "json",
 		async : false,
 		success : function(data) {
 			showMenu(data, menuIds);	
 			validator();
 		}
 	});
}

function showMenu(obj, menuIds){//一级菜单
	var str = "";
	for(var i = 0; i < obj.length; i++){
		var checked = "";
		if(menuIds.indexOf(","+obj[i].idResource+",") > -1){
			checked = "checked='checked'";
		}
		if(i == 0) str += "<div id='" + obj[i].idResource + "Tip'></div>";
		str += "<tr class='lh40'><td class='f18' colspan='4'><input type='checkbox' onclick='changeSonCheck(this)' id='" + obj[i].idResource + "' name='menuIds' value='" + obj[i].idResource + "'" + checked + "/>&nbsp;<b>" + obj[i].resourceName + "</b></td>";
		if(obj[i].hasChild) {
			str += "</tr><tr class='f16'>";
			$.each(obj[i].subMenuList,function(i, v) {
				var checked = menuIds.indexOf("," + v.idResource + ",") != -1 ? "checked" : "";
				str += "<td class='w250 pl20'><input type='checkbox' onclick='boxcheck(this)' id='" + v.idResource + "' name='menuIds' value='" + v.idResource + "' parent_id='" + v.parentResourceId + "'" + checked + "/>&nbsp;" + v.resourceName +"</td>";
			});
		}
		str += "</tr>";
	}
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
	window.location.href = roleQueryUrl;
}

//绑定表单验证控件
function validator() {
	$("#addForm").validate({
		errorPlacement : function(error, element) {
			$(element).closest("form").find("#" + element.attr("id") + "Tip").append(error);
		},
		errorElement : "span",
		rules : {
			roleName : {
				required : true,
				maxlength : 40,
			},
			roleDesc : {
				required : true,
				maxlength : 50
			},
			menuIds : {
				required : true
			}
		},
		submitHandler : function(form) {
//				getMenuIds();
				$.messager.progress({
					title : '系统提示',
					msg : '处理中，请稍候...'
				});

				$.ajax({
					url : addUpdateRoleUrl,
					type : 'POST',
					error : function() {
						$.messager.progress('close');
						$.messager.alert('系统提示', '操作异常', 'error');
					},
					data : $('#addForm').serialize(),
					success : function(data) {
						$.messager.progress('close');
						if (data.code == '000') {
							$.messager.alert('系统提示', $("#idRole").val() == '' ? '角色新增成功' : '角色修改成功', 'info');
							cleanFormPanel("addForm");
							loadData();
						} else {
							$.messager.alert('系统提示', $("#idRole").val() == '' ? '角色新增失败' : '角色修改失败', 'warning');
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