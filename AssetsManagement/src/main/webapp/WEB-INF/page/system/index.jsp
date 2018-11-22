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
<title>主页</title>
</head>
<body>
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10 hidden-xs"
					href="${pageContext.request.contextPath}/system/index.do">OSSJK</a>
				<span class="logo navbar-slogan f-l mr-10 hidden-xs"></span> <a
					aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs"
					href="javascript:;">&#xe667;</a>
				<nav class="nav navbar-nav">
					<ul class="cl">
						<li class="navbar-levelone current"><a href="javascript:;">平台</a></li>
					</ul>
				</nav>
				<nav id="Hui-userbar"
					class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<!-- <li>超级管理员</li> -->
						<li class="dropDown dropDown_hover"><a href="#"
							class="dropDown_A">${sessionScope.assetuser.name } <i
								class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
								<li><a href="javascript:;" onClick="logout()">退出</a></li>
							</ul></li>
						<!-- 						<li id="Hui-msg"><a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size: 18px">&#xe68a;</i></a></li>-->
						<li id="Hui-skin" class="dropDown right dropDown_hover"><a
							href="javascript:;" class="dropDown_A" title="换肤"><i
								class="Hui-iconfont" style="font-size: 18px">&#xe62a;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" data-val="default"
									title="默认（黑色）">默认（黑色）</a></li>
								<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
								<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
								<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
								<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
								<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">
		<div class="menu_dropdown bk_2">
			<ul>
				<li><dl id="menu-user">
						<dt>系统管理</dt>
						<dd>
							<ul>
								<li><a
									data-href="${pageContext.request.contextPath}/user/getAll.do"
									data-title="用户列表" href="javascript:void(0)">用户列表</a></li>
								<li><a
									data-href="${pageContext.request.contextPath}/role/getAll.do"
									data-title="角色列表" href="javascript:void(0)">角色列表</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/organ/getAll.do"
									data-title="部门列表" href="javascript:void(0)">部门列表</a></li>		
							</ul>
						</dd>
						
						<dt>设备管理</dt>
						<dd>
							<ul>	
								<li><a
									data-href="${pageContext.request.contextPath}/deviceType/getAll.do"
									data-title="设备类型" href="javascript:void(0)">设备类型</a></li>
								<li><a
									data-href="${pageContext.request.contextPath}/device/getAll.do"
									data-title="设备登记" href="javascript:void(0)">设备登记</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/devicereceive/getAll.do"
									data-title="设备领用" href="javascript:void(0)">设备领用</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/devicerepair/getAll.do"
									data-title="设备维修" href="javascript:void(0)">设备维修</a></li>		
								<li><a
									data-href="${pageContext.request.contextPath}/deviceout/getAll.do"
									data-title="设备出库申请" href="javascript:void(0)">设备出库申请</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/deviceout/getAll_record.do"
									data-title="设备出库记录" href="javascript:void(0)">设备出库记录</a></li>					
							</ul>
						</dd>
						
						<dt>车辆管理</dt>
						<dd>
							<ul>	
								<li><a
									data-href="${pageContext.request.contextPath}/carType/getAll.do"
									data-title="车辆类型" href="javascript:void(0)">车辆类型</a></li>
								<li><a
									data-href="${pageContext.request.contextPath}/car/getAll.do"
									data-title="车辆登记" href="javascript:void(0)">车辆登记</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/carreceive/getAll.do"
									data-title="车辆领用" href="javascript:void(0)">车辆领用</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/carrepair/getAll.do"
									data-title="车辆维修" href="javascript:void(0)">车辆维修</a></li>		
								<li><a
									data-href="${pageContext.request.contextPath}/carout/getAll.do"
									data-title="车辆出库申请" href="javascript:void(0)">车辆出库申请</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/carout/getAll_record.do"
									data-title="车辆出库记录" href="javascript:void(0)">车辆出库记录</a></li>					
							</ul>
						</dd>
						
						<dt>土地管理</dt>
						<dd>
							<ul>	
								<li><a
									data-href="${pageContext.request.contextPath}/land/getAll.do"
									data-title="土地登记" href="javascript:void(0)">土地登记</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/landout/getAll.do"
									data-title="土地出库申请" href="javascript:void(0)">土地分配申请</a></li>						
							</ul>
						</dd>
						
						<dt>房子管理</dt>
						<dd>
							<ul>	
								<li><a
									data-href="${pageContext.request.contextPath}/house/getAll.do"
									data-title="房子登记" href="javascript:void(0)">房子登记</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/houseout/getAll.do"
									data-title="房子出库申请" href="javascript:void(0)">房子分配申请</a></li>						
							</ul>
						</dd>
						
						<dt>家具图书管理</dt>
						<dd>
							<ul>	
								<li><a
									data-href="${pageContext.request.contextPath}/booksType/getAll.do"
									data-title="家具图书类型" href="javascript:void(0)">家具图书类型</a></li>
								<li><a
									data-href="${pageContext.request.contextPath}/books/getAll.do"
									data-title="家具图书登记" href="javascript:void(0)">家具图书登记</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/booksreceive/getAll.do"
									data-title="家具图书领用" href="javascript:void(0)">家具图书领用</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/booksrepair/getAll.do"
									data-title="家具图书维修" href="javascript:void(0)">家具图书维修</a></li>		
								<li><a
									data-href="${pageContext.request.contextPath}/booksout/getAll.do"
									data-title="家具图书出库申请" href="javascript:void(0)">家具图书出库申请</a></li>	
								<li><a
									data-href="${pageContext.request.contextPath}/booksout/getAll_record.do"
									data-title="家具图书出库记录" href="javascript:void(0)">家具图书出库记录</a></li>					
							</ul>
						</dd>
					</dl></li>
			</ul>
		</div>


	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面"
						data-href="${pageContext.request.contextPath}/system/desktop.do">我的桌面</span>
						<em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0"
					src="${pageContext.request.contextPath}/system/desktop.do"></iframe>
			</div>
		</div>
	</section>

	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前</li>
			<li id="closeall">关闭全部</li>
		</ul>
	</div>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
		$(function() {
			$("body").Huitab({
				tabBar : ".navbar-wrapper .navbar-levelone",
				tabCon : ".Hui-aside .menu_dropdown",
				className : "current",
				index : 0,
			});
		});
		/*登出*/
		function logout() {
			layer.confirm(
							'确认要退出吗？',
							function(index) {
								location.href = "${pageContext.request.contextPath}/system/logout.do";
							});
		}
		/*个人信息*/
		function myselfinfo() {
			layer_show(
					"个人信息",
					"${pageContext.request.contextPath}/user/getId.do?id=${sessionScope.user.id}",
					800, 500);
		}
	</script>


</body>
</html>