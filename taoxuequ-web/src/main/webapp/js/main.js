var menu_order='';
var idChanlParam='';
var headMenuObj;
$(function(){
	init();
})
window.onload=function(){
	windowSize();
}
function init(){
	leftSlide();
	leftBtn();
	//windowSize();
	window.tabScroll=new tabBarScroll();
}

//左侧下拉效果
function leftSlide(){
	$(".menuLev1").click(function(){
		var obj=$(this);
		var nextobj=obj.next();
		var objLi=obj.closest("li");
		var objSiblings=objLi.siblings().find(".menuLev2Wrap");
		
		nextobj.slideToggle("slow");
		if(objSiblings.is(":visible")){
			objSiblings.slideUp("slow");
		}
	})
	
	$(".menuLev2Wrap ul li:last-child").css({"border-bottom":"0"});
}

//左侧伸缩按钮
function leftBtn(){
	var siderBtn=$("#siderBtn");
	
	siderBtn.click(function(){
		var obj=$(this);
		if(!obj.hasClass("siderBtnOn")){
			$("#sider").hide();
			obj.css({"left":"0px"});
			obj.addClass("siderBtnOn");
			$("#mainWrap").css({"margin-left":0});
			$("body").removeClass("image");
		}else{
			$("#sider").show();
			obj.css({"left":"211px"});
			obj.removeClass("siderBtnOn");
			$("#mainWrap").css({"margin-left":"211px"});
			$("body").addClass("image");
		}
	})
	
}

//处理高度和宽度
function layoutSize(){
	var sider=$("#sider");
	
	var size=(function(){
		var winOjb=$(window);
		var topH=109;
		return {w:winOjb.width(),h:winOjb.height()-topH,topH:topH};
	})();
	
	$("#siderBtn").css({"top":(size.h/2 + size.topH)+"px"}).show();
	
	(function(){
		var wrapH=$("#wrap").outerHeight();
		var siderH=sider.outerHeight();
		var h=siderH>wrapH?siderH:wrapH;
		h=h>size.h?h:size.h;
		sider.css({"min-height":h});
	})();
}

//窗口改变
function windowSize(){
	$(window).resize(function(){
		layoutSize();
	}).trigger("resize");
}

//同步iframe高度
function iFrameHeight(ifmID) { 
	var pTar = null;
	if (document.getElementById){
		pTar = document.getElementById(ifmID);
	}
	else{
		eval('pTar = ' + ifmID + ';');
	}
	if (pTar && !window.opera){
		//begin resizing iframe
		pTar.style.display="block"
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){
			//ns6 syntax
			pTar.height = pTar.contentDocument.body.offsetHeight +20;
			//pTar.width = pTar.contentDocument.body.scrollWidth+20;
		}
		else if (pTar.Document && pTar.Document.body.scrollHeight){
			//ie5+ syntax
			pTar.height = pTar.Document.body.scrollHeight;
			//pTar.width = pTar.Document.body.scrollWidth;
		}
	}
}

//点击菜单，中间内容切换
function mainShow(title,src,order,height,isSystem){
	var tabBar=$("#tabBar");
	var tabBarUl=tabBar.find("ul");
	var main=$("#main");
	var tabItem=$("#tabItem-"+order);
	var mainItem=$("#main-"+order);
	
	$("nav li[data-order="+order+"]").addClass("navOn").siblings().removeClass("navOn");
	if(tabItem.length==0){
		//处理tabBar
		(function(tabBarUl,title,order){
			tabBarUl.find(".tabItem").removeClass("tabOn");
			tabBarUl.append(getTabLi(title,order,isSystem));
		})(tabBarUl,title,order);
		
		//处理main
		(function(main){
			main.find(".webContain").addClass("none");
			main.append(getIframe(src,order,height));
		})(main);
	}else{
		tabItem.addClass("tabOn").siblings().removeClass("tabOn");
		mainItem.removeClass("none").siblings().addClass("none");
	}
	$("nav").find("li").removeClass("navOn").filter("[data-order="+order+"]").addClass("navOn");
	menu_order=order;
	addNav(isSystem);
	tabScroll.judge();
}
function tabItem(order,obj,isSystem){
	var obj=$(obj);
	var mainThis=$("#main-"+order);
	
	$("nav").find("li").removeClass("navOn").filter("[data-order="+order+"]").addClass("navOn");
	obj.addClass("tabOn").siblings().removeClass("tabOn");
	mainThis.removeClass("none").siblings().addClass("none");
	if(obj.attr("first-page") != 'Y') {
		addNav(isSystem);
	}
}
function tabItemClose(order,obj,isSystem){
	var e = arguments.callee.caller.arguments[0] || window.event; 
	if(window.event){
		window.event.cancelBubble = true;
	}else{
		e.stopPropagation();
	}
	
	var obj=$(obj).closest(".tabItem");
	var mainThis=$("#main-"+order);
	//var navObj=$("nav li[data-order="+order+"]");
	
	var objPrev=obj.prev();
	var mainThisPrev=mainThis.prev();
	
	if(obj.hasClass("tabOn")){
		objPrev.addClass("tabOn");
		mainThisPrev.removeClass("none");
		
		$("nav li[data-order="+objPrev.attr("data-order")+"]").addClass("navOn").siblings().removeClass("navOn");
		if(objPrev.attr("first-page") != 'Y') {
			addNav(objPrev.attr("is-system"));
		}
	}
	obj.remove();
	mainThis.remove();
	return false;
	
}
function getTabLi(title,order,isSystem){
	return '<li class="tabItem tabOn" id="tabItem-'+order+'" data-order="'+order+'" is-system="'+isSystem+'" onclick="tabItem(\''+order+'\',this,\''+isSystem+'\')"><div class="tabCenter"><div class="tabRight"><div class="tabLeft"><span class="tab-close" onclick="tabItemClose(\''+order+'\',this,\''+isSystem+'\')">x</span><span class="tab-ico"></span> <span class="tab-text">' + title + '</span></div></div></div></li>';
}
function getIframe(src,order,height){
	var arr=['intoCussWorkOrderAdd.do'];
	var leng=arr.length;
	var s="";
	for(var i=0;i<leng;i++){
		if(src.indexOf(arr[i])>-1){
			s='onload="iFrameHeight(\'iframe-'+order+'\')"';
			break;
		}
	}
	var iframeObj='<div class="webContain main-'+order+'" id="main-'+order+'"><iframe id="iframe-'+order+'" '+s+' src="'+src+'" width="100%" height="'+height+'" frameborder="0" scrolling="no"></iframe></div>';
	return iframeObj;
}
function navShow(title,src,order,obj,height,isSystem){
	$(obj).addClass("navOn").siblings().removeClass("navOn");
	mainShow(title,src,order,height,isSystem);
	channelShow(isSystem,obj);
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

function channelShow(isSystem, obj,idChannel) {
	headMenuObj=obj;
	idChanlParam=idChannel;
	$(obj).addClass("navOn").siblings().removeClass("navOn");	
	menuShow(isSystem);
};

function menuShow(isSystem) {
	var list = eval('(' + menuList + ')'); 
	if(list != null) {
		var siderMenu = '';
		for(var i = 0; i < list.length; i++) {
			var parent = list[i];
			if(parent.isSystemConfMenu == isSystem) {
				siderMenu += '<li class="menuItem menu-01">';
				siderMenu += '<dl class="menuItemInner clearfix">';
				siderMenu += '<dt class="menuLev1"><div class="menuLev1Inner">' + parent.resourceName + '</div></dt>';
				if(parent.hasChild) {
					siderMenu += '<dd class="menuLev2Wrap"><ul class="clearfix">';
					var subMenuList = parent.subMenuList;
					for(var j = 0; j < subMenuList.length; j++) {
						var sub = subMenuList[j];
						if(sub.isSystemConfMenu == isSystem) {
							siderMenu += '<li class="menuLev2 menu-01-0' + j + '" onclick="mainShow(\'' + sub.resourceName + '\',\'' + 
							basePath + sub.resourceUrl + '?idChannel=' + idChanlParam +'\',\'' + sub.parentResourceId + '-' + 
							sub.idResource + '\',780,\'' + sub.isSystemConfMenu + '\')"><div class="menuLev2Inner">'+ sub.resourceName + '</div></li>';
						}
					}
					siderMenu += '</ul></dd>';
				}
				siderMenu += '</dl></li>';
			}
		}
		$("#siderMenu").html(siderMenu);
		leftSlide();
	}
}

function addNav(isSystem) {
	var val = "sysConf";
	if(isSystem == 'N') {
		val = "channelConf";
	}
	$("#" + val).addClass("navOn").siblings().removeClass("navOn");
	$(headMenuObj).addClass("navOn").siblings().removeClass("navOn");
}

//tab滚动
function tabBarScroll(){
	this.init();
}
tabBarScroll.prototype={
	liWidth:145,
	init:function(){
		this.getElem();
		this.attachEvent();
	},
	getElem:function(){
		this.tabBar=$("#tabBar");
		this.tabBarUl=this.tabBar.find("ul");
		this.leftBtn=$("#tabBar-leftBtn");
		this.rightBtn=$("#tabBar-rightBtn");
	},
	attachEvent:function(){
		this.leftClick();
		this.rightClick();
		this.resizeWindow();
	},
	leftClick:function(){
		var that=this;
		this.leftBtn.click(function(){
			that.tabBarUl.animate({left:"+="+that.liWidth+"px"},100,function(){
				that.judge();
			});
		})
	},
	rightClick:function(){
		var that=this;
		this.rightBtn.click(function(){
			that.tabBarUl.animate({left:"-="+that.liWidth+"px"},100,function(){
				that.judge();
			});
		})
	},
	resizeWindow:function(){
		var that=this;
		$(window).resize(function(){
			that.judge();
		})
	},
	judge:function(){
		var allWidth=this.getAllWidth();
		var ulWidth=this.getUlWidth();
		if(allWidth<ulWidth){
			var ulLeft=this.getUlLeft();
			if(ulLeft<0){
				this.leftBtn.show();
			}else{
				this.leftBtn.hide();
			}
			
			if(Math.abs(ulLeft)+allWidth<ulWidth){
				this.rightBtn.show();
			}else{
				this.rightBtn.hide();
			}
		}else{
			this.leftBtn.hide();
			this.rightBtn.hide();
		}
	},
	getAllWidth:function(){
		return this.tabBar.width();
	},
	getNum:function(){
		var num=this.tabBar.find("li").size();
		return num;
	},
	getUlWidth:function(){
		return this.liWidth*this.getNum();
	},
	getUlLeft:function(){
		return this.left(this.tabBarUl.css("left"));
	},
	left:function(str){
		return str.substr(0,str.length-2);
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




















