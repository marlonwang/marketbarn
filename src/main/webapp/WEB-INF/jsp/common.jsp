<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!-- 导航部分  -->
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