<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>课程报名[陶学趣]</title>
</head>
<jsp:include page="/WEB-INF/page/common/_header.jsp"/>
<script src="<%=basePath %>/js/plugins/wechat-config.js?v=${config.version}" type="text/javascript" charset="utf-8"></script>
<body>
	<header class="header">
		队伍信息管理
	</header>
	<div class="mt44"></div>
	<c:if test="${members == 0 }">
		<div class="group-add-tips">
			队伍尚未组建，请填写以下信息完成队伍组建
			<c:if test="${isPay == 0 }"><a class="group-pay" href="javascript:void(0)" onclick="groupPay()">付款</a></c:if>
		</div>
	</c:if> 
	<div class="group-process">
		<span class="active">1.组建队伍</span>
		<span> > </span>
		<span class="dis">2.邀请队友</span>
		<span> > </span>
		<span class="dis">3.确定支付</span>
	</div>
	<div>
		<div style="width: 100%;text-align: center;margin: 30px 0;">队员资料</div>
		<p style="padding-left: 10px;color: #3F3E3E;font-size: 16px;">以下信息仅用于活动报名，主办方承诺对资料保密</p>
		<div class="mui-content">
			<form id="courseSignForm" class="mui-form">
				<input type="hidden" id="id" name="id" value="${childInfo.id}">
				<div class="mui-textfield">
					<input type="text" placeholder="请填写您孩子的姓名" id="childName" value="${childInfo.childName }">
					<label>姓名</label>
				</div>
				<div class="mui-textfield">
					<input type="text" placeholder="紧急联系人姓名" id="emerName" value="${childInfo.emerName }">
					<label>家长姓名</label>
				</div>
				<div class="mui-textfield">
					<input type="text" placeholder="紧急联系人电话" id="emerMobile" value="${childInfo.emerMobile }">
					<label>联系电话</label>
				</div>
				<div class="mui-textfield">
					<input type="text" placeholder="用于购买活动当天的保险" id="childIdcard" value="${childInfo.childIdcard }">
					<label>小朋友的身份证</label>
				</div>
				<label>小孩性别：</label>
				<div class="mui-radio">
	                <input type="radio" style="padding-top: 10px;left: 0;top: -4px;" class="check" name="childSex" value="F" <c:if test="${childInfo.childSex == null || (childInfo.childSex != null && childInfo.childSex == 'F')}">checked="checked"</c:if>>
	            	<label class="pl30 pt10">男</label>
	            </div>
	            <div class="mui-radio" style="padding-top: 20px;">
	                <input type="radio" style="padding-top: 10px;left: 0;" class="check" name="childSex" value="M" <c:if test="${childInfo.childSex != null && childInfo.childSex == 'M'}">checked="checked"</c:if>>
	            	<label class="pl30">女</label>
	            </div>
			</form>
		</div>
		<div class="sign-btn">
			<button type="button" id="regMT-btn">提交</button>
		</div>
	</div>
	<div class="group-tips">
		<div class="group-tips-pannel"><a><i class="fa fa-exclamation-circle"></i> 温馨提示</a></div>
		<div class="group-tips-pannel1">
			1、要求年龄7-12周岁，身体健康，独立能力较强的青少年朋友参加；<br/>
			2、需父母或法定监护人同意，自愿参加； <br/>
			3、需个人自备雨伞，水壶； <br/>
			4、要求穿休闲运动装。
		</div>
	</div>
	<div class="group-tips">
		<div class="group-tips-pannel"><a><i class="fa fa-exclamation-circle"></i> 责任与义务声明</a></div>
		<div class="group-tips-pannel1 active" style="display: block;">
		    <b>一、家长的权利与义务</b><br/>
		　    1、报名时，家长应向主办方承诺其提供的个人及小朋友的身份信息全部为真实的。<b>不得隐瞒参赛小朋友的重大疾病史，否则，由此导致参赛小朋友的人身安全与主办方无关。</b><br/>
		    2、家长务必在报名时一次性付清参赛费用。参赛费用中，已包含为执行此次活动而产生的资料费、为参赛小朋友购买的活动期间内的意外保险、服装费、餐费及车票费。<br/>
		　    3、报名后，家长原则上不得要求退出。家长如要求退出的，应至迟不得晚于活动日期前7天向主办方提出书面申请，主办方收到该申请后，有权要求该家长推荐另外一名小朋友报名参加，或要求该家长取得本组其它全部家长的书面自愿推迟活动日期的承诺。
			家长如在活动日期前7天内要求退出的，应向主办方成功推荐另外一名小朋友报名参加，或取得本组其它全部家长的书面自愿推迟活动日期的承诺。否则，主办方有权不予退还参赛费用。
			活动进行时，家长及参赛小朋友要求退出的，参赛费用不予退还。<br/>
			4、家长应在参赛小朋友出发前，避免其携带贵重物品，未委托主办方代管而损坏或丢失的，主办方不承担赔偿责任。<br/>
			<b>二、主办方的权利与义务</b><br/>
			1、主办方不得无故单方面更改参赛日期，但如遇不可抗力或中至暴雨（以天气预报为参考）等恶劣天气的，为保障参赛小朋友们的人身安全，主办方可更改参赛日期，但主办方应提前告知家长。待不可抗力因素消失后，应第一时间通知家长更改后的参赛日期。家长如不同意新的参赛日期要求退赛的，可参照上述第3条第2款的规定执行。<br/>
			<b>上款所称的不可抗力是指，在活动路线的任何一处或会场地，发生了在报名时主办方不能预见、不能避免并不能克服的客观情况。包括但不限于自然灾害，如台风、冰雹、地震、海啸、洪水、火山爆发、山体滑坡、其它恶劣天气等；政府行为，如征收、征用、法律及政策的变更；社会异常事件，如战争、武装冲突、罢工、骚乱、暴动、流行病等情况。</b><br/>
			2、来回路程期间以及参赛期间，主办方将配置摔伤、撞伤、中暑应急救护包，制定走失、食物中毒等各类应急预案等一切措施保障每位参赛小朋友的人身安全，保证活动项目的安全性，同时为每位参赛小朋友购买参赛期间的人身意外保险。<br/>
			3、参赛小朋友因自身疾病而无法继续参赛的，主办方应及时通知家长并有义务就近送医治疗。但主办方不负担其医疗费用。<br/>
			<b>三、其它</b><br/>
			1、如家长与主办方发生争议的，双方应友好协商，协商不成的可向主办方所在地人民法院起诉解决。<br/>
			2、本报名协议具有合同性质，一式两份，家长及主办方应严格恪守。<br/>
		</div>
	</div>
	<div class="group-tips">
		<div class="group-tips-pannel"><a><i class="fa fa-exclamation-circle"></i> 报名费用</a></div>
		<div class="group-tips-pannel1">
			${course.totalPrice/5 }元/人（本费用包括服装、背包、帽子、车费、餐费、景点门票、地图、保险等） <br/>
			${course.totalPrice }元/组（暂仅接受按组收费）。
		</div>
	</div>
	
	<script type="text/javascript">
		$(function() {
			$("#regMT-btn").click(function() {
				var childName = $("#childName").val();
				var emerName = $("#emerName").val();
				var emerMobile = $("#emerMobile").val();
				var childIdcard = $("#childIdcard").val();
				var childSex = $("input[name='childSex']:checked").val();
				var id = $("#id").val();
				if(!checkName(childName)) {
					if(childName == '') mui.alert('请填写您孩子的姓名','消息提示');
					else mui.alert('孩子姓名格式不正确','消息提示');
					return false;
				}
				if(!checkName(emerName)) {
					if(emerName == '') mui.alert('请填写紧急联系人姓名','消息提示');
					else mui.alert('紧急联系人姓名格式不正确','消息提示');
					return false;
				}
				if(!checkPhone(emerMobile)) {
					if(emerMobile == '') mui.alert('请填写紧急联系人电话','消息提示');
					else mui.alert('紧急联系人电话格式不正确','消息提示');
					return false;
				}
				if(!idCardNo(childIdcard)) {
					if(childIdcard == '') mui.alert('请填写小朋友身份证','消息提示');
					else mui.alert('小朋友身份证格式不正确, 用于购买活动当天的保险请正确填写','消息提示');
					return false;
				}
				if(!childSex) {
					mui.alert('请选择小孩性别','消息提示');
					return;
				}
				$.ajax({
			 		url : basePath + "/course/json/group/addMember",
			 		type : "post",
			 		data : {
			 			id: id,
			 			childName: childName,
			 			emerName: emerName,
			 			emerMobile: emerMobile,
			 			childIdcard: childIdcard,
			 			childSex: $("input[name='childSex']:checked").val(),
			 			orderNo: '${orderNo}'
			 		},
			 		dataType : "json",
			 		async : false,
			 		success : function(data) {
			 			if(data.code == '000') {
			 				if(id.length > 0) {
			 					alert("队员信息变更成功！");
			 				}else {
			 					alert("队员信息提交成功！");
			 				}
			 				if(data.data.isSigner == 1) {
			 					window.location.href = basePath + "/course/group/add/" + data.data.orderNo;
			 				}else {
			 					window.location.href = basePath + "/course";
			 				}
			 			}else {
			 				mui.alert(data.msg,'消息提示');
			 				return;
			 			}
			 		}, error:function(){
			 			mui.alert("系统异常，请联系管理员",'消息提示');
	        		}
			 	});
			});
			$(".group-tips-pannel").click(function() {
				if($(this).closest(".group-tips").find('.active').length == 1) {
					$(this).closest(".group-tips").find(".group-tips-pannel1").removeClass("active")
					$(this).closest(".group-tips").find(".group-tips-pannel1").hide();
				}else {
					$(this).closest(".group-tips").siblings().find(".group-tips-pannel1").hide();
					$(this).closest(".group-tips").find(".group-tips-pannel1").show();
					$(this).closest(".group-tips").find(".group-tips-pannel1").addClass("active");
				}
			})
		})
	</script>
</body>
</html>