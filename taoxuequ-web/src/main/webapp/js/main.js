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






















