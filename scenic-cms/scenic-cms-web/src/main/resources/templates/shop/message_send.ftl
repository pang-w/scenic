﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发送消息 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/member_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
<script type="text/javascript">
$().ready( function() {
	
	$(".messageType").click( function(event) {
		$toMemberTr = $(".toMemberTr");
		$toMemberInput = $(".toMemberTr input");
		if ($(this).val() == "member") {
			$toMemberTr.show();
			$toMemberInput.attr("disabled", false);
		} else {
			$toMemberTr.hide();
			$toMemberInput.attr("disabled", true);
		}
	});
	
});
</script>
</head>
<body class="memberCenter">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body messageSend">
		<div class="bodyLeft">
			<#include "/WEB-INF/template/shop/member_menu.ftl">
		</div>
		</div>
		<div class="bodyRight bodyCenter">
			<div class="memberCenterDetail">
				<div class="top">
					发送消息
				</div>
				<div class="middle">
					<div class="blank"></div>
					<form id="inputForm" class="validate" action="message!save.action" method="post">
						<table class="inputTable">
							<tr>
								<th>
									发送给:
								</th>
								<td>
									<label><input type="radio" name="messageType" class="messageType" value="member"<#if (message == null || message.toMember != null)!> checked</#if>>其它会员</label>
									<label><input type="radio" name="messageType" class="messageType" value="admin"<#if (message.toMember == null)!> checked</#if>>管理员</label>
								</td>
							</tr>
							<tr class="toMemberTr"<#if (message.toMember == null)!> style="display: none;"</#if>>
								<th>
									对方用户名:
								</th>
								<td>
									<input type="text" name="toMemberUsername" class="formText {required: true, notEqual: '${loginMember.username}', remote: 'message!checkUsername.action', messages: {required: '请填写用户名!', notEqual:'收件人不允许为自己!', remote: '此会员不存在!'}}" value="${(message.toMember.username)!}"<#if (message.toMember == null)!> disabled="true"</#if> />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									标题:
								</th>
								<td>
									<input type="text" name="message.title" class="formText {required: true, messages: {required: '请填写标题!'}}" value="${(message.title)!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									内容:
								</th>
								<td>
									<textarea name="message.content" class="formTextarea {required: true, messages: {required: '请填写消息内容!'}}" rows="5" cols="50">${(message.content)!}</textarea>
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									选项:
								</th>
								<td>
									<label><input type="radio" name="message.isSaveDraftbox" value="false" checked>立即发送</label>
									<label><input type="radio" name="message.isSaveDraftbox" value="true">保存到草稿箱</label>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
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