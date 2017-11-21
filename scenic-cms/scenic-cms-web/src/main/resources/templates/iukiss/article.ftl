<!DOCTYPE html>
<!-- saved from url=(0026)http://www.iukiss.com/ -->
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>${article.title}</title>
<meta name="description" content="${article.description}">
<meta name="keywords" content="文章,商品,旅行，装修，办事流程">

<meta name="HandheldFriendly" content="True">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="referrer" content="origin">
<meta name="generator" content="IUKISS 1.0.0">

<link rel="canonical" href="/">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="next" href="/">

<link rel="stylesheet" href="../assets/base/css/bootstrap.min.css">
<link rel="stylesheet" href="../assets/base/css/font-awesome.min.css">
<link rel="stylesheet" href="../assets/base/css/screen.css">
<link rel="stylesheet" href="../assets/css/iukiss.css">

<script type="text/javascript" src="../assets/base/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../assets/base/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../assets/base/layer/layer.js"></script>
<script type="text/javascript" src="../assets/ctrl/index.js"></script>
<script type="text/javascript" src="../assets/ctrl/base.js"></script>
<script type="text/javascript" src="../assets/ctrl/articleLoad.js"></script>
<script type="text/javascript" src="../assets/base/js/angular.min.js"></script>
<script type="text/javascript" src="../assets/ctrl/article.js"></script>


</head>

<body class="home-template" ng-app="iukissApp" ng-controller="iukissCtrl">
	<!-- start navigation -->
	<#include "components/bodyNav.ftl">
	<!-- end navigation -->


	<!-- start site's main content area -->
	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<main class="col-md-8 main-content">

				
				
				<article class="post">
					<div class="row">
						<div class="titleInputForm">
							<label >${article.title}</label>
							<input id="articleUuid" type="hidden" value="${articleUuid}"/>
						</div>
					</div>
					<hr />
					<div class="row">
						<div >${article.content}</div>
					</div>
					<hr />
					<div class="row">
							<div class="widget">
								<div class="content tag-cloud">
									<i class="fa fa-tag"></i>标签 
									<#list article.tags as tag>
										<a href="../tags/${tag.value}">${tag.value}</a>
									</#list>
								</div>
							</div>
							<div class="widget">
								<div class="content tag-cloud">
									<i class="fa fa-tag"></i>关联商品 
									<#list article.products as product>
										<a >${product.name}</a>
									</#list>
								</div>
							</div>
						</div>
				</article>

							<div class="panel panel-warning">
								<div class='btn-block panel-heading panel-title' id="collapseAddMessageAction">点击添加评论
								</div>
								<div id="collapseAddMessage" class="panel-collapse collapse">
										<textarea id="articleMessage" name="form-about-yourself" placeholder="添加评论....." class="form-control" id="user-signature"></textarea>
										<span  class='btn btn-default btn-block' id="addMessageBtn" ><i class="fa fa-save"></i> 提交</span>
								</div>
							</div>
							<div class="panel panel-warning"  ng-repeat="a in articleMsg" >
								<div class='btn-block panel-heading panel-title' ><i class="fa fa-user"></i> {{a.actionUser}}:
								<span  class='btn pull-right' ng-click="likeMessage(a.id)" >{{a.likedCount}}赞<i class="fa fa-heart"></i> 点赞</span>
								</div>
								<div id="collapseImage" class="panel-collapse collapse in">
									<ul class="list-group panel-body" >
										<p>{{a.message}}</p>
									</ul>
								</div>
							</div>
				<nav class="pagination" role="navigation">
					<span class="page-number">第 1 页 ⁄ 共 1 页</span> <a
						class="older-posts" href="/"><i
						class="fa fa-angle-right"></i></a>
				</nav>

				</main>

				<aside class="col-md-4 sidebar">
					<!-- start widget -->
					

					<!-- start tag cloud widget -->
					<div class="widget">
						<h4 class="title">标签云</h4>
						<div class="content tag-cloud">
							<a href="/tags/jquery">jQuery</a> <a
								href="/tags/ghost-0-7-ban-ben">IUKISS
								0.7 版本</a> <a href="/tags/opensource">开源</a> <a
								href="/tags/zhu-shou-han-shu">助手函数</a> <a
								href="/tags/tag-cloud">标签云</a> <a
								href="/tags/navigation">导航</a> <a
								href="/tags/customize-page">自定义页面</a> <a
								href="/tags/static-page">静态页面</a> <a
								href="/tags/roon-io">Roon.io</a> <a
								href="/tags/configuration">配置文件</a> <a
								href="/tags/upyun">又拍云存储</a> <a
								href="/tags/upload">上传</a> <a
								href="/tags/handlebars">Handlebars</a> <a
								href="/tags/email">邮件</a> <a
								href="/tags/shortcut">快捷键</a> <a
								href="/tags/yong-hu-zhi-nan">用户指南</a> <a
								href="/tags/theme-market">主题市场</a> <a
								href="/tags/release">新版本发布</a> <a
								href="/tag-cloud">...</a>
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
								<a href="/custom-excerpts/"
									class="post-title">自定义文章摘要（Excerpt）</a>
								<div class="date">2017年8月9日</div>
							</div>
							<div class="recent-single-post">
								<a href="/primary-tags/" class="post-title">首要“标签”</a>
								<div class="date">2017年8月3日</div>
							</div>
							<div class="recent-single-post">
								<a href="/ghost-1-0-released/"
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
							<a href="/tags/about-ghost">IUKISS</a> <a
								href="/tags/release">新版本发布</a> <a
								href="/tags/javascript">JavaScript</a> <a
								href="/tags/promise">Promise</a> <a
								href="/tags/zhuti">主题</a> <a
								href="/tags/nodejs">Node.js</a> <a
								href="/tags/mysql">MySQL</a> <a
								href="/tags/nginx">Nginx</a> <a
								href="/tags/aliyun-ecs">阿里云服务器</a> <a
								href="/tags/ubuntu">Ubuntu</a> <a
								href="/tags/ghost-in-depth">深度玩转 IUKISS</a>
							<a href="/tags/theme">Theme</a> <a
								href="/tags/zhu-shou-han-shu">助手函数</a> <a
								href="/tags/markdown">Markdown</a> <a
								href="/tags/proxy">反向代理</a> <a
								href="/tag-cloud">...</a>
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
					<span>Copyright © <a href="/">IUKISS中文网</a></span>
					| <span><a href="http://www.miibeian.gov.cn/"
						target="_blank">豫ICP备16004342号-2</a></span> |
				</div>
			</div>
		</div>
	</div>

	<a href="/#" id="back-to-top"
		style="display: none;"><i class="fa fa-angle-up"></i></a>

</body>
</html>
