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
	                <div data-options="region:'west',title:'字典类别',iconCls:'icon-ok'" 
	                  style="width:20%;">
	                  <a href="javascript:void(0)" class="easyui-linkbutton"  plain="true" onclick="addDictType()"><i class="fa fa-plus"></i> 新增</a>
	                  <a href="javascript:void(0)" class="easyui-linkbutton"  plain="true" onclick="editData()"><i class="fa fa-edit"></i> 编辑</a>
	                  <ul id="dataTypeAllMenu" class="nav nav-list"></ul>
	                </div>
	                <div data-options="region:'center'">
	                    <table id="dataGrid" class="easyui-datagrid"></table>
	                    <%--主界面工具栏--%>
	                    <div id="toolbar">
	                        <a href="javascript:void(0)" class="easyui-linkbutton"  plain="true" onclick="addData()"><i class="fa fa-plus"></i> 新增</a>
	                        <a href="javascript:void(0)" class="easyui-linkbutton"  plain="true" onclick="editData()"><i class="fa fa-edit"></i> 编辑</a>
	                    </div>
	                </div>
	            </div>
			</div>
		</div>
	</div>
	<%--新增管理类别对话框--%>
	<div id="addDictType" class="easyui-dialog" style="width: 400px;height:auto;"  closed="true" >
	    <form id="addDictTypeForm" class="easyui-form form-search" >
	    	<input type="hidden" id="id" name="id" />
	        <table class="table" style="margin:0 auto;">
	            <tr>
	                <td class="text-right" width="40%">编码</td>
	                <td width="60%">
	                    <input name="dictCode" id="dictCode" class="input-medium search-query search-comp th25">
	                </td>
	            </tr>
	            <tr>
	                <td class="text-right" >类别名称</td>
	                <td>
	                    <input name="dictTypeName" id="dictTypeName" class="input-medium search-query search-comp th25 ">
	                </td>
	            </tr>
	            <tr>
	                <td class="text-right" >是否启用</td>
	                <td>
	                    <select name="isActive" class="easyui-combobox input-medium search-query search-comp th25">
	                        <option value="1">启用</option>
	                        <option value="0">禁用</option>
	                    </select>
	                </td>
	            </tr>
	        </table>
	    </form>
	
	    <div id="addDictTypeButton" class="text-center pd10">
	        <a href="javascript:void(0)" class="btn btn-success" onclick="submitDictType()"  plain="true">保存</a>
	    </div>
	</div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath%>/js/content/dictManage.js"></script>
</body>
</html>