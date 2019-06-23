<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>私密日志</title>
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
<!-- Theme style -->
<link rel="stylesheet"
	href="../static/AdminLTE-2.4.2/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="../static/AdminLTE-2.4.2/dist/css/skins/skin-red.css">
<!--sui-->
<link rel="stylesheet" href="../static/sui/css/sui.min.css">
<!--自定义css-->
<link rel="stylesheet" href="../static/mycss/common.css">
<style>
.blog_list_main {
	margin-top: 10px;
	min-width: 1004px;
}

.blog_list_header {
	margin: 5px, 0;
	font-size: 18px;
	color: #b5b5b5;
	padding-left: 2px;
	font-weight: 500;
}
.blog_list_header .new-blog{
	margin-left:5px;
	width:175px;
	height: 34px;
	background: #FFFFFF;
	border: 1px solid #EEE;
	padding: 5px 9px;
}
.blog_list_header .new-blog >*{
	float:left;
}
.blog_list_header .new-blog a{
	color: #dd4b39;
	line-height: 20px;
	font-size: 15px;
	text-decoration: none;
	position: relative;
	padding-left: 5px;
	padding-right: 5px;
	font-weight: 600;
}
.blog_list_header .new-blog a .i-write{
	background: url(http://static1.51cto.com/edu/blog/images/03_110_1.png);
	position: absolute;
	top: 0;
	left: 0;
}
.blog_list_header .new-blog p{
	//float: right;
	border-left: 1px solid #EEE;
	line-height: 20px;
	font-size: 12px;
	padding-left: 5px;
}
.blog_list_header .new-blog p span{
	font-size: 14px;
	color: #333;
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
	font-family: "Hiragino Sans GB W3", "Hiragino Sans GB", Arial, Helvetica,
		simsun, u5b8bu4f53;
	font-size: 12px;
}

.blog_list_left ul li a:hover {
	color: #d5c0c0 !important
}
.blog_list_left ul li a.active{
	color: #d5c0c0 !important
}

.li_selected {
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
	font-family: "Hiragino Sans GB W3", "Hiragino Sans GB",
		"Microsoft YaHei", "\5FAE\8F6F\96C5\9ED1", Arial, Helvetica, simsun,
		"\5B8B\4F53";
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

.span_read_num, .span_comment_num {
	margin: 0 5px;
	float: none;
}

.blog_list_item .blog_operate {
	margin-top: 10px;
	margin-right: 20px;
}

.blog_list_item .blog_operate span {
	margin-left: 15px;
}

.blog_list_item .blog_operate a {
	cursor: pointer;
}
</style>
</head>

<body class="hold-transition skin-red sidebar-mini"
	categoryId="${pm.categoryId}" tagId="${pm.tagId}">
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
				<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
				<li class="active">博客</li>
			</ol>
			</section>
			<!-- Main content -->
			<section class="content">
			<div class="container-fluid">
				<div class="blog_list_main">
					<div class="blog_list_header">
						<div class="new-blog">
							<a class="" href="/blog/add">我要写文章</a>
							<p>共<span>33</span>篇</p>
						</div>
					</div>
					<div class="blog_list_content clearfix">
						<div class="blog_list_left pull-left">
							<h4 class="menu_title">分类</h4>
							<ul>
								<c:forEach items="${categoryList}" var="category">
									<c:choose>
									    <c:when test="${pm.categoryId==category.categoryId}">
									    	<li class="ahref "
												data-url="/blog/home?categoryId=${category.categoryId}"><a
												class="active">${category.categoryName}(${category.num})</a></li>
									    </c:when>
									    <c:otherwise>
									    	<li class="ahref"
												data-url="/blog/home?categoryId=${category.categoryId}"><a
												class="">${category.categoryName}(${category.num})</a></li>
									    </c:otherwise>
									</c:choose>
								
								</c:forEach>
							</ul>
							<h4 class="menu_title">标签</h4>
							<ul>
								<c:forEach items="${tagList}" var="tag">
									<c:choose>
									    <c:when test="${pm.tagId==tag.tagId}">
									       <li class="ahref" data-url="/blog/home?tagId=${tag.tagId}">
												<a class="active">${tag.tagName}(${tag.num})</a>
											</li>
									    </c:when>
									    <c:otherwise>
									    	<li class="ahref" data-url="/blog/home?tagId=${tag.tagId}">
												<a>${tag.tagName}(${tag.num})</a>
											</li>
									    </c:otherwise>
									</c:choose>
									
								</c:forEach>

							</ul>
							<h4 class="menu_title">归档</h4>
							<ul>
								<li><a href="#">2018</a></li>
							</ul>
						</div>
						<div class="blog_list_right">
							<div class="list_content"></div>
							<div class="blog_list_page">
								<div class="sui-pagination">
									<ul>
										<li class="prev disabled"><a href="#">«上一页</a></li>
										<li class="active"><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li class="dotted"><span>...</span></li>
										<li class="next"><a href="#">下一页»</a></li>
									</ul>
									<div>
										<span>共10页&nbsp;</span><span>到<input class="page-num"
											type="text">
										<button class="page-confirm" onclick="alert(1)">确定</button>页
										</span>
									</div>
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
	<!--sui-->
	<script src="../static/sui/js/sui.min.js"></script>
	<!--layui-->
	<script type="text/javascript" src="../static/layui/layui.js"></script>
	<script src="../static/myjs/xdx.js"></script>
	<script src="../static/myjs/common.js"></script>
	<script src="../static/myjs/blog/blog.js"></script>
	<script>
			var page=1;//查询第几页
			var limit=10;//每页查询数目
			var currentPage=1;
			var categoryId=$('body').attr('categoryId');
			var tagId=$('body').attr('tagId');
			var itemsCount=0;
			$(function(){
				blogData();
			})
			function blogData(){
				$.ajax({
					type:"get",
					url:"./priList?categoryId="+categoryId+"&tagId="+tagId+"&page="+page,
					async:true,
					datatype:'json',
					success:function(data){
						var html;
						var blogList=data.data;
						for(var i in blogList){
							var blog=blogList[i];
							var createTime=$.timestampToTime(blog.createTime);
							html='<div class="blog_list_item clearfix "><div class="pull-left"><h4 class="blog_title">'+blog.blogTitle+'</h4><p class="blog_info"><span>'+createTime+'</span><span class="span_read_num">阅读'+blog.readNum+'</span><span class="span_comment_num">评论1123</span></p></div><div class="pull-right blog_operate">';
							if(blog.status==0){
								html+='<span><a class="publish" blogId="'+blog.blogId+'">发表</a></span>';
							}
							html+='<span class="blog_edit"><a class="" href="./edit?blogId='+blog.blogId+'">编辑</a></span><span><a class="del" blogId="'+blog.blogId+'">删除</a></span><span><a class="priview" target="_blank" href="/pri/'+blog.blogId+'">预览</a></span></div></div>';
							$(".list_content").append(html);
						}
						itemsCount=data.count;
					},
					error:function(){
						console.log("错误");
					}
				});
			}
		    $('.blog_list_page').pagination({
		        //pages: 50,
		        itemsCount:itemsCount,
		        pageSize:10,
		        styleClass: ['pagination'],
		        showCtrl: true,
		        displayPage: 6,
		        onSelect: function (num) {
		        	page=num;//赋值查询第几页
		            console.log(num)  //打开控制台观察
		            blogData();
		        }        
		    })
		    $('body').on('click','.ahref',function(){
		    	var _this=$(this);
		    	var url=_this.data('url');
		    	location.href=url;
		    })
		    $('body').on('click','.publish',function(){
		    	var _this=$(this);
		    	var blogId=_this.attr('blogId');
		    	$.ajax({
				type: 'post',
				url: './status?blogId='+blogId+'&status=1',
				dataType: 'html',
				success: function(data) {
					if(data > 0) {
						alert("发表成功");
						$(".list_content .blog_list_item").remove();
						blogData();
					} else {
						alert("发表失败");
					}
				}

			});
		  })
	$('body').on('click','.del',function(){
		  var _this=$(this);
		   var blogId=_this.attr('blogId');
		  	layui.use('layer', function() {
			var layer = layui.layer;
			layer.confirm('确定要删除吗？', {
			  btn: ['no', 'yes'] //可以无限个按钮
			  ,btn2: function(index, layero){
		    	$.ajax({
				type: 'post',
				url: './hidden?blogId='+blogId,
				dataType: 'html',
				success: function(data) {
					if(data > 0) {
						alert("删除成功");
						$(".list_content .blog_list_item").remove();
						blogData();
					} else {
						alert("删除失败");
					}
				}
			 });
			}
		}, function(index, layero){
			  layer.closeAll();
			});
	});
})
	</script>
</body>

</html>