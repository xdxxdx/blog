<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>菜单</title>
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<!--layui-->
		<link rel="stylesheet" href="../static/layui/css/layui.css">
		<!-- Bootstrap 3.3.7 -->
		<link rel="stylesheet" href="../static/AdminLTE-2.4.2/bower_components/bootstrap/dist/css/bootstrap.min.css">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="../static/AdminLTE-2.4.2/bower_components/font-awesome/css/font-awesome.min.css">
		<!-- Theme style -->
		<link rel="stylesheet" href="../static/AdminLTE-2.4.2/dist/css/AdminLTE.min.css">
		<link rel="stylesheet" href="../static/AdminLTE-2.4.2/dist/css/skins/skin-red.css">
		<!--ztree-->
		<link href="../static/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
		<!--自定义css-->
		<link rel="stylesheet" href="../static/mycss/common.css">
		<link rel="stylesheet" href="../static/mycss/menu.css">
		<style>
			.ztree li a {
				color: #28a3ef;
				vertical-align: -5px;
			}
		</style>
	</head>

	<body class="hold-transition skin-red sidebar-mini" menu_id="${menu.menuId}" p_menu_id="${menu.pMenuId}">
		<div class="wrapper">
			<c:import url="/head"></c:import>
			<c:import url="/side"></c:import>
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>
				菜单<small>列表</small>
			</h1>
					<ol class="breadcrumb">
						<li>
							<a href="#"><i class="fa fa-dashboard"></i> 首页</a>
						</li>
						<li class="active">菜单</li>
					</ol>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-xs-11">
								<div class="panel-body panel-body-padding-vert">
									<div class="box box-danger">
										<div class="panel-body">
											<div class="col-lg-3 col-md-3 col-sm-4 col-xs-6" style="padding-bottom: 2px">
												<div class="input-group">
													<div class="input-group-btn">
														<button type="button" class="btn btn-danger btn-sm">菜单名称</button>
													</div>
													<!-- /btn-group -->
													<input type="text" id="menuName" name="menuName" class="form-control">
												</div>
											</div>
											<div class="col-lg-4 col-md-3 col-sm-4 col-xs-6" style="padding-bottom: 2px">
												<div class="input-group">
													<div class="input-group-btn">
														<button type="button" class="btn btn-danger btn-sm">菜单类型</button>
													</div>
													<!-- /btn-group -->
													<SELECT id="menuType" name="menuType" class="form-control">
														<option value="">全部</option>
														<option value="1">根目录</option>
														<option value="2">树目录</option>
													</SELECT>
												</div>
											</div>

											<div class="col-lg-3 col-xs-4 col-js-3" style="padding-bottom: 5px">
												<button type="button" id="btn_search" class="btn btn-default btn-sm">
											<span class="glyphicon glyphicon-search"></span> 查询
										</button>
												<button type="button" class="btn btn-danger btn-sm btn_add">
											<span class="glyphicon glyphicon-plus"></span> 新增
										</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--<div class="row">-->
							<div class="col-xs-11">
								<div class="layer">
									<div id="tableMain">
										<ul id="dataTree" class="ztree">
										</ul>
									</div>
								</div>
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
		<div id="priority_div" class="priority_div" style="display: none;">
			<form id="thisForm">
				<div class="form-group">
					<div class="box-body">
					<label class="control-label label-layer-left" for="priority">优先级</label>
					<div class="div-layer-left">
						<input type="hidden" name="menuId">
						<input name="priority" class="form-control" type="text" placeholder="请输入优先级，必须是数字">
					</div>
					</div>
					<div class="box-footer">
						<div class="btn btn-default btn-sm" type="button">取消</div>
						<div class="btn btn-danger btn-sm pull-right" type="button" id="submit1">提交</div>
					</div>
				</div>
			</form>
		</div>
		<!-- ./wrapper -->

		<!-- jQuery 3 -->
		<script src="../static/AdminLTE-2.4.2/bower_components/jquery/dist/jquery.min.js"></script>
		<!-- jQuery UI 1.11.4 -->
		<script src="../static/AdminLTE-2.4.2/bower_components/jquery-ui/jquery-ui.min.js"></script>
		<!-- Bootstrap 3.3.7 -->
		<script src="../static/AdminLTE-2.4.2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- AdminLTE App -->
		<script src="../static/AdminLTE-2.4.2/dist/js/adminlte.min.js"></script>
		<!--layui-->
		<script type="text/javascript" src="../static/layui/layui.js"></script>
		<!--zTree-->
		<script src="../static//zTree/js/jquery.ztree.core.min.js"></script>
		<script src="../static/myjs/xdx.js"></script>
		<script src="../static/myjs/common.js"></script>
		<script src="../static/myjs/menu/menu.js"></script>
		<script type="text/html" id="xdx_bar">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>
	</body>

</html>