<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>注册</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signup {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signup label{
	font-family: 黑体;
}
.form-signup .form-signup-heading, .form-signup .checkbox {
	margin-bottom: 10px;
}

.form-signup .checkbox {
	font-weight: normal;
}

.form-signup .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signup .form-control:focus {
	z-index: 2;
}

.form-signup input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signup input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>
</head>
<body>
	<div class="container">
		<form class="form-signup" role="form"
			action="<%=request.getContextPath() %>/register.do" method="post" >
			<h2 class="form-signup-heading">用户注册</h2>
			<div class="form-group">
				<input type="text" name="staffName" class="form-control"
				placeholder="name" required autofocus />
				<br />
				<input type="text" name="email" class="form-control"
				placeholder="example@example.com" />
			</div>
			<div class="form-group">
				<label class="radio-inline ">性别</label>
				<label class="radio-inline">
					<input type="radio" name="staffSex" id="sex1" value="男" /> 男
				</label>
				<label class="radio-inline">
				 	<input type="radio" name="staffSex" id="sex2" value="女" checked/> 女
				</label>
			</div>
			<div class="form-group">
				<label class="radio-inline ">年龄</label>
				<label class="radio-inline ">
					<input type="text" name="staffAge" style="width:55px" placeholder="20"></input>
				</label>
			</div>
			<div class="form-group">
				<label class="radio-inline">职位</label>
				<select class="radio-inline" name="staffRank">
					<option value="1">职员</option>
					<option value="7">经理</option>
					<option value="0" selected>其他</option>
				</select>
			</div>
			<div class="form-group">
				<input type="password" name="staffPasswd" class="form-control" placeholder="password" required />
				
				<input type="password" name="password_again" class="form-control" placeholder="confirm password" required />
			</div>
			<script type="text/javascript">
				$(document).ready(function(e){
					$(".container form").submit(function(e){
						var password = $("input[name='staffPasswd']").val();
						var repassword = $("input[name='password_again']").val();
						if (password!=repassword) {
							alert("两次输入的密码不正确，请更正...");
							return false;
						} else {
							return true;
						}
					});
				});
			</script>
			<div class="form-group">
				<button class="btn btn-lg btn-primary" type="submit">confirm</button>
				<button class="btn btn-lg btn-primary" type="reset">reset</button>
			</div>
				
		</form>
		
	</div>
</body>
</html>