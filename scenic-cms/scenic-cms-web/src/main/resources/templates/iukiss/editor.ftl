<!DOCTYPE html>
<!-- saved from url=(0026)http://www.iukiss.com/ -->
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>IUKISS</title>
<meta name="description"
	content="IUKISS是一个简洁、强大的消费故事写作分享平台。在IUKISS可以方便的记录物品给你带来的便捷，也可以看到同一个物品鲜为人知的价值，IUKISS只求事情和物品完美的结合。">
<meta name="keywords" content="IUKISS 写作 记录 商品 消费故事">

<meta name="HandheldFriendly" content="True">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="referrer" content="origin">
<meta name="generator" content="IUKISS 1.0.0">

<link rel="canonical" href="/">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="next" href="/">

<link rel="stylesheet" href="../../../assets/base/css/bootstrap.min.css">
<link rel="stylesheet" href="../../../assets/base/css/font-awesome.min.css">
<link rel="stylesheet" href="../../../assets/base/css/screen.css">
<link rel="stylesheet" href="../../../assets/css/iukiss.css">

<script type="text/javascript" src="../../../assets/base/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../../../assets/base/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../../assets/base/layer/layer.js"></script>
<script type="text/javascript" src="../../../assets/ctrl/base.js"></script>
<script type="text/javascript" src="../../../assets/base/js/angular.min.js"></script>


<script type="text/javascript"	src="../../../assets/base/summernote/summernote.js"></script>
<script type="text/javascript" src="../../../assets/ctrl/editor.js"></script>
<script type="text/javascript" src="../../../assets/ctrl/editorLoad.js"></script>
<link rel="stylesheet" href="../../../assets/base/summernote/summernote.css" >
</head>

<body class="home-template" ng-app="iukissApp"
	ng-controller="iukissCtrl">

	<!-- start navigation -->
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
						<ul class="nav navbar-nav">
							<li><a href="/">IUKISS</a></li>
							<li><a href="/articles.html">文章</a></li>
							<li><a href="/products.html">商品</a></li>
						</ul>

						<ul class="nav navbar-nav">
							<li class="active"><a href="/tags/游记">游记 <span
									class="sr-only">(current)</span></a></li>
							<li><a href="/tags/装修">装修</a></li>
							<li><a href="/tags/办事流程">办事流程</a></li>
							<li><a href="/tags/IT技能">IT技能</a></li>
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
										onclick="support.layerChangePassword()">修改密码</a></li>
									<li data-filter-camera-type="Bravo"><a
										onclick="support.logout()">退出</a></li>
								</ul></li>
						</ul>
						<!-- <form class="navbar-form navbar-right">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-default">搜</button>
						</form> -->
					</div>

				</div>
			</div>
		</div>
	</nav>
	<!-- end navigation -->


	<!-- start site's main content area -->

<section class="content-wrap">
		<div class="container">
			<div class="row">
				<main class="col-md-8 main-content">

				<article id="108" class="post">
				<div class="form-group">
						<div class="row">
							<div class="titleInputForm">

								<input class="post-title form-control" id="articleTitle" value="${editArticle.title}"
									placeholder="标题..." type="text" /> <input id="articleUuid"
									type="hidden" value="${articleUuid}" />

							</div>
							<hr />
						</div>

						<div class="row">
							<div id="summernote">${editArticle.content}</div>
							<div class="widget">
							<div class="content tag-cloud">
								<a ng-click="deselectTag(st.value)" ng-repeat="st in selectedTags" >{{st.value}}</a>
							</div>
							</div>
							
							<hr />
							<div class="widget">
								<div class="content tag-cloud">
									<i class="fa fa-tag"></i>选择标签 
									<a ng-click="selectTag(ut.value)" ng-repeat="ut in userTags" >{{ut.value}}</a>
									<a ng-click="refreshUserTag()">换一批</a>
									<input class="post-title form-control" id="addSelfTagBtn" placeholder="自定义标签..." onen type="text" />
								</div>
							</div>
							<hr />
							<div class="widget">
							<div class="content tag-cloud">
								<a ng-click="deselectProduct(sp)" ng-repeat="sp in selectedProducts" >{{sp.name}}</a>
							</div>
							</div>
							<div class="widget">
								<div class="content tag-cloud">
									<i class="fa fa-shopping-cart"></i>关联商品
									<a ng-click="selectProduct(lp)" ng-repeat="lp in linkedProduct" >{{lp.name}}</a>
								</div>
							</div>
							<div id="editBtns" class="post-permalink">
								<input class="btn btn-default" id="saveArticle" type="submit"
									value="保存" /> <input class="btn btn-default"
									id="saveAndPreview" type="submit" value="保存并预览" /> <input
									class="btn btn-default" id="publishArticle" type="submit"
									value="发布" /> <input class="btn btn-default"
									id="unpublishArticle" type="submit" value="取消发布" /> <a
									class="btn btn-default" target="_blank"
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
					
				</article>
				</main>
				<aside class="col-md-4 sidebar">
					<!-- start widget -->
					<div class="widget">
						<h4 class="title">写文章</h4>
						<div class="content download">
							<input id="createArticle" type="button"
								class="btn btn-default btn-block" value="写事专用" />
						</div>
					</div>
					<!-- end widget -->

					<!-- start resource widget -->
					<div id="loggedInfo" class="widget">
						<h4 class="title">我的资源</h4>
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class='btn-block panel-heading panel-title'
									id="collapseArticleAction">我的文章</div>
								<div id="collapseArticle" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in articleMenu">
											<a href="/article/{{a.uuid}}.html" target="_blank">{{
												a.title }}</a> <a href="/action/edit/article/{{a.uuid}}"> <i
												class="pull-right fa fa-edit"></i>
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
										<li class="list-group-item" ng-repeat="a in attentionMenu">
											<a href="/article/{{a.uuid}}.html" target="_blank">{{
												a.title }}</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-info">
								<div class='btn-block panel-heading panel-title'
									id="collapseProductAction">
									我的商品 <a onclick="support.layerAddProduct()"> <i
										class="pull-right fa fa-edit"></i>
									</a>
								</div>
								<div id="collapseProduct" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in productMenu">
											<a href="#">{{ a.name }}</a> <a
											class='btn pull-right' ng-click="ngEditProduct(a.id)"> <i
												class="pull-right fa fa-edit"></i>								
										</a>
										<a class='btn pull-right' ng-click="insertProduct(a)">
												<i class="pull-right fa fa-arrow-left"></i>
											</a>
										</li>
									</ul>
								</div>
							</div>

							<div class="panel panel-warning">
								<div class='btn-block panel-heading panel-title'
									id="collapseImageAction">我的图片</div>
								<div id="collapseImage" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in imageMenu"><a
											ng-click='ngShowImg(a.url)'>{{ a.imageName }}</a>
											<a class='btn pull-right' ng-click="insertImage(a)">
												<i class="pull-right fa fa-arrow-left"></i>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- end resource widget -->

					<!-- start tag cloud widget -->
					<div class="widget">
						<h4 class="title">标签云</h4>
						<div class="content tag-cloud">
							<a ng-click="selectTag(ut.value)" ng-repeat="ut in topTags" >{{ut.value}}</a>
							
						</div>
					</div>
					<!-- end tag cloud widget -->
				</aside>
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
								<a href="/article/FBDF1D49.html" class="post-title">顺应时代才能缔造时代</a>
								<div class="date">2017年11月16日</div>
							</div>
							<div class="recent-single-post">
								<a href="/article/B7F48C3D.html" class="post-title">IUKISS
									1.0 版本正式发布</a>
								<div class="date">2017年7月29日</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">标签云</h4>
						<div class="content tag-cloud">
							<a href="/tags/about-ghost">IUKISS</a> <a href="/tags/release">新版本发布</a>
							<a href="/tags/javascript">JavaScript</a> <a href="/tags/promise">Promise</a>
							<a href="/tags/zhuti">主题</a> <a href="/tags/nodejs">Node.js</a> <a
								href="/tags/mysql">MySQL</a> <a href="/tags/nginx">Nginx</a> <a
								href="/tags/aliyun-ecs">阿里云服务器</a> <a href="/tags/ubuntu">Ubuntu</a>
							<a href="/tags/ghost-in-depth">深度玩转 IUKISS</a> <a
								href="/tags/theme">Theme</a> <a href="/tags/zhu-shou-han-shu">助手函数</a>
							<a href="/tags/markdown">Markdown</a> <a href="/tags/proxy">反向代理</a>
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
					<span>Copyright © <a href="/">IUKISS</a></span> | <span><a
						href="http://www.miibeian.gov.cn/" target="_blank">豫ICP备16004342号-2</a></span>
				</div>
			</div>
		</div>
	</div>

	<a href="/#" id="back-to-top" style="display: none;"><i
		class="fa fa-angle-up"></i></a>

</body>
</html>
