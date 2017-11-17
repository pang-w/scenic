<!DOCTYPE html>
<!-- saved from url=(0026)http://www.iukiss.com/ -->
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>哎呦KISS_IUKISS</title>
<meta name="description" content="我的消费，我的故事。">
<meta name="keywords" content="文章,商品,旅行，装修，办事流程">

<meta name="HandheldFriendly" content="True">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="referrer" content="origin">
<meta name="generator" content="IUKISS 1.0.0">

<link rel="canonical" href="http://www.iukiss.com/">
<link rel="shortcut icon" href="http://www.iukiss.com/favicon.ico">
<link rel="next" href="http://www.iukiss.com/">

<link rel="stylesheet" href="../assets/base/css/bootstrap.min.css">
<link rel="stylesheet" href="../assets/base/css/font-awesome.min.css">
<link rel="stylesheet" href="../assets/base/css/screen.css">

<script type="text/javascript" src="../assets/base/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../assets/base/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../assets/base/layer/layer.js"></script>
<script type="text/javascript" src="../assets/ctrl/index.js"></script>
<script type="text/javascript" src="../assets/ctrl/base.js"></script>
<script type="text/javascript" src="../assets/ctrl/indexLoad.js"></script>

<script type="text/javascript" src="../assets/base/js/angular.min.js"></script>

</head>

<body class="home-template" ng-app="iukissApp" ng-controller="iukissCtrl">

	<!-- start navigation -->
	<#include "components/bodyNav.ftl">
	<!-- end navigation -->

	<!-- start site's main content area -->
	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<#include "components/userTagsArticleLoop.ftl">
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
					
					<#include "components/userResourceLoop.ftl">
					
					<!-- start tag cloud widget -->
					<#include "components/tags.ftl">

				</aside>

			</div>
		</div>
	</section>
	<#include "components/footer.ftl">
</body>
</html>
