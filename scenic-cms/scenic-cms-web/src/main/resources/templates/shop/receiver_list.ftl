<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>收货地址 - Powered By ${systemConfig.systemName}</title>
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
	
	// 删除
	$(".deleteReceiver").click( function() {
		if (confirm("您确定要删除吗？") == false) {
			return false;
		}
	});
	
});
</script>
</head>
<body class="memberCenter">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body receiverList">
		<div class="bodyLeft">
			<#include "/WEB-INF/template/shop/member_menu.ftl">
		</div>
		<div class="bodyRight  bodyCenter">
			<div class="memberCenterDetail">
				<div class="top">
					收货地址
				</div>
				<div class="middle">
					<div class="blank"></div>
					<a class="addButton" href="${base}/shop/receiver!add.action" hidefocus="true">添加地址</a>
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<th>收货人</th>
							<th>地址</th>
							<th>电话</th>
							<th>手机</th>
							<th>邮编</th>
							<th>默认</th>
							<th>操作</th>
						</tr>
						<#list receiverSet as list>
							<tr>
								<td>
									${list.name}
								</td>
								<td>
									${list.address}
								</td>
								<td>
									${list.phone}
								</td>
								<td>
									${list.mobile}
								</td>
								<td>
									${list.zipCode}
								</td>
								<td>
									<#if list.isDefault>是<#else>否</#if>
								</td>
								<td>
									<a href="${base}/shop/receiver!edit.action?id=${list.id}">编辑</a>
									<a href="${base}/shop/receiver!delete.action?id=${list.id}" class="deleteReceiver">删除</a>
								</td>
							</tr>
						</#list>
					</table>
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