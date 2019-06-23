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
						<c:forEach items="${tagList}" var="tag" varStatus="status">
							<a href="/tagArticle/${tag.tagId}/p1">${tag.tagName}</a>
						</c:forEach>
					</div>

				</div>
			</div>
			<div class="sui-content">
				<c:choose>
				    <c:when test="${blog.isPrivate==1}">
				        	您无权查看该文章
				    </c:when>
				    <c:otherwise>
				    	<h1>${blog.blogTitle}</h1>
				    	<div class="article-top-info ">
							<div class="user-face pull-left">
								<c:if test="${author.userFace==null}">
											<img src="/static/img/logo.png">
										</c:if>
										<c:if test="${author.userFace!=null}">
											<img src="${author.userFace}">
										</c:if>
							</div>
							<a class="author pull-left" href="#">${author.adminName}</a>
							<p class="publish-time pull-right"><fmt:formatDate value="${blog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;&nbsp;${blog.readNum}人阅读 &nbsp;·${blog.commentNum}人评论</p>
							<div class="clear"></div>
						</div>
						<div class="article-content">${blog.blogContent}</div>
				    </c:otherwise>
				</c:choose>
				

			</div>
		</div>

	</div>
	</div>
	<div class="footer">
		<ul class="unstyled">
			<li>最后修改时间 &nbsp;<fmt:formatDate value="${blog.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
			<li>@author&nbsp;${author.adminName}</li>
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
</body>
</html>