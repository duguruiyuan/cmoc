var queryDebtSetUrl = basePath + "/json/product/queryDebtSetList";
var addFinancePlan = basePath + "/json/product/addFinancePlan";
var getMatchRuleSetComponent = basePath + "/json/component/getMatchRuleSetComponent";
var getBizSysBoxComponentUrl = basePath + "/json/component/getBizSysBoxComponent";

var setDataGrid;
var confirmDialog;
$(function() {
	loadData();
});

/**
 * 初始化数据
 */
function loadData() {
	setDataGrid = $('#setDataGrid').datagrid({
		url : queryDebtSetUrl,
		fit : true,
		fitColumns : false,
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
			width : 200,
			formatter : function(value, row, index) {
				var str = $.formatString('<button type="button" class="btn btn-info btn-xs"  style="margin:4px 4px; width:85px;" onclick="originAssetList(\'{0}\');">原始债权明细</button>', row.debtSetNo);
				str += $.formatString('<button type="button" class="btn btn-warning btn-xs" style="margin:4px 4px; width:85px;" onclick="financePlanCreate(' + index + ');">理财计划生成</button>', row.id);
				return str;
			}
		},{
			field : 'debtSetNo',
			title : '债权集编号',
			align : "center",
			resizable : true
		},{
			field : 'debtSetName',
			title : '债权集名称',
			align : "center",
			resizable : true
		}, {
			field : 'debtSetAmt',
			title : '资产总额',
			align : "center",
			resizable : true
		}, {
			field : 'debtPackRuleName',
			title : '打包规则名称',
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

/**
 * 查询数据
 */
var search=function(formId){
	setDataGrid.datagrid('load', $.serializeObject($('#' + formId)));
};

/**
 * 清空表单
 */
var cleanFormPanel=function(formId){
	$("#" + formId)[0].reset();
	$('form .textbox-value').val('');
	$(".valid").removeClass("valid");
	$(".error").removeClass("error");
	$("label").find("span").remove();
}

var financePlanCreate = function(index){
	var data = $("#setDataGrid").datagrid('getData').rows[index];
	if(data) {
		confirmDialog = $('#confirmDialog').dialog({
			title : "理财计划创建",
			modal : true,
			width : 1200,
			top : 100,
			draggable : true,
			resizable : true,
			buttons : '#btns',
			onClose : function() {
				cleanFormPanel("addForm");
			},
			onOpen : function() {
				$("#addForm #debtSetName").val(data.debtSetName);
				$("#addForm #debtSetNo").val(data.debtSetNo);
				$("#addForm #debtSetAmt").val(data.debtSetAmt);
			}
		}).show();
		validator();
		getMatchRuleSet();
		getBizSys();
	}
}

function getMatchRuleSet() {
	$.ajax({
		url : getMatchRuleSetComponent,
		type : 'GET',
		contentType : "application/json; charset=utf-8",
		error : function() {
			console.error("init failed");
		},
		success : function(data) {
			$('#matchRuleNo').html(data);
		}
	});
}

function getBizSys() {
	$.ajax({
		url : getBizSysBoxComponentUrl,
		type : 'GET',
		dataType:"json",
		error : function() {
			console.error("init failed");
		},
		success : function(data) {
			if (data.success == true ||data.success == 'true') {
				var str = "";
				data.obj.forEach(function(v,i) {
					str += "<span class='chbox'><input type='checkbox' name='bizSys' id='bizSys' value='" + v.bizSysNo + "'/> " + v.bizSysName + "</span>";
					if((i+1)%5 == 0 && (i+1) < data.obj.length) {
						str += "</br>";
					}
				});
				$('#pushSysList').html(str);
			}
		}
	});
}

var cancelHandle = function(){
	cleanFormPanel("addForm");
	$('#confirmDialog').dialog("close");
};

//绑定表单验证控件
function validator() {
	$("#addForm").validate({
		errorPlacement : function(error, element) {
			$(element).closest("form").find("label[for='" + element.attr("id") + "']").append(error);
		},
		errorElement : "span",
		rules : {
			productName : {
				required : true,
				maxlength : 20,
				minlength : 2
			},
			totalType : {
				required : true,
				number : true,
				maxlength : 2
			},
			rateType : {
				required : true,
				number : true,
				maxlength : 2
			},
			rate : {
				required : true,
				number: true
			},
			termType:{
				required : true,
				number : true,
				maxlength : 2
			},
			fixedTerm : {
				required : true,
				number : true,
				maxlength : 2
			},
			effectRule : {
				required : true,
				number : true,
				maxlength : 2
			},
			settleType :{
				required : true,
				number : true,
				maxlength : 2
			},
			dueTransferType :{
				required : true,
				number : true,
				maxlength : 2
			},
			interestType :{
				required : true,
				maxlength : 5
			},
			isReinvest: {
				required : true,
				maxlength : 2
			},
			reinvestType: {
				required : true,
				maxlength : 2
			},
			buyMinAmt :{
				required : true,
				number : true,
				amtCompare : true
			},
			issueAmt :{
				required : true,
				number : true,
				amtCompare : true
			},
			issueTerm :{
				required : true,
				number : true,
				maxlength : 20
			},
			matchRuleNo :{
				required : true
			},
			bizSys : {
				required : true
			}
		},
		submitHandler : function(form) {
				getPushSys();
				$.messager.progress({
					title : '系统提示',
					msg : '处理中，请稍候...'
				});

				$.ajax({
					url : addFinancePlan,
					type : 'POST',
					error : function() {
						$.messager.progress('close');
						$.messager.alert('系统提示', '理财计划创建异常', 'error');
					},
					data : $('#addForm').serialize(),
					success : function(data) {
						$.messager.progress('close');
						data = $.parseJSON(data);
						if (data.success == true ||data.success == 'true') {
							$.messager.alert('系统提示', '理财计划创建成功, 理财计划编号是' + data.obj, 'info');
							cancelHandle();
							loadData();
						} else {
							$.messager.alert('系统提示', data.msg, 'warning');
						}
					}
				});
		}
	});
	jQuery.validator.addMethod("amtCompare", function(value, element) {
		if(parseFloat(value) > parseFloat($("#debtSetAmt").val())) {
			return false;
		}
		return true;
	}, "发布金额不能大于债权集总额");
}

function getPushSys() {
	var pushSysList = $("input[name='bizSys']:checked");
	var pushSys = "";
	pushSysList.each(function(i, v) {
		pushSys += v.value + ",";
	})
	pushSys = pushSys.substr(0, pushSys.length-1);
	$("#pushSys").val(pushSys);
}

$(".number").blur(function() {
	var value = this.value;
	if(value) {
		if(!positiveValidate(value)) {
			$.messager.alert('系统提示',"请输入正确格式", 'warning');
			this.value = null;
		}
	}
})

