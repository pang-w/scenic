<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>密码找回 </title>
<meta name="Author" content="足衣" />
<meta name="Copyright" content="足衣客" />
</head>
<body>
<p>亲爱的${member.username}：</p>
<p>您好！感谢您使用${systemConfig.shopName}。</p>
<p>您提交了密码找回操作，您可以通过点击<a href="${systemConfig.shopUrl}/shop/member!passwordModify.action?id=${member.id}&passwordRecoverKey=${member.passwordRecoverKey}">更改密码</a>进行密码修改。</p>
</body>
</html>