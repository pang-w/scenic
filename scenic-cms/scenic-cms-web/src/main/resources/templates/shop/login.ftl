﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>会员登录</title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<#if (article.metaKeywords)! != ""><meta name="keywords" content="${article.metaKeywords}" /></#if>
<#if (article.metaDescription)! != ""><meta name="description" content="${article.metaDescription}" /></#if>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
</head>
<body class="login">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="blank"></div>
	<div class="body">
		<div class="loginDetail">
			<div class="top">会员登录</div>
			<div class="middle">
				<form id="loginForm" action="${base}/shop/member!login.action"  method="post">
					<table>
						<tr>
							<th>用户名</th>
							<td>
								<input type="text" id="loginUsername" name="member.username" class="formText" />
							</td>
						</tr>
						<tr>
							<th>密   码</th>
							<td>
								<input type="password" id="loginPassword" name="member.password" class="formText" />
							</td>
						</tr>
						<tr>
							<th>验证码</th>
							<td>
								<input type="text" id="loginCaptcha" name="j_captcha" class="formTextS" />
								<img id="loginCaptchaImage" src="${base}/captcha.jpg" alt="换一张" />
							</td>
						</tr>
						<tr>
							<th> </th>
							<td>
								<span class="warnIcon"> </span><a href="${base}/shop/member!passwordRecover.action">忘记了密码? 点击找回!</a>
							</td>
						</tr>
						<tr>
							<th> </th>
							<td>
								<input type="submit" class="submitButton" value="登 录" hidefocus="true" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="bottom"></div>
		</div>
		<div class="blank"></div>
		<div class="blank"></div>
		
	</div>
	<div class="blank"></div>
<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>