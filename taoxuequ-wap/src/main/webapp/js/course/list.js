$(function(){
	$("#head_title").find(".active").removeClass("active");
	$("#courseList").addClass("active");
	$(".listSelect-btn").click(function(){
		$(".listSelect-line2").toggle();
	})
})
