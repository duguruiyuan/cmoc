$(function(){
	$(".buy").click(function(){
		$(this).hide();
		$(".buy-dialog").show();
	});
	$(".buy-dialog-close").click(function(){
		$(".buy").show();
		$(".buy-dialog").hide();
	})
	play();
})

function play() {
	$("#jquery_jplayer").jPlayer({
		ready: function () {
			if(type == "1") {
				$(this).jPlayer("setMedia", {
					title: "好好学习，天天向上",
					m4v: "http://120.77.54.247:8020/filextra/ab.mp4",
					poster: "http://www.jplayer.org/video/poster/Finding_Nemo_Teaser_640x352.png"
				});
			}else {
				$(this).jPlayer("setMedia", {
					title: "好好学习，天天向上",
					mp3: "http://120.77.54.247:8020/filextra/aa.mp3",
					poster: "http://www.jplayer.org/video/poster/Finding_Nemo_Teaser_640x352.png"
				});
			}
		},
		play: function() { // To avoid multiple jPlayers playing together.
			$(this).jPlayer("pauseOthers");
		},
		swfPath: "<%=basePath %>/jplayer",
		supplied: "webmv, ogv, m4v, mp3",
		cssSelectorAncestor: "#detail-video",
		globalVolume: true,
		useStateClassSkin: true,
		autoBlur: false,
		smoothPlayBar: true,
		keyEnabled: true
	});
}
