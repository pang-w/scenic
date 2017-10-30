<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



<link rel="stylesheet" href="${baseDomain}/shop/bootstrap.min.css">
<link rel="stylesheet" href="${baseDomain}/shop/font-awesome.min.css">
<link rel="stylesheet" href="${baseDomain}/shop/monokai_sublime.min.css">
<link href="${baseDomain}/shop/magnific-popup.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${baseDomain}/shop/screen.css">
<script type="text/javascript" src="${baseDomain}/shop/ghost-url.min.js"></script>

<link href="http://www.jq22.com/jquery/bootstrap-3.3.4.css"
	rel="stylesheet">
<script src="http://www.jq22.com/jquery/2.1.1/jquery.min.js"></script>
<script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
<link href="${baseDomain}/summernote/summernote.css" rel="stylesheet">
<script src="${baseDomain}/summernote/summernote.js"></script>
<script src="${baseDomain}/assets/ctrl/editor.js"></script>
<script src="${baseDomain}/assets/layer/layer.js"></script>
<script src="${baseDomain}/assets/ctrl/base.js"></script>

</head>
<body class="home-template">

	<!-- start header -->
	<header class="main-header"
		style="background-image: url(http://img.iukiss.com/bg/head_bg_fang.JPG)">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">

					<!-- start logo -->
					<a class="branding" href="http://www.iukiss.com/"
						title="IUKiss 说正事平台"><img src="${baseDomain}/shop/iukiss.png"
						alt="IUKiss 开源博客平台"></a>
					<!-- end logo -->
					<h2 class="text-hide">IUKiss
						是一个简洁、强大的写作平台。你只须专注于用文字表达你的想法就好，其余的事情就让 IUKiss 来帮你处理吧。</h2>

					<img src="${baseDomain}/shop/fcb3879e14429d75833a461572e64.jpg"
						alt="IUKiss 博客系统" class="hide">
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
							data-target="#main-menu"> <span class="sr-only">Toggle
								navigation</span> <i class="fa fa-bars"></i>
						</span>
					</div>
					<div class="collapse navbar-collapse" id="main-menu">
						<ul class="menu">
							<li class="nav-current" role="presentation"><a
								href="http://www.iukiss.com/">首页</a></li>
							<li role="presentation"><a
								href="http://www.iukiss.com/articles.html">文章</a></li>
							<li role="presentation"><a
								href="http://www.iukiss.com/products.html">商品</a></li>
							<li role="presentation"><a
								href="http://www.iukiss.com/topic/游记">游记</a></li>
							<li role="presentation"><a
								href="http://www.iukiss.com/topic/装修">装修</a></li>
							<li role="presentation"><a
								href="http://www.iukiss.com/topic/办事流程">办事流程</a></li>
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
			<input id="articleTitle" type="text" /> 
			<input id="articleUuid" type="text" value="${articleUuid}"/> 
				<div id="summernote" placeholder=在这里写点东西......">
					<p>在这里写点东西.......</p>
				</div>
				<div id="editBtns"class="post-permalink">
					<input  class="btn btn-default" id="saveArticle" type="submit" value="保存" />
					<input  class="btn btn-default" id="saveAndPreview" type="submit" value="保存并预览" />
					<input  class="btn btn-default" id="publishArticle" type="submit" value="发布" />
					<a  class="btn btn-default" target="_blank" href="/article/${articleUuid}.html" >访问文章</a>
				</div>
			</div>
			</div>
		</div>
	</section>
<section class="content-wrap">
		<div class="container">
			<div class="row" id="previewContent">
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
								<a href="http://www.iukiss.com/custom-excerpts/"
									class="post-title">自定义文章摘要（Excerpt）</a>
								<div class="date">2017年8月9日</div>
							</div>
							<div class="recent-single-post">
								<a href="http://www.iukiss.com/primary-tags/" class="post-title">首要“标签”</a>
								<div class="date">2017年8月3日</div>
							</div>
							<div class="recent-single-post">
								<a href="http://www.iukiss.com/ghost-1-0-released/"
									class="post-title">IUKiss 1.0 版本正式发布</a>
								<div class="date">2017年7月29日</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">标签云</h4>
						<div class="content tag-cloud">
							<a href="http://www.iukiss.com/tag/about-ghost/">IUKiss</a> <a
								href="http://www.iukiss.com/tag/release/">新版本发布</a> <a
								href="http://www.iukiss.com/tag/javascript/">JavaScript</a> <a
								href="http://www.iukiss.com/tag/promise/">Promise</a> <a
								href="http://www.iukiss.com/tag/zhuti/">主题</a> <a
								href="http://www.iukiss.com/tag/nodejs/">Node.js</a> <a
								href="http://www.iukiss.com/tag/mysql/">MySQL</a> <a
								href="http://www.iukiss.com/tag/nginx/">Nginx</a> <a
								href="http://www.iukiss.com/tag/aliyun-ecs/">阿里云服务器</a> <a
								href="http://www.iukiss.com/tag/ubuntu/">Ubuntu</a> <a
								href="http://www.iukiss.com/tag/ghost-in-depth/">深度玩转 IUKiss</a>
							<a href="http://www.iukiss.com/tag/theme/">Theme</a> <a
								href="http://www.iukiss.com/tag/zhu-shou-han-shu/">助手函数</a> <a
								href="http://www.iukiss.com/tag/markdown/">Markdown</a> <a
								href="http://www.iukiss.com/tag/proxy/">反向代理</a> <a
								href="http://www.iukiss.com/tag-cloud/">...</a>
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">合作伙伴</h4>
						<div class="content tag-cloud friend-links">
							<a href="http://www.bootcss.com/" title="Bootstrap中文网"
								onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;link&#39;, &#39;click&#39;, &#39;bootcsscom&#39;])"
								target="_blank">Bootstrap中文网</a> <a
								href="http://www.bootcdn.cn/" title="开放CDN服务"
								onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;link&#39;, &#39;click&#39;, &#39;bootcdncn&#39;])"
								target="_blank">开放CDN服务</a> <a href="http://www.gruntjs.net/"
								title="Grunt中文网"
								onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;link&#39;, &#39;click&#39;, &#39;gruntjsnet&#39;])"
								target="_blank">Grunt中文网</a> <a href="http://www.gulpjs.com.cn/"
								title="Gulp中文网"
								onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;link&#39;, &#39;click&#39;, &#39;gulpjscomcn&#39;])"
								target="_blank">Gulp中文网</a>
							<hr>
							<a href="http://lodashjs.com/" title="Lodash中文文档"
								onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;link&#39;, &#39;click&#39;, &#39;lodashjscom&#39;])"
								target="_blank">Lodash中文文档</a> <a
								href="http://www.jquery123.com/" title="jQuery中文文档"
								onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;link&#39;, &#39;click&#39;, &#39;jquery123com&#39;])"
								target="_blank">jQuery中文文档</a>
							<hr>
							<a href="http://www.aliyun.com/" title="阿里云"
								onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;link&#39;, &#39;click&#39;, &#39;aliyun&#39;])"
								target="_blank">阿里云</a>
							<hr>
							<a href="https://www.upyun.com/" title="又拍云"
								onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;link&#39;, &#39;click&#39;, &#39;upyun&#39;])"
								target="_blank">又拍云</a> <a href="http://www.qiniu.com/"
								title="七牛云存储"
								onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;link&#39;, &#39;click&#39;, &#39;qiniu&#39;])"
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
					<span>Copyright © <a href="http://www.iukiss.com/">IUKiss中文网</a></span>
					| <span><a href="http://www.miibeian.gov.cn/"
						target="_blank">豫ICP备16004342号-2</a></span> |
				</div>
			</div>
		</div>
	</div>



</body>
</html>
