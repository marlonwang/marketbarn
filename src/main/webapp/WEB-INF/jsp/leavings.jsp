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
		.file_up_load{margin-top:10px;}
		#time_zone{margin-left:200px;}
		.xml_related{margin-top:5px;}
		a{text-decoration: none;}
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
		<form action="" class="form-inline">
			<div class="form-group">
				<label>出库时间</label><input type="text" name="" id="calender" class="form-control" />
				<label>--</label><input type="text" name="" id="calender2" class="form-control" />
				<input type="submit" value="查询" class="form-control btn-info" />
			</div>
		</form>
		<br />

		<div class="form-group form-inline">
			<label>生成库存XML文件到[d:/market/]</label>
			<a href="<c:url value="/report/generate.do" />"><button class="form-control btn-danger">生成XML</button></a>
			
			<!-- request.getContextPath不能缺省 否则指向127.0.0.1/report/leftReport.do -->
			<form class="form-inline xml_related" action="<%=request.getContextPath() %>/report/leftReport.do" method="post" enctype="multipart/form-data">
				<span class="form-control">
					<input type="file" name="fileToUpload" id="fileToUpload" multiple="multiple" />
				</span>
				<input type="submit" value="上传XML" class="form-control btn-danger" />
			</form>
			
			<form class="form-inline xml_related" action="<%=request.getContextPath() %>/report/leftImport.do" method="post" enctype="multipart/form-data">
				<span class="form-control">
					<input type="file" name="fileToImport" id="fileToImport" multiple="multiple" />
				</span>
				<input type="submit" value="导入XML" class="form-control btn-danger" />
			</form>
		</div>
			
		<p><label>当前库存</label></p>		
		<div>
			<table class="table table-bordered table-hover" >
				<tr>
					<td>#</td>
					<td>名称</td>
					<td>剩余数量</td>
					<td>分类号</td>
					<td>商品条形码</td>
					<td>商品描述</td>
					<td>生产商</td>
					<td>厂商联系电话</td>
					<td>统计时间</td>
				</tr>
				<c:forEach var="lefts" items="${LEFT_GOODS }">
				<tr>
					<td style="width:10px;"><input type="checkbox" /></td>
					<td>${lefts.itemName }</td>
					<td>${lefts.itemNum }</td>
					<td>${lefts.itemCode }</td>
					<td>${lefts.barcode }</td>
					<td>${lefts.description }</td>
					<td>${lefts.producer }</td>
					<td>${lefts.telnumber }</td>
					<td>${lefts.countTime }</td>
				</tr>		
				</c:forEach>
								
			</table>
		</div>
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