<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>提示信息 </title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
</head>
<body class="error">
	<div class="body">
		<div class="errorBox">
			<div class="errorDetail">
				<div class="errorContent">请勿重复提交请求！</div>
				<div class="errorUrl">点击此处<a href="#" onclick="window.history.back(); return false;">返回</a>，或回到<a href="${base}/">首页</a></div>
			</div>
		</div>
	</div>
</body>
</html>