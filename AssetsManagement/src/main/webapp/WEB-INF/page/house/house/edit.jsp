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
			action="${pageContext.request.contextPath}/house/update.do">
			<!-- 隐藏表单 -->
			<c:if test="${user != null }">
				<input type="hidden" name="did" value="${user.did }">
			</c:if>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>房子代号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" readonly="readonly"
						value="${user.housecode }" placeholder="" id="housecode"
						name="housecode">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>所属部门：</label>
				<div class="formControls col-xs-8 col-sm-9">
				<select name="oid" class="input-text" >
					<c:forEach items="${requestScope.organs }" var="organ">
						<c:if test="${user.oid==organ.oid }">
							<option  selected="selected"  value=${organ.oid }>${organ.name }
						</c:if>
						<c:if test="${user.oid!=organ.oid }">
							<option  value=${organ.oid }>${organ.name }
						</c:if>
						
					</c:forEach> 
				</select>			
					
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>房子位置：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.houselaction }" placeholder="" id="houselaction"
						name="houselaction">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>占地面积：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.housearea }" placeholder="" id="housearea"
						name="housearea">
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
		$("#form").validate({
			rules : {
				housearea : {
					required : true,
				},
				houselaction : {
					required : true,
				},
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