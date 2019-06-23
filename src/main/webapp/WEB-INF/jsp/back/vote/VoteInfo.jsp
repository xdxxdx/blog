<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投票--基本设置</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!--layui-->
<link rel="stylesheet" href="../static/layui/css/layui.css">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="../static/AdminLTE-2.4.2/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="../static/AdminLTE-2.4.2/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="../static/AdminLTE-2.4.2/bower_components/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="../static/AdminLTE-2.4.2/dist/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="../static/AdminLTE-2.4.2/dist/css/skins/skin-red.css">
<!--验证-->
<link rel="stylesheet"
	href="../static/formvalidation/dist/css/formValidation.css">
<!--阿里图标-->
<link rel="stylesheet" href="../static/font/fontbase.css">
<!--自定义css-->
<link rel="stylesheet" href="../static/mycss/common.css">
<style>
.vote-nav {
	background: #fff;
	padding: 0;
	margin: 0;
	height: 40px;
	line-height: 40px;
	border-bottom: 1px solid #d2d2d2;
	padding-left: 120px;
	margin-top: 15px;
}

.vote-nav .next {
	float: left;
	margin: 0 5px;
}

.vote-nav .active span {
	height: 40px;
	display: block;
	border-bottom: 3px solid #1b1b1b;
}

.vote-nav .next i {
	color: #afafaf;
	margin-right: 10px;
}

.vote-nav .next span {
	font-size: 13px;
}
.vote-time input{
	width:40%;
	margin-left: 5px;
	float: left;
}
.vote-time span{
	float: left;
	line-height: 30px;
}
.next-step{
	width: 160px;
	height: 40px;
	margin-left: 9%;
	background: #F7B824;
	font-size: 16px;
	color: #fff;
	line-height: 40px;
	text-align: center;
	cursor: pointer;
}
.next-step i{
	font-size: 18px;
}
</style>
</head>

<body class="hold-transition skin-red sidebar-mini"
	menu_id="${menu.menuId}" p_menu_id="${menu.pMenuId}">
	<div class="wrapper">
		<c:import url="/head"></c:import>
		<c:import url="/side"></c:import>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>
				投票<small>发起</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
				<li class="active">投票</li>
			</ol>
			</section>
			<ul class="vote-nav">
				<li class="next active"><span><a href="">基本设置</a></span></li>
				<li class="next"><i class="iconfont">&#xe656;</i> <span><a
						href="">投票选项</a></span></li>
				<li class="next"><i class="iconfont">&#xe656;</i> <span><a
						href="">其他设置</a></span></li>
			</ul>
			<!-- Main content -->
			<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-11">
						<form class="form-horizontal" id="thisForm">
							<input type="hidden" name="type" value="1">
							<div class="box-body">
								<div class="form-group">
									<label class="control-label label-layer-left-8" for="title">活动名称</label>
									<div class="div-layer-left-80">
										<input name="blogTitle" class="form-control" type="text"
											placeholder="请输入活动名称">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label label-layer-left-8" for="menuIcon">投票类型</label>
									<div class="div-layer-left-80">
										<select class="category" name="categoryId">
											<OPTION value="1">常规投票</OPTION>
											<OPTION value="2">图文投票</OPTION>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label label-layer-left-8" for="roleIntro">投票时间</label>
									<div class="div-layer-left-80 vote-time">
										<input name="startTime"
											value="<fmt:formatDate value="${vote.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
											class="form-control" type="text"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											placeholder="开始时间"><span> 至</span><input
											name="endTime"
											value="<fmt:formatDate value="${vote.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
											class="form-control" type="text"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											placeholder="截止时间">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label label-layer-left-8" for="menuIcon">活动规则</label>
									<div class="div-layer-left-80">
										<div type="text/plain" id="myEditor" name="blogContent"
											style="width: 100%; height: 500px;"></div>
									</div>
								</div>
							</div>
							<div class="next-step">
								<i class="iconfont">&#xe656;</i>保存并继续设置
							</div>
						</form>
					</div>
					<!--</div>-->
				</div>
			</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer">
		<div class="pull-right hidden-xs">
			<b>Version</b> 2.4.0
		</div>
		<strong>Copyright &copy; 2014-2016 <a
			href="http://www.xdxxdxxdx.com" target="blank">专注web开发</a>.
		</strong> All rights reserved. </footer>
	</div>
	<!-- jQuery 3 -->
	<script
		src="../static/AdminLTE-2.4.2/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script
		src="../static/AdminLTE-2.4.2/bower_components/jquery-ui/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<!-- Bootstrap 3.3.7 -->
	<script
		src="../static/AdminLTE-2.4.2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../static/AdminLTE-2.4.2/dist/js/adminlte.min.js"></script>
	<!--layui-->
	<script type="text/javascript" src="../static/layui/layui.js"></script>
	<!-- 百度编辑器 -->
	<script type="text/javascript" charset="utf-8"
		src="/ueditor1_4_3/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="/ueditor1_4_3/ueditor.all.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="/ueditor1_4_3/lang/zh-cn/zh-cn.js">
	<!--表单验证-->
		<script type="text/javascript"
		src="../static/formvalidation/vendor/jquery/jquery.min.js">
	</script>
	<script type="text/javascript"
		src="../static/formvalidation/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="../static/formvalidation/dist/js/formValidation.js"></script>
	<script type="text/javascript"
		src="../static/formvalidation/dist/js/framework/bootstrap.js"></script>
	<script type="text/javascript"
		src="../static/formvalidation/dist/js/language/zh_CN.js"></script>
	<!--日期控件-->
	<script type="text/javascript"
		src="/static/My97DatePicker/WdatePicker.js"></script>
	<script src="../static/myjs/common.js"></script>
	<script src="../static/myjs/blog/blog_add.js"></script>
</body>
</html>