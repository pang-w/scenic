<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理菜单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/menu.css" rel="stylesheet" type="text/css" />
</head>
<body class="menu">
	<div class="menuContent">
		<dl>
			<dt>
				<span>内容管理</span>
			</dt>
			<dd>
				<a href="navigation!list.action" target="mainFrame">导航管理</a>
			</dd>
			<dd>
				<a href="article!list.action" target="mainFrame">文章管理</a>
			</dd>
			<dd>
				<a href="article_category!list.action" target="mainFrame">文章分类</a>
			</dd>
			<dd>
				<a href="friend_link!list.action" target="mainFrame">友情链接</a>
			</dd>
			<dd>
				<a href="footer!edit.action" target="mainFrame">网页底部信息</a>
			</dd>
			<dd>
				<a href="agreement!edit.action" target="mainFrame">会员注册协议</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>模板管理</span>
			</dt>
			<dd>
				<a href="template_dynamic!list.action" target="mainFrame">动态模板管理</a>
			</dd>
			<dd>
				<a href="template_html!list.action" target="mainFrame">静态模板管理</a>
			</dd>
			<dd>
				<a href="template_mail!list.action" target="mainFrame">邮件模板管理</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>缓存管理</span>
			</dt>
			<dd>
				<a href="cache!flush.action" target="mainFrame">更新缓存</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>网站静态管理</span>
			</dt>
			<dd>
				<a href="build_html!allInput.action" target="mainFrame">一键网站更新</a>
			</dd>
			<dd>
				<a href="build_html!articleInput.action" target="mainFrame">文章更新</a>
			</dd>
			<dd>
				<a href="build_html!productInput.action" target="mainFrame">商品更新</a>
			</dd>
		</dl>
	</div>
</body>
</html>