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

<script type="text/javascript"
	src="../assets/base/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../assets/base/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../assets/base/layer/layer.js"></script>
<script type="text/javascript" src="../assets/ctrl/index.js"></script>
<script type="text/javascript" src="../assets/ctrl/base.js"></script>
<script type="text/javascript" src="../assets/ctrl/articleLoad.js"></script>
<script type="text/javascript" src="../assets/base/js/angular.min.js"></script>

</head>

<body class="home-template" ng-app="iukissApp"
	ng-controller="iukissCtrl">
	<!-- start navigation -->
	<#include "components/bodyNav.ftl"> <!-- end navigation -->


	<!-- start site's main content area -->
	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<main class="col-md-8 main-content">

				<article class="post">
					<div class="row">
						<div class="titleInputForm">
							<label>${article.title}</label> <input id="articleUuid"
								type="hidden" value="${articleUuid}" />
						</div>
					</div>
					<div class="row">
						<span>作者：<a href="../../../i/${author.username}">${author.nickname} </a></span>
						<span>创建于：${article.createDate}</span>
						 <#if article.lastModifyDate??><span>修改：${article.lastModifyDate}</span></#if>
					</div>
					<hr />
					<div class="row">
						<div>${article.content}</div>
					</div>
					<hr />
					<div class="row">
						<#if article.tags??>
						<div class="widget">
							<div class="content tag-cloud">
								<i class="fa fa-tag"></i> 标签：
								<#list article.tags as tag> <a
									href="../tags/${tag.value}">${tag.value}</a> </#list>
							</div>
						</div>
						</#if>
						<#if article.products??>
						<div class="widget">
							<div class="content tag-cloud">
								<i class="fa fa-cogs"></i> 配置：
								<#list article.products as product> <a>${product.name}</a>
								</#list>
							</div>
						</div>
						</#if>
					</div>
				</article>
				<article id="108" class="post">
					<#if author.alipayImgUrl??>
					<aside class="col-md-2 sidebar">
						<img height="100%" width="100%"  src="http://img.iukiss.com/${author.alipayImgUrl}"></img>
						<div class="author">支付宝打赏</div>
						<input id="authorName" type="hidden" value="${author.username}"/>
					</aside>
					</#if>
					<aside class="col-md-3 sidebar">
						<div>
							<input id="signatureLikedUsername" type="hidden" value="ITMAOO"/>作者：
							<span class="author"><a href="../../../i/${author.username}">${author.nickname}</a></span> 
						</div>
						<div>
							 ${author.signatureLikedCount}赞<span class='btn' id="likeSignatureBtn" ><i class="fa fa-heart"></i> 点赞</span> 
						</div>
					</aside>
					<#if author.weixinImgUrl??>
					<aside class="col-md-2 sidebar">
						<img height="100%" width="100%" src="http://img.iukiss.com/${author.weixinImgUrl}"></img>
						<div class="author">微信打赏</div>
					</aside>
					</#if>
					
					<aside class="col-md-5 sidebar">
						<div>
							<p>${author.signature}</p>
						</div>
					</aside>
					
					
				</article>
				<div class="panel panel-warning">
					<div class='btn-block panel-heading panel-title'
						id="collapseAddMessageAction">点击添加评论</div>
					<div id="collapseAddMessage" class="panel-collapse collapse">
						<textarea id="articleMessage" name="form-about-yourself"
							placeholder="添加评论....." class="form-control" id="user-signature"></textarea>
						<span class='btn btn-default btn-block' id="addMessageBtn"><i
							class="fa fa-save"></i> 提交</span>
					</div>
				</div>
				<div class="panel panel-warning" ng-repeat="a in articleMsg.dataList">
					<div class='btn-block panel-heading panel-title'>
						<i class="fa fa-user"></i> {{a.actionUser}}: <span
							class='btn pull-right' ng-click="likeMessage(a.id)">{{a.likedCount}}赞<i
							class="fa fa-heart"></i> 点赞
						</span>
					</div>
					<div id="collapseImage" class="panel-collapse collapse in">
						<ul class="list-group panel-body">
							<p>{{a.message}}</p>
						</ul>
					</div>
				</div>
				<nav class="pagination" role="navigation">

					<span ng-if="articleMsg.pageIndex!=1" class="btn btn-default" id="pagePrevious" ng-click="searchMessagePrevious()">
					<i class="fa fa-angle-left"></i></span> 
					
					<span class="page-number">第 <span id="messagePageIndex">{{articleMsg.pageIndex}}</span> 页 ⁄ 共 
					<span id="totalPage">{{articleMsg.totalPage}}</span> 页</span>
					
					<span  ng-if="articleMsg.pageIndex!=articleMsg.totalPage" class="btn btn-default" id="pageNext" ng-click="searchMessageNext()">
					<i class="fa fa-angle-right"></i></span>

				</nav>

				</main>

				<aside class="col-md-4 sidebar">
					<!-- start tag cloud widget -->
					<#include "components/asideTags.ftl"> <!-- end tag cloud widget -->
				</aside>

			</div>
		</div>
	</section>

	<#include "components/footer.ftl">
</body>
</html>
