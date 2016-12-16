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
							<label for="name">课程名称</label>								
							<input class="form-control" name="courseName" id="courseName">
						</div>
						<div class="form-group col-md-6">
							<label for="courseType">课程类型</label>								
							<select class="form-control courseType" name="courseType" id="courseType">
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="city">城市</label>								
							<select class="form-control city" name="city" id="city">
								<option value="">请选择</option>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="addr">地址</label>								
							<input class="form-control" name="addr" id="addr">
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
							<label for="activityAddr">总价</label>								
							<input class="form-control" name="totalPrice" id="totalPrice">
						</div>
						<div class="form-group col-md-6">
							<label for="activityAddr">优惠价</label>								
							<input class="form-control" name="activityPrice" id="activityPrice">
						</div>
						<div class="form-group col-md-6">
							<label for="activityAddr">预约金</label>								
							<input class="form-control" name="resAmount" id="resAmount">
						</div>
						<div class="form-group col-md-6">
							<label for="activityAddr">课程最小开课人数</label>								
							<input class="form-control" name="coursePeoples" id="coursePeoples">
						</div>
						<div class="form-group col-md-6">
							<label for="activityDesc">课程简述</label>	
							<textarea class="form-control" name="courseDesc" id="courseDesc" rows="3"></textarea>							
						</div>
						<div class="form-group col-md-6 regMT-headPic">
							<label for="courseImg">课程主图</label>	
							<div class="pr">
								<input type="hidden" id="courseImg" name="courseImg"/>
								<img src="<%=basePath %>/images/m-taoxuequ.jpg" id="regMT-uploadPic" data-state="no">
								<input type="file" name="regMT-uploadInput" id="regMT-uploadInput" accept="image/png,image/jpeg" value="">
							</div>
						</div>
						<div class="form-group col-md-12 text-left">
							<button type="submit" class="btn btn-success">保存</button>
							<button class="btn btn-info" type="button"
								onclick="cleanFormPanel('addForm')">重置</button>
							<button class="btn btn-info" type="button"
								onclick="returnBack()">返回</button>
						</div>
					</form>
				</div>
				<div class="col-md-6">
					<label for="activityName">课程详情</label>		
	   				<script id="editor" type="text/plain" style="width: 414px;height: 500px;">
					
					</script>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/page/common/_footer.jsp"/>
	<script type="text/javascript" src="<%=basePath %>/js/jquery.base64.js" ></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/plugin/Ueditor-1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/plugin/Ueditor-1.4.3.3/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/plugin/Ueditor-1.4.3.3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
		var ue;
		$(function(){
			uploadPic();
	        ue = getUeditor();
	        initData();
	        initCity();
	        initCourseType();
	        validator();
		})
		function initData() {
			var courseId = '${courseId}';
			if(courseId.length == 0) return;
			$.ajax({
         		url : basePath + "/course/json/detail/query",
         		type : "post",
         		data : {
         			courseId: parseInt(courseId),
         		},
         		error : function() {
					$.messager.progress('close');
					$.messager.alert('系统提示', '操作异常', 'error');
				},
				success : function(data) {
					$("#id").val(data.id);
					$("#courseName").val(data.courseName);
					$("#courseNum").val(data.courseNum);
					$("#courseType").val(data.courseType);
					$("#city").val(data.city);
					$("#addr").val(data.addr);
					$("#strStartDate").val(getTime(data.startDate, "yyyy-MM-dd hh:mm"));
					$("#strEndDate").val(getTime(data.endDate, "yyyy-MM-dd hh:mm"));
					$("#resAmount").val(data.resAmount);
					$("#totalPrice").val(data.totalPrice);
					$("#activityPrice").val(data.activityPrice);
					$("#coursePeoples").val(data.coursePeoples);
					$("#courseDesc").val(data.courseDesc);
					$("#courseImg").val(data.courseImg);
					if(data.courseImg != null)$("#regMT-uploadPic").attr({"src": imgUrl + data.courseImg+"?v=" + new Date().getTime(), "data-state": "yes"});
					ue.ready(function() {//编辑器初始化完成再赋值  
				           ue.setContent(Base64.decode(data.courseDetails));  //赋值给UEditor  
				    });  
				}
         	});
		}
		
		//绑定表单验证控件
		function validator() {
			$("#addForm").validate({
				errorPlacement : function(error, element) {
					$(element).closest("form").find("label[for='" + element.attr("id") + "']").append(error);
				},
				errorElement : "span",
				rules : {
					name : {
						required : true,
						maxlength : 60,
					},
					courseType : {
						required : true
					},
					city : {
						required : true
					},
					startDate : {
						required : true,
						date:true
					},
					endDate : {
						required : true,
						date:true
					},
					resAmount : {
						required : true,
						number:true
					},
					totalPrice : {
						required : true,
						number:true
					},
					activityPrice : {
						number:true
					},
					coursePeoples :{
						number:true
					},
					courseDesc:{
						required : true,
						maxlength : 200
					}
				},
				submitHandler : function(form) {
						$.messager.progress({
							title : '系统提示',
							msg : '处理中，请稍候...'
						});
						var data = $('#addForm').serialize();
						data += '&courseDetails='+encodeURIComponent(ue.getContent());
						$.ajax({
							url : basePath + "/course/json/addUpdate",
							type : 'POST',
							error : function() {
								$.messager.progress('close');
								$.messager.alert('系统提示', '操作异常', 'error');
							},
							data : data,
							success : function(data) {
								$.messager.progress('close');
								if (data.code == '000') {
									$.messager.alert('系统提示', $("#id").val() == '' ? '课程新增成功' : '课程信息修改成功', 'info');
									returnBack();
								} else {
									$.messager.alert('系统提示', $("#id").val() == '' ? '课程新增失败' : '课程信息修改失败', 'warning');
								}
							}
						});
				}
			});
		}
		
        function getUeditor() {
        	return UE.getEditor('editor', {
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
        }
        //上传图片
		function uploadPic(){
			//上传按钮
			document.querySelector('input[type=file]').addEventListener('change', function () {
				var that=this;
				var imgWrapObj;
				lrz(that.files[0],{
					width:1024
				})
		    	//rst格式
		    	//rst.origin:图片信息，如大小、日期等
		    	//rst.base64:生成后的图片base64，后端可以处理此字符串为图片
		    	//rst.base64Len:生成后的图片的大小，后端可以通过此值来校验是否传输完整
		        .then(function (rst) {
		        	var fileName = rst.origin.name;
		        	var extName = fileName.substring(fileName.lastIndexOf("."), fileName.length)
		        	if(!(".jpg|.png|.bmp|.jpeg".toUpperCase().indexOf(extName)==-1)){
		        		alert("只允许上传jpg、png、bmp、jpeg格式的图片");
		        		return false;
		        	}
		        	extName = extName.substring(1, extName.length);
		        	$.ajax({
		        		type:"post",
		        		url: "<%=basePath%>/attachment/course/upload/img",
		        		data: {
		        			extName: extName,
		        			imgdata: rst.base64
		        		},
		        		async: false,
		        		cache: false,
		        		dataType:"json",
		        		success:function(result){
		        			result=result||{};
		        			if(result.code == "000"){
		        				$("#regMT-uploadPic").attr({"src": imgUrl + result.data+"?v=" + new Date().getTime(), "data-state": "yes"});
		        				$("#courseImg").val(result.data)
		        			}else{
		        				mui.alert(result.msg,'消息提示');
		        			}
		        		},
		        		error:function(){
		        			alert("网络错误！");
		        		},
		        		beforeSend:function(){
		        		},
		        		complete:function(){
		        			
		        		}
		        	})
		        })
		        .catch(function (err) {
		        	alert("图片处理失败");
		        })
		        .always(function () {
		            // 不管是成功失败，都会执行
		        });
			})
		}
		var cleanFormPanel=function(formId){
			$("#" + formId)[0].reset();
			$(".valid").removeClass("valid");
			$(".error").removeClass("error");
			$("label").find("span").remove();
			ue.setContent("");
		}
		function returnBack(){
			window.location.href=basePath+"/course/manage";
		}
    </script>
</body>
</html>