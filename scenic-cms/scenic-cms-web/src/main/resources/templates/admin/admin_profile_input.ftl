<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑个人资料 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>编辑个人资料</h1>
		</div>
		<form id="inputForm" class="validate" action="admin_profile!update.action" method="post">
			<table class="inputTable">
				<tr>
					<th>
						用户名:
					</th>
					<td>
						${(admin.username)!}
					</td>
				</tr>
				<tr>
					<th>
						姓&nbsp;&nbsp;&nbsp;名:
					</th>
					<td>
						${(admin.name)!}
					</td>
				</tr>
				<tr>
					<th>
						部&nbsp;&nbsp;&nbsp;门:
					</th>
					<td>
						${(admin.department)!}
					</td>
				</tr>
				<tr>
					<th>
						当前密码:
					</th>
					<td>
						<input type="password" id="currentPassword" name="currentPassword" class="formText {remote: 'admin_profile!checkCurrentPassword.action', messages:{remote: '当前密码错误,请重新输入!'}}" />
					</td>
				</tr>
				<tr>
					<th>
						新密码:
					</th>
					<td>
						<input type="password" id="password" name="admin.password" class="formText {requiredTo: '#currentPassword', minlength: 4, maxlength: 20, messages:{requiredTo: '请输入新密码!'}}" title="密码长度只允许在4-20之间" />
					</td>
				</tr>
				<tr>
					<th>
						确认新密码:
					</th>
					<td>
						<input type="password" name="rePassword" class="formText {equalTo: '#password', messages:{equalTo: '两次密码输入不一致!'}}" />
					</td>
				</tr>
				<tr>
					<th>
						E-mail:
					</th>
					<td>
						<input type="text" name="admin.email" class="formText {email: true, required: true, messages:{required: '请输入E-mail!'}}" value="${(admin.email)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						&nbsp;
					</th>
					<td>
						<span class="warnInfo"><span class="icon">&nbsp;</span>系统提示：如果要修改密码，请先填写当前密码，如留空，则密码保持不变</span>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
			</div>
		</form>
	</div>
</body>
</html>