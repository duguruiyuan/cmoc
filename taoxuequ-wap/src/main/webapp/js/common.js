initAllDictData(false);

function getShareUrl(channel) {
	return window.location.href + "?from=" + channel;
}

function initRelation() {
	$.ajax({
		url : basePath + "/dict/json/dictData/compent",
		type : 'POST',
		data : {
			dictCode : "relation"
		},
		success : function(data) {
			$(".relation").append(data);
		}
	});
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

function initAllDictData(reload) {
	if(window.localStorage.getItem("dictDataMap") != null && !reload) return;
	$.ajax({
		url : basePath + "/dict/json/init/dictData",
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

function getTime(v, format) {
	if(v) return (new Date(v)).Format(format);
	return v;
}

function addDate(date,day){
	var a = new Date(date)
	a = a.valueOf()
	a = a + day * 24 * 60 * 60 * 1000
	a = new Date(a)
	return a;
}

function setAccessToken(snsToken) {
	if(snsToken) {
		//未关注用户授权有效期7天
		window.localStorage.setItem("snsTokenExpire", addDate(new Date(), 7).getTime());
		window.localStorage.setItem("snsToken",snsToken);
	}
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

/**
 * 正数校验
 */
var positiveValidate = function(e) {
	if (!isNaN(e)) {
		if(e.trim()==""){
			return false;
		}
		if(e.trim() == 0) return true;
		if (e.indexOf('0') == 0) {
			if (e.indexOf('.') == 1) {
				return true;
			} else {
				return false
			}
		} else {
			return true;
		}
	} else {
		return false;
	}
};

/**
 * 百分数校验
 */
var percentNumValidate = function(e) {
	if (!isNaN(e)) {
		if(e.trim()==""){
			return false;
		}
		if (parseFloat(e)>100||parseFloat(e)<0){
			return false;
		}
		if (e.indexOf('0') == 0) {
			if (e.indexOf('.') == 1) {
				return true;
			} else {
				return false
			}
		} else {
			return true;
		}
	} else {
		return false;
	}
};

/**
 * 去掉字符中所有空格
 */
var excludeSpace = function(e) {
	return e.replace(/(^\s*)|(\s*$)/g, '');
};

//验证姓名
function checkName(name) {
	if(!/^[\u2E80-\u9FFF]{2}/.test(name)){
		return false;
	}
	return true;
}

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
	              ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/; //闰年出生日期的合法性正则表达式
	          }
	          else {
	              ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/; //平年出生日期的合法性正则表达式
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

function closeCode() {
	$(".code").remove();
}

function groupPay() {
	var str = '<div class="code">\
		<div class="payGrid">\
			<div class="payGrid-title">报名支付方式</div>\
			<div class="payGrid-content">\
				<img src="'+ basePath +'/images/wechat-customer.png"/>\
				<p>长按二维码识别，联系客服人员进行支付</p>\
				<a href="tel:18027274621"><span>客服热线：18027274621</span></a>\
			</div>\
			<div class="payGrid-btn" onclick="closeCode()">关闭</div>\
		</div>\
	</div>';
	$("body").append(str);
};