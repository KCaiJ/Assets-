<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>

<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<jsp:include page="/WEB-INF/inc/common.jsp"></jsp:include>
<title>列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<a class="btn-refresh btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>

	<div class="page-container">
		<div class="text-c">
			<form class="form form-horizontal" id="form" method="post"
				action="${pageContext.request.contextPath}//duvice_register/getName.do">
				<i class="Hui-iconfont">&#xe665;</i> 流水号
				<input type="text" class="input-text" style="width: 250px"
					placeholder="" id="number" name="number" value="${requestScope.number}">
			   
			  <i class="Hui-iconfont">&#xe665;</i> 设备类型
				<select name="" class="input-text" ></select>	
				
			</form>
		</div>
		
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
	
		<a href="javascript:;"
				onclick="insert('添加','${pageContext.request.contextPath}/device_register/add.do','800','500')"
				class="btn btn-primary radius"> <i class="Hui-iconfont">&#xe600;</i>
					添加
		</a> 
		<a href="javascript:;"
				onclick="batchdel('${pageContext.request.contextPath}/device_register/batchDelete.do')"
				class="btn btn-danger radius">
				<i class="Hui-iconfont">&#xe6e2;</i>
					批量删除 
		</a> 
		<a href="javascript:;"
				onclick="insert('添加','${pageContext.request.contextPath}/device_register/apply.do','800','500')"
				class="btn btn-primary radius"> <i class="Hui-iconfont">&#xe600;</i>
					出库
		</a> 
			</span>
		</div>
		<table
			class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th hidden="ture">ID</th>
					<th>流水号</th>
					<th>品牌</th>
					<th>型号</th>
					<th>国际编号</th>
					<th>入库时间</th>
					<th>入库人</th>
					<th>购买时间</th>
					<th>购买人</th>
					<th>状态</th>
				</tr>
			</thead>
			
		</table>
	</div>
	
</body>
</html>