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
<script src="<%=basePath %>/js/plugin/jquery-2.1.4.min.js"></script>
<script src="<%=basePath %>/js/plugin/extJquery.js"></script>
<!-- bootstrap js -->
<script src="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/js/fileinput.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/js/zh.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/js/ie-emulation-modes-warning.js" type="text/javascript" charset="utf-8"></script>

<!-- easyui js -->
<script src="<%=basePath%>/js/plugin/jquery-easyui-1.4.3/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/plugin/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>

<script src="<%=basePath%>/js/plugin/particleground/jquery.particleground.min.js" type="text/javascript" charset="utf-8"></script>

<!-- 逃学趣 js -->
<script src="<%=basePath%>/js/plugin/jquery.validate.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/plugin/jquery-ui-date.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/plugin/messages_zh.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/plugin/My97DatePicker/WdatePicker.js" type="text/javascript" charset="utf-8"></script>

<!--[if lt IE 9]><script src="<%=basePath %>/js/html5.js"></script><![endif]-->
<script src="<%=basePath %>/js/main.js"></script>
<script src="<%=basePath %>/js/common.js"></script>

<script type="text/javascript" src="<%=basePath %>/js/plugin/localResizeIMG/dist/lrz.bundle.js" ></script>
