﻿
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="${baseDomain}/assets/base/css/screen.css">
<link rel="stylesheet" href="${baseDomain}/assets/base/css/bootstrap.min.css">
<link rel="stylesheet" href="${baseDomain}/assets/base/css/font-awesome.min.css">
<link rel="stylesheet" href="${baseDomain}/assets/base/css/monokai_sublime.min.css">
<link href="${baseDomain}/assets/base/summernote/summernote.css" rel="stylesheet">

<script type="text/javascript" src="${baseDomain}/assets/base/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${baseDomain}/assets/base/summernote/summernote.js"></script>
<script type="text/javascript" src="${baseDomain}/assets/base/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${baseDomain}/assets/base/layer/layer.js"></script>
<script type="text/javascript" src="${baseDomain}/assets/base/js/angular.min.js"></script>


<link rel="stylesheet" href="${baseDomain}/assets/css/iukiss.css">

<script type="text/javascript" src="${baseDomain}/assets/ctrl/base.js"></script>
<script type="text/javascript" src="${baseDomain}/assets/ctrl/editor.js"></script>
<script type="text/javascript" src="${baseDomain}/assets/ctrl/editorLoad.js"></script>


</head>

<body class="home-template" ng-app="iukissApp" ng-controller="iukissCtrl">

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
							<li id="loggedDropdown" class="dropdown"><a
								id="loggedUesername" class="dropdown-toggle"
								data-toggle="dropdown"> <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="/profile.html">个人资料</a></li>
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
	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<main class="col-md-8 main-content">

				<section class="content-wrap">
					<div class="form-group">
						<div class="row">
							<div class="titleInputForm">

								<input class="post-title form-control" id="articleTitle" placeholder="标题..."
									value="${editArticle.title}" type="text" />
								<input id="articleUuid" type="hidden" value="${articleUuid}" />
							</div>
							<hr />
						</div>
						<div class="row">
							<div id="summernote">${editArticle.content}</div>
							<div id="editBtns" class="post-permalink">
								<input class="btn btn-default" id="saveArticle" type="submit"
									value="保存" /> <input class="btn btn-default"
									id="saveAndPreview" type="submit" value="保存并预览" /> <input
									class="btn btn-default" id="publishArticle" type="submit"
									value="发布" /> <a class="btn btn-default" target="_blank"
									href="/article/${articleUuid}.html">访问文章</a>
							</div>
						</div>
						<hr>
						<div class="row">
							<div id="previewTitleFormat" class="row">
								<div class="post-title" id="previewTitle"></div>
								<hr />
							</div>
							<div class="row">
								<div id="previewContent"></div>
							</div>
						</div>
					</div>
				</section>
				

				</main>

				<aside class="col-md-4 sidebar">
					<!-- start tag cloud widget -->
					<div id="loggedInfo" class="widget">
						<h4 class="title">我的资源</h4>
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class='btn-block panel-heading panel-title'
									id="collapseArticleAction">我的文章</div>
								<div id="collapseArticle" class="panel-collapse collapse in">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in articleMenu">
											<a href="/article/{{a.uuid}}.html">{{ a.title }}</a>
											<a href="/action/edit/article/{{a.uuid}}">
        											<i class="pull-right fa fa-edit"></i>
        									</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-success">
								<div class='btn-block panel-heading panel-title'
									id="collapseAttentionAction">我的收藏</div>
								<div id="collapseAttention" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in articleMenu">
											<a href="/">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-info">
								<div class='btn-block panel-heading panel-title'
									id="collapseProductAction">我的商品</div>
								<div id="collapseProduct" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in articleMenu">
											<a href="/">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-warning">
								<div class='btn-block panel-heading panel-title'
									id="collapseImageAction">我的图片</div>
								<div id="collapseImage" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in articleMenu">
											<a href="/">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>

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
								<a href="custom-excerpts/" class="post-title">自定义文章摘要（Excerpt）</a>
								<div class="date">2017年8月9日</div>
							</div>
							<div class="recent-single-post">
								<a href="primary-tags/" class="post-title">首要“标签”</a>
								<div class="date">2017年8月3日</div>
							</div>
							<div class="recent-single-post">
								<a href="ghost-1-0-released/" class="post-title">IUKISS 1.0
									版本正式发布</a>
								<div class="date">2017年7月29日</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">标签云</h4>
						<div class="content tag-cloud">
							<a href="/tag/about-ghost/">IUKISS</a> <a href="/tag/release/">新版本发布</a>
							<a href="/tag/javascript/">JavaScript</a> <a href="/tag/promise/">Promise</a>
							<a href="/tag/zhuti/">主题</a> <a href="/tag/nodejs/">Node.js</a> <a
								href="/tag/mysql/">MySQL</a> <a href="/tag/nginx/">Nginx</a> <a
								href="/tag/aliyun-ecs/">阿里云服务器</a> <a href="/tag/ubuntu/">Ubuntu</a>
							<a href="/tag/ghost-in-depth/">深度玩转 IUKISS</a> <a
								href="/tag/theme/">Theme</a> <a href="/tag/zhu-shou-han-shu/">助手函数</a>
							<a href="/tag/markdown/">Markdown</a> <a href="/tag/proxy/">反向代理</a>
							<a href="/tag-cloud/">...</a>
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">合作伙伴</h4>
						<div class="content tag-cloud friend-links">
							<a href="http://www.bootcss.com/" title="Bootstrap中文网"
								target="_blank">Bootstrap中文网</a> <a
								href="http://www.bootcdn.cn/" title="开放CDN服务" target="_blank">开放CDN服务</a>
							<a href="http://www.gruntjs.net/" title="Grunt中文网"
								target="_blank">Grunt中文网</a> <a href="http://www.gulpjs.com.cn/"
								title="Gulp中文网" target="_blank">Gulp中文网</a>
							<hr>
							<a href="http://lodashjs.com/" title="Lodash中文文档" target="_blank">Lodash中文文档</a>
							<a href="http://www.jquery123.com/" title="jQuery中文文档"
								target="_blank">jQuery中文文档</a>
							<hr>
							<a href="http://www.aliyun.com/" title="阿里云" target="_blank">阿里云</a>
							<hr>
							<a href="https://www.upyun.com/" title="又拍云" target="_blank">又拍云</a>
							<a href="http://www.qiniu.com/" title="七牛云存储" target="_blank">七牛云存储</a>
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
					<span>Copyright © <a href="">IUKISS中文网</a></span> | <span><a
						href="http://www.miibeian.gov.cn/" target="_blank">豫ICP备16004342号-2</a></span>
					|
				</div>
			</div>
		</div>
	</div>
</body>
</html>
