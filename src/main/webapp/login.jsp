<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

</head>
<body>
	<div class="container">
		<form class="form-signin" role="form"
			action="<%=request.getContextPath() %>/login.do" method="post">
			<h2 class="form-signin-heading">用户登录</h2>
			<input type="text" name="name" class="form-control"
				placeholder="user name" required autofocus /> <br /> 
				<input type="password" name="password" value="" class="form-control"
				placeholder="password"></input>
			<button class="btn btn-lg btn-primary" type="submit">login</button>
			<button class="btn btn-lg btn-primary" type="reset">reset</button>
		</form>
		<div><center><a href="register.jsp">注册</a></center></div>
	</div>
</body>
</html>