initAllDictData(false);

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
