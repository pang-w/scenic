<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${(systemConfig.shopName)!}</title>
<#if (systemConfig.metaKeywords)! != ""><meta name="keywords" content="${systemConfig.metaKeywords}" /></#if>
<#if (systemConfig.metaDescription)! != ""><meta name="description" content="${systemConfig.metaDescription}" /></#if>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/index.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/product.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/article.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/index.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/product.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	$(".slider .scrollable").scrollable({
		circular: true,
		speed: 500
	}).autoscroll({
		autoplay: true,
		interval: 4000
	}).navigator();
	
	$(".hotProduct .scrollable").scrollable({
		circular: true,
		speed: 500
	});
	
	$(".newProduct ul.newProductTab").tabs(".newProduct .newProductTabContent", {
		effect: "fade",// 逐渐显示动画
		fadeInSpeed: 500,// 动画显示速度
		event: "mouseover"// 触发tab切换的事件
	});

})
</script>
</head>
<body class="index">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body">
<div>
		<div class="bodyLeft">
			<#include "/WEB-INF/template/shop/components/product_menu.ftl">
	        </div>
		<div class="bodyRight">
			<div class="slider">
				<div class="scrollable">
					<div class="items">
						<div>
							<a href="http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0GoT805sipKacUlNiC3VtX9aa%2BKw9ms%2F3q4TPyWtnZ2U3WpF7F6zYQeAFWdDm5hYTweD4LAVCPgp2kJ0h9w%3D%3D" target="_blank"><img src="${base}/upload/image/banner1.jpg" /></a>
						</div>
						<div>
							<a href="http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0GoT805sipKacUlNiCrn8mF5YDuDN%2BLdBzydidOEbTNZYDuC1SUHIUEZfIG7vIqpgbeGB8lpgPXdK39sSyEI%3D" target="_blank"><img src="${base}/upload/image/banner2.jpg" /></a>
						</div>
						<div>
							<a href="http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0GoT805sipKacUlNiDYt4auRtKBbTSMsHDE1qJBCzyUKUllQzIcyLyEn1AjYsar8an2Y3T%2BZ3xE6c4qHRnA%3D%3D" target="_blank"><img src="${base}/upload/image/banner3.jpg" /></a>
						</div>
					</div>
					<div class="navi"></div>
					<div class="prevNext">
						<a class="prev browse left"></a>
						<a class="next browse right"></a>
					</div>
				</div>
			</div>

				<div class="scrollableBotom">
					<div class="items">
						<div>
							<a href="http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0GoT805sipKacUlNiC3VtX9aa%2BKw9ms%2F3q4TPyWtnZ2U3WpF7F6zYQeAFWdDm5hYTweD4LAVCPgp2kJ0h9w%3D%3D" target="_blank"><img src="${base}/upload/image/banner1.jpg" /></a>
						</div>
						
					</div>
					
				</div>
			
			<div class="blank"></div>
                </div>
<div class="bodyIndexCenter">
			<div class="hotsProduct">
				
				<div class="scrollable">
					<div class="items">
					  <div class="middle">
					   <ul>
						<#list (hotProductList)! as list>
							<li>
								<a href="${base}${list.htmlFilePath}">
									<img src="${base}${(list.productImageList[0].thumbnailProductImagePath)!systemConfig.defaultThumbnailProductImagePath}" alt="${list.name}" />
									<#if (list.name?length < 12)>
										<p title="${list.name}">${list.name}</p>
									<#else>
										<p title="${list.name}">${list.name[0..9]}...</p>
									</#if>
									
								</a>
							</li>
							<#if list_index + 1 == 16>
								<#break />
							</#if>
						</#list>
					    </ul>
					    <div class=""></div>
				          </div>
					</div>
				</div>

			</div>
		</div>
                  
</div>
<div class="blank"></div>
		<img src="${base}/upload/image/banner4.jpg" />
<div class="blank"></div>
		<div class="newProduct">
			<div class="left">
				<ul class="newProductTab">
					<#list rootProductCategoryList as list>
						<li>
							${list.name}
						</li>
						<#if list_index + 1 == 5>
							<#break />
						</#if>
					</#list>
				</ul>
			</div>
			<div class="right">
				<#list rootProductCategoryList as list>
					<ul class="newProductTabContent">
						<#list newProductMap[list.id] as list>
							<li>
								<a href="${base}${list.htmlFilePath}">
									<img src="${base}${(list.productImageList[0].thumbnailProductImagePath)!systemConfig.defaultThumbnailProductImagePath}" alt="${list.name}" />
									<#if (list.name?length < 12)>
										<p title="${list.name}">${list.name}</p>
									<#else>
										<p title="${list.name}">${list.name[0..9]}...</p>
									</#if>
								</a>
							</li>
							<#if list_index + 1 == 6>
								<#break>
							</#if>
						</#list>
					</ul>
					<#if list_index + 1 == 6>
						<#break />
					</#if>
				</#list>
			</div>
		</div>

		<div class="blank"></div>
		<div class="bodyLeft">
			<div class="hotProduct">
				<div class="top">热销排行</div>
				<div class="middle clearfix">
					<ul>
						<#list (hotProductList)! as list>
							<li class="number${list_index + 1}">
								<#if (list.name?length < 12)>
									<span class="icon"> </span><a href="${base}${list.htmlFilePath}" title="${list.name}">${list.name}</a>
								<#else>
									<span class="icon"> </span><a href="${base}${list.htmlFilePath}" title="${list.name}">${list.name[0..9]}...</a>
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
			<div class="hotArticle">
				<div class="top">热点文章</div>
				<div class="middle clearfix">
					<ul>
						<#list (hotArticleList)! as list>
							<li class="number${list_index + 1}">
								<#if (list.title?length < 12)>
									<span class="icon"> </span><a href="${base}${list.htmlFilePath}" title="${list.title}">${list.title}</a>
								<#else>
									<span class="icon"> </span><a href="${base}${list.htmlFilePath}" title="${list.title}">${list.title[0..9]}...</a>
								</#if>
							</li>
							<#if list_index + 1 == 10>
								<#break/>
							</#if>
						</#list>
					</ul>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		
			<div class="bestProduct">
				<div class="top">
					<strong>精品推荐</strong>BEST
				</div>
				<div class="middle">
					<ul>
						<#list (bestProductList)! as list>
							<li>
								<a href="${base}${list.htmlFilePath}">
									<img src="${base}${(list.productImageList[0].thumbnailProductImagePath)!systemConfig.defaultThumbnailProductImagePath}" alt="${list.name}" />
									<#if (list.name?length < 12)>
										<p title="${list.name}">${list.name}</p>
									<#else>
										<p title="${list.name}">${list.name[0..9]}...</p>
									</#if>
									<p class="red">${list.price?string(priceCurrencyFormat)}</p>
								</a>
							</li>
							<#if list_index + 1 == 15>
								<#break />
							</#if>
						</#list>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="bottom"></div>
			</div>
	
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>