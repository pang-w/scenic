<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>支付结果</title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/assets/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/shop/css/payment.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/assets/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/assets/shop/js/register.js"></script>
</head>
<body class="paymentResult">
	<#include "header.ftl">
	<div class="body">
		<div class="blank"></div>
		<div class="paymentResultDetail">
			<#if paymentResult == "success">
				<span class="icon success"> </span>请根据您选择的支付方式来选择银行汇款。汇款以后，请立即通知我们，支付总金额<span class="red">${(amountPayable + paymentFee)?string(orderUnitCurrencyFormat)}</span>
			<#else>
				<span class="icon error"> </span>支付错误</span>
			</#if>
			<div class="buttonArea">
				<input type="submit" class="formButton" onclick="location.href='${base}/'" value="返回首页" />
			</div>
		</div>
		<div class="blank"></div>
		<#include "friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "footer.ftl">
</body>
</html>