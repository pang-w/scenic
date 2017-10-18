﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理登录</title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

// 登录页面若在框架内，则跳出框架
if (self != top) {
	top.location = self.location;
};

$().ready( function() {

	var $username = $("#username");
	var $password = $("#password");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $isSaveUsername = $("#isSaveUsername");

	// 判断"记住用户名"功能是否默认选中,并自动填充登录用户名
	if($.cookie("adminUsername") != null) {
		$isSaveUsername.attr("checked", true);
		$username.val($.cookie("adminUsername"));
		$password.focus();
	} else {
		$isSaveUsername.attr("checked", false);
		$username.focus();
	}

	// 提交表单验证,记住登录用户名
	$("#loginForm").submit( function() {
		if ($username.val() == "") {
			$.message("请输入您的用户名!");
			return false;
		}
		if ($password.val() == "") {
			$.message("请输入您的密码!");
			return false;
		}
		if ($captcha.val() == "") {
			$.message("请输入您的验证码!");
			return false;
		}
		
		if($isSaveUsername.attr("checked") == true) {
			$.cookie("adminUsername", $username.val(), {expires: 30});
		} else {
			$.cookie("adminUsername", null);
		}
		
	});

	// 刷新验证码
	$captchaImage.click( function() {
		var timestamp = (new Date()).valueOf();
		var imageSrc = $captchaImage.attr("src");
		if(imageSrc.indexOf("?") >= 0) {
			imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
		}
		imageSrc = imageSrc + "?timestamp=" + timestamp;
		$captchaImage.attr("src", imageSrc);
	});

	<#if (actionErrors?size > 0)>
		$.message("error", "<#list errorMessages as list>${list}<br></#list>");
	</#if>

});
</script>
</head>
<body class="login">
	<div class="body">
		<div class="loginBox">
			<div class="boxTop"></div>
			<div class="boxMiddle">
				<form id="loginForm" class="form" action="${base}/admin/loginVerify" method="post">
					<div class="loginLogo">
				    	<img src="${base}/template/admin/images/login_logo.gif" />
				    </div>
		            <table class="loginTable">
		            	<tr>
		                    <th>
		                    	用户名:
		                    </th>
							<td>
		                    	<div class="formText">
		                    		<input type="text" id="username" name="j_username" />
		                    	</div>
		                    </td>
		                </tr>
		                <tr>
							<th>
								密&nbsp;&nbsp;&nbsp;码:
							</th>
		                    <td>
		                    	<div class="formText">
		                    		<input type="password" id="password" name="j_password" />
		                    	</div>
		                    </td>
		                </tr>
		                <tr>
		                	<th>
		                		验证码:
		                	</th>
		                    <td>
		                    	<div class="formTextS">
		                    		<input type="text" id="captcha" name="j_captcha" />
		                    	</div>
		                    	<div class="captchaImage">
		                   			<img id="captchaImage" src="${base}/captcha.jpg" alt="换一张" />
		                   		</div>
		                    </td>
		                </tr>
		                <tr>
		                	<th>
		                		&nbsp;
		                	</th>
		                    <td>
		                    	<input type="checkbox" id="isSaveUsername" /><label for="isSaveUsername">&nbsp;记住用户名</label>
		                    </td>
		                </tr>
		                <tr>
		                	<th>
		                		&nbsp;
		                	</th>
		                    <td>
		                        <input type="button" class="homeButton ignoreForm" value="" onclick="window.open('${base}/')" hidefocus="true" />
		                        <input type="submit" class="submitButton ignoreForm" value="登 陆" hidefocus="true" />
		                        <input type="button" class="helpButton ignoreForm" value="帮 助" onclick="window.open('http://help.shopxx.net')" hidefocus="true" />
		                    </td>
		                </tr>
		            </table>
		        </form>
			</div>
			<div class="boxBottom"></div>
		</div>
		<div class="blank"></div>
		
	</div>
</body>
</html>