<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>博客新增</title>
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<!--layui-->
		<link rel="stylesheet" href="../static/layui/css/layui.css">
		<!-- Bootstrap 3.3.7 -->
		<link rel="stylesheet" href="../static/AdminLTE-2.4.2/bower_components/bootstrap/dist/css/bootstrap.min.css">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="../static/AdminLTE-2.4.2/bower_components/font-awesome/css/font-awesome.min.css">
		<!-- Ionicons -->
		<link rel="stylesheet" href="../static/AdminLTE-2.4.2/bower_components/Ionicons/css/ionicons.min.css">
		<!-- Theme style -->
		<link rel="stylesheet" href="../static/AdminLTE-2.4.2/dist/css/AdminLTE.min.css">
		<link rel="stylesheet" href="../static/AdminLTE-2.4.2/dist/css/skins/skin-red.css">
		<!--验证-->
		<link rel="stylesheet"
			href="../static/formvalidation/dist/css/formValidation.css">
		<!--wangeditor-->
		<link rel="stylesheet" type="text/css" href="/static/wangEditor/wangEditor.min.css">
		<!--自定义css-->
		<link rel="stylesheet" href="../static/mycss/common.css">
		<style>
			.submit_ok{
				/*/text-align: center;*/
				padding-left:40px;
			}
			.btn-div{
				float:left;
				width:70px;
				height: 28px;
				line-height: 28px;
				text-align: center;
				font-size: 14px;
				font-weight: 500;
				color: #FFFFFF;
				background-color: #3c8dbc;
				border: #595151 solid 1px;
				margin-left:10px;
				cursor: pointer;
			}
			ul.tag li{
				float:left;
				margin:5px 5px;
			}
			ul.tag li input{
				margin-right: 3px;
			}
			select.category{
				width: 200px;
				margin-top:5px;
			}
			.w-e-text-container{
				height: 650px !important;
			}
			
			.word-count{
				position: relative;
			}
			.word-count .word-wrap{
				position: absolute;
				right: 6px;
				bottom: 6px;
			}
			.word-count .word-wrap .current-num{
				color: red;
				padding: 0 4px;
			}
			.form-group #isPrivate{
				margin-top: 10px;
			}
			.div-layer-left-80{
				width:90%;
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
				博客<small>新博客</small>
			</h1>
					<ol class="breadcrumb">
						<li>
							<a href="#"><i class="fa fa-dashboard"></i> 首页</a>
						</li>
						<li class="active">博客</li>
					</ol>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-xs-11">
								<form class="form-horizontal" id="thisForm">
									<input type="hidden" name="type" value="1">
									<div class="box-body">
										<div class="form-group">
											<label class="control-label label-layer-left-8" for="title">标题</label>
											<div class="div-layer-left-80">
												<input name="blogTitle" class="form-control" type="text" placeholder="请输入文章标题">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label label-layer-left-8" for="roleIntro">摘要</label>
											<div class="div-layer-left-80">
												<div class="word-count">
													<textarea class="form-control" id="blogAbstract" name="blogAbstract" rows="3" placeholder="摘要,150字以内"></textarea>
													<span class="word-wrap">
														<span class='current-num'>150</span>/150
													</span>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label label-layer-left-8" for="menuIcon">正文</label>
											<div class="div-layer-left-80">
												<div id="editor" style="width:100%;">
												　<!-- 默认显示 -->
												   <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
												</div>
												<input type="hidden" name="blogContent" id="blogContent">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label label-layer-left-8" for="menuIcon">标签</label>
											<div class="div-layer-left-80 check-wrap">
													<c:forEach items="${tagList}" var="tag" varStatus="status">
														<c:if test="${status.count eq 1 || (status.count-1) % 10 eq 0}"><ul class="tag clearfix"></c:if>
														<li><input type="checkbox" name="ck_tag" value="${tag.tagId}">${tag.tagName}</li>
														<c:if test="${status.count % 10 eq 0 || status.count eq 10}"></ul></c:if>
													</c:forEach>
											</div>
											<input type="hidden" name="tags" id="tags">
										</div>
										<div class="form-group">
											<label class="control-label label-layer-left-8" for="menuIcon">类别</label>
											<div class="div-layer-left-80">
												<select class="category" name="categoryId">
													<c:forEach items="${categoryList}" var="category">
														<OPTION value="${category.categoryId}">${category.categoryName}</OPTION>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label label-layer-left-8" for="menuIcon">设为私密</label>
											<div class="div-layer-left-80">
												<input type="checkbox" name="isPrivate" id="isPrivate">
											</div>
										</div>
									</div>
									<div class="submit_ok">
										<div class="btn-div" id="submit1">保存</div>
										<div class="btn-div"  id="publish">发布</div>
										<div class="btn-div"  id="cancel">取消</div>
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
		<script src="../static/AdminLTE-2.4.2/bower_components/jquery/dist/jquery.min.js"></script>
		<!-- jQuery UI 1.11.4 -->
		<script src="../static/AdminLTE-2.4.2/bower_components/jquery-ui/jquery-ui.min.js"></script>
		<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
		<!-- Bootstrap 3.3.7 -->
		<script src="../static/AdminLTE-2.4.2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- AdminLTE App -->
		<script src="../static/AdminLTE-2.4.2/dist/js/adminlte.min.js"></script>
		<!--layui-->
		<script type="text/javascript" src="../static/layui/layui.js"></script>
		 <script src="/static/wangEditor/wangEditor.js"></script>
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
		<script src="../static/myjs/common.js"></script>
		<script src="../static/myjs/blog/blog_add.js"></script>
		<script type="text/javascript">
		</script>
	</body>
</html>