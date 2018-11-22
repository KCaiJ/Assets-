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
			action="${pageContext.request.contextPath}/car/update.do?id=${user.did}">	
		
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>车辆类型：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<select name="dtid" class="input-text" >
						<c:forEach items="${requestScope.types }" var="role">
							<c:if test="${user.dtid==role.dtid }">
								<option  selected="selected"   value=${role.dtid  } >${role.name } 
							</c:if>
							<c:if test="${user.dtid!=role.dtid }">
								<option  value=${role.dtid  } >${role.name } 
							</c:if>
						</c:forEach> 
					</select>		
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
					class="c-red">*</span>净残值：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" autocomplete="off"
						value="${user.residual }" placeholder="" id="residual"
						name="residual">
				</div>
			</div>
			
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>原值：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" autocomplete="off"
						value="${user.original }" placeholder="" id="original"
						name="original">
				</div>
			</div>
			
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>生产日期：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
					value="${user.proddate }" placeholder="" id="proddate"
						name="proddate">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>购买人：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
					value="${user.buyer }"	 placeholder="" id="buyer"
						name="buyer">
				</div>
			</div>
			
			
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>购买日期：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
					value="${user.bugdate }"	 placeholder="" id="bugdate"
						name="bugdate">
				</div>
			</div>
			
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>序列号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${user.sno }" placeholder="" id="sno"
						name="sno">
				</div>
			</div>
			
			<div class="row cl">	
				<div class="formControls col-xs-8 col-sm-9">
					<input id="did" name="did" value="${user.did }" hidden="true"> 
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