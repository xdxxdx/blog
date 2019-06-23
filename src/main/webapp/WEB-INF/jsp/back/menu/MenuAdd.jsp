<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>菜单新增</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!--layui-->
<link rel="stylesheet" href="../static/layui/css/layui.css">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="../static/AdminLTE-2.4.2/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="../static/AdminLTE-2.4.2/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
   folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="../static/AdminLTE-2.4.2/dist/css/skins/skin-red.css">
<!--验证-->
<link rel="stylesheet"
	href="../static/formvalidation/dist/css/formValidation.css">
<!--common.css-->
<link rel="stylesheet" href="../static/mycss/common.css">
<style type="text/css">
.form-horizontal .has-feedback .form-control-feedback {
	right: 0;
}

.form-group.has-error .help-block {
	float: left;
	padding-left: calc(20% + 18px);
}
.form-group.has-success label{
	color:#333;
}
.box{
	border-radius: 0;
}
</style>
</head>

<body class="hold-transition skin-red sidebar-mini">
		<div class="box box-danger">
			<div class="lay-form">
			<div class="box-header with-border">
				<h3 class="box-title">菜单新增</h3>
			</div>
			<form class="form-horizontal" id="thisForm">
				<input type="hidden" name="type" value="1">
				<div class="box-body">
					<div class="form-group">
						<label class="control-label label-layer-left" for="menuType">菜单类别</label>
						<div class="div-layer-left">
							<select name="menuType" id="menuType" class="form-control">
								<option value="1">主菜单</option>
								<option value="2">树菜单</option>
							</select>
						</div>
					</div>
					<input type="hidden" id="pMenuIdTmp" value="0">
					<input type="hidden" name="pMenuId" id="pMenuId" >
					<div class="form-group">
						<label class="control-label label-layer-left" for="pMenuId">上级菜单</label>
						<div class="div-layer-left">
							<select name="pMenuIdSelect" id="pMenuIdSelect" class="form-control">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label label-layer-left" for="menuName">菜单名称</label>
						<div class="div-layer-left">
							<input name="menuName" class="form-control"
								type="text" placeholder="请输入菜单名称">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label label-layer-left" for="menuIcon">菜单图标</label>
						<div class="div-layer-left">
							<input name="menuIcon" class="form-control"
								type="text" placeholder="请输入菜单图标">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label label-layer-left" for="menuSrc">菜单地址</label>
						<div class="div-layer-left">
							<input  name="menuSrc" class="form-control"
								type="text" placeholder="请输入菜单地址">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label label-layer-left" for="menuIntro">简介</label>
						<div class="div-layer-left">
							<textarea class="form-control" name="menuIntro" rows="3" placeholder="Enter ..."></textarea>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<div class="btn btn-default" type="button">取消</div>
					<div class="btn btn-danger pull-right" type="button" id="submit1">提交</div>
				</div>
			</form>
		</div>

	</div>
	<!-- ./wrapper -->

	<!-- jQuery 3 -->
	<script
		src="../static/AdminLTE-2.4.2/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="../static/AdminLTE-2.4.2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../static/AdminLTE-2.4.2/dist/js/adminlte.min.js"></script>
	<!--layui-->
	<script src="../static/AdminLTE-2.4.2/dist/js/demo.js"></script>
	<script src="../static/layui/layui.js"></script>
	<!--表单验证-->
	<script type="text/javascript"
		src="../static/formvalidation/vendor/jquery/jquery.min.js"></script>
	<script type="text/javascript"
		src="../static/formvalidation/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="../static/formvalidation/dist/js/formValidation.js"></script>
	<script type="text/javascript"
		src="../static/formvalidation/dist/js/framework/bootstrap.js"></script>
	<script type="text/javascript"
		src="../static/formvalidation/dist/js/language/zh_CN.js"></script>
	<!--级联-->
	<script src="../static/js/jquery.chained.remote.min.js"></script>
	<script src="../static/myjs/common.js"></script>
	<!-- page script -->
	<script src="../static/myjs/menu/menu_add.js"></script>
</body>

</html>