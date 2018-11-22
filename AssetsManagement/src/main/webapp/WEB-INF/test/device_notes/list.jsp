itle>
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
				action="${pageContext.request.contextPath}//device_notes/getName.do">
				
			  <i class="Hui-iconfont">&#xe665;</i>审批结果
				<select name="" class="input-text" ></select>	
				
			</form>
		</div>
		
		
		<table
			class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th hidden="ture">ID</th>
					<th>流水号</th>
					<th>设备名称</th>
					<th>申请时间</th>
					<th>保修人</th>
					<th>审批时间</th>
					<th>审批人</th>
					
					<th>审批结果</th>
					<th>备注</th>
					
					
				</tr>
			</thead>
			
		</table>
	</div>
	
</body>
</html>