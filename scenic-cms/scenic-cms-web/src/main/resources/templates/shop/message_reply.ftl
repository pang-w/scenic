<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>回复消息 </title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/assets/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/shop/css/member_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/assets/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/assets/shop/js/register.js"></script>
</head>
<body class="memberCenter">
	<#include "header.ftl">
	<div class="body messageReply">
		<div class="bodyLeft">
			<#include "member_menu.ftl">
		</div>
		</div>
		<div class="bodyRight bodyCenter">
			<div class="memberCenterDetail">
				<div class="top">
					回复消息
				</div>
				<div class="middle">
					<div class="blank"></div>
					<form id="inputForm" class="validate" action="message!save.action" method="post">
						<input type="hidden" name="toMemberUsername" value="${(message.fromMember.username)!}" />
						<table class="inputTable">
							<tr>
								<th>
									收件人:
								</th>
								<td>
									${(message.fromMember.username)!"管理员"}
								</td>
							</tr>
							<tr>
								<th>
									标题:
								</th>
								<td>
									<input type="text" name="message.title" class="formText {required: true, messages: {required: '请填写标题!'}}" value="${message.title}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									内容:
								</th>
								<td>
									<textarea name="message.content" class="formTextarea {required: true, messages: {required: '请填写消息内容!'}}" rows="5" cols="50">${message.content}