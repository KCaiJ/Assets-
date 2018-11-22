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
		<form class="form form-horizontal" id="form" method="post">
			<!-- 隐藏表单 -->
			<c:if test="${user != null }">
				<input type="hidden" name="id" value="${user.id }">
			</c:if>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">流水号</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="" placeholder="" id="number"
						name="number">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">设备类型</label>
				<div class="formControls col-xs-8 col-sm-9">
					<select name="" class="input-text" >
						
					</select>		
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">购买人</label>
				<div class="formControls col-xs-8 col-sm-9">
				<select name="" class="input-text" >
				
				
				</select>			
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">购买时间</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.date }" placeholder="" id="date"
						name="date">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">sno</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="" placeholder="" id="number"
						name="number">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">生产日期</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.date }" placeholder="" id="date"
						name="date">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">价格</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="" placeholder="" id="price"
						name="price">
				</div>
			</div>
			
		<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">净残值率</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="" placeholder="" id="price"
						name="price">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">使用年限</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="" placeholder="" id="year"
						name="year">
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
			elem : '#date' //指定元素
		});

		$("#form").validate({
			rules : {
				number : {
					required : true,
					minlength : 2,
					maxlength : 20
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