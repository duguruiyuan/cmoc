function activityTypeFormat(v) {
	if(v == '1') return "亲子活动";
	if(v == '2') return "城市体验";
	return "";
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

function getAccessToken(wechat_redirect) {
	var snsTokenExpire = window.localStorage.getItem("snsTokenExpire");	
	var snsToken = JSON.parse(window.localStorage.getItem("snsToken"));
	var currUrl = window.location.href;
	var start = true;
	if(snsToken != null && snsTokenExpire != null) {
		if(snsTokenExpire > new Date().getTime()) {
			start = false;
			window.location.href = currUrl + "?type=scope&token=" + snsToken.refresh_token;
		}
	}
	if(start){
		currUrl = encodeURIComponent(currUrl);
		currUrl = wechat_redirect.replace('{1}', currUrl);
		window.location.href = currUrl;
	}
}

function setAccessToken(snsToken) {
	window.localStorage.setItem("snsTokenExpire", addDate(new Date(), 7).getTime());
	window.localStorage.setItem("snsToken",JSON.stringify(snsToken));
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