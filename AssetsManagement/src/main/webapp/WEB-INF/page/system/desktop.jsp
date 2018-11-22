<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<jsp:include page="/WEB-INF/inc/common.jsp"></jsp:include>
<title>我的桌面</title>
</head>
<body>
	<div class="page-container">
		<p class="f-20 text-success">
			欢迎使用固定资产系统 <span class="f-14">v1.0</span>
		</p>
		<p>上次登录IP：${user.logintime } 上次登录时间：${user.loginip }</p>
		<table class="table table-border table-bordered table-bg mt-20">
			<thead>
				<tr>
					<th colspan="2" scope="col">服务器信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>JDK版本</td>
					<td> <%=System.getProperties().getProperty("java.version")%></td>
				</tr>
				<tr>
					<td>处理器个数</td>
					<td><%=Runtime.getRuntime().availableProcessors()%></td>
				</tr>
				<tr>
					<td>总内存</td>
					<td><%=Runtime.getRuntime().totalMemory()/1024/1024%>M</td>
				</tr>
				<tr>
					<td>剩余内存</td>
					<td><%=Runtime.getRuntime().freeMemory()/1024/1024%>M</td>
				</tr>
			</tbody>
		</table>
	</div>
	<footer class="footer mt-20">
		<div class="container">
			<p>
				感谢jQuery、layer、laypage、Validform、UEditor、My97DatePicker、iconfont、Datatables、WebUploaded、icheck、highcharts、bootstrap-Switch<br> Copyright &copy;2015-2017 H-ui.admin v3.1 All Rights Reserved.<br> 本后台系统由<a href="http://www.h-ui.net/" target="_blank" title="H-ui前端框架">H-ui前端框架</a>提供前端技术支持
			</p>
		</div>
	</footer>
</body>
</html>