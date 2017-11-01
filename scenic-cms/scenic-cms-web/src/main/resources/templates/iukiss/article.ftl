﻿﻿<!DOCTYPE html>
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
	<!-- start header -->
	<header class="main-header"
		style="background-image: url(${imgDomain}/bg/head_bg_fang.JPG)">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<!-- start logo -->
					<a class="branding" href="${baseDomain}" title="说正事平台_IUKISS">
						<img src="${baseDomain}/assets/base/img/iukiss.png" alt="IUKISS 说正事平台"></a>
					<!-- end logo -->
					<h2 class="text-hide">IUKISS
						是一个简洁、强大的写作平台。你只须专注于用文字表达你的想法就好，其余的事情就让 IUKISS 来帮你处理吧。</h2>
				</div>
			</div>
		</div>
	</header>

	<nav class="main-navigation">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="navbar-header">
						<span class="nav-toggle-button collapsed" data-toggle="collapse"
							data-target="#main-menu"> <span class="sr-only">Toggle	navigation</span> <i class="fa fa-bars"></i>
						</span>
					</div>
					<div class="collapse navbar-collapse" id="main-menu">
						<ul class="menu">
							<li class="nav-current" role="presentation"><a
								href="${baseDomain}">首页</a></li>
							<li role="presentation"><a
								href="${baseDomain}articles.html">文章</a></li>
							<li role="presentation"><a
								href="${baseDomain}products.html">商品</a></li>
							<li role="presentation"><a
								href="${baseDomain}/topic/游记">游记</a></li>
							<li role="presentation"><a
								href="${baseDomain}/topic/装修">装修</a></li>
							<li role="presentation"><a
								href="${baseDomain}/topic/办事流程">办事流程</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>

	<section class="content-wrap">
		<div class="container">
			<div class="form-group">
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
