<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title></title>
  <style>
  	.content table td{
  		padding-left: 10px;
  	}
  	.ml80{margin-left: 80px;}
  	.ml120{margin-left: 120px;}
  	.span-bottom{
  		display: inline-table;
  		width: 240px;
  		border-bottom: 1px solid;
  	}
  	input[type='checkbox'] {
  	  -ms-transform: scale(1.6); /* IE */
	  -moz-transform: scale(1.6); /* FireFox */
	  -webkit-transform: scale(1.6); /* Safari and Chrome */
	  -o-transform: scale(1.6); /* Opera */
  	}
  </style>
</head>
<body>
  <div align="center">
    <h1>陶学趣报名表</h1>
  </div>
  <div align="center" class="content">
  	<input type="hidden" id="isDisease" value="${signInfo.isDisease }"/>
  	<input type="hidden" id="childSexV" value="${signInfo.childSex }"/>
  	<input type="hidden" id="signRelationV" value="${signInfo.signRelation }"/>
    <table border="1" cellspacing="0" cellpadding="0">
      <tr>
        <td><p align="center">姓名</p></td>
        <td><p align="left">${signInfo.childName } </p></td>
        <td><p align="center">性别</p></td>
        <td><p align="left" id="childSex"></p></td>
        <td><p align="center">年龄</p></td>
        <td><p align="left">${signInfo.childAge } </p></td>
      </tr>
      <tr>
        <td><p align="center">身份证号码</p></td>
        <td colspan="5">
        	<p align="left">${signInfo.childIdcard } </p>
        </td>
      </tr>
      <tr>
        <td><p align="center">紧急联系人</p></td>
        <td><p align="left">${signInfo.emerName }</p></td>
        <td><p align="center">关系</p></td>
        <td><p align="left" id="signRelation"></p></td>
        <td><p align="center">联系方式</p></td>
        <td><p align="left">${signInfo.emerMobile }</p></td>
      </tr>
      <tr>
        <td colspan="6">
          <p>有无重大疾病（例如：哮喘、心脏病、三个月内做过大型手术等不宜参加户外活动）</p>
          <p>
            <span><input type="checkbox" id="noDisease" class="ml80" onclick="return false;" /> 无</span>
            <span><input type="checkbox" id="hadDisease" class="ml120" onclick="return false;" /> 有 
            <span class="span-bottom" id="diseaseDesc">${signInfo.diseaseDesc }</span></span>
          </p>
        </td>
      </tr>
      <tr>
        <td colspan="6">
          <p>温馨提示：</p>
  		  <p>1、要求年龄7-15周岁，身体健康，独立能力较强的青少年朋友参加；</p>
  		  <p>2、需父母或法定监护人同意，自愿参加；</p>
  		  <p>3、需个人自备雨伞，水壶，要求穿休闲运动装。</p>
  		 </p>
        </td>
      </tr>
      <tr>
        <td colspan="6">
          <p>责任与义务：</p>
          <p>1、客户需积极配合本次活动的工作，需遵守通知参加活动的时间及各项安排；</p>
          <p>1、如推迟参加，需家长书面说明，转入下一期；</p>
          <p>2、自承诺书签署之日起，因个人原因不能参加此次活动要求退款的，将扣除所交费用的20%作为活动的物资费、保险费等；</p>
          <p>3、必须全程参与，因个人原因中途退营，费用不予退款；</p>
          <p>4、您的孩子若有特殊病史，并可能诱发或导致疾病发生的请勿报名参加，必须提前与主办方声明；</p>
          <p>5、穿越珠海是一项集体活动，不提倡学员携带手机、贵重物品和现金零用钱，若需要携带请与主办方联系，将统一登记和管理；</p>
          <p>6、我们将为每个孩子购买活动当天价值10万元的人生意外保险。</p>
        </td>
      </tr>
    </table>
  </div>
  <p align="left">
	    <h2 style="color: red;">我公司承诺对以上内容将进行严格保密！</h2>
  </p><br/>
  <div>
	<p style="float: left"><b>活动主办方签名：____________</b></p>
	<p style="float: right"><b>乙方（代表家长）签名：____________</b></p>
  </div>
  <script src="<%=basePath %>/js/plugin/jquery-2.1.4.min.js"></script>
  <script src="<%=basePath %>/js/main.js"></script>
  <script type="text/javascript">
  	 $("#childSex").html(sex1Format($("#childSexV").val()));
  	 $("#signRelation").html(dictDataFormat("relation", $("#signRelationV").val()));
  	 var isDisease = $("#isDisease").val();
  	 if(isDisease == 'N') {
  		$("#noDisease").attr("checked", true);
  		$("#diseaseDesc").html('');
  	 }else {
  		$("#hadDisease").attr("checked", true);
  	 }
  </script>
</body>
</html>