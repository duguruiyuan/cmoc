<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"       
    pageEncoding="UTF-8"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>家长邦</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta name="msapplication-tap-highlight" content="no" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta name="format-detection" content="telephone=no,email=no" />
		
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/style.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/plugins/jPlayer-2.9.2/skin/blue.monday/css/jplayer.blue.monday.min.css" />
		<script src="<%=basePath %>/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath %>/js/adapt.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath %>/js/plugins/jPlayer-2.9.2/jplayer/jquery.jplayer.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>/js/course/detail.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			var type = "${type}";
		</script>
	</head>
	<body>
		<div class="detail-top">
			<a class="prev" href="javascript:volid(0)" onClick="javascript:history.back(-1);"></a>
			我的公开课
		</div>
		<div id="detail-video" class="jp-video jp-video-270p" role="application" aria-label="media player">
			<div class="jp-type-single">
				<div id="jquery_jplayer" class="jp-jplayer"></div>
				<div class="jp-gui">
					<div class="jp-video-play">
						<button class="jp-video-play-icon" role="button" tabindex="0">play</button>
					</div>
					<div class="jp-interface">
						<div class="jp-progress">
							<div class="jp-seek-bar">
								<div class="jp-play-bar"></div>
							</div>
						</div>
						<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
						<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
						<div class="jp-controls-holder">
							<div class="jp-volume-controls">
								<button class="jp-mute" role="button" tabindex="0">mute</button>
								<button class="jp-volume-max" role="button" tabindex="0">max volume</button>
								<div class="jp-volume-bar">
									<div class="jp-volume-bar-value"></div>
								</div>
							</div>
							<div class="jp-controls">
								<button class="jp-play" role="button" tabindex="0">play</button>
								<button class="jp-stop" role="button" tabindex="0">stop</button>
							</div>
							<div class="jp-toggles">
								<button class="jp-repeat" role="button" tabindex="0">repeat</button>
								<button class="jp-full-screen" role="button" tabindex="0">full screen</button>
							</div>
						</div>
						<div class="jp-details">
							<div class="jp-title" aria-label="title">&nbsp;</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<h1 class="detail-title">公开课第34讲：如何教育青春期的孩子</h1>
		<div class="detail-total clearfix">
			<span class="detail-price">￥<em>89.00</em></span>
			<span class="detail-people">2008人已学习</span>
		</div>
		<div class="detail-tag clearfix">
			<span>家长邦认证</span>
			<span>平台交易保证</span>
		</div>
		<div class="jiange"></div>
		<div class="detail-sh">
			<div class="detail-sh-title">能收获什么？</div>
			<div class="detail-sh-intro">
				1.青春期孩子的教育方式  2.青春期孩子的教育方式  3.青春期孩子的教育方式  
			</div>
		</div>
		<div class="jiange"></div>
		<div class="detail-js">
			<div class="detail-js-title">特邀讲师</div>
			<div class="detail-js-line1 clearfix">
				<div class="detail-js-pic fl"><img src="<%=basePath %>/images/pic03.png"></div>
				<div class="detail-js-intro fr">
					国际职业培训师行业协会认证讲师<br>
					国际职业培训师行业协会认证讲师<br>
					国际职业培训师行业协会认证讲师<br>
					国际职业培训师行业协会认证讲师<br>
					国际职业培训师行业协会认证讲师<br>
					国际职业培训师行业协会认证讲师<br>
					国际职业培训师行业协会认证讲师
				</div>
			</div>
			<div class="detail-js-line2">
				郭立群老师从事儿童及青少年教育30年，教育生涯中积累了大量的实际案例与教育经验。08年她走上了心灵成长的道路，先后学习了《PET父母效能训练》、《萨提亚模式家庭治疗》、李中莹老师《跳好双人舞》导师班、《亲密之旅》（师从创始人黄维仁博士）、《绘画与艺术治疗》专业班（、《HMI美式专业催眠师》、沙盘治疗，并把所学运用到亲子关系、夫妻关系中，帮助了大量的家庭，在全国各地传播爱，分享爱。作为广 东狮子会资深会员，用所学知识，帮助服务对象，用生命去感召生命。
			</div>
		</div>
		<div class="jiange"></div>
		<hr>
		<div class="videoList">
			<div class="listHead clearfix">
				<h4>精选课程</h4>
				<a class="listMore">更多课程</a>
			</div>
			<div class="videoList-content">
				<ul class="clearfix">
					<li>
						<div class="videoList-pic">
							<a href="<%=basePath %>/course/detail/1">
								<img src="<%=basePath %>/images/pic02.jpg"/>
								<span class="videoList-tag">免费版</span>
								<span class="videoList-stop"></span>
							</a>
						</div>
						<p class="videoList-title">公开课第34讲：如何教育青春期的孩子</p>
					</li>
					<li>
						<div class="videoList-pic">
							<a href="<%=basePath %>/course/detail/1">
								<img src="<%=basePath %>/images/pic02.jpg"/>
								<span class="videoList-tag">免费版</span>
								<span class="videoList-stop"></span>
							</a>
						</div>
						<p class="videoList-title">公开课第34讲：如何教育青春期的孩子</p>
					</li>
					<li>
						<div class="videoList-pic">
							<a href="<%=basePath %>/course/detail/1">
								<img src="<%=basePath %>/images/pic02.jpg"/>
								<span class="videoList-tag">免费版</span>
								<span class="videoList-stop"></span>
							</a>
						</div>
						<p class="videoList-title">公开课第34讲：如何教育青春期的孩子</p>
					</li>
				</ul>
			</div>
		</div>
		<hr>
		<div class="jiange"></div>
		<div class="jiange"></div>
		<div class="buy">购买本次课程</div>
		<div class="buy-dialog none">
		<div class="buy-dialog-inner">
			<div class="buy-dialog-line1">
				购买课程
				<span class="buy-dialog-close"></span>
			</div>
			<div class="buy-dialog-line2">
				<p>公开课第34讲：如何教育青春期的孩子</p>
				<p class="buy-dialog-price">￥<span>28.00</span></p>
			</div>
			<div class="buy-dialog-line3">
				<p>规格</p>
				<p>公开课第34讲：如何教育青春期的孩子</p>
				<p>购买会员免费观看或收听</p>
			</div>
			<div class="buySure">确认付款</div>
		</div>
		</div>
	</body>
</html>
