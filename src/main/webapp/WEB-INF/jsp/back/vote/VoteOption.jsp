<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投票--选项设置</title>
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
	float: right;
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
.vote-left{
	float:left;
	width: 414px;
}
.vote-left .phone-preview{
	width: 100%;
	height: 736px;
}
.vote-left .phone-preview iframe{
	width: 100%;
	height: 100%;
}
.vote-option-wrap{
	margin-left: 15px;
	float:left;
	width: calc(100% - 460px);
}
.vote-option-wrap .vote-header{
	position: relative;
	height: 40px;
	line-height: 40px;
	border-left: #ffc526 5px solid;
	font-size: 18px;
	background: #f8f6f3;
	padding: 0 10px;
}
.vote-option-wrap .vote-header .option-num{
	position: absolute;
	right: 0px;
	top: 0;
	line-height: 40px;
}
.vote-option-wrap .vote-header .option-num span{
	color: #ffc526;
}
.vote-add-wrap{
	margin-top:18px ;
	padding: 10px;
	border-bottom: 1px solid #ccc;
}
.vote-add-wrap div{
	float: left;
}
.vote-option-list-wrap{
	margin-top: 15px;
}
.vote-option-item{
	padding: 10px;
	border-bottom: 1px solid #ccc;
}
.option-operation{
	padding: 10px 30px;
}
.btn-100{
	width:100px;
	float:left;
	margin-right:10px ;
}
.option-save{
	margin-top:30px ;
	width: 160px;
	height: 40px;
	margin-left: 10px;
	background: #F75124;
	font-size: 16px;
	color: #fff;
	line-height: 40px;
	text-align: center;
	cursor: pointer;
}
.refresh-preview{
	margin-top: 5px;
	padding: 10px;
	text-align: center;
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
				投票<small>选项</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
				<li class="active">投票</li>
			</ol>
			</section>
			<ul class="vote-nav">
				<li class="next"><span><a href="">基本设置</a></span></li>
				<li class="next active" ><i class="iconfont">&#xe656;</i> <span><a
						href="">投票选项</a></span></li>
				<li class="next"><i class="iconfont">&#xe656;</i> <span><a
						href="">其他设置</a></span></li>
			</ul>
			<!-- Main content -->
			<section class="content">
			<div class="container-fluid clearfix">
				<div class="vote-left">
					<div class="phone-preview">
						<iframe src="http://www.omlzz.com/index.php/toupiao/h5/index?check=1&vid=911093295"></iframe>
					</div>
					<div class="refresh-preview">
						<button class="btn btn-danger btn-sm" onclick="reloadIframe()"><i class="iconfont icon-refresh"></i> 刷新</button>
						<button class="btn btn-danger btn-sm"><i class="iconfont icon-qrcode"></i> 预览活动</button>
					</div>
				</div>
				<div class="vote-option-wrap">
					<div class="vote-header">
						页面设置
						<div class="option-num">
							当前选项<span id="option_num">3</span>个
						</div>
					</div>
					<div class="vote-add-wrap clearfix">
						<div class="btn-add">
							<button class="layui-btn layui-btn-warm layui-btn-normal" id="btn-add-option"><i class="layui-icon">&#xe608;</i>添加选项</button>
						</div>
						<div style="margin-left:20px;color:#FF5722;font-size:12px;line-height: 38px;">
                                <i id="new-notice" class="layui-icon" style="font-size:16px;">&#xe702;</i>上传的图片请小于3M
                        </div>
					</div>
					<div class="vote-option-list-wrap">
						<div class="vote-option-item">
							<div class="form-group option-title">
								<label class="control-label label-layer-left-8" for="title">选项名称</label>
								<div class="div-layer-left-80">
									<input name="optionTitle" class="form-control" type="text"
										placeholder="请输入选项名称">
								</div>
								<div style="clear:both"></div>
							</div>
							<div class="option-operation clearfix">
						        <button class="btn btn-xs btn-100">
						            <i class="layui-icon">&#xe642;</i>
						           	 修改编号
						        </button>
						        <button class="btn  btn-xs btn-100">
						            <i class="layui-icon">&#xe642;</i>
						            	修改描述
						        </button>
						         <button class="btn   btn-xs btn-100">
						            <i class="layui-icon">&#xe642;</i>
						            	上传封面
						        </button>
						        <button class="btn  btn-danger btn-xs btn-100">
						        <i class="layui-icon">&#xe640;</i>
						        	删除选项
						        </button>
						   </div>
						</div>
						<div class="vote-option-item">
							<div class="form-group option-title">
								<label class="control-label label-layer-left-8" for="title">选项名称</label>
								<div class="div-layer-left-80">
									<input name="optionTitle" class="form-control" type="text"
										placeholder="请输入选项名称">
								</div>
								<div style="clear:both"></div>
							</div>
							<div class="option-operation clearfix">
						        <button class="btn  btn-xs btn-100">
						            <i class="layui-icon">&#xe642;</i>
						           	 修改编号
						        </button>
						        <button class="btn  btn-xs btn-100">
						            <i class="layui-icon">&#xe642;</i>
						            	修改描述
						        </button>
						         <button class="btn   btn-xs btn-100">
						            <i class="layui-icon">&#xe642;</i>
						            	上传封面
						        </button>
						        <button class="btn  btn-danger btn-xs btn-100">
						        <i class="layui-icon">&#xe640;</i>
						        	删除选项
						        </button>
						   </div>
						</div>
						<div class="vote-option-item">
							<div class="form-group option-title">
								<label class="control-label label-layer-left-8" for="title">选项名称</label>
								<div class="div-layer-left-80">
									<input name="optionTitle" class="form-control" type="text"
										placeholder="请输入选项名称">
								</div>
								<div style="clear:both"></div>
							</div>
							<div class="option-operation clearfix">
						        <button class="btn  btn-xs btn-100">
						            <i class="layui-icon">&#xe642;</i>
						           	 修改编号
						        </button>
						        <button class="btn  btn-xs btn-100">
						            <i class="layui-icon">&#xe642;</i>
						            	修改描述
						        </button>
						         <button class="btn   btn-xs btn-100">
						            <i class="layui-icon">&#xe642;</i>
						            	上传封面
						        </button>
						        <button class="btn  btn-danger btn-xs btn-100">
						        <i class="layui-icon">&#xe640;</i>
						        	删除选项
						        </button>
						   </div>
						</div>
					</div>
					<div class="option-save">
						<i class="iconfont">&#xe656;</i>保存投票选项
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
	<script>
	//刷新iframe
    function reloadIframe() {
      setTimeout(function () {
        $('.phone-preview iframe').attr('src', $('.phone-preview iframe').attr('src'));
      }, 500);
    }
    $(function(){
   		 $("#btn-add-option").click(function(){
   		 var html='<div class="vote-option-item" option-id="4"><div class="form-group option-title"><label class="control-label label-layer-left-8" for="title">选项名称</label><div class="div-layer-left-80"><input name="optionTitle" class="form-control" type="text" placeholder="请输入选项名称">';
   		 html+='</div><div style="clear:both"></div></div><div class="option-operation clearfix"><button class="btn btn-xs btn-100 edit-num" ><i class="layui-icon">&#xe642;</i>修改编号</button><button class="btn  btn-xs btn-100">';
   		 html+='<i class="layui-icon">&#xe642;</i>修改描述</button><button class="btn   btn-xs btn-100"><i class="layui-icon">&#xe642;</i>上传封面</button><button class="btn  btn-danger btn-xs btn-100 del-option"><i class="layui-icon">&#xe640;</i>';
   		 html+='删除选项</button></div></div>';
    	 $(".vote-option-list-wrap").append(html);
    	 reloadIframe();
    	 $("#option_num").text($(".vote-option-item").length);
    	})
   		 $('body').on('click','.edit-num', function(){
   		 	var _this=$(this);
   		 	var optionId=_this.parent().parent().attr("option-id");
   		 	console.log("编号"+optionId);
       });
         $('body').on('click','.del-option', function(){
   		 	var _this=$(this);
   		 	_this.parent().parent().remove();
   		 	reloadIframe();
        })
    })
	</script>
</body>
</html>