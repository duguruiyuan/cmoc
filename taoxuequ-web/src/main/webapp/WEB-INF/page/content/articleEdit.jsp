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
		    <div class="col-md-12">
		    	<div class="col-md-4">
					<button type="button" style="height: 28px;" class="btn btn-default" onclick="submit()">保存</button>
				</div>
		    </div>
			<div class="col-md-12" id="content_div">
				<input type="hidden" id="id" name="id">
   				<script id="editor" type="text/plain" style="width: 414px;height: 600px;">
						 
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
        function submit() {
        	$.ajax({
         		url : basePath + "/ueditor/course/edit",
         		type : "post",
         		data : {
         			content : ue.getContent()
         		},
         		error : function() {
					$.messager.progress('close');
					$.messager.alert('系统提示', '操作异常', 'error');
				},
				success : function(data) {
					$.messager.progress('close');
					if (data.code == '000') {
						$.messager.alert('系统提示', $("#id").val() == '' ? '战队信息新增成功' : '战队信息修改成功', 'info');
					} else {
						$.messager.alert('系统提示', $("#id").val() == '' ? '战队信息新增失败' : '战队信息修改失败', 'warning');
					}
				}
         	});
        }
    </script>
</body>
</html>