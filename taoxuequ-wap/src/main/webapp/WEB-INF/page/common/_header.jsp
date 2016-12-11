<%@page import="com.xuequ.cmoc.common.Configuration"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	String imgUrl = Configuration.getInstance().getImgUrl();
	if(StringUtils.isBlank(imgUrl)) {
		Configuration.getInstance().setImgUrl(basePath + "/xuequ/");
	}
%>
<script>
	var basePath = '<%=basePath %>';
	var imgUrl = '${config.imgUrl}';
</script>
<link href="<%=basePath %>/css/mui.min.css" rel="stylesheet" />
<link href="<%=basePath %>/css/mui.picker.css" rel="stylesheet" />
<link href="<%=basePath %>/css/mui.poppicker.css" rel="stylesheet" />
<link  href="<%=basePath %>/css/display.css?v=${config.version}" rel="stylesheet" type="text/css"/>

<link href="//cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">

<script src="<%=basePath %>/js/plugins/mui/zepto.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/js/plugins/mui/mui.js"></script>
<script src="<%=basePath %>/js/plugins/mui/mui.picker.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/js/plugins/mui/mui.poppicker.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/js/jquery.base64.js" type="text/javascript" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/js/plugins/wechat-config.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/js/plugins/handlebars-1.0.0.beta.6.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/js/common.js?v=${config.version}" type="text/javascript" charset="utf-8"></script>

