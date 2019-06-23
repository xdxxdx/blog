<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="assets/js/google-code-prettify/prettify.css"
	rel="stylesheet">
<!--百度富文本代码高亮-->
<link rel="stylesheet" href="/ueditor1_4_3/third-party/SyntaxHighlighter/shCoreDefault.css">
<link href="/static/css/common.css" rel="stylesheet">
<title>${blog.blogTitle}</title>
<style>
	
</style>
</head>

<body>
	<jsp:include page="../common/ClientHead.jsp" flush="true"/>
	<div class="main">
		<div class="sui-container">
			<div class="sui-right">

				<h3>最新回答</h3>
				<div class="div-right">
					<ul class="unstyled right-ul">
						<li><a href="#">小二：这是一个神奇的网站，我们来花时间把作业做了吧</a></li>
						<li><a href="#">xdx：学习前端技术真是一件很有趣的事情啊</a></li>
						<li><a href="#">xdx：这是一个神奇的网站</a></li>
						<li><a href="#">xdx：css和JS是一项需要长久的投入和积累的工作</a></li>
						<li><a href="#">xdx：这是一个神奇的网站</a></li>
						<li><a href="#">xdx：这是一个神奇的网站</a></li>
						<li><a href="#">xdx：这是一个神奇的网站</a></li>
						<li><a href="#">xdx：这是一个神奇的网站</a></li>
						<li><a href="#">xdx：这是一个神奇的网站</a></li>
						<li><a href="#">xdx：这是一个神奇的网站</a></li>
						<li><a href="#">xdx：这是一个神奇的网站</a></li>
						<li><a href="#">xdx：这是一个神奇的网站</a></li>
					</ul>
				</div>
				<h3>关于作者</h3>
				<div class="div-right">
					<div class="face-div">
						<a href="https://home.cnblogs.com/u/roy-blog/"><img
							src="/static/img/xdx_gaitubao_com_50x50.jpg" title="xdx" alt="xdx" /></a>
					</div>
					<div class="author-intro">
						<dl>
							<dt>
								<a href="https://home.cnblogs.com/u/roy-blog/">xdx</a>
							</dt>
							<dd>
								<span class="area">福建 厦门</span>
							</dd>
						</dl>
						<p>乐观的悲观主义者</p>
					</div>
				</div>
				<div class="div-right">
					<h5>QQ群：481845043</h5>
				</div>
				<h3>标签云</h3>
				<div class="div-right ">
					<div class="tag-cloud">
						<a href="#">JavaScript</a> <a href="#">Spring</a> <a href="#">Nginx</a>
						<a href="#">Redis</a> <a href="#">Spring-MVC</a> <a href="#">CSS</a>
						<a href="#">RabbitMQ</a> <a href="#">Websocket</a>
					</div>

				</div>
			</div>
			<div class="sui-content">
				    	<h1>${blog.blogTitle}</h1>
						<div class="article-content">${blog.blogContent}</div>
			</div>
		</div>

	</div>
	</div>
	<div class="footer">
		<ul class="unstyled">
			<li>@time 2014.03.07</li>
			<li>@author xdx写字的地方</li>
		</ul>
	</div>
	<script src="js/lib/jquery.js"></script>
	<script src="assets/js/google-code-prettify/prettify.js"></script>
	<script src="assets/js/google-code-prettify/lang-basic.js"></script>
	<script src="assets/js/google-code-prettify/lang-css.js"></script>
	<script src="assets/js/application.js"></script>
	<!--百度编辑器及代码高亮-->
	<script type="text/javascript" charset="utf-8"
    		src="/ueditor1_4_3/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8"
		    src="/ueditor1_4_3/ueditor.all.js"></script>
		<script type="text/javascript" charset="utf-8"
    		src="/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" charset="utf-8"
    		src="/ueditor1_4_3/third-party/SyntaxHighlighter/shCore.js"></script>
    <script type="text/javascript">
	    SyntaxHighlighter.all();
	</script>
</body>
</html>