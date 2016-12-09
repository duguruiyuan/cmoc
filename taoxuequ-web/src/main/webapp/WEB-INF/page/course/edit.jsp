<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
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
		    <div class="col-md-12">
		    	<div class="col-md-6 p10">
				  <form id="addForm" novalidate="novalidate">
						<input type="hidden" id="id" name="id">
						<div class="form-group col-md-6">
							<label for="activityName">活动名称</label>								
							<input class="form-control" name="activityName" id="activityName">
						</div>
						<div class="form-group col-md-6">
							<label for="activityNum">活动期数</label>								
							<input class="form-control" name="activityNum" id="activityNum">
						</div>
						<div class="form-group col-md-6">
							<label for="activityType">活动类型</label>								
							<select class="form-control" name="activityType" id="activityType">
								<option value="">请选择</option>
								<option value="1">亲子活动</option>	
								<option value="2">城市体验</option>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="activityPeoples">活动发布人数</label>								
							<input class="form-control" name="activityPeoples" id="activityPeoples">
						</div>
						<div class="form-group col-md-6">
							<label for="city">活动城市</label>								
							<input class="form-control" name="city" id="city">
						</div>
						<div class="form-group col-md-6">
							<label for="strStartDate">活动开始时间</label>								
							<input class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'strEndDate\')}',readOnly:true})" name="strStartDate" id="strStartDate">
						</div>
						<div class="form-group col-md-6">
							<label for="strEndDate">活动结束时间</label>								
							<input class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'strStartDate\')}',readOnly:true})" name="strEndDate" id="strEndDate">
						</div>
						<div class="form-group col-md-6">
							<label for="activityAddr">活动地址</label>								
							<input class="form-control" name="activityAddr" id="activityAddr">
						</div>
						<div class="form-group col-md-6">
							<label for="activityDesc">活动描述</label>	
							<textarea class="form-control" name="activityDesc" id="activityDesc" rows="3"></textarea>							
						</div>
						<div class="form-group col-md-6">
							<label for="activityDesc">活动图片</label>	
							<input id="activity1-img-upload" name="files" type="file" accept=".png,.jpg,.gif" multiple class="file" data-overwrite-initial="false" data-max-file-count="1">
						</div>
						<div class="form-group col-md-12 text-right">
							<button type="submit" class="btn btn-success">保存</button>
							<button class="btn btn-info" type="button"
								onclick="closeFormPanel('addForm')">取消</button>
						</div>
					</form>
				</div>
				<div class="col-md-6">
					<input type="hidden" id="id" name="id">
	   				<script id="editor" type="text/plain" style="width: 414px;height: 600px;">
					
					</script>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/plugin/Ueditor-1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/plugin/Ueditor-1.4.3.3/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/plugin/Ueditor-1.4.3.3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
        var ue = UE.getEditor('editor', {
            toolbars: [
                       [
						 'bold', //加粗
						 'italic', //斜体
						 'underline', //下划线
						 'fontborder', //字符边框
						 'strikethrough', //删除线
						 'superscript', //字符边框
						 'subscript', //下标
						 'removeformat', //清除格式
						 'formatmatch', //格式刷
						 'autotypeset', //自动排版
						 'blockquote', //引用
						 'forecolor', //字体颜色
						 'backcolor', //背景色
						 'background', //背景
						 'insertorderedlist', //有序列表
						 'insertunorderedlist', //无序列表
						 'justifyleft', //居左对齐
					     'justifyright', //居右对齐
					     'justifycenter', //居中对齐
					     'justifyjustify', //两端对齐
					     'rowspacingtop', //段前距
					     'rowspacingbottom', //段后距
					     'lineheight', //行间距
					     'paragraph', //段落格式
					     'fontfamily', //字体
					     'fontsize', //字号
					     'inserttable', //插入表格
					     'link', //超链接
					     'emotion', //表情
					     'spechars', //特殊字符
					     'map', //Baidu地图
					     'insertvideo', //视频
					     'simpleupload', //单图上传
					     'insertimage', //多图上传
					     
					   ]
                   ],
                   autoHeightEnabled: true,
                   autoFloatEnabled: true
               });
        ue.setContent("${course.courseDetails}")
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