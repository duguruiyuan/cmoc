var roleQueryUrl = basePath + "/power/role/json/query";

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
		columns : [ [  {
			field : 'idRole',
			checkbox : true
		},{
			field : 'action',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var str = $.formatString('<button  type="button" class="btn btn-warning btn-xs" onclick="editRole(\'{0}\');">编辑</button>', row.idRole);
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
