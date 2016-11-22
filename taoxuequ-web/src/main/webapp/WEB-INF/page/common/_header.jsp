<%
	String basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>

<!-- Bootstrap core CSS -->
<link href="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/css/docs.min.css" rel="stylesheet">
<link href="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/css/bootstrap.css.map" rel="stylesheet">
<link href="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/css/bootstrap-theme.css" rel="stylesheet">
<link href="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/css/fileinput.css" rel="stylesheet">

<link href="//cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/display.css"/>

<!-- easyui css -->
<link rel="stylesheet" href="<%=basePath%>/js/plugin/jquery-easyui-1.4.3/themes/bootstrap/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>/js/plugin/jquery-easyui-1.4.3/themes/icon.css" type="text/css"></link>

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

<!-- 逃学趣 js -->
<script src="<%=basePath%>/js/plugin/jquery.validate.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/plugin/jquery-ui-date.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/plugin/messages_zh.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/js/plugin/My97DatePicker/WdatePicker.js" type="text/javascript" charset="utf-8"></script>

<!--[if lt IE 9]><script src="<%=basePath %>/js/html5.js"></script><![endif]-->
<script src="<%=basePath %>/js/main.js"></script>
<script src="<%=basePath %>/js/common.js"></script>
<script>var basePath = '<%=basePath %>';</script>	