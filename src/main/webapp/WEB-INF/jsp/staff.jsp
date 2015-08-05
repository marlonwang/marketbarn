<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>人员组织</title>
	<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet" />
	<link href="<c:url value="/css/bootstrap-datetimepicker.min.css" />" rel="stylesheet" />
	<script src="<c:url value="/js/jquery.js" />" type="text/javascript"></script>
	<script src="<c:url value="/js/bootstrap-datetimepicker.min.js" />" ></script>
	<script src="<c:url value="/js/bootstrap-datetimepicker.zh-CN.js" />" ></script>
	<style type="text/css">
		.nav {
			background-color: #F5F5F5;
		}
		.row label{
			margin-right: 10px;
		}
		.del_button_left{margin-left: 80px;}
	</style>
</head>
<body>
<div class="container">
	<div class="row">  
		<ul class="nav nav-pills">
			<li class="active"><a href="#">Home - <i>${G_NAME }</i></a></li>
			<li><a href="<c:url value="/goods/allgoods.do" />">商品管理</a></li>
			<li><a href="<c:url value="/stock/stockList.do" />">进货管理</a></li>
			<li><a href="<c:url value="/sales/saleList.do" />">出货管理</a></li>
			<li><a href="<c:url value="/goods/leftgoods.do" />">库存管理</a></li>
			<li><a href="<c:url value="/manage/allstaff.do" />">用户管理</a></li>
			<li><a>&nbsp;</a></li>
			<li><a>&nbsp;</a></li>
			<li><a href="logout.jsp">注销/退出</a></li>
		</ul>
	</div>
	<hr />
	<div class="row">
		<label>查询</label>
		<form action="<c:url value="/manage/findStaff.do" />" class="form-inline">
			<div class="form-group">
				<label>员工姓名</label><input type="text" name="userName"  class="form-control" />
				<label>&nbsp;&nbsp;员工级别</label><input type="text" name="userRank" class="form-control" />
				<input type="submit" value="查询" class="form-control btn-info" />
			</div>
		</form>
		<br />
		<form action="" class="form-inline">
			<button class="form-control btn-info">添加</button>
			<button class="form-control btn-info">修改</button>
			<button class="form-control btn-danger del_button_left">删除</button>
			<p>&nbsp;</p>
			<p><label>查询结果</label></p>		
			<div>
				<table class="table table-bordered table-hover" >
					<tr>
						<td style="width:10px;">#</td>
						<td>ID</td>
						<td>姓名</td>
						<td>年龄</td>
						<td>性别</td>
						<td>邮箱</td>
						<td>级别</td>
					</tr>
					<c:forEach var="staff" items="${allStaff}">
					<tr>
						<td style="width:10px;">
							<input type="checkbox" name="choosenId" value="${staff.staffId }"/>
						</td>
						<td>${staff.staffId }</td>
						<td>${staff.staffName}</td>
						<td>${staff.staffAge }</td>
						<td>${staff.staffSex }</td>
						<td>${staff.staffMail }</td>
						<td>${staff.staffRank }</td>
					</tr>
					</c:forEach>
					
				</table>
			</div>
		</form>
	</div>

</div>

</body>
</html>  
