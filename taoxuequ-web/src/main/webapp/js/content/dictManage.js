var dictTypeAllUrl = basePath + '/content/dict/json/dictType/all';
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
        	var currId = $("#dataTypeAllMenu").find("li.dict-cur").find('a').attr("id");
            var html = '';
            data.forEach(function(item,index){
            	var clasz="";
            	if(!currId && index == 0){
            		clasz="class=\"dict-cur\"";
            		currId = item.id;
            	}else {
            		if(item.id == currId) clasz="class=\"dict-cur\"";
            	}
                html += '<li ' + clasz + '><a href="javascript:void(0)" id="' + item.id + '" onclick="linkDictDate(this)" class="tdictType_link">'+item.dictTypeName+'</a></li>'
            });
            initDictData(currId);
            $("#dataTypeAllMenu").html(html);
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
            "dictTypeId": id
        },
        columns : [ [{
	        	field : 'action',
	        	title : '操作',
	        	align : 'center',
	        	formatter : function(value, row, index) {
	        		return $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="updateDictData(\'{0}\');">编辑</button>', row.id);
	        	}
			}, {
            	field : 'id',
            	align : 'center',
            	title : '<b>编号</b>'
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
            	field : 'createTime',
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
	$('#' + formId)[0].reset();
	$('#' + formId + " #id").val(null);
}

function addDictType(){
	cleanFormPanel("addDictTypeForm");
	$("#addDictTypeForm #dictCode").attr("disabled", false);
    $('#addDictType').dialog('open').dialog('center').dialog('setTitle','新增字典类别');
}

function addDictData(){
	cleanFormPanel("addDictDataForm");
	$("#addDictDataForm #dictTypeId").val($(".dict-cur >a")[0].id);
    $('#addDictData').dialog('open').dialog('center').dialog('setTitle','新增字典类别');
}

function updateDictType() {
	$.ajax({
 		url : dictTypeByIdUrl,
 		type : "post",
 		data : {
 			id : $(".dict-cur >a")[0].id
 		},
 		dataType : "json",
 		async : false,
 		success : function(data) {
			addDictType();
			$("#addDictTypeForm #id").val(data.id);
			$("#addDictTypeForm #dictCode").val(data.dictCode);
			$("#addDictTypeForm #dictTypeName").val(data.dictTypeName);
			$("#addDictTypeForm #isActive").val(data.isActive);
			$("#addDictTypeForm #dictCode").attr("disabled", true);
 		}
 	});
}

function updateDictData(id) {
	$.ajax({
 		url : dictDataByIdUrl,
 		type : "post",
 		data : {
 			id : id
 		},
 		dataType : "json",
 		async : false,
 		success : function(data) {
			addDictData();
			$("#addDictDataForm #id").val(data.id);
			$("#addDictDataForm #dictTypeId").val(data.dictTypeId);
			$("#addDictDataForm #dictDataKey").val(data.dictDataKey);
			$("#addDictDataForm #dictDataValue").val(data.dictDataValue);
			$("#addDictDataForm #isActive").val(data.isActive);
 		}
 	});
}

function submitDictType() {
	var dictTypeName = $("#dictTypeName").val();
	var dictCode = $("#dictCode").val();
	if(dictTypeName.length == 0) {
		$.messager.alert('系统提示', '类别编码不能为空', 'error');
		return;
	}else if(dictCode.length == 0) {
		$.messager.alert('系统提示', '类别名称不能为空', 'error');
		return;
	}
	if(!$('#addDictTypeForm #id').val()) {
		$.messager.confirm('系统提示', '确认编码，提交以后编码不能更新了！', function(r) {
			if (r){
				commitDictType();
			}
		});
	}else {
		commitDictType();
	}
}

function commitDictType(){
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

function submitDictData() {
	var dictDataValue = $("#dictDataValue").val();
	if(dictDataValue.length == 0) {
		$.messager.alert('系统提示', '名称不能为空', 'error');
		$("#dictDataValue").focus();
		return;
	}
	$.ajax({
		url : addUpdateDictDataUrl,
		type : 'POST',
		error : function() {
			$.messager.progress('close');
			$.messager.alert('系统提示', '操作异常', 'error');
		},
		data : $('#addDictDataForm').serialize(),
		success : function(data) {
			$.messager.progress('close');
			if (data.code == '000') {
				$.messager.alert('系统提示', $("#addDictDataForm #id").val() == '' ? '字典类别新增成功' : '字典类别修改成功', 'info');
				initDictType();
				cleanFormPanel("addDictDataForm");
				$('#addDictData').dialog('close')
			} else {
				$.messager.alert('系统提示', $("#addDictDataForm #id").val() == '' ? '字典类别新增失败' : '字典类别修改失败', 'warning');
			}
		}
	});
}

function linkDictDate(thiz) {
	var lic = $(thiz.closest('li'));
	if(!lic.hasClass('dict-cur')) {
		initDictData(thiz.id);
		lic.addClass("dict-cur").siblings().removeClass("dict-cur");
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
 			dictCode : this.value
 		},
 		dataType : "json",
 		async : false,
 		success : function(data) {
 			if(data>0)
 			 $.messager.alert('系统提示', '输入的编码已存在，请重新输入', 'warning');
 		}
 	});
});

