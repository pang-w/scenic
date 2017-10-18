<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>密码修改</title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/single_page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
</head>
<body class="singlePage">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body passwordRecover">
		<div class="titleBar">
			<div class="left"></div>
			<div class="middle">
				<span class="icon"> </span>密码修改
			</div>
			<div class="right"></div>
		</div>
		<div class="blank"></div>
		<div class="singlePageDetail">
			<form id="inputForm" class="validate" action="${base}/shop/member!passwordUpdate.action" method="post">
				<input type="hidden" name="id" value="${id}" />
				<input type="hidden" name="passwordRecoverKey" value="${passwordRecoverKey}" />
				<table class="inputTable">
					<tr>
						<th>新密码：</th>
						<td>
							<input type="password" id="password" name="member.password" class="formText {required: true, minlength: 4, maxlength: 20, messages: {required: '请填写新密码!'}}" />
						</td>
					</tr>
					<tr>
						<th>确认新密码：</th>
						<td>
							<input type="password" name="rePassword" class="formText {required: true, equalTo: '#password', messages: {required: '请填写确认新密码！', equalTo: '两次密码输入不一致！'}}" />
						</td>
					</tr>
					<tr>
						<th> </th>
						<td>
							<input type="submit" id="submitButton" class="formButton" value="确  定" hidefocus="true" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>