<%
	String basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
<!-- Bootstrap core CSS -->
<link href="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/css/docs.min.css" rel="stylesheet">
<link href="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/css/bootstrap.css.map" rel="stylesheet">
<link href="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/css/bootstrap-theme.css" rel="stylesheet">
<link href="<%=basePath%>/js/plugin/bootstrap-3.3.5-dist/css/fileinput.css" rel="stylesheet">

<link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/display.css"/>

<!-- easyui css -->
<link rel="stylesheet" href="<%=basePath%>/js/plugin/jquery-easyui-1.4.3/themes/bootstrap/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>/js/plugin/jquery-easyui-1.4.3/themes/icon.css" type="text/css"></link>

