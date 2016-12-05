<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>透明人注册[陶学趣]</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<body>
	<div class="regTMHeader">
		<img src="<%=basePath %>/images/slider4.jpg" class="regTMPic"><br>
		<span class="regTMTitle">注册透明人</span><br>
		<span class="regTMName">陶学趣</span>
	</div>
	<div class="regTM-formTitle">填写透明人资料</div>
	<div class="mui-content">
		<form id="hmRegForm" class="mui-input-group">
			<div class="mui-input-row">
			    <label>姓名</label>
			    <input id="hmName" name="hmName" type="text" placeholder="请输入姓名">
			</div>
			<div class="mui-input-row">
			    <label>手机</label>
			    <input id="hmMobile" name="hmMobile" type="text" maxlength="11" placeholder="请输入手机号码">
			</div>
			<div class="mui-input-row">
			    <label>证件</label>
			    <input type="hidden" id="idType" name="idType" value="01" />
			    <input type="hidden" id="openid" name="openid" value="34324324" />
			    <input id="idCard" name="idCard" type="text" placeholder="请输入身份证号">
			</div>
			<div class="mui-input-row">
			    <label>学校</label>
			    <input id="schoole" name="schoole" type="text" placeholder="请输入学校">
			</div>
			<div class="mui-input-row">
			    <label>籍贯</label>
			    <input id="place" name="place" type="text" placeholder="请输入籍贯">
			</div>
			<div class="mui-input-row pr">
			    <label>年级</label>
			    <select id="grade" name="grade" class="regMT-select pr">
			    	<option value="大一">大一</option>
			    	<option value="大二">大二</option>
			    	<option value="大三">大三</option>
			    	<option value="大四" selected>大四</option>
			    </select>
			    <span class="mui-icon mui-icon-arrowright regMT-select-arrow"></span>
			</div>
			<div class="mui-input-row mui-radio">
			    <label>帅哥</label>
			    <input name="sex" type="radio" value="F" checked>
			</div>
			<div class="mui-input-row mui-radio">
			    <label>美女</label>
			    <input name="sex" type="radio" value="M">
			</div>
			<div class="mui-input-row">
			    <label>上传照片</label>
			</div>
			<div class="mui-input-row regMT-headPic">
				<div class="pr">
					<input type="hidden" id="idPhoto" name="idPhoto" />
			    	<img src="<%=basePath %>/images/regTM.png" id="regMT-uploadPic" data-state="no">
					<input type="file" name="regMT-uploadInput" id="regMT-uploadInput" accept="image/png,image/jpeg" value="">
				</div>
			</div>
		</form>
		<div class="regMT-btnWrap">
			<button type="button" id="regMT-btn">确定注册</button>
		</div>
		<div class="regMT-text">
			<p>
				注意事项：<br>
				1、需认可穿越部落活动文化<br>
				2、不限学校、不限专业、不限年级、不限性别<br>
				3、做事细心、耐心、能吃苦、不畏惧困难，工作积极主动、有团队合作精神<br>
				4、具备强烈的责任感和服务意识，逻辑能力强。<br>
				5、有清晰的语言表达能力，为人谦逊、接受能力强。<br>
				6、有文体、摄影等特长或有社会实践经验者、组织参与过大型活动者优先。<br>
				7、在校时间灵活且有市场意识或有过招生经验才优先录用
			</p>
		</div>
	</div>
	<div class="code" style="display: none;">								
		<div class="code-inner" style="height: 170px;">									
			<div class="code-title">[陶学趣]透明人注册</div>									
			<div class="code-pic">										
				<p style="color: red;">您已经注册成功</p>								
			</div>									
			<div class="code-btn">确定</div>								
		</div>							
	</div>
	<script type="text/javascript" src="<%=basePath %>/js/plugins/localResizeIMG/dist/lrz.bundle.js" ></script>
	<script type="text/javascript">
		initSnsToken();
		mui.init();
		
		$(function(){
			uploadPic();
			$("#regMT-btn").click(function(){
				var arr = ["hmName","hmMobile","idCard"];
				for(var i = 0, len = arr.length; i < len; i++){
					window[arr[i]] = $("#"+arr[i]).val();
				}
				
				var sex = $("input[name=sex]:checked").val();
				
				var nameReg = /[\u4E00-\u9FA5]{2,}/g;
				var phoneReg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				
				//验证姓名
				if(!nameReg.test(hmName)){
					if(hmName == '') mui.alert('请填写姓名','消息提示');
					else mui.alert('姓名不正确','消息提示');
					return false;
				}
				
				//验证手机号
				if(!phoneReg.test(hmMobile)){
					if(hmMobile == '') mui.alert('请填写手机号码','消息提示');
					else mui.alert('手机号不正确','消息提示');
					return false;
				}
				
				//验证身份证
				if(!idCardNo(idCard)){
					if(idCard == '') mui.alert('请填写身份证','消息提示');
					else mui.alert('身份证不正确','消息提示');
					return false;
				}	
				$.ajax({
			 		url : "<%=basePath%>/hm/register",
			 		type : "post",
			 		data : $('#hmRegForm').serialize(),
			 		dataType : "json",
			 		async : false,
			 		success : function(data) {
			 			if(data.code == '000') {
			 				mui.alert('注册成功，请等待管理员审核。','消息提示');
			 			}else {
			 				mui.alert(data.msg,'消息提示');
			 			}
			 		}, error:function(){
			 			mui.alert("系统异常，请联系管理员",'消息提示');
	        		}
			 	});
			})
		})
		function initSnsToken() {
			var snsToken = ${snsToken};
			setAccessToken(snsToken);
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
		        		url: "<%=basePath%>/attachment/idPhoto/upload",
		        		data: {
		        			extName: extName,
		        			openid: $("#openid").val(),
		        			imgdata: rst.base64
		        		},
		        		async: false,
		        		cache: false,
		        		dataType:"json",
		        		success:function(result){
		        			result=result||{};
		        			if(result.code == "000"){
		        				$("#regMT-uploadPic").attr({"src": imgUrl + result.data, "data-state": "yes"});
		        				$("#idPhoto").val(result.data)
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

		
		function idCardNo(value){
		  //验证身份证号方法
		  var area = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "xinjiang", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外" }
		  var idcard, Y, JYM;
		  var idcard=value;
		  var S, M;
		  var idcard_array = new Array();
		  idcard_array = idcard.split("");
		  if (area[parseInt(idcard.substr(0, 2))] == null) return false;
		  switch (idcard.length) {
		      case 15:
		          if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
		              ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/; //测试出生日期的合法性
		          }
		          else {
		              ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/; //测试出生日期的合法性
		          }
		          if (ereg.test(idcard))
		              //return Errors[0];
		            var res = true;
		          else
		              //return Errors[2];
		            var res = false;
		          return res;
		          break;
		      case 18:
		          if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
		              ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/; //闰年出生日期的合法性正则表达式
		          }
		          else {
		              ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/; //平年出生日期的合法性正则表达式
		          }
		          if (ereg.test(idcard)) {
		              S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 + parseInt(idcard_array[7]) * 1 + parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9]) * 3;
		              Y = S % 11;
		              M = "F";
		              JYM = "10X98765432";
		              M = JYM.substr(Y, 1);
		              if (M == idcard_array[17])
		                  //return Errors[0];
		                var res = true;
		              else
		                  //return Errors[3];
		                var res = false;
		          }
		          else
		              //return Errors[2];
		            res = false;
		          return res;
		          break;
		      default:
		          res = false;
		          return res;
		          break;
		    };
		}
	</script>
</body>
</html>