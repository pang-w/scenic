<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>订单列表</title>
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
	<div class="body orderList">
		<div class="bodyLeft">
			<#include "/WEB-INF/template/shop/member_menu.ftl">
		</div>
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">订单列表</div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<th style="width: 300px;">商品名称</th>
							<th>订单编号</th>
							<th>下单时间</th>
							<th>订单金额</th>
							<th>订单状态</th>
						</tr>
						<#list pager.list as list>
							<tr>
								<td>
									<a href="order!view.action?id=${list.id}">
										<span title="<#list list.productItemSet as list><#if list_index != 0>、</#if>${list.name}</#list>">
											<#list list.orderItemSet as list>
												<#if list_index != 0>、</#if>
												${list.productName}
												<#if list_index == 3 && list_has_next>
													...<#break />
												</#if>
											</#list>
										</span>
									</a>
								</td>
								<td>
									<a href="order!view.action?id=${list.id}">${list.orderSn}</a>
								</td>
								<td>
									<span title="${list.createDate?string("yyyy-MM-dd HH:mm:ss")}">${list.createDate}</span>
								</td>
								<td>
									${list.totalAmount?string(orderUnitCurrencyFormat)}
								</td>
								<td>
									<#if list.orderStatus != "processed" && list.orderStatus != "unprocessed">
										<p>[${action.getText("OrderStatus." + list.orderStatus)}]</p>
									</#if>
									<p>[${action.getText("PaymentStatus." + list.paymentStatus)}]</p>
									<p>[${action.getText("ShippingStatus." + list.shippingStatus)}]</p>
								</td>
							</tr>
						</#list>
					</table>
					<div class="blank"></div>
         			<link href="${base}/template/shop/css/pager.css" rel="stylesheet" type="text/css" />
         			<#import "/WEB-INF/template/shop/pager.ftl" as p>
         			<@p.pager pager = pager baseUrl = "/shop/order!list.action" />
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