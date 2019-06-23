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
<!--sui-->
<link rel="stylesheet" href="/static/sui/css/sui.min.css">
<link href="/static/css/common.css" rel="stylesheet">
<title>Nginx</title>
<style>
	.sui-container .ad,.sui-container .ad img{
		width:100%;
	}
	.sui-content .alist-head{
		border-bottom: 1px solid #eee;
		overflow: hidden;
		margin-top: 30px;
		padding-bottom: 15px;
		line-height: 20px;
	}
	.sui-content .alist-head .alist-tab .alist-title{
		color:#4285f4;
		padding-left: 10px;
		position: relative;
		margin-right: 45px;
		font-size: 16px;
	}
	.bluedot {
	    width: 4px;
	    height: 4px;
	    background: #4285f4;
	    border-radius: 4px;
	    overflow: hidden;
	    padding: 0;
	    display: block;
	    position: absolute;
	}
	.alist-tab .alist-title .bluedot{
		left: 3px;
		top: 50%;
		margin-top: -2px;
	}
	.sui-content .alist-body{
		
	}
	.sui-content .alist-body .alist{
		margin-bottom: 20px;
	}
	.alist .alist-item{
		padding: 20px 20px 20px 10px;
		border-bottom: 1px solid #eee;
		font-size: 12px;
		position: relative;
		overflow: hidden;
	}
	.alist .alist-item:hover{
		background: #FFFFFF;
	}
	
	.alist .alist-item h2{
		line-height: 1;
		margin-bottom: 15px;
		font-size: 20px;
	}
	.alist .alist-item h2 a{
		color:#333;
		line-height: 1.5;
	}
	.alist .alist-item:hover h2 a{
		color:#4285f4;
	}
	.alist .alist-item h2 a span{
		color: #FFF;
		background: #F05F0E;
		border-radius: 2px;
		padding: 2px;
		font-weight: normal;
		margin-left: 5px;
		font-size: 16px;
	}
	.alist .alist-item .abstract{
		color: #666;
		height: 48px;
		line-height: 24px;
		overflow: hidden;
		font-size: 14px;
	}
	.alist .alist-item .info{
		overflow: hidden;
		line-height: 24px;
		padding-top: 15px;
	}
	.alist .alist-item .info > *{
		float:left;
		color:#999;
	}
	.alist .alist-item .info .tag{
		font-size: 12px;
		padding: 0 8px;
		line-height: 20px;
		color: #ee5e0f;
		background: #fff;
		border: 1px solid #ee5e0f;
		border-radius: 2px;
		margin-right: 16px;
	}
	.alist .alist-item .info p{
		margin-right: 20px;
		margin-top: 0;
	}
	
</style>
</head>

<body>
	<jsp:include page="../common/ClientHead.jsp" flush="true"/>
	<div class="main">
		<div class="sui-container">
			<div class="ad">
				<img src="http://s1.51cto.com/oss/201807/11/ad134a6d9c5c26a55e13f8a658412114.jpg">
			</div>
			<div class="sui-right">
				<h3>标签云</h3>
				<div class="div-right ">
					<div class="tag-cloud">
						<c:forEach items="${tagList}" var="tag" varStatus="status">
							<c:if
							<a href="#">${tag.tagName}</a>
						</c:forEach>
					</div>
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

			</div>
			<div class="sui-content">
				<div class="alist-head">
					<ul class="alist-tab">
						<li class="alist-title">
							<i class="bluedot"></i>
							Nginx优质文章
						</li>
					</ul>
				</div>
				<div class="alist-body">
					<ul class="alist">
						<li class="alist-item" blogid="">
							<h2>
								<a href="" target="_blank" title="6个平凡人的经理，参悟工程师成功秘密">6个平凡人的经理，参悟工程师成功秘密<span>荐</span></a>
							</h2>
							<p class="abstract">你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？</p>
							<div class="info">
								<a class="tag" href="" target="_blank">Redis</a>
								<p>阅读<span>1991</span></p>
								<p>评论<span>1991</span></p>
								<p>收藏<span>1991</span></p>
							</div>
						</li>
						<li class="alist-item" blogid="">
							<h2>
								<a href="" target="_blank" title="6个平凡人的经理，参悟工程师成功秘密">6个平凡人的经理，参悟工程师成功秘密<span>荐</span></a>
							</h2>
							<p class="abstract">你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？</p>
							<div class="info">
								<a class="tag" href="" target="_blank">Redis</a>
								<p>阅读<span>1991</span></p>
								<p>评论<span>1991</span></p>
								<p>收藏<span>1991</span></p>
							</div>
						</li>
						<li class="alist-item" blogid="">
							<h2>
								<a href="" target="_blank" title="6个平凡人的经理，参悟工程师成功秘密">6个平凡人的经理，参悟工程师成功秘密<span>荐</span></a>
							</h2>
							<p class="abstract">你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？</p>
							<div class="info">
								<a class="tag" href="" target="_blank">Redis</a>
								<p>阅读<span>1991</span></p>
								<p>评论<span>1991</span></p>
								<p>收藏<span>1991</span></p>
							</div>
						</li>
						<li class="alist-item" blogid="">
							<h2>
								<a href="" target="_blank" title="6个平凡人的经理，参悟工程师成功秘密">6个平凡人的经理，参悟工程师成功秘密<span>荐</span></a>
							</h2>
							<p class="abstract">你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？</p>
							<div class="info">
								<a class="tag" href="" target="_blank">Redis</a>
								<p>阅读<span>1991</span></p>
								<p>评论<span>1991</span></p>
								<p>收藏<span>1991</span></p>
							</div>
						</li>
						<li class="alist-item" blogid="">
							<h2>
								<a href="" target="_blank" title="6个平凡人的经理，参悟工程师成功秘密">6个平凡人的经理，参悟工程师成功秘密<span>荐</span></a>
							</h2>
							<p class="abstract">你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？</p>
							<div class="info">
								<a class="tag" href="" target="_blank">Redis</a>
								<p>阅读<span>1991</span></p>
								<p>评论<span>1991</span></p>
								<p>收藏<span>1991</span></p>
							</div>
						</li>
						<li class="alist-item" blogid="">
							<h2>
								<a href="" target="_blank" title="6个平凡人的经理，参悟工程师成功秘密">6个平凡人的经理，参悟工程师成功秘密<span>荐</span></a>
							</h2>
							<p class="abstract">你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？</p>
							<div class="info">
								<a class="tag" href="" target="_blank">Redis</a>
								<p>阅读<span>1991</span></p>
								<p>评论<span>1991</span></p>
								<p>收藏<span>1991</span></p>
							</div>
						</li>
						<li class="alist-item" blogid="">
							<h2>
								<a href="" target="_blank" title="6个平凡人的经理，参悟工程师成功秘密">6个平凡人的经理，参悟工程师成功秘密<span>荐</span></a>
							</h2>
							<p class="abstract">你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？</p>
							<div class="info">
								<a class="tag" href="" target="_blank">Redis</a>
								<p>阅读<span>1991</span></p>
								<p>评论<span>1991</span></p>
								<p>收藏<span>1991</span></p>
							</div>
						</li>
						<li class="alist-item" blogid="">
							<h2>
								<a href="" target="_blank" title="6个平凡人的经理，参悟工程师成功秘密">6个平凡人的经理，参悟工程师成功秘密<span>荐</span></a>
							</h2>
							<p class="abstract">你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？</p>
							<div class="info">
								<a class="tag" href="" target="_blank">Redis</a>
								<p>阅读<span>1991</span></p>
								<p>评论<span>1991</span></p>
								<p>收藏<span>1991</span></p>
							</div>
						</li>
						<li class="alist-item" blogid="">
							<h2>
								<a href="" target="_blank" title="6个平凡人的经理，参悟工程师成功秘密">6个平凡人的经理，参悟工程师成功秘密<span>荐</span></a>
							</h2>
							<p class="abstract">你如何定义十年工作经验？是踏踏实实干十年，学十年？还是你认真干了两年，接下来把这两年的活重复了5次？</p>
							<div class="info">
								<a class="tag" href="" target="_blank">Redis</a>
								<p>阅读<span>1991</span></p>
								<p>评论<span>1991</span></p>
								<p>收藏<span>1991</span></p>
							</div>
						</li>
					</ul>
				</div>
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
				  <div><span>共10页&nbsp;</span><span>
				      到
				      <input class="page-num" type="text"><button class="page-confirm" onclick="alert(1)">确定</button>
				      页</span></div>
				</div>
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
	<script src="/static/sui/js/sui.min.js"></script>
</body>
</html>