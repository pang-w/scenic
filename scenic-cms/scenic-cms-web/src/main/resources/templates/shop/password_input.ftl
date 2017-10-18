<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>修改密码 </title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/member_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
</head>
<body class="memberCenter">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body passwordInput">
		<div class="bodyLeft">
			<#include "/WEB-INF/template/shop/member_menu.ftl">
		</div>
		<div class="bodyRight bodyCenter">
			<div class="memberCenterDetail">
				<div class="top">
					修改密码
				</div>
				<div class="middle">
					<div class="blank"></div>
					<form id="inputForm" class="validate" action="password!update.action" method="post">
						<ul class="tab">
							<li>
								<input type="button" value="修改密码" hidefocus="true" />
							</li>
							<li>
								<input type="button" value="安全问题" hidefocus="true" />
							</li>
						</ul>
						<table class="inputTable tabContent">
							<tr>
								<th>
									旧密码:
								</th>
								<td>
									<input type="password" name="oldPassword" class="formText {requiredTo: '#password', messages: {requiredTo: '请输入旧密码!'}}" />
								</td>
							</tr>
							<tr>
								<th>
									新密码:
								</th>
								<td>
									<input type="password" id="password" name="member.password" class="formText {minlength: 4, maxlength : 20}" />
								</td>
							</tr>
							<tr>
								<th>
									确认新密码:
								</th>
								<td>
									<input type="password" class="formText {equalTo: '#password', messages: {equalTo: '两次密码输入不一致!'}}" />
								</td>
							</tr>
							<tr>
								<th>
									 
								</th>
								<td>
									<span class="warnInfo"><span class="icon"> </span>系统提示：若密码留空，则保持不变</span>
								</td>
							</tr>
							<tr>
								<th>
									 
								</th>
								<td>
									<input type="submit" class="submitButton" value="提 交" hidefocus="true" />
								</td>
							</tr>
						</table>
						<table class="inputTable tabContent">
							<tr>
								<th>
									安全问题:
								</th>
								<td>
									<input type="text" id="safeQuestion" name="member.safeQuestion" class="formText {requiredTo: '#safeAnswer', messages: {requiredTo: '请输入安全问题!'}}" />
								</td>
							</tr>
							<tr>
								<th>
									回答:
								</th>
								<td>
									<input type="text" id="safeAnswer" name="member.safeAnswer" class="formText {requiredTo: '#safeQuestion', messages: {requiredTo: '请输入回答!'}}" />
								</td>
							</tr>
							<tr>
								<th>
									 
								</th>
								<td>
									<span class="warnInfo"><span class="icon"> </span>系统提示：若安全问题留空，则保持不变</span>
								</td>
							</tr>
							<tr>
								<th>
									 
								</th>
								<td>
									<input type="submit" class="submitButton" value="提 交" hidefocus="true" />
								</td>
							</tr>
						</table>
					</form>
					<div class="blank"></div>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>