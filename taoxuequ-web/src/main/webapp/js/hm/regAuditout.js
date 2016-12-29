var hmRegQueryUrl = basePath + "/hm/json/regAuditout/query";

var dataGrid;
$(function() {
	loadData();
});

function loadData() {
	dataGrid = $('#dataGrid').datagrid({
		url : hmRegQueryUrl,
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
			field : 'isActive',
			title : '状态',
			align : "center",
			resizable : true,
			formatter : function(v) {
				return "审核不通过";
			}
		},{
			field : 'reason',
			title : '原因',
			resizable : true,
			formatter : function(v) {
				if(v == null) return "";
				return "<span title=\"" + v +"\">" + v + "</span>";
			}
		},{
			field : 'hmName',
			title : '姓名',
			align : "center",
			resizable : true
		},{
			field : 'hmName',
			title : '姓名',
			align : "center",
			resizable : true
		},{
			field : 'hmMobile',
			title : '手机号码',
			align : "center",
			resizable : true
		}, {
			field : 'idCard',
			title : '证件号码',
			align : "center",
			resizable : true
		}, {
			field : 'sex',
			title : '性别',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return sexFormat(v);
			}
		}, {
			field : 'place',
			title : '籍贯',
			align : "center",
			resizable : true
		}, {
			field : 'schoole',
			title : '学校',
			align : "center",
			resizable : true
		}, {
			field : 'grade',
			title : '年级',
			align : "center",
			resizable : true
		}, {
			field : 'idPhoto',
			title : '证件照',
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
			field : 'createTime',
			title : '申请时间',
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
	$('#' + formId)[0].reset();
}

