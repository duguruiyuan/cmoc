<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		var currUrl = window.location.href;
		var oauth2Base = '${oauth2Base}';
		oauth2Base = oauth2Base.replace("{1}", encodeURIComponent(currUrl));
		window.location.href = oauth2Base;
	</script>
</body>
</html>