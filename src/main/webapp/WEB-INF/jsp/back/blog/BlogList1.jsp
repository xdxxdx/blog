<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>博客管理</title>
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
		<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
		<link rel="stylesheet" href="../static/AdminLTE-2.4.2/dist/css/skins/skin-red.css">
		<!--sui-->
		<link rel="stylesheet" href="../static/sui/css/sui.min.css">
		<!--自定义css-->
		<link rel="stylesheet" href="../static/mycss/common.css">
		<style>
			.blog_list_main {
				margin: 10px;
				min-width: 1004px;
			}
			
			.blog_list_header {
				margin: 5px, 0;
				font-size: 18px;
				color: #b5b5b5;
				padding-left: 2px;
				font-weight: 500;
			}
			
			.blog_list_content {
				margin: 0 14px 0 14px;
			}
			
			.blog_list_left {
				float: left;
				width: 180px;
				/*margin-right: -180px;*/
				text-align: left;
				border-style: solid;
				border-width: 0 1px 0 0;
				border-color: #d5c0c0;
			}
			
			.menu_title {
				margin: 10px 24px 5px 0;
				padding: 0 0 6px 3px;
				border-style: solid;
				border-width: 0 0 1px;
				font-size: 13px;
				border-color: #d5c0c0;
				color: #333333;
				font-weight: bold;
			}
			
			.blog_list_right {
				margin-left: 180px;
				padding-top: 20px;
				padding-left: 25px;
				overflow: hidden;
			}
			
			.blog_list_left ul li {
				line-height: 20px;
				cursor: pointer;
				color: #333333;
				text-align: left;
			}
			
			.blog_list_left ul li a {
				text-decoration: none;
				color: #333333;
				font-family: "Hiragino Sans GB W3", "Hiragino Sans GB", Arial, Helvetica, simsun, u5b8bu4f53;
				font-size: 12px;
			}
			.blog_list_left ul li a:hover {color: #d5c0c0!important}
			.li_selected{
				background-color: #e6e1e1;
			}
			
			.blog_list_left ul li:before {
				content: '.';
				font-weight: 800;
				vertical-align: 2px;
			}
			
			.blog_list_item {
				margin-bottom: 8px;
				padding: 0 10px 12px 3px;
				border-color: #d5c0c0;
				border-width: 0 0 1px 0;
				border-style: dashed;
			}
			
			h4.blog_title {
				font-weight: bold;
				font-family: "Hiragino Sans GB W3", "Hiragino Sans GB", "Microsoft YaHei", "\5FAE\8F6F\96C5\9ED1", Arial, Helvetica, simsun, "\5B8B\4F53";
				line-height: 24px;
				margin: 0;
				padding: 0;
				font-size: 16px;
			}
			
			p.blog_info {
				color: #999999;
				font-size: 12px;
				margin-bottom: 2px;
			}
			
			.span_read_num,
			.span_comment_num {
				margin: 0 5px;
				float: none;
			}
			.blog_list_item .blog_operate{
				margin-top: 10px;
				margin-right:20px;
			}
			.blog_list_item .blog_operate span{
				margin-left:15px;
			}
		</style>
	</head>

	<body class="hold-transition skin-red sidebar-mini">
		<div class="wrapper">
			<c:import url="/head"></c:import>
			<c:import url="/side"></c:import>
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>
				博客<small>列表</small>
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
						<div class="blog_list_main">
							<div class="blog_list_header">
								日志</div>
							<div class="blog_list_content clearfix">
								<div class="blog_list_left pull-left">
									<h4 class="menu_title">分类</h4>
									<ul>
										<li class="li_selected">
											<a href="#" class="">web前端（123）</a>
										</li>
										<li>
											<a href="#">java后端（324）</a>
										</li>
										<li>
											<a href="#">架构师之路</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
									</ul>
									<h4 class="menu_title">标签</h4>
									<ul>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
									</ul>
									<h4 class="menu_title">归档</h4>
									<ul>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
										<li>
											<a href="#">web前端</a>
										</li>
									</ul>
								</div>
								<div class="blog_list_right">
									<div class="list_content">
										<div class="blog_list_item clearfix ">
											<div class="pull-left">
												<h4 class="blog_title">RabbitMQ在java项目中的应用</h4>
												<p class="blog_info">
													<span>2012-06-18 02:30</span>
													<span class="span_read_num">阅读1123</span>
													<span class="span_comment_num">评论1123</span>
												</p>
											</div>
											<div class="pull-right blog_operate">
												<span class="blog_edit"><a class="">编辑</a></span><span class="blog_edit"><a class="">删除</a></span>
											</div>
										</div>
										<div class="blog_list_item clearfix ">
											<div class="pull-left">
												<h4 class="blog_title">js的一些技巧</h4>
												<p class="blog_info">
													<span>2012-06-18 02:30</span>
													<span class="span_read_num">阅读1123</span>
													<span class="span_comment_num">评论1123</span>
												</p>
											</div>
											<div class="pull-right blog_operate">
												<span class="blog_edit"><a class="">编辑</a></span><span class="blog_edit"><a class="">删除</a></span>
											</div>
										</div>
										<div class="blog_list_item clearfix ">
											<div class="pull-left">
												<h4 class="blog_title">websocket原理简介</h4>
												<p class="blog_info">
													<span>2012-06-18 02:30</span>
													<span class="span_read_num">阅读1123</span>
													<span class="span_comment_num">评论1123</span>
												</p>
											</div>
											<div class="pull-right blog_operate">
												<span class="blog_edit"><a class="">编辑</a></span><span class="blog_edit"><a class="">删除</a></span>
											</div>
										</div>
										<div class="blog_list_item clearfix ">
											<div class="pull-left">
												<h4 class="blog_title">左小祖咒在音乐上的成就</h4>
												<p class="blog_info">
													<span>2012-06-18 02:30</span>
													<span class="span_read_num">阅读1123</span>
													<span class="span_comment_num">评论1123</span>
												</p>
											</div>
											<div class="pull-right blog_operate">
												<span class="blog_edit"><a class="">编辑</a></span><span class="blog_edit"><a class="">删除</a></span>
											</div>
										</div>
											<div class="blog_list_item clearfix ">
											<div class="pull-left">
												<h4 class="blog_title">左小祖咒在音乐上的成就</h4>
												<p class="blog_info">
													<span>2012-06-18 02:30</span>
													<span class="span_read_num">阅读1123</span>
													<span class="span_comment_num">评论1123</span>
												</p>
											</div>
											<div class="pull-right blog_operate">
												<span class="blog_edit"><a class="">编辑</a></span><span class="blog_edit"><a class="">删除</a></span>
											</div>
										</div>
											<div class="blog_list_item clearfix ">
											<div class="pull-left">
												<h4 class="blog_title">左小祖咒在音乐上的成就</h4>
												<p class="blog_info">
													<span>2012-06-18 02:30</span>
													<span class="span_read_num">阅读1123</span>
													<span class="span_comment_num">评论1123</span>
												</p>
											</div>
											<div class="pull-right blog_operate">
												<span class="blog_edit"><a class="">编辑</a></span><span class="blog_edit"><a class="">删除</a></span>
											</div>
										</div>
											<div class="blog_list_item clearfix ">
											<div class="pull-left">
												<h4 class="blog_title">左小祖咒在音乐上的成就</h4>
												<p class="blog_info">
													<span>2012-06-18 02:30</span>
													<span class="span_read_num">阅读1123</span>
													<span class="span_comment_num">评论1123</span>
												</p>
											</div>
											<div class="pull-right blog_operate">
												<span class="blog_edit"><a class="">编辑</a></span><span class="blog_edit"><a class="">删除</a></span>
											</div>
										</div>
											<div class="blog_list_item clearfix ">
											<div class="pull-left">
												<h4 class="blog_title">左小祖咒在音乐上的成就</h4>
												<p class="blog_info">
													<span>2012-06-18 02:30</span>
													<span class="span_read_num">阅读1123</span>
													<span class="span_comment_num">评论1123</span>
												</p>
											</div>
											<div class="pull-right blog_operate">
												<span class="blog_edit"><a class="">编辑</a></span><span class="blog_edit"><a class="">删除</a></span>
											</div>
										</div>
											<div class="blog_list_item clearfix ">
											<div class="pull-left">
												<h4 class="blog_title">左小祖咒在音乐上的成就</h4>
												<p class="blog_info">
													<span>2012-06-18 02:30</span>
													<span class="span_read_num">阅读1123</span>
													<span class="span_comment_num">评论1123</span>
												</p>
											</div>
											<div class="pull-right blog_operate">
												<span class="blog_edit"><a class="">编辑</a></span><span class="blog_edit"><a class="">删除</a></span>
											</div>
										</div>
											<div class="blog_list_item clearfix ">
											<div class="pull-left">
												<h4 class="blog_title">左小祖咒在音乐上的成就</h4>
												<p class="blog_info">
													<span>2012-06-18 02:30</span>
													<span class="span_read_num">阅读1123</span>
													<span class="span_comment_num">评论1123</span>
												</p>
											</div>
											<div class="pull-right blog_operate">
												<span class="blog_edit"><a class="">编辑</a></span><span class="blog_edit"><a class="">删除</a></span>
											</div>
										</div>
										
									</div>
									<div class="blog_list_page">
										<div class="sui-pagination">
											<ul>
												<li class="prev disabled">
													<a href="#">«上一页</a>
												</li>
												<li class="active">
													<a href="#">1</a>
												</li>
												<li>
													<a href="#">2</a>
												</li>
												<li>
													<a href="#">3</a>
												</li>
												<li>
													<a href="#">4</a>
												</li>
												<li>
													<a href="#">5</a>
												</li>
												<li class="dotted"><span>...</span></li>
												<li class="next">
													<a href="#">下一页»</a>
												</li>
											</ul>
											<div><span>共10页&nbsp;</span><span>到<input class="page-num" type="text"><button class="page-confirm" onclick="alert(1)">确定</button>页</span></div>
										</div>
									</div>
								</div>
							</div>
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
		<!-- ./wrapper -->

		<!-- jQuery 3 -->
		<script src="../static/AdminLTE-2.4.2/bower_components/jquery/dist/jquery.min.js"></script>
		<!-- jQuery UI 1.11.4 -->
		<script src="../static/AdminLTE-2.4.2/bower_components/jquery-ui/jquery-ui.min.js"></script>
		<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
		<!-- Bootstrap 3.3.7 -->
		<script src="../static/AdminLTE-2.4.2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- AdminLTE App -->
		<script src="../static/AdminLTE-2.4.2/dist/js/adminlte.min.js"></script>
		<!--sui-->
		<script src="../static/sui/js/sui.min.js"></script>
		<script src="../static/myjs/common.js"></script>
		<script src="../static/myjs/blog/blog.js"></script>
		<script type="text/html" id="xdx_bar">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>
	</body>

</html>