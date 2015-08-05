<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>商品信息</title>
	<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet" />
	<link href="<c:url value="/css/bootstrap-datetimepicker.min.css" />" rel="stylesheet" />
	<script src="<c:url value="/js/jquery.js" />" ></script>
	<script src="<c:url value="/js/bootstrap-datetimepicker.min.js" />" ></script>
	<script src="<c:url value="/js/bootstrap-datetimepicker.zh-CN.js" />" ></script>
	<style type="text/css">
		.nav {
			background-color: #F5F5F5;
		}
		.row label{
			margin-right: 10px;
		}
		.sync_button_left{margin-left: 140px;}
		#time_zone{margin-left:200px;}
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
		<form class="form-group form-inline" action="<%=request.getContextPath() %>/goods/queryGoods.do" method="get">
			<label>类别码</label>
			<select name="typeName" style="height:32px;">
				<option>选择类别码</option>
				<c:forEach var="category" items="${ALL_CATEGORY }">
				<option>${category.categoryCode}</option>
				</c:forEach>
			</select>
			<label>&nbsp;&nbsp;名称</label><input type="text" name="itemName" class="form-control" />
			<input type="submit" value="查询" class="form-control btn-info"/>
		</form>
		
		<br />
		<form action="" class="form-inline">
			<button class="form-control btn-info">添加</button>
			<button class="form-control btn-info">修改</button>
			<button class="form-control btn-info">删除</button>
			
			<p>&nbsp;</p>
			<p><label>查询结果</label></p>		
			<div>
				<table class="table table-bordered table-hover" >
					<tr>
						<td style="width:10px;">#</td>
						<td>ID</td>
						<td>名称</td>
						<td>分类号</td>
						<td>商品条形码</td>
						<td>商品描述</td>
						<td>执行标准</td>
						<td>生产商</td>
						<td>生产地址</td>
						<td>厂商联系电话</td>
						<td>其他细心</td>
					</tr>
					<c:forEach var="goods" items="${ALL_GOODS }">
					<tr>
						<td style="width:10px;"><input type="checkbox" /></td>
						<td>${goods.itemId }</td>
						<td>${goods.itemName }</td>
						<td>${goods.itemCode }</td>
						<td>${goods.barcode }</td>
						<td>${goods.description }</td>
						<td>${goods.standard }</td>
						<td>${goods.producer }</td>
						<td>${goods.address }</td>
						<td>${goods.telnumber }</td>
						<td>${goods.addition }</td>
					</tr>		
					</c:forEach>
								
				</table>
			</div>
		</form>
		
		<br />
		<label>类别信息对照表</label>
		<div>
			<table class="table table-bordered table-hover" >
				<tr>
					<td>类别码</td><td>类别名称</td><td>单位</td>
				</tr>
				<c:forEach var="category" items="${ALL_CATEGORY }">
				<tr>
					<td>${category.categoryCode }</td>
					<td>${category.categoryName }</td>
					<td>${category.categoryDesc }</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</div>

</body>
</html>  