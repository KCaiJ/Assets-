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
				action="${pageContext.request.contextPath}/device/getName.do">
				<input type="text" class="input-text" style="width: 250px"
					placeholder="流水号" id="code" name="code" value="${requestScope.code}">
				<button type="submit" class="btn btn-success" id="" name="">
					<i class="Hui-iconfont">&#xe665;</i> 查询
				</button>
			</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;"
				onclick="batchdel('${pageContext.request.contextPath}/device/batchDelete.do')"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除 </a> 
			<a href="javascript:;"
				onclick="insert('添加','${pageContext.request.contextPath}/device/add.do','800','400')"
				class="btn btn-primary radius"> <i class="Hui-iconfont">&#xe600;</i>
					添加
			</a> 
			<a href="javascript:;"
				onclick="insert('添加','${pageContext.request.contextPath}/device/import.do','800','400')"
				class="btn btn-primary radius"> <i class="Hui-iconfont">&#xe645;</i>
					导入数据
			</a> 
			<a href="${pageContext.request.contextPath}/device/export.do?string=deviceData"
				class="btn btn-primary radius"> <i class="Hui-iconfont">&#xe644;</i>
					数据导出
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
					<th>入库人</th>
					<th>入库时间</th>
					<th>购买人</th>
					<th>购买时间</th>					
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.records }" var="record">
					<tr class="text-c">
						<td><input type="checkbox" value="${record.did }"
							class="checkbox-box"></td>
						<td hidden="true">${record.did }</td>
						<td>${record.code }</td>
						<td>${record.brand }</td>
						<td>${record.model }</td>
						<td>${record.intlcode  }</td>
						<td>${record.creator }</td>
						<td>${record.createtime }</td>
						<td>${record.buyer }</td>
						<td>${record.bugdate }</td>
						
						<c:choose>
							<c:when test="${record.status  == 1}">
						      <td>入库</td>
						    </c:when>
						    <c:when test="${record.status  == 2}">
						      <td>出库中</td>
						    </c:when>
						    <c:when test="${record.status  == 3}">
						      <td>出库</td>
						    </c:when>
						    <c:when test="${record.status  == 4}">
						     <td>领用</td>
						    </c:when>
						    <c:when test="${record.status  == 5}">
						      <td>报修</td>
						    </c:when>
						</c:choose>
						
						<td class="td-manage"><a title="编辑" href="javascript:;"
							onclick="update('编辑','${pageContext.request.contextPath}/device/get.do?id=${record.did }','1','800','500')"
							class="ml-5" style="text-decoration: none"><i
								class="Hui-iconfont">&#xe6df;</i></a> 
								
							<c:if test="${record.status  ==1||record.status==3}">
								<a title="删除"
							href="javascript:;"
							onclick="del(this,'${pageContext.request.contextPath}/device/del.do?id=${record.did}')"
							class="ml-5" style="text-decoration: none"><i
								class="Hui-iconfont">&#xe6e2;</i></a>
							</c:if>
							
							
								
								
							<c:if test="${record.status  ==1}">
								<a title="出库"
								href="javascript:;"
								onclick="update('出库','${pageContext.request.contextPath}/device/out.do?id=${record.did }','1','700','400')"
								class="ml-5" style="text-decoration: none"><i
								class="Hui-iconfont">&#xe634;</i></a>
							</c:if>
							
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

		function batchdel(url) {
			if ($(".checkbox-box:checked")) {
				if ($(".checkbox-box:checked").length > 0) {
					var pars = '';
					$(".checkbox-box:checked").each(function(i, v) {
						pars += '&ids=' + $(v).val();
					});
					layer.confirm('确认要删除吗？', function(index) {
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
					layer.msg("请选择你要删除的内容", {
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
		function expot(obj, url) {
			$.ajax({
				type : 'POST',
				url : url,
				dataType : 'json',
				success : function(data) {
					commonreuslt(data);
				}
			});
		}
	</script>
</body>
</html>