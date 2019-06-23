<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/static/mycss/my-login.css">
<script src="/static/js/jquery.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/myjs/login/my-login.js"></script>
<title>登录</title>
</head>
<body class="my-login-page">
	<section class="h-100">
	<div class="container h-100">
		<div class="row justify-content-md-center h-100">
			<div class="card-wrapper">
				<div class="brand">
					<img src="/static/img/logo.png">
				</div>
				<div class="card fat">
					<div class="card-body">
						<h4 class="card-title">登录</h4>
						<form id="thisForm" method="POST" action="/admin">

							<div class="form-group">
								<label for="email">用户名/邮箱/电话</label> <input id="adminName"
									type="text" class="form-control" name="adminName" value=""
									required autofocus>
							</div>

							<div class="form-group">
								<label for="password">密码 <a href="forgot.html"
									class="float-right"> 忘记密码? </a>
								</label> <input id="password" type="password" class="form-control"
									name="password" required data-eye>
							</div>

							<div class="form-group">
								<label> <input type="checkbox" name="remember">
									记住我 <font class="float-right msg">${msg}</font>
								</label>
							</div>

							<div class="form-group no-margin">
								<button type="submit" class="btn btn-primary btn-block">
									登录</button>
							</div>
							<div class="margin-top20 text-center">
								还没帐号? <a href="register.html">注册</a>
							</div>
						</form>
					</div>
				</div>
				<div class="footer">Copyright &copy; 文逸科技 2017</div>
			</div>
		</div>
	</div>
	</section>


</body>
</html>