<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注销</title>
</head>
<body>
<%
	session.removeAttribute("G_NAME");
	session.invalidate();
	out.print("<script>alert('用户即将退出，确定?');window.location.href='login.jsp'</script>");
%>
</body>
</html>