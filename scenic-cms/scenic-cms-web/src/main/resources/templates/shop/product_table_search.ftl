﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${(pager.keyword)!} 商品搜索结果 </title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<meta name="keywords" content="${pager.keyword}" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/product.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/product_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/product.js"></script>
</head>
<body class="productList">
	<div id="addCartItemTip" class="addCartItemTip">
		<div class="top">
			<div class="tipClose addCartItemTipClose"></div>
		</div>
		<div class="middle">
			<p>
				<span id="addCartItemTipMessageIcon"> </span>
				<span id="addCartItemTipMessage"></span>
			</p>
			<p id="addCartItemTipInfo" class="red"></p>
			<input type="button" class="formButton tipClose" value="继续购物" hidefocus="true" />
			<input type="button" class="formButton" onclick="location.href='${base}/shop/cart_item!list.action'" value="进入购物车" hidefocus="true" />
		</div>
		<div class="bottom"></div>
	</div>
	<#include "/WEB-INF/template/shop/header.ftl" />
	<div class="body">
		<div class="bodyLeft">
			<#include "/WEB-INF/template/shop/components/product_menu.ftl">
			<div class="blank"></div>
			<div class="hotProduct">
				<div class="top">热销排行</div>
				<div class="middle clearfix">
					<ul>
						<#list (hotProductList)! as list>
							<li class="number${list_index + 1}">
								<#if (list.name?length < 15)>
									<span class="icon"> </span><a href="${base}${list.htmlFilePath}" title="${list.name}">${list.name}</a>
								<#else>
									<span class="icon"> </span><a href="${base}${list.htmlFilePath}" title="${list.name}">${list.name[0..12]}...</a>
								</#if>
							</li>
							<#if list_index + 1 == 10>
								<#break />
							</#if>
						</#list>
					</ul>
				</div>
				<div class="bottom"></div>
			</div>
			<div class="blank"></div>
			<div class="productHistory">
				<div class="top">浏览记录</div>
				<div class="middle clearfix">
					<ul id="productHistoryListDetail"></ul>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="bodyRight">
			<form id="productListForm" action="${base}/shop/product!search.action" method="get">
				<input type="hidden" id="viewType" name="viewType" value="tableType" />
				<input type="hidden" id="pageNumber" name="pager.pageNumber" value="${pager.pageNumber}" />
				<input type="hidden" name="pager.keyword" value="${pager.keyword}" />
				<div class="listBar">
					<div class="left"></div>
					<div class="middleCenter">
						<div class="path">
							<a href="${base}/" class="home"><span class="icon"> </span>首页</a>> 搜索 "${(pager.keyword)!}" 结果列表
						</div>
						<div class="total">共计: ${pager.totalCount} 款商品</div>
					</div>
					<div class="right"></div>
				</div>
				<div class="blank"></div>
				<div class="operateBar">
					<div class="left"></div>
					<div class="middleCenter">
						<span class="tableDisabledIcon"> </span>列表
						<span class="pictureIcon"> </span><a id="pictureType" class="pictureType" href="#">图片</a>
						<span class="separator"> </span>
						<select id="orderType" name="orderType">
							<option value="default"<#if orderType == "default"> selected="selected"</#if>>默认排序</option>
							<option value="priceAsc"<#if orderType == "priceAsc"> selected="selected"</#if>>价格从低到高</option>
							<option value="priceDesc"<#if orderType == "priceDesc"> selected="selected"</#if>>价格从高到低</option>
							<option value="dateAsc"<#if orderType == "dateAsc"> selected="selected"</#if>>按上价时间排序</option>
	                    </select>
	                    <span class="separator"> </span>
	                    显示数量:
	                    <select name="pager.pageSize" id="pageSize">
							<option value="12" <#if pager.pageSize == 12>selected="selected" </#if>>
								12
							</option>
							<option value="24" <#if pager.pageSize == 24>selected="selected" </#if>>
								24
							</option>
							<option value="60" <#if pager.pageSize == 60>selected="selected" </#if>>
								60
							</option>
							<option value="120" <#if pager.pageSize == 120>selected="selected" </#if>>
								120
							</option>
						</select>
					</div>
					<div class="right"></div>
				</div>
				<div class="blank"></div>
				<div class="productTableList">
					<ul class="productListDetail">
						<#list pager.list as list>
							<li>
								<a href="${base}${list.htmlFilePath}" class="productImage" target="_blank">
									<img src="${base}${(list.productImageList[0].thumbnailProductImagePath)!systemConfig.defaultThumbnailProductImagePath}" alt="${list.name}" />
								</a>
								<div class="productTitle">
									<#if (list.name?length < 28)>
										<a href="${base}${list.htmlFilePath}" alt="${list.name}" target="_blank">${list.name}</a>
									<#else>
										<a href="${base}${list.htmlFilePath}" alt="${list.name}" target="_blank">${list.name[0..25]}...</a>
									</#if>
								</div>
								<div class="productRight">
									<div class="productPrice">
										<span class="price">${list.price?string(priceCurrencyFormat)}</span>
										<span class="marketPrice">${list.marketPrice?string(priceCurrencyFormat)}</span>
									</div>
									<div class="productButton">
										<#if list.isOutOfStock>
											<input type="button" class="outOfStockButton" value="" hidefocus="true" />
										<#else>
											<input type="button" name="addCartItemButton" class="addCartItemButton addCartItem {id: '${list.id}'}" value="" hidefocus="true" />
										</#if>
										<input type="button" name="addFavoriteButton" class="addFavoriteButton addFavorite {id: '${list.id}'}" value="" hidefocus="true" />
									</div>
								</div>
							</li>
						</#list>
						<#if (pager.list?size == 0)!>
                			<li class="noRecord">非常抱歉，没有找到相关商品！</li>
                		</#if>
					</ul>
					<div class="blank"></div>
         			<link href="${base}/template/shop/css/pager.css" rel="stylesheet" type="text/css" />
         			<#import "/WEB-INF/template/shop/pager.ftl" as p>
         			<#assign parameterMap = {"orderType": (orderType)!, "viewType": (viewType)!} />
         			<@p.pager pager = pager baseUrl = "/shop/product!search.action" parameterMap = parameterMap />
				</div>
			</form>
		</div>
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl" />
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl" />
</body>
</html>