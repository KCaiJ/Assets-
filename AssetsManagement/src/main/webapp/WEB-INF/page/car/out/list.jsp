﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
				action="${pageContext.request.contextPath}/carout/getName.do">
				<input type="text" class="input-text" style="width: 250px"
					placeholder="流水号" id="code" name="code" value="${requestScope.code}">
				<button type="submit" class="btn btn-success" id="" name="">
					<i class="Hui-iconfont">&#xe665;</i> 查询
				</button>
			</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
				
					<a href="javascript:;"
				onclick="batchdel('${pageContext.request.contextPath}/carout/updatebatch_To.do','通过')"
				class="btn btn-primary radius"> <i class="Hui-iconfont">&#xe6e1;</i>
					批量通过 </a> 
					<a href="javascript:;"
				onclick="batchdel('${pageContext.request.contextPath}/carout/updatebatch_No.do','拒绝')"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6dd;</i>
					批量拒绝 </a> 
				 </span>
		</div>
		<table
			class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th hidden="ture">ID</th>
					<th>流水号</th>
					<th>车辆名称</th>
					<th>申请时间</th>
					<th>申请人</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.records }" var="record">
					<tr class="text-c">
						<td><input type="checkbox" value="${record.id }"
							class="checkbox-box"></td>
						<td hidden="true">${record.id }</td>
						<td>${record.code }</td>
						<td>${record.name }</td>
						<td>${record.outdate }</td>
						<td>${record.outter  }</td>
						<td>${record.remarks }</td>
						<td class="td-manage">
							<a title="通过" href="javascript:;"
							onclick="update('通过','${pageContext.request.contextPath}/carout/out_To.do?id=${record.id }','1','800','500')"
							class="ml-5" style="text-decoration: none"><i
								class="Hui-iconfont">&#xe6e1;</i></a> 
							<a title="拒绝" href="javascript:;"
							onclick="update('拒绝','${pageContext.request.contextPath}/carout/out_No.do?id=${record.id }','1','800','500')"
							class="ml-5" style="text-decoration: none"><i
								class="Hui-iconfont">&#xe6dd;</i></a> 
						
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		$('.table-sort').dataTable({
			"bFilter" : true,//过滤功能
			"bPaginate" : true,//翻页信息 //是否分页。
			"bInfo" : true,//数量信息
			"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
			"bStateSave" : true,//状态保存
			"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false,
				"aTargets" : [ 0, ]
			} // 制定列不参与排序
			]
		});
		/*
		 参数解释：
		 title	标题
		 url		请求的url
		 id		需要操作的数据id
		 w		弹出层宽度（缺省调默认值）
		 h		弹出层高度（缺省调默认值）
		 */
		/*增加*/
		function insert(title, url, w, h) {
			layer_show(title, url, w, h);
		}
		/*编辑*/
		function update(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}

		function batchdel(url,string) {
			if ($(".checkbox-box:checked")) {
				if ($(".checkbox-box:checked").length > 0) {
					var pars = '';
					$(".checkbox-box:checked").each(function(i, v) {
						pars += '&ids=' + $(v).val();
					});
					layer.confirm('确认要'+string+'吗？', function(index) {
						$.ajax({
							type : 'POST',
							url : url,
							data : pars,
							success : function(data) {
								commonreuslt(data);
							}
						});
					});
				} else {
					layer.msg("请选择你要"+string+"的内容", {
						icon : 5,
						time : 1000
					});
				}
			}

		}

		/*管理员-删除*/
		function del(obj, url) {
			layer.confirm('确认要删除吗？', function(index) {
				$.ajax({
					type : 'POST',
					url : url,
					dataType : 'json',
					success : function(data) {
						commonreuslt(data);
					}
				});
			});
		}
	</script>
</body>
</html>