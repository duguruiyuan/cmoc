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
	});
	initAllDictData(false);
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
							pwdCloseFormPanel("updatePwdForm");
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

function pwdCloseFormPanel(formId){
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


function dictDataFormat(code, v) {
	if(v) {
		var dictDataMap = JSON.parse(window.localStorage.getItem("dictDataMap"));
		var list = dictDataMap[code];
		if(!list || (list && !list[v])) {
			initAllDictData(true);
			dictDataMap = JSON.parse(window.localStorage.getItem("dictDataMap"));
			return dictDataMap[code][v];
		}
		return list[v];
	}
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

function sex1Format(v) {
	if(v == 'F') return "男";
	if(v == 'M') return "女";
	return "";
}

function diseaseFormat(v) {
	if(v == 'N') return "无";
	if(v == 'Y') return "有";
	return "";
}

function showedFormat(v) {
	if(v == null) return "";
	if(v == 1) return "差";
	if(v == 2) return "良";
	if(v == 3) return "优";
	return "";
}

function signResourceFormat(v) {
	if(v == 'ONLINE') return "线上报名";
	if(v == 'SCHOOL') return "学校报名";
	return "";
}

function signWayFormat(v) {
	if(v == 0) return "单人";
	if(v == 1) return "组队";
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

function initCourseType() {
	$.ajax({
		url : basePath + "/content/dict/json/dictData/compent",
		type : 'POST',
		data : {
			dictCode : "course_type"
		},
		error : function() {
			$.messager.progress('close');
			$.messager.alert('系统提示', '操作异常', 'error');
		},
		success : function(data) {
			$(".courseType").each(function(index,item) {
				$(item).append(data);
			});
		}
	});
}

function initCity() {
	$.ajax({
		url : basePath + "/content/dict/json/dictData/compent",
		type : 'POST',
		data : {
			dictCode : "city"
		},
		error : function() {
			$.messager.progress('close');
			$.messager.alert('系统提示', '操作异常', 'error');
		},
		success : function(data) {
			$(".city").each(function(index,item) {
				$(item).append(data);
			});
		}
	});
}

function initCourse() {
	$.ajax({
		url : basePath + "/course/json/course/compent",
		type : 'POST',
		error : function() {
			$.messager.progress('close');
			$.messager.alert('系统提示', '操作异常', 'error');
		},
		success : function(data) {
			$(".course").each(function(index,item) {
				$(item).append(data);
			});
		}
	});
}
function initActivity(courseId) {
	$.ajax({
		url : basePath + "/activity/json/activity/compent",
		type : 'POST',
		data: {
			courseId: courseId
		},
		error : function() {
			$.messager.progress('close');
			$.messager.alert('系统提示', '操作异常', 'error');
		},
		success : function(data) {
			$(".actCompent").each(function(index,item) {
				$(item).html(data);
			});
		}
	});
}

function initAllDictData(reload) {
	if(window.localStorage.getItem("dictDataMap") != null && !reload) return;
	$.ajax({
		url : basePath + "/content/dict/json/init/dictData",
		type : 'POST',
		error : function() {
			$.messager.progress('close');
			$.messager.alert('系统提示', '操作异常', 'error');
		},
		success : function(data) {
			window.localStorage.setItem("dictDataMap", JSON.stringify(data));
		}
	});
}

/**
 * 模版下载操作
 */
function downloadTemplate(fileName){
	window.location.href= basePath + "/common/upload?fileName=" + encodeURI(encodeURI(fileName));
}

jQuery.validator.addMethod("isPhone", function(value, element) {   
    return this.optional(element) || (checkPhone(value));
}, "请正确填写11手机号码");

jQuery.validator.addMethod("isIdcard", function(value, element) {   
    return this.optional(element) || (idCardNo(value));
}, "请正确填写身份证");

//验证手机号
function checkPhone(phone) {
	if(!/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(phone)){
		return false;
	}
	return true;
}


function idCardNo(value){
  //验证身份证号方法
  var area = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "xinjiang", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外" }
  var idcard, Y, JYM;
  var idcard=value;
  var S, M;
  var idcard_array = new Array();
  idcard_array = idcard.split("");
  if (area[parseInt(idcard.substr(0, 2))] == null) return false;
  switch (idcard.length) {
      case 15:
          if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
              ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/; //测试出生日期的合法性
          }
          else {
              ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/; //测试出生日期的合法性
          }
          if (ereg.test(idcard))
              //return Errors[0];
            var res = true;
          else
              //return Errors[2];
            var res = false;
          return res;
          break;
      case 18:
          if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
              ereg = /^[1-9][0-9]{5}[1-9][0-9]{3}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/; //闰年出生日期的合法性正则表达式
          }
          else {
              ereg = /^[1-9][0-9]{5}[1-9][0-9]{3}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/; //平年出生日期的合法性正则表达式
          }
          if (ereg.test(idcard)) {
              S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 + parseInt(idcard_array[7]) * 1 + parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9]) * 3;
              Y = S % 11;
              M = "F";
              JYM = "10X98765432";
              M = JYM.substr(Y, 1);
              if (M == idcard_array[17])
                  //return Errors[0];
                var res = true;
              else
                  //return Errors[3];
                var res = false;
          }
          else
              //return Errors[2];
            res = false;
          return res;
          break;
      default:
          res = false;
          return res;
          break;
    };
}
