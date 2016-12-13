<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<jsp:include page="/WEB-INF/page/common/_header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/page/common/header.jsp"></jsp:include>
	<div id="middle">
		<jsp:include page="/WEB-INF/page/common/menu.jsp"></jsp:include>
		<div id="right">
		    <jsp:include page="/WEB-INF/page/common/localinfo.jsp" />
			<div class="col-md-12" id="content_div">
				<div class="easyui-layout" style="width:100%;height:95%;">
	                <div data-options="region:'west',title:'位置',iconCls:'icon-ok'" 
	                  style="width:20%;">
	                  <ul id="dataTypeAllMenu"></ul>
	                </div>
	                <div data-options="region:'center'">
	                    <table id="dataGrid" class="easyui-datagrid"></table>
	                    <%--主界面工具栏--%>
	                    <div id="toolbar">
	                        <a href="javascript:void(0)" class="easyui-linkbutton"  plain="true" onclick="addImgGroup()"><i class="fa fa-plus"></i> 新增</a>
	                    </div>
	                </div>
	            </div>
			</div>
		</div>
	</div>
	
	<%--新增管理类别对话框--%>
	<div id="addImgGroup" style="display: none;height: 'auto';">
	    <div class="col-md-12 p10">
   			<form id="addImgGroupForm" novalidate="novalidate">
				<input type="hidden" id="id" name="id">
				<input type="hidden" id="position" name="position">
				<div class="form-group col-md-6 regMT-headPic">
					<label for="courseImg">图片</label>	
					<div class="pr">
						<input type="hidden" id="imgUrl" name="imgUrl"/>
						<img src="<%=basePath %>/images/m-taoxuequ.jpg" id="regMT-uploadPic" data-state="no">
						<input type="file" name="regMT-uploadInput" id="regMT-uploadInput" accept="image/png,image/jpeg" value="">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label for="name">标题描述</label>								
					<input class="form-control" name="title" id="title">
				</div>
				<div class="form-group col-md-6">
					<label for="courseType">链接地址</label>								
					<input class="form-control" name="linkUrl" id="linkUrl">
				</div>
				<div class="form-group col-md-6">
					<label for="city">城市</label>								
					<select class="form-control city" name="city" id="city">
						<option value="">请选择</option>
					</select>
				</div>
				<div class="form-group col-md-6">
				</div>
				<div class="form-group col-md-6">
					<label for="addr">是否启用</label>								
                    <select name="shelves" id="shelves" class="form-control">
                        <option value="1" selected>启用</option>
                        <option value="0">禁用</option>
                    </select>
				</div>
			</form>
		</div>
	    <div class="text-center pd10">
	        <a href="javascript:void(0)" class="btn btn-success" onclick="commitImgGroup()"  plain="true">保存</a>
	    </div>
	</div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath%>/js/content/imgGroup.js"></script>
</body>
</html>