<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>预存款列表</title>
<meta name="Author" content="ZUUYII" />
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
	<div class="body orderList">
		<div class="bodyLeft">
			<#include "member_menu.ftl">
		</div>
		<div class="bodyRight bodyCenter">
			<div class="memberCenterDetail">
				<div class="top">预存款记录 <span class="red">[预存款余额：${loginMember.deposit?string(orderUnitCurrencyFormat)}]</span></div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<th>操作类型</th>
							<th>存入金额</th>
							<th>支出金额</th>
							<th>当前余额</th>
							<th>日期</th>
						</tr>
						<#list pager.list as list>
							<tr>
								<td>
									<#if (list.payment != null && list.payment.order != null)!>
										<a href="order!view.action?id=${list.payment.order.id}">
											${action.getText("DepositType." + list.depositType)}
										</a>
									<#else>
										${action.getText("DepositType." + list.depositType)}
									</#if>
								</td>
								<td>
									<#if list.credit != 0>
										${list.credit?string(orderUnitCurrencyFormat)}
									<#else>
										-
									</#if>
								</td>
								<td>
									<#if list.debit != 0>
										${list.debit?string(orderUnitCurrencyFormat)}
									<#else>
										-
									</#if>
								</td>
								<td>
									${list.balance?string(orderUnitCurrencyFormat)}
								</td>
								<td>
									<span title="${list.createDate?string("yyyy-MM-dd HH:mm:ss")}">${list.createDate}</span>
								</td>
							</tr>
						</#list>
					</table>
					<div class="blank"></div>
         			<link href="${base}/assets/shop/css/pager.css" rel="stylesheet" type="text/css" />
         			<#import "pager.ftl" as p>
         			<@p.pager pager = pager baseUrl = "/shop/deposit!list.action" />
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "footer.ftl">
</body>
</html>