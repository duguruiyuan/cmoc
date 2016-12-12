var dictTypeAllUrl = basePath + '/content/dict/json/dictType/all';
var dictDataUrl = basePath+"/content/dict/json/dictData";
var addUpdateDictTypeUrl = basePath + "/content/dict/json/dictType/addUpdate";
var addUpdateDictDataUrl = basePath + "/content/dict/json/dictDate/addUpdate";
var dictTypeByIdUrl = basePath + "/content/dict/json/dictType/id";
var dictDataByIdUrl = basePath + "/content/dict/json/dictData/id";
var dictCodeExistsUrl = basePath + "/content/dict/json/dictCode/exists";

var dataDataGrid;
var confirmDialog;
$(function(){
    initDictType();
});

/**
 * 初始化字典类别
 */
function initDictType(){
    $.ajax({
        url: dictTypeAllUrl,
        type: 'POST',
        dataType: 'json',
        error : function(data) {
            $.messager.progress('close');
            $.messager.alert('系统提示', data.msg, 'error');
        },
        success: function (data) {
        	initDictData(data[0].id);
            var html = '';
            data.forEach(function(item,index){
                html += '<li' + (index == 0? ' class="dict-cur"' : '') + '><a href="javascript:void(0)" id="' + item.id + '" onclick="linkDictDate(this)" class="tdictType_link">'+item.dictTypeName+'</a></li>'
            });
            $("#dataTypeAllMenu").append(html);
        }
    });

}

/**
 * 菜单数据表
 */
function initDictData(id){
    dataDataGrid = $('#dataGrid').datagrid({
        url : basePath+"/content/dict/json/dictData",
        fit : true,
        fitColumns : true,
        border : true,
        singleSelect : true,
        pagination : true,
        idField : 'id',
        pageSize : 10,
        autoRowHeight : 800,
        pageList : [ 10 ],
        nowrap : true,
        striped : true,
        toolbar:'#toolbar',
        queryParams : {
            "dictTypeId": $("#dictTypeId").val()
        },
        columns : [ [{
	        	field : 'action',
	        	title : '操作',
	        	align : 'center',
	        	formatter : function(value, row, index) {
	        		if(row.isActive == 1) {
	        			return $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="updateActivity(\'{0}\');">禁用</button>', row.id);
	        		}else {
	        			return $.formatString('<button type="button" class="btn btn-info btn-xs" style="margin:4px 4px;" onclick="uploadNamelist({0});">启用</button>', index);
	        		}
	        	}
			}, {
				field : 'isActive',
				align : 'center',
				title : '<b>状态</b>',
            	formatter:function(value,row,index){
                    if(value == 1){
                        return "启用";
                    }else{
                        return "禁用";
                    }
                }
            }, {
            	field : 'id',
            	align : 'center',
            	title : '<b>编号</b>'
            }, {
            	field : 'id',
            	align : 'center',
            	title : '<b>编号</b>'
            }, {
            	field : 'dictDataKey',
            	align : 'center',
            	title : '<b>字典数据编码</b>'
            }, {
            	field : 'dictDataValue',
            	align : 'center',
            	title : '<b>字典数据名称</b>'
            }, {
            	field : 'creater',
            	align : 'center',
            	title : '<b>创建人</b>'
            }, {
            	field : 'create_time',
            	align : 'center',
            	title : '<b>创建时间</b>', 
            	formatter : function(value) {
    				return getTime(value, "yyyy-MM-dd hh:mm");
    			}
            }
        ]]
    });
}

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$("#" + formId)[0].reset();
}

function addDictType(){
    $('#addDictType').dialog('open').dialog('center').dialog('setTitle','新增字典类别');
}

function updateDictType(id) {
	$.ajax({
 		url : dictTypeByIdUrl,
 		type : "post",
 		data : {
 			id : id
 		},
 		dataType : "json",
 		async : false,
 		success : function(data) {
 			$("#addDictTypeForm #id").val(data.id);
			$("#addDictTypeForm #dictCode").val(data.dictCode);
			$("#addDictTypeForm #dictTypeName").val(data.dictTypeName);
			$("#addDictTypeForm #isActive").val(data.isActive);
			submitDictType();
 		}
 	});
}

function submitDictType() {
	var dictTypeName = $("#dictTypeName").val();
	var dictCode = $("#dictCode").val();
	if(dictTypeName.length == 0) {
		$.messager.alert('系统提示', '类别编码不能为空', 'error');
		dictTypeName.focus();
		return;
	}else if(dictCode.length == 0) {
		$.messager.alert('系统提示', '类别名称不能为空', 'error');
		dictCode.focus();
		return;
	}
	$.ajax({
		url : addUpdateDictTypeUrl,
		type : 'POST',
		error : function() {
			$.messager.progress('close');
			$.messager.alert('系统提示', '操作异常', 'error');
		},
		data : $('#addDictTypeForm').serialize(),
		success : function(data) {
			$.messager.progress('close');
			if (data.code == '000') {
				$.messager.alert('系统提示', $("#addDictTypeForm #id").val() == '' ? '字典类别新增成功' : '字典类别修改成功', 'info');
				initDictType();
				cleanFormPanel("addDictTypeForm");
				$('#addDictType').dialog('close')
			} else {
				$.messager.alert('系统提示', $("#addDictTypeForm #id").val() == '' ? '字典类别新增失败' : '字典类别修改失败', 'warning');
			}
		}
	});
}

function linkDictDate(thiz) {
	var lic = $(thiz.closest('li'));
	if(!lic.hasClass('dict-cur')) {
		lic.siblings().removeClass("dict-cur").addClass("dict-cur");
		initDictData(thiz.id);
	}
}

$("#dictCode").on("blur", function() {
	var value = this.value;
	if(value.length == 0) return;
	if (!(/^[A-Za-z_-]+$/).test(value)) {
		$.messager.alert('系统提示', '只能输入字母', 'warning');
	}
	$.ajax({
 		url : dictCodeExistsUrl,
 		type : "post",
 		data : {
 			dictCode : this.val()
 		},
 		dataType : "json",
 		async : false,
 		success : function(data) {
 			$.messager.alert('系统提示', '输入的编码已存在，请重新输入', 'warning');
 			$("input[name='dictCode']").focus();
 		}
 	});
});

