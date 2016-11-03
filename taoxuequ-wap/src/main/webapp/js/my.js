$(function(){
	$("#head_title").find(".active").removeClass("active");
	$("#my").addClass("active");
	$(".listSelect-btn").click(function(){
		var obj=$(this);
		if(obj.hasClass("listSelect-btn2")){
			obj.removeClass("listSelect-btn2");
			$(".videoList-content2").addClass("videoList-content").removeClass("videoList-content2");
		}else{
			obj.addClass("listSelect-btn2");
			$(".videoList-content").addClass("videoList-content2").removeClass("videoList-content");
		}
	})
})
