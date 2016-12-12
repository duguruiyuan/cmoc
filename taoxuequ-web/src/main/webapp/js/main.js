$(function(){
	$(".switch").click(function(){
	    $("#left").toggle();
	});
	$(".menuLev1").click(function(){
		var obj=$(this);
		if(obj.parent("div").attr('class') == "collapsed") {
			obj.parent("div").removeClass("collapsed");
		}else {
			obj.parent("div").addClass("collapsed");
		}
	})
})

function logout() {
	$.messager.confirm('系统提示', '确定退出系统吗？', function(r) {
		if (r) {
			window.location.href = basePath + "/logout";
		}
	});
}

function addFavorite() {
    try {
        window.external.addFavorite(window.location.href, document.title);
    } catch (e) {
    	try {
    		window.sidebar.addPanel(document.title, window.location.href, "");
    	} catch (e) {
    		alert("抱歉，由于chrome,safari,opara浏览器还未支持自动收藏,请使用Ctrl+D进行添加!");
    	}
    }
}

function validator() {
	$("#updatePwdForm").validate({
		errorPlacement : function(error, element) {
			$(element).closest("form").find("#" + element.attr("id") + "Tip").append(error);
		},
		errorElement : "span",
		rules : {
			userAccount : {
				required : true,
				maxlength : 40,
			},
			oldPwd : {
				required : true,
				maxlength : 50
			},
			newPwd : {
				required : true,
				maxlength : 50
			}
		},
		submitHandler : function(form) {
				$.ajax({
					url : basePath + "/auth/updatePwd",
					type : 'POST',
					error : function() {
						$.messager.progress('close');
						$.messager.alert('系统提示', '操作异常', 'error');
					},
					data : $('#updatePwdForm').serialize(),
					success : function(data) {
						$.messager.progress('close');
						if (data.code == '000') {
							$.messager.alert('系统提示', '密码修改成功', 'info');
							closeFormPanel("updatePwdForm");
						} else {
							$.messager.alert('系统提示', data.msg, 'warning');
						}
					}
				});
		}
	});
}

var updatePwdForm = function(){
	$('#updatePwdDialog').dialog({
		title : "修改密码",
		modal : true,
		width : 500,
		top : 100,
		draggable : true,
		resizable : true,
		buttons : '#btns',
		onClose : function() {
			cleanFormPanel("updatePwdForm");
		}
	}).show();
	validator();
}

function closeFormPanel(formId){
	$("#" + formId + " #oldPwd").val('');
	$("#" + formId + " #newPwd").val('');
	$(".valid").removeClass("valid");
	$(".error").removeClass("error");
	$("label").find("span").remove();
	$('#updatePwdDialog').dialog("close");
}

//四舍五入到分
function toAmount(amount, fixed) {
	var num = Math.round(amount)/100;
	num = String(num.toFixed(fixed));
	var re = /(-?\d+)(\d{3})/;
	while(re.test(num)) num = num.replace(re,"$1,$2");
	return num;
}

//获取yyyy-MM-dd格式日期
function getDate(beforeDay) {
	var date = new Date();
	date.setDate(date.getDate() - beforeDay);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate(); 
	var currentDate = year + "-" + month + "-" + day;
	return currentDate;
}

function getTime(v, format) {
	if(v) return (new Date(v)).Format(format);
	return v;
}

//对Date的扩展，将 Date 转化为指定格式的String    
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，    
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)    
//例子：    
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423    
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18    
Date.prototype.Format = function(fmt)   
{    
var o = {   
 "M+" : this.getMonth()+1,                 //月份    
 "d+" : this.getDate(),                    //日    
 "h+" : this.getHours(),                   //小时    
 "m+" : this.getMinutes(),                 //分    
 "s+" : this.getSeconds(),                 //秒    
 "q+" : Math.floor((this.getMonth()+3)/3), //季度    
 "S"  : this.getMilliseconds()             //毫秒    
};   
if(/(y+)/.test(fmt))   
 fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
for(var k in o)   
 if(new RegExp("("+ k +")").test(fmt))   
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
return fmt;   
}  


function activityTypeFormat(v) {
	if(v == '1') return "亲子活动";
	if(v == '2') return "城市体验";
	return "";
}

function activeFormat(v) {
	if(v == 0) return "注册申请中";
	if(v == 1) return "已激活";
	return "";
}

function effectFormat(v) {
	if(v == 0) return "报名申请中";
	if(v == 1) return "已生效";
	return "";
}

function sexFormat(v) {
	if(v == 'F') return "帅哥";
	if(v == 'M') return "美女";
	return "";
}

function shelvesFormat(v) {
	if(v == 0) return "<span style='color:#f3450f'>待审核</span>";
	if(v == 1) return "已上架";
	if(v == 2) return "<span style='color:#0f4dde'>已下架</span>";
}

function initActivityType() {
	$.ajax({
		url : basePath + "/content/dict/json/dictData/compent",
		type : 'POST',
		data : {
			dictCode : "activity_type"
		},
		error : function() {
			$.messager.progress('close');
			$.messager.alert('系统提示', '操作异常', 'error');
		},
		success : function(data) {
			$(".activityType").each(function(index,item) {
				$(item).append(data);
			});
		}
	});
}