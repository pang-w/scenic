<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>会员中心</title>
<meta name="Author" content="足衣客男鞋网" />
<meta name="Copyright" content="足衣男鞋" />
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
	<div class="body memberCenterIndex">
		<div class="bodyLeft">
			<#include "member_menu.ftl">
		</div>
		<div class="bodyRight bodyCenter">
			<div class="memberCenterDetail">
				<div class="top">
					会员中心首页
				</div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<td colspan="4">
								您目前是[${loginMember.memberRank.name}]
								<#if loginMember.memberRank.preferentialScale != 100>
									<span class="red">[优惠百分比：${loginMember.memberRank.preferentialScale}%]</span>
								</#if>
							</td>
						</tr>
						<tr>
							<th>帐户总积分</th>
							<td>${loginMember.point}</td>
							<th>订单总数量</th>
							<td>
								${loginMember.orderSet?size}&nbsp;&nbsp;
								<a href="order!list.action">[查看订单列表]</a>
							</td>
						</tr>
						<tr>
							<th>预存款余额</th>
							<td>${loginMember.deposit?string(orderUnitCurrencyFormat)}</td>
							<th>未读消息数</th>
							<td>
								${unreadMessageCount}&nbsp;&nbsp;
								<a href="message!inbox.action">[查看收件箱]</a>
							</td>
						</tr>
						<tr>
							<th>注册日期</th>
							<td>${loginMember.createDate?string("yyyy-MM-dd HH:mm:ss")}</td>
							<th>最后登录IP</th>
							<td>${loginMember.loginIp}</td>
						</tr>
					</table>
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<th style="width: 300px;">商品名称</th>
							<th>订单编号</th>
							<th>下单时间</th>
							<th>订单金额</th>
							<th>订单状态</th>
						</tr>
						<#list loginMember.orderSet as list>
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
							<#if (list_index + 1 > 10)>
								<#break />
							</#if>
						</#list>
						<tr>
							<td colspan="5">
								<a href="order!list.action">更多订单>></a>
							</td>
						</tr>
					</table>
					<div class="blank"></div>
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