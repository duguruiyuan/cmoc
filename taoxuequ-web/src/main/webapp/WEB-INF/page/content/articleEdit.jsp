<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<jsp:include page="/WEB-INF/page/common/_header.jsp"></jsp:include>
<link href="<%=basePath%>/js/plugin/wangEditor-2.1.22/dist/css/wangEditor.css" rel="stylesheet">
<body>
	<jsp:include page="/WEB-INF/page/common/header.jsp"></jsp:include>
	<div id="middle">
		<jsp:include page="/WEB-INF/page/common/menu.jsp"></jsp:include>
		<div id="right">
		    <jsp:include page="/WEB-INF/page/common/localinfo.jsp" />
			<div class="col-md-12" id="content_div">
   				<script id="editor" type="text/plain" style="width: 414px;height: 600px;">
						 这里编辑内容
				</script>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/plugin/Ueditor-1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/plugin/Ueditor-1.4.3.3/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/plugin/Ueditor-1.4.3.3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
        var ue = UE.getEditor('editor');
    </script>
</body>
</html>