<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>头像及简介</title>
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
		<!--图片上传-->
		<link rel="stylesheet"
			href="/static/imgUpload/css/imgUpload.css">
		<!--自定义css-->
		<link rel="stylesheet" href="../static/mycss/common.css">
		<style>
			.submit_ok{
				text-align: center;
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
				管理员<small>头像及简介</small>
			</h1>
					<ol class="breadcrumb">
						<li>
							<a href="#"><i class="fa fa-dashboard"></i> 首页</a>
						</li>
						<li class="active">管理员</li>
					</ol>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-xs-11">
								<form class="form-horizontal" id="thisForm">
									<input type="hidden" name="adminId" value="${adminId}">
									<div class="box-body">
										<div class="form-group">
											<label class="control-label label-layer-left-8" for="roleIntro">简介</label>
											<div class="div-layer-left-80">
												<div class="word-count">
													<textarea class="form-control" id="briefInfo" name="briefInfo" rows="3" placeholder="简介,150字以内">${admin.briefInfo}</textarea>
													<span class="word-wrap">
														<span class='current-num'>150</span>/150
													</span>
												</div>
											</div>
										</div>
								         <div class="form-group">
				                            <label class="control-label label-layer-left-8" for="img">头像</label>
				                            <div class="div-layer-left-80">
				                                <!--<input id="img" name="upload" class="form-control"
				                                       type="file" value="${carousel.img}">-->
				                           <input type="hidden" name="userFace" value="${admin.userFace}">
					                      		<div class="img-box full">
													<section class=" img-section">
														<p class="up-p">图片：<span class="up-span">不超过500k，长宽180px</span></p>
														<script type='text/jade' class="templ">
															<section class="z_file fl">
																	<img src="/static/imgUpload/img/a11.png" class="add-img"> <input
																		type="file" name="upload"  class="file"/>
															</section>
														</script>
														<div class="z_photo upimg-div clear" >
												               	 <section class="z_file fl">
																	<c:choose>
																	    <c:when test="${not empty admin.userFace}">
																	        <img src="${admin.userFace}" class="add-img"/>
																	    </c:when>
																	    <c:otherwise><img src="/static/imgUpload/img/a11.png" class="add-img"></c:otherwise>
																	</c:choose>
												               	 	<input type="file" name="upload" id="file" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" multiple />
												               	 </section>
												         </div>
													 </section>
												</div>
				                            </div>
				                        </div>
									</div>
									<div class="submit_ok">
										<div class="btn btn-default" type="button" id="cancel">取消</div>
										<div class="btn btn-danger" type="button" id="submit1">提交</div>
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
			href="#" target="blank">专注web开发</a>.
		</strong> All rights reserved. </footer>
		</div>
	    <aside class="mask works-mask">
			<div class="mask-content">
				<p class="del-p">您确定要删除图片吗？</p>
				<p class="check-p"><span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span></p>
			</div>
		</aside>
		<!-- jQuery 3 -->
		<script src="../static/AdminLTE-2.4.2/bower_components/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap 3.3.7 -->
		<script src="../static/AdminLTE-2.4.2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- AdminLTE App -->
		<script src="../static/AdminLTE-2.4.2/dist/js/adminlte.min.js"></script>
		<!--layui-->
		<script type="text/javascript" src="../static/layui/layui.js"></script>
		<script src="../static/myjs/common.js"></script>
		<!--图片上传-->
		<script src="../static/imgUpload/js/imgUp.js"></script>
		<script src="../static/myjs/admin/admin_face.js"></script>
	</body>
</html>