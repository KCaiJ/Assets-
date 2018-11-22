<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<jsp:include page="/WEB-INF/inc/common.jsp"></jsp:include>
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" id="form" method="post"
			action="${pageContext.request.contextPath}/house/acceptout.do">		
			<input hidden="ture"  id="did" name="did" value="${id }"/> 
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>被分配人：</label>
				<div class="formControls col-xs-8 col-sm-9">
				<select name="applyer" class="input-text" >
					<c:forEach items="${requestScope.users }" var="user">
						<option  value=${user.name }>${user.name }
					</c:forEach> 
				</select>			
					
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>申请备注：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea rows="10" cols="50" name="remarks"></textarea>
				</div>
			</div>
			
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</article>

	<script type="text/javascript">
		//日期控件
		laydate.render({
			elem : '#proddate,#bugdate' //指定元素
		});

		$("#form").validate({
			rules : {
				residual : {
					required : true,
				},
				original : {
					required : true,
				},
				creator : {
					required : true,
				},
				buyer : {
					required : true,
				},
				
				bugdate : {
					required : true,
					dateISO : true
				},
				
				proddate: {
					required : true,
					dateISO : true
				},
				
				sno : {
					required : true,
				}
			},
			onkeyup : false,
			focusCleanup : true,
			submitHandler : function(form) {
				$(form).ajaxSubmit({
					type : form.method,
					url : form.action,
					success : function(data) {
						 commonreuslt(data)
					}
				});
			}
		});
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>