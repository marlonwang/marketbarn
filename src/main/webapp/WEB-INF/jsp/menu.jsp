<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>功能菜单</title>
	<link href='<c:url value="/css/menu.css"></c:url>' rel="stylesheet" type="text/css" />
	<script type="text/javascript" src='<c:url value="/js/jquery.js"></c:url>'></script>
	
</head>
<body>
	<div id="top_space">超市库存管理系统</div>
	<script type="text/javascript">
	function showtime(){
           var now = new Date();
           year=now.getFullYear();
           mon=now.getUTCMonth()+1;
           day=now.getDate();
           hour=now.getHours();
           min=now.getMinutes();
           sec=now.getSeconds();
           return "&nbsp; 今天是:"+year+"年"+mon+"月"+day+"日";
 }

	$(document).ready(function(e){
		console.log(showtime());
		$("#center_left_title").html(showtime());
	});
	</script>
	 
	<div id="center_space">
		<div id="center_left">
			<div id="center_left_title"></div>
			<div id="center_left_content">
				<ul>
					<span>&nbsp;用户:
					<c:choose>
			  			<c:when test="${G_NAME==null }">未登录</c:when>
			  			<c:otherwise>${G_NAME }</c:otherwise>
			  		</c:choose>
			  		</span>
				</ul>
				<ul>
					<li><a href='<c:url value="login.jsp"></c:url>'>&nbsp;&nbsp;登录</a></li>
					<li><a href='<c:url value="register.jsp"></c:url>'>&nbsp;&nbsp;注册</a></li>
				</ul>

			</div>
		</div>

		<div id="center_right">
			<table>
				<tr>
					<td><a href='<c:url value="staff.jsp" />'>员工管理</a></td>
					<td><a href='<c:url value="items.jsp"/>'>商品管理</a></td>
				</tr>
				<tr>
					<td><a href='<c:url value="stock.jsp"/>'>进货管理</a></td>
					<td><a href='<c:url value="sales.jsp"/>'>出库管理</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="footer">
		<div>Contact:marlonwang@163.com</div>
		<div>Copyright &copy; 2014-2015, marlonwang, All Rights Reserved</div>
	</div>
</body>
</html>