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
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
		<a href="javascript:;"
				onclick="batchdel('${pageContext.request.contextPath}/booksreceive/batchDelete.do','删除')"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除 </a>	
		<a href="javascript:;"
				onclick="insert('添加','${pageContext.request.contextPath}/booksreceive/add.do','800','500')"
				class="btn btn-primary radius"> <i class="Hui-iconfont">&#xe600;</i>
					添加
		</a> 
			</span>
		</div>
		<table
			class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th hidden="ture">ID</th>
					<th>单据号</th>
					<th>流水号</th>
					<th>家具图书名称</th>
					<th>领用时间</th>
					<th>领用人</th>
					<th>归还日期</th>					
					<th>状态</th>
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
						<td>${record.code_S }</td>
						<td>${record.name }</td>
						<td>${record.receivedate  }</td>
						<td>${record.recipients }</td>						
						<td>${record.returndate }</td>
						<c:choose>
							<c:when test="${record.status  == 1}">
						      <td>领用</td>
						    </c:when>
						    <c:when test="${record.status  == 2}">
						      <td>归还</td>
						     </c:when>
						</c:choose>
						<td class="td-manage">
						
							<c:choose>
								<c:when test="${record.status  == 1}">
							      <a title="归还" href="javascript:;"
							onclick="update('归还','${pageContext.request.contextPath}/booksreceive/return.do?id=${record.id }','1','800','500')"
							class="ml-5" style="text-decoration: none"><i
								class="Hui-iconfont">&#xe603;</i></a> 
							    </c:when>
							   <c:otherwise>
							    <a title="删除"
									href="javascript:;"
									onclick="del(this,'${pageContext.request.contextPath}/booksreceive/del.do?id=${record.id}')"
									class="ml-5" style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a>
							    </c:otherwise>
							</c:choose>
						
							<c:if test="${record.status  ==1}">
								
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