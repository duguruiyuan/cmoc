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
</head>
<script src="<%=basePath %>/js/common.js?v=${config.version}" type="text/javascript"></script>
<body>
	<script type="text/javascript">
		var wechat_redirect = "${wechat_redirect}";
		var snsToken = JSON.parse(window.localStorage.getItem("snsToken"));
		var currUrl = window.location.href;
		if(snsToken != null) {
			var sp = currUrl.indexOf("?") != -1 ? "&" : "?";
			var reqUrl = currUrl + sp + "type=scope&token=" + 
			snsToken.refresh_token + "&openid=" + snsToken.openid;
			window.location.href = reqUrl;
		} else {
			currUrl = encodeURIComponent(currUrl);
			currUrl = wechat_redirect.replace('{1}', currUrl);
			window.location.href = currUrl;
		}
	</script>
</body>
</html>