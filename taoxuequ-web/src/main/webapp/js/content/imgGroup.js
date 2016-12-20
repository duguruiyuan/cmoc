var imgGroupByPositionUrl = basePath + '/content/json/imgGroup/position';
var imgGroupByIdUrl = basePath + "/content/json/imgGroup/id";
var addUpdateImgGroupUrl = basePath + "/content/json/imgGroup/addUpdate";

var dataDataGrid;
var confirmDialog;
$(function(){
	uploadPic();
    initPosition();
    initCity();
});

/**
 * 初始图组位置
 */
function initPosition(){
    $.ajax({
        url: basePath + "/content/dict/json/dictData/dictCode",
        type: 'POST',
        data: {
        	dictCode: "img_group"
        },
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
            		currId = item.dictDataKey;
            	}else {
            		if(item.id == currId) clasz="class=\"dict-cur\"";
            	}
                html += '<li ' + clasz + '><a href="javascript:void(0)" id="' + item.dictDataKey + '" onclick="linkImgGroup(this)" class="tdictType_link">'+item.dictDataValue+'</a></li>'
            });
            initImgGroup(currId);
            $("#dataTypeAllMenu").html(html);
        }
    });

}

/**
 * 菜单数据表
 */
function initImgGroup(position){
	$("#addImgGroup #position").val(position);
    dataDataGrid = $('#dataGrid').datagrid({
        url : imgGroupByPositionUrl,
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
            position: position 
        },
        columns : [ [{
	        	field : 'action',
	        	title : '操作',
	        	align : 'center',
	        	formatter : function(value, row, index) {
	        		return $.formatString('<button  type="button" class="btn btn-warning btn-xs" style="margin:4px 4px;" onclick="updateImgGroup(\'{0}\');">编辑</button>', row.id);
	        	}
			}, {
            	field : 'id',
            	align : 'center',
            	title : '<b>编号</b>'
            }, {
				field : 'shelves',
				align : 'center',
				title : '<b>状态</b>',
            	formatter:function(value,row,index){
                    return shelvesFormat(value);
                }
            }, {
            	field : 'title',
            	align : 'center',
            	title : '<b>标题描述</b>'
            }, {
            	field : 'imgUrl',
            	align : 'center',
            	title : '<b>图片</b>',
            	formatter : function(v, row, index) {
    				if(v) {
    					return "<img style='width:15px;height:15px;' src='" + imgUrl + v + "'>";
    				}else {
    					return "<span color='gray'>无</span>";
    				}
    			}
            }, {
            	field : 'linkUrl',
            	align : 'center',
            	title : '<b>链接地址</b>',
            	formatter : function(v) {
            		return "<a href=\"" + v + "\" target='_blank'>查看</a>";
            	}
            }, {
            	field : 'city',
            	align : 'center',
            	title : '<b>城市</b>',
            	formatter : function(value) {
    				return dictDataFormat('city',value);
    			}
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
	$('#' + formId + " #imgUrl").val(null);
	$('#' + formId + " #id").val(null);
}

function addImgGroup(){
	$("#regMT-uploadPic").attr({"src": basePath+"/images/m-taoxuequ.jpg"});
	$('#addImgGroup').dialog({
		title : $("#addImgGroupForm #id").val() == '' ? '新增图组' : '修改图组',
		modal : true,
		width : 600,
		top : 100,
		draggable : true,
		resizable : true,
		buttons : '#btns',
		onClose : function() {
			cleanFormPanel("addImgGroupForm");
		}
	}).show();
}

function updateImgGroup(id) {
	$.ajax({
 		url : imgGroupByIdUrl,
 		type : "post",
 		data : {
 			id : id
 		},
 		dataType : "json",
 		async : false,
 		success : function(data) {
 			addImgGroup();
			$("#addImgGroupForm #id").val(data.id);
			$("#addImgGroupForm #city").val(data.city);
			$("#addImgGroupForm #imgUrl").val(data.imgUrl);
			$("#addImgGroupForm #linkUrl").val(data.linkUrl);
			$("#addImgGroupForm #position").val(data.position);
			$("#addImgGroupForm #shelves").val(data.shelves);
			$("#addImgGroupForm #title").val(data.title);
			if(data.imgUrl != null)$("#regMT-uploadPic").attr({"src": imgUrl + data.imgUrl+"?v=" + new Date().getTime(), "data-state": "yes"});
 		}
 	});
}


function commitImgGroup(){
	var imgUrl = $("#imgUrl").val();
	if(imgUrl.length == 0) {
		$.messager.alert('系统提示', '请上传图片', 'error');
		return;
	}
	$.messager.confirm('系统提示', '确认提交吗！', function(r) {
		if (r){
			$.ajax({
				url : addUpdateImgGroupUrl,
				type : 'POST',
				error : function() {
					$.messager.progress('close');
					$.messager.alert('系统提示', '操作异常', 'error');
				},
				data : $('#addImgGroupForm').serialize(),
				success : function(data) {
					$.messager.progress('close');
					if (data.code == '000') {
						$.messager.alert('系统提示', $("#addImgGroupForm #id").val() == '' ? '新增成功' : '修改成功', 'info');
						initImgGroup($("#dataTypeAllMenu").find("li.dict-cur >a").attr('id'));
						cleanFormPanel("addImgGroupForm");
						$('#addImgGroup').dialog('close')
					} else {
						$.messager.alert('系统提示', $("#addImgGroupForm #id").val() == '' ? '新增失败' : '修改失败', 'warning');
					}
				}
			});
		}
	});
}

function linkImgGroup(thiz) {
	var lic = $(thiz.closest('li'));
	if(!lic.hasClass('dict-cur')) {
		initImgGroup(thiz.id);
		lic.addClass("dict-cur").siblings().removeClass("dict-cur");
	}
}


//上传图片
function uploadPic(){
	//上传按钮
	document.querySelector('#regMT-uploadInput').addEventListener('change', function () {
		var that=this;
		var imgWrapObj;
		lrz(that.files[0],{
			width:1024
		})
    	//rst格式
    	//rst.origin:图片信息，如大小、日期等
    	//rst.base64:生成后的图片base64，后端可以处理此字符串为图片
    	//rst.base64Len:生成后的图片的大小，后端可以通过此值来校验是否传输完整
        .then(function (rst) {
        	var fileName = rst.origin.name;
        	var extName = fileName.substring(fileName.lastIndexOf("."), fileName.length)
        	if(!(".jpg|.png|.bmp|.jpeg".toUpperCase().indexOf(extName)==-1)){
        		alert("只允许上传jpg、png、bmp、jpeg格式的图片");
        		return false;
        	}
        	extName = extName.substring(1, extName.length);
        	$.ajax({
        		type:"post",
        		url: basePath + "/attachment/course/upload/img",
        		data: {
        			extName: extName,
        			imgdata: rst.base64
        		},
        		async: false,
        		cache: false,
        		dataType:"json",
        		success:function(result){
        			result=result||{};
        			if(result.code == "000"){
        				$("#regMT-uploadPic").attr({"src": imgUrl + result.data+"?v=" + new Date().getTime(), "data-state": "yes"});
        				$("#imgUrl").val(result.data)
        			}else{
        				mui.alert(result.msg,'消息提示');
        			}
        		},
        		error:function(){
        			alert("网络错误！");
        		},
        		beforeSend:function(){
        		},
        		complete:function(){
        			
        		}
        	})
        })
        .catch(function (err) {
        	alert("图片处理失败");
        })
        .always(function () {
            // 不管是成功失败，都会执行
        });
	})
}

