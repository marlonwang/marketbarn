<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>进货管理</title>
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
		.sync_button_left{margin-left: 225px;}
		.form_add {width:120px; height:35px;}
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
		<form action="<%=request.getContextPath() %>/stock/queryStock.do" class="form-inline">
			<div class="form-group">
				<label>商品类别</label><input type="text" name="itemType" class="form-control" />
				<label>&nbsp;&nbsp;进货时间</label><input type="text" name="stockDate1" id="calender" class="form-control" />
				<label>--</label><input type="text" name="stockDate2" id="calender2" class="form-control" />
				<input type="submit" value="查询" class="form-control btn-info" />
			</div>
		</form>
		<br />
		<hr />
		<form action="<%=request.getContextPath() %>/stock/addStock.do" class="form-inline" method="post">
			<label>商品条形码</label><input type="text" name="goodsBarcode" class="form_add"></input>
			<label>类别码</label><input type="text" name="goodsCate" class="form_add"></input>
			<label>进货价格</label><input type="text" name="goodsPrice" class="form_add"></input>
			<label>进货数量</label><input type="text" name="goodsQuantity" class="form_add"></input>
			<label>进货员</label><input type="text" name="goodsOperator" class="form_add"></input>
			<input type="submit" value="添加" class="form-control btn-info" />
			<p>&nbsp;</p>
			<p><label>查询结果</label></p>		
			<div>
				<table class="table table-bordered table-hover" >
					<tr>
						<td style="width:10px;">#</td>
						<td>ID</td>
						<td>分类号</td>
						<td>商品条形码</td>
						<td>进货价格($)</td>
						<td>进货数量</td>
						<td>入库时间</td>
						<td>操作人员</td>
					</tr>
					<c:forEach var="stock" items="${ALL_STOCKS }">
					<tr>
						<td style="width:10px;"><input type="checkbox" /></td>
						<td>${stock.stockId }</td>
						<td>${stock.categoryCode }</td>
						<td>${stock.barcode }</td>
						<td>${stock.stockPrice }</td>
						<td>${stock.stockQuantity }</td>
						<td>${stock.stockDate }</td>
						<td>${stock.operator }</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>

</div>

</body>
</html>  
<script>
	$(document).ready(function(e){
		$('#calender').datetimepicker({
			language: 'zh-CN',
		    format: 'yyyy.mm.dd'
		});
		$('#calender2').datetimepicker({
			language: 'zh-CN',
		    format: 'yyyy.mm.dd'
		});
	});
	
</script>