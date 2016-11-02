$(function(){
	init();
});
function init(){
	jiesuanTab();
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
		pTar.style.display="block";
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

//结算tab选择
function jiesuanTab(){
	var jsTab=$(".jsTabWrap li");
	var jsContain=$(".jsContain");
	
	jsTab.click(function(){
		var obj=$(this);
		obj.addClass("jsTabOn").siblings().removeClass("jsTabOn");
		var index=jsTab.index($(this));
		jsContain.eq(index).show().siblings().hide();
	});
}








