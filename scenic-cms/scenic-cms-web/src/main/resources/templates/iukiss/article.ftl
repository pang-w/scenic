﻿<!DOCTYPE html>
<html>
<head>
<title>${article.title}</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="${baseDomain}/assets/base/css/screen.css">
<link rel="stylesheet" href="${baseDomain}/assets/base/css/bootstrap.min.css">
<link rel="stylesheet" href="${baseDomain}/assets/base/css/font-awesome.min.css">
<link rel="stylesheet" href="${baseDomain}/assets/base/css/monokai_sublime.min.css">
<link href="${baseDomain}/assets/base/summernote/summernote.css" rel="stylesheet">

<script type="text/javascript" src="${baseDomain}/assets/base/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${baseDomain}/assets/base/summernote/summernote.js"></script>
<script type="text/javascript" src="${baseDomain}/assets/base/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${baseDomain}/assets/base/layer/layer.js"></script>

<link rel="stylesheet" href="${baseDomain}/assets/css/iukiss.css">

<script type="text/javascript" src="${baseDomain}/assets/ctrl/index.js"></script>
<script type="text/javascript" src="${baseDomain}/assets/ctrl/base.js"></script>
<script type="text/javascript" src="${baseDomain}/assets/ctrl/editor.js"></script>

</head>
<body class="home-template">
	<!-- start navigation -->
	<nav class="main-navigation">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">

					<div class="collapse navbar-collapse" id="main-menu">
						<a class="navbar-brand" href="/">IUKISS</a>
						<ul class="nav navbar-nav">
							<li><a href="/articles.html">文章</a></li>
							<li><a href="/products.html">商品</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="/register.html">注册</a></li>
							<li id="loginLayerOutBtn"><a onclick="support.layerLogin()">登录</a></li>
							<li id="loggedDropdown" class="dropdown">
								<a	id="loggedUesername" class="dropdown-toggle" data-toggle="dropdown"> <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="/user/profile.html">个人资料</a></li>
									<li data-filter-camera-type="Zed"><a
										onclick="support.layerLogin()">修改密码</a></li>
									<li data-filter-camera-type="Bravo"><a
										onclick="support.logout()">退出</a></li>
								</ul></li>
						</ul>
						<ul class="nav navbar-nav">
							<li class="active"><a href="/tags/游记">游记 <span
									class="sr-only">(current)</span></a></li>
							<li><a href="/tags/装修">装修</a></li>
							<li><a href="/tags/办事流程">办事流程</a></li>
						</ul>
						<form class="navbar-form navbar-right">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-default">搜</button>
						</form>
					</div>

				</div>
			</div>
		</div>
	</nav>
	<!-- end navigation -->
	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<main class="col-md-8 main-content">

				<article id="108" class="post">
					<div class="row">
						<div class="titleInputForm">
							<label>${article.title}</label>
							<input id="articleUuid" type="hidden" value="${articleUuid}"/>
						</div>
						<hr />
					</div>
					<div class="row">
						<div >${article.content}</div>
					</div>
				</article>
				
				


				

				</main>

				<aside class="col-md-4 sidebar">

					<!-- start tag cloud widget -->
					<div class="widget">
						<h4 class="title">标签云</h4>
						<div class="content tag-cloud">
							<a href="/tags/jquery/">jQuery</a> <a
								href="/tags/ghost-0-7-ban-ben/">IUKISS
								0.7 版本</a> <a href="/tags/opensource/">开源</a> <a
								href="/tags/zhu-shou-han-shu/">助手函数</a> <a
								href="/tags/tag-cloud/">标签云</a> <a
								href="/tags/navigation/">导航</a> <a
								href="/tags/customize-page/">自定义页面</a> <a
								href="/tags/static-page/">静态页面</a> <a
								href="/tags/roon-io/">Roon.io</a> <a
								href="/tags/configuration/">配置文件</a> <a
								href="/tags/upyun/">又拍云存储</a> <a
								href="/tags/upload/">上传</a> <a
								href="/tags/handlebars/">Handlebars</a> <a
								href="/tags/email/">邮件</a> <a
								href="/tags/shortcut/">快捷键</a> <a
								href="/tags/yong-hu-zhi-nan/">用户指南</a> <a
								href="/tags/theme-market/">主题市场</a> <a
								href="/tags/release/">新版本发布</a> <a
								href="/tag-cloud/">...</a>
						</div>
					</div>
					<!-- end tag cloud widget -->

					<!-- start widget -->
					<!-- end widget -->

					<!-- start widget -->
					<!-- end widget -->
				</aside>

			</div>
		</div>
	</section>

	<footer class="main-footer">
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">最新文章</h4>
						<div class="content recent-post">
							<div class="recent-single-post">
								<a href="${baseDomain}custom-excerpts/"
									class="post-title">自定义文章摘要（Excerpt）</a>
								<div class="date">2017年8月9日</div>
							</div>
							<div class="recent-single-post">
								<a href="${baseDomain}primary-tags/" class="post-title">首要“标签”</a>
								<div class="date">2017年8月3日</div>
							</div>
							<div class="recent-single-post">
								<a href="${baseDomain}ghost-1-0-released/"
									class="post-title">IUKISS 1.0 版本正式发布</a>
								<div class="date">2017年7月29日</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">标签云</h4>
						<div class="content tag-cloud">
							<a href="${baseDomain}/tag/about-ghost/">IUKISS</a> 
							<a href="${baseDomain}/tag/release/">新版本发布</a>
							<a href="${baseDomain}/tag/javascript/">JavaScript</a> 
							<a href="${baseDomain}/tag/promise/">Promise</a> 
							<a href="${baseDomain}/tag/zhuti/">主题</a> 
							<a href="${baseDomain}/tag/nodejs/">Node.js</a> 
							<a href="${baseDomain}/tag/mysql/">MySQL</a>
							<a href="${baseDomain}/tag/nginx/">Nginx</a> 
							<a href="${baseDomain}/tag/aliyun-ecs/">阿里云服务器</a> 
							<a href="${baseDomain}/tag/ubuntu/">Ubuntu</a> 
							<a href="${baseDomain}/tag/ghost-in-depth/">深度玩转 IUKISS</a>
							<a href="${baseDomain}/tag/theme/">Theme</a> 
							<a href="${baseDomain}/tag/zhu-shou-han-shu/">助手函数</a>
							<a href="${baseDomain}/tag/markdown/">Markdown</a> 
							<a href="${baseDomain}/tag/proxy/">反向代理</a> 
							<a href="${baseDomain}/tag-cloud/">...</a>
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">合作伙伴</h4>
						<div class="content tag-cloud friend-links">
							<a href="http://www.bootcss.com/" title="Bootstrap中文网"
								target="_blank">Bootstrap中文网</a> 
							<a
								href="http://www.bootcdn.cn/" title="开放CDN服务"
								target="_blank">开放CDN服务</a> 
							<a href="http://www.gruntjs.net/"
								title="Grunt中文网"
								target="_blank">Grunt中文网</a> 
							<a href="http://www.gulpjs.com.cn/"
								title="Gulp中文网"
								target="_blank">Gulp中文网</a>
							<hr>
							<a href="http://lodashjs.com/" title="Lodash中文文档"
								target="_blank">Lodash中文文档</a> 
							<a
								href="http://www.jquery123.com/" title="jQuery中文文档"
								target="_blank">jQuery中文文档</a>
							<hr>
							<a href="http://www.aliyun.com/" title="阿里云"
								target="_blank">阿里云</a>
							<hr>
							<a href="https://www.upyun.com/" title="又拍云"
								target="_blank">又拍云</a> 
							<a href="http://www.qiniu.com/"
								title="七牛云存储"
								target="_blank">七牛云存储</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<div class="copyright">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<span>Copyright © <a href="${baseDomain}">IUKISS中文网</a></span>
					| <span><a href="http://www.miibeian.gov.cn/" target="_blank">豫ICP备16004342号-2</a></span> |
				</div>
			</div>
		</div>
	</div>
</body>
</html>
