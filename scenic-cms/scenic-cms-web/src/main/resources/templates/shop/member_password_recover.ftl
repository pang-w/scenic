<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>密码找回</title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/assets/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/shop/css/single_page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/assets/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/assets/shop/js/register.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $safeAnswer = $("input[name='member.safeAnswer']");

	// 表单验证
	$("#passwordRecoverForm").validate({
		errorClass: "validateError",
		errorPlacement: function(error, element) {
			var messagePosition = element.metadata().messagePosition;
			if("undefined" != typeof messagePosition && messagePosition != "") {
				var $messagePosition = $(messagePosition);
				if ($messagePosition.size() > 0) {
					error.insertAfter($messagePosition).fadeOut(300).fadeIn(300);
				} else {
					error.insertAfter(element).fadeOut(300).fadeIn(300);
				}
			} else {
				error.insertAfter(element).fadeOut(300).fadeIn(300);
			}
		},
		submitHandler: function(form) {
			$("#submitButton").attr("disabled", true);
			// 表单提交
			$(form).ajaxSubmit({
				dataType: "json",
				beforeSubmit: function(data) {
					$("#status").html('<span class="loadingIcon"> </span>正在检测用户状态，请稍后...');
				},
				success: function(data) {
					if (data.status == "warn") {
						$("#status").html(data.message);
						var windowHtml = '<table class="windowInputTable"><tr><th>保护问题：</th><td>' + data.safeQuestion + '</td></tr><tr><th>问题回答：</th><td><input type="text" name="safeAnswerInput" class="formText {required: true, messages: {required: \'请填写密码保护回答！\'}}" /></td></tr><tr><th> </th><td><p id="safeQuestionStatus"></p><input type="button" id="safeQuestionButton" class="formButton" value="确  定" /></td></tr></table>';
						$.window("密码保护问题", windowHtml);
					} else if (data.status == "success") {
						$safeAnswer.val("");
						$("#status").html(data.message);
						$.closeWindow();
						$.message(data.status, data.message);
					} else {
						$("#status").html(data.message);
						$.message(data.status, data.message);
					}
					$("#submitButton").attr("disabled", false);
				}
			});
		}
	});
	
	// 提交表单
	$("#safeQuestionButton").livequery("click", function() {
		var safeAnswerVal = $("input[name='safeAnswerInput']").val();
		if ($.trim(safeAnswerVal) == "") {
			$.tip("请填写保护答案!");
			return false;
		}
		$safeAnswer.val(safeAnswerVal);
		$("#passwordRecoverForm").submit();
	})

})
</script>
</head>
<body class="singlePage">
	<#include "header.ftl">
	<div class="body passwordRecover">
		<div class="titleBar">
			<div class="left"></div>
			<div class="middle">
				<span class="icon"> </span>找回密码
			</div>
			<div class="right"></div>
		</div>
		<div class="blank"></div>
		<div class="singlePageDetail">
			<form id="passwordRecoverForm" action="${base}/shop/member!sendPasswordRecoverMail.action" method="post">
				<input type="hidden" name="member.safeAnswer" />
				<table class="inputTable">
					<tr>
						<th>用户名：</th>
						<td>
							<input type="text" name="member.username" class="formText {required: true, messages: {required: '请填写用户名!'}}" />
						</td>
					</tr>
					<tr>
						<th>E-mail：</th>
						<td>
							<input type="text" name="member.email" class="formText {required: true, email: true, messages: {required: '请填写E-mail!'}}" />
						</td>
					</tr>
					<tr>
						<th> </th>
						<td>
							<input type="submit" id="submitButton" class="formButton" value="确  定" hidefocus="true" />
						</td>
					</tr>
					<tr>
						<th> </th>
						<td height="20">
							<span class="gray"><span id="status"></span></span> 
						</td>
					</tr>
					<tr>
						<th> </th>
						<td>
							<span class="warnIcon"> </span>如果忘记密码，请填写您的用户名和注册邮箱重新获取密码！
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="blank"></div>
		<#include "friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "footer.ftl">
</body>
</html>