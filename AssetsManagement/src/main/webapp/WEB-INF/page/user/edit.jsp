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
			action="${pageContext.request.contextPath}${user.id!=0?'/user/update.do' : '/user/insert.do'}">
			<!-- 隐藏表单 -->
			<c:if test="${user != null }">
				<input type="hidden" name="id" value="${user.id }">
			</c:if>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>姓名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.name }" placeholder="" id="name"
						name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>角色：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<select name="roleid" class="input-text" >
						<c:forEach items="${requestScope.roles }" var="role">
							<c:if test="${user.roleid==role.roleid }">
								<option  selected="selected"  value=${role.roleid } >${role.name }
							</c:if>
							<c:if test="${user.roleid!=role.roleid }">
								<option  value=${role.roleid } >${role.name } 
							</c:if>
						</c:forEach> 
					</select>		
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>部门：</label>
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
					class="c-red">*</span>密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" class="input-text" autocomplete="off"
						value="${user.pwd }" placeholder="" id="pwd"
						name="pwd">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>性别：</label>
				<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input name="sex" type="radio" id="sex-1" value="1"
							${user.sex == 1 ? 'checked' : '' }> <label
							for="sex-1">男</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="sex-2" name="sex" value="2"
							${user.sex == 2 ? 'checked' : '' }> <label
							for="sex-2">女</label>
					</div>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>生日：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.birth }" placeholder="" id="birth"
						name="birth">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>手机：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.phone }" placeholder="" id="phone"
						name="phone">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>邮箱：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.email }" placeholder="@" name="email"
						id="email">
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
			elem : '#birth' //指定元素
		});

		$("#form").validate({
			rules : {
				name : {
					required : true,
					minlength : 2,
					maxlength : 50
				},
				pwd : {
					required : true,
					minlength : 6,
				},
				sex : {
					required : true,
				},
				birth : {
					required : true,
					dateISO : true
				},
				phone : {
					required : true,
					minlength : 11,
					maxlength : 11,
					digits : true
				},
				email : {
					required : true,
					email : true,
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