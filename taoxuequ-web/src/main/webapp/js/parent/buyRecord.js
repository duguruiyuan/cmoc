var buyRecordListUrl = basePath + "/parent/buy/json/record";
var buyRecordByIdUrl = basePath + "/parent/buy/json/record/id";

var dataGrid;
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
				if(row.buyCount > 0) {
					str += $.formatString('<button  type="button" class="btn btn-info btn-xs" style="margin:4px 4px;" onclick="buyRecord({0});">购买记录</button>', row.id);
				}
				return str;
			}
		},{
			field : 'buyCount',
			title : '购买次数',
			align : "center",
			resizable : true
		},{
			field : 'parentName',
			title : '家长姓名',
			align : "center",
			resizable : true
		}, {
			field : 'parentMobile',
			title : '家长号码',
			align : "center",
			resizable : true
		}, {
			field : 'relation',
			title : '与小孩关系',
			align : "center",
			resizable : true,
			formatter: function(v) {
				return dictDataFormat("relation", v);
			}
		}, {
			field : 'points',
			title : '积分',
			align : "center",
			resizable : true
		}, {
			field : 'openid',
			title : '公众号标识',
			align : "center",
			resizable : true
		}, {
			field : 'familyNo',
			title : '家庭编号',
			align : "center",
			resizable : true
		},{
			field : 'createTime',
			title : '创建时间',
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
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$('#' + formId)[0].reset();
}


function buyRecord(parentId){
	var thirdHtml;
	thirdHtml=buyList(parentId);
	
	$("#buyNumDiolog").html(thirdHtml);
	thirdAcctDialog = $('#buyNumDiolog').dialog({
		title : "购买记录",
		modal : true,
		width : 750,
		height : 400,
		top : 200,
		draggable : true,
		//resizable : true,
		//buttons : '#btns',
		onClose : function() {
			$('#buyNumDiolog').empty();
		}
	}).show();
}

function buyList(parentId){
	var html="<table class='table table-condensed'><thead><tr>"
					+"<th style='text-align: center;'>课程编号</th>"
					+"<th style='text-align: center;width: 200px;'>课程名称</th>"
					+"<th style='text-align: center;'>课程类型</th>"
					+"<th style='text-align: center;'>小孩姓名</th>"
					+"<th style='text-align: center;'>小孩性别</th>"
					+"<th style='text-align: center;'>小孩年龄</th>"
					+"<th style='text-align: center;'>报名时间</th>"
				+"</tr></thead><tbody>";
	$.ajax({
		url : buyRecordByIdUrl,
	    type : 'post',
	    data : {
	    	parentId : parentId
	    },
	    dataType : 'json',
	    async:false,
		cache:false,  
	    success : function(data){
	    	if(data.code == '000'){
    			if(data.data !=null){
	    			var dataHtml=buildAssetData(data.data);
	    			html=html+dataHtml;
    			}else{
    				dataExceptionFlag=true;
    			}
    		}else{
    			$.messager.alert('系统提示', data.msg,'warning');
    		}
	    }
	});
	html=html+"</tbody></table>";
	return html;
}

function buildAssetData(obj){
	var dataHtml="";
	$.each(obj, function(n,v) {
		dataHtml += "<tr>"
			+"<td remark='课程编号' style='text-align: center;'>"+getNullString(v.productId)+"</td>"
			+"<td remark='课程名称' style='text-align: center;' title='" + v.courseName +"'>"+getNullString(v.courseName)+"</td>"
			+"<td remark='课程类型' style='text-align: center;'>"+dictDataFormat("course_type", v.courseType)+"</td>"
			+"<td remark='小孩姓名' style='text-align: center;'>"+getNullString(v.childName)+"</td>"
			+"<td remark='小孩性别' style='text-align: center;'>"+sexFormat(v.childSex)+"</td>"
			+"<td remark='小孩年龄' style='text-align: center;'>"+v.childAge+"</td>"	
			+"<td remark='报名时间' style='text-align: center;'>"+getTime(v.createTime, "yyyy/MM/dd")+"</td>"
		+"</tr>";
	});	
	return dataHtml;
}
