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
			action="${pageContext.request.contextPath}/role/update.do">
			<!-- 隐藏表单 -->
			<c:if test="${user != null }">
				<input type="hidden" name="roleid" value="${user.roleid }">
			</c:if>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>角色：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.name }" placeholder="" id="name"
						name="name">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>权限设置(至少一项)：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<c:choose> 	
						 <c:when test="${select == 1}">
					      <input type="checkbox" checked="checked" name="power" value="select"/>查询
					    </c:when>
					    <c:otherwise>
					      <input type="checkbox" name="power" value="select"/>查询
					    </c:otherwise>
					</c:choose>
					<c:choose>  
					    <c:when test="${insert == 1}">
					      <input type="checkbox" checked="checked"  name="power" value="insert" />增加
					    </c:when>
					    <c:otherwise>
					      <input type="checkbox"  name="power" value="insert" />增加
					    </c:otherwise>
					</c:choose>		
					<c:choose> 	   
					     <c:when test="${update == 1}">
					      <input type="checkbox"  checked="checked" name="power" value="update"/>更改
					    </c:when>
					    <c:otherwise>
					      <input type="checkbox" name="power" value="update"/>更改
					    </c:otherwise>
					</c:choose>	
					<c:choose> 	   
					     <c:when test="${delete == 1}">
					      <input type="checkbox" checked="checked" name="power" value="delete" />删除	
					    </c:when>
					    <c:otherwise>
					      <input type="checkbox"  name="power" value="delete" />删除	
					    </c:otherwise>
					</c:choose>	
				</div>		
					
					
				<div class="formControls col-xs-8 col-sm-9">
					  
		
			</div>
			
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">备注：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.remarks }" placeholder="" id="remarks"
						name="remarks">
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
				name : {
					required : true,
					minlength : 2,
					maxlength : 50
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