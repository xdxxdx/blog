<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--百度富文本代码高亮-->
<link rel="stylesheet" href="/ueditor1_4_3/third-party/SyntaxHighlighter/shCoreDefault.css">
<!--sui-->
<link rel="stylesheet" href="/static/sui/css/sui.min.css">
<link href="/static/css/common.css" rel="stylesheet">
<title>xdx's blog</title>
<style>
	
</style>
</head>

<body>
	<jsp:include page="../common/ClientHead.jsp" flush="true"/>
	<div class="main">
		<div class="sui-container">
<!-- 			<div class="ad"> -->
<!-- 				<img src="http://s1.51cto.com/oss/201807/11/ad134a6d9c5c26a55e13f8a658412114.jpg"> -->
<!-- 			</div> -->
			<div class="sui-right">

				<h3>热门推荐</h3>
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
						<c:forEach items="${tagList}" var="tag" varStatus="status">
							<a href="/tagArticle/${tag.tagId}/p1">${tag.tagName}</a>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="sui-content">
				<div class="alist-head">
					<ul class="alist-tab">
						<li class="alist-title">
							<i class="bluedot"></i>
							推荐阅读
						</li>
					</ul>
				</div>
				<div class="alist-body">
					<ul class="alist">
						<c:forEach items="${blogList}" var="blog" varStatus="status">
								<li class="alist-item" blogid="${blog.blogId}">
									<div class="top-info clearfix">
										<div class="user-face pull-left">
										<c:if test="${blog.userFace==null}">
											<img src="/static/img/logo.png">
										</c:if>
										<c:if test="${blog.userFace!=null}">
											<img src="${blog.userFace}">
										</c:if>
										</div>
										<a class="author pull-left" href="#">${blog.authorName}</a>
										<p class="publish-time pull-left">发布于：${blog.createTime}</p>
									</div>
									<h2>
										<a href="/article/${blog.blogId}" target="_blank" title="${blog.blogTitle}">${blog.blogTitle}<span>荐</span></a>
									</h2>
									<p class="abstract">摘要：${blog.blogAbstract}</p>
									<div class="info">
										<a class="tag" href="" target="_blank">${blog.categoryName}</a>
										<p>阅读<span>${blog.readNum}</span></p>
										<p>评论<span>${blog.commentNum}</span></p>
										<p>收藏<span>${blog.collectNum}</span></p>
									</div>
								</li>
						</c:forEach>
					</ul>
				</div>
				<div class="look-more-wrap">
				  <a class='look-more' href='/categoryArticle/p1' target='_blank'>点击浏览更多>></a>
				</div>
			</div>
		</div>

	</div>
	</div>
	<div class="footer">
		<ul class="unstyled">
			<li>专注web开发</li>
			<li>@author xdx写字的地方</li>
		</ul>
	</div>
	<script
		src="/static/AdminLTE-2.4.2/bower_components/jquery/dist/jquery.min.js"></script>
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
	<script src="/static/sui/js/sui.min.js"></script>
</body>
</html>