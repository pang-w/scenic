<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>填写订单信息</title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/order.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
<script type="text/javascript">
$().ready( function() {

	var productTotalPrice = ${productTotalPrice};// 商品总价格
	var deliveryFee = 0;// 配送费用
	var paymentFee = 0;// 支付费用
	
	var $orderInfoForm = $("#orderInfoForm");
	var $areaSelect = $(".areaSelect");
	var $receiverInput = $("input[name='receiver.id']");
	var $otherReceiverTable = $("#otherReceiverTable");
	var $otherReceiverTableInput = $(".otherReceiverTable :input");
	var $deliveryTypeInput = $("input[name='deliveryType.id']");
	var $paymentConfigTr = $(".paymentConfigTr");
	var $paymentConfigInput = $("input[name='paymentConfig.id']");
	var $productTotalPrice = $("#productTotalPrice");
	var $deliveryFee = $("#deliveryFee");
	var $paymentFee = $("#paymentFee");
	var $orderAmount = $("#orderAmount");
	
	// 地区选择菜单
	$areaSelect.lSelect({
		url: "${base}/shop/area!ajaxChildrenArea.action"// Json数据获取url
	});
	
	// 如果默认选择“其它收货地址”，则显示“其它收货地址输入框”
	if ($receiverInput.val() == "") {
		$otherReceiverTable.fadeIn();
		$otherReceiverTableInput.removeClass("ignoreValidate");
	} else {
		$otherReceiverTableInput.addClass("ignoreValidate");
	}
	
	// 显示“其它收货地址输入框”
	$receiverInput.click( function() {
		$this = $(this);
		if ($this.val() == "") {
			$otherReceiverTable.fadeIn();
			$otherReceiverTableInput.removeClass("ignoreValidate");
		} else {
			$otherReceiverTable.fadeOut();
			$otherReceiverTableInput.addClass("ignoreValidate");
		}
	});
	
	// 根据配送方式修改配送费用、订单总金额，并显示/隐藏支付方式
	$deliveryTypeInput.click( function() {
		var $this = $(this);
		var $parent = $this.parent();
		$paymentConfigInput.attr("checked", false);
		paymentFee = 0;
		var deliveryMethod = $parent.metadata().deliveryMethod;
		if (deliveryMethod == "deliveryAgainstPayment") {
			$paymentConfigInput.removeClass("ignoreValidate");
			$paymentConfigTr.show();
		} else {
			$paymentConfigInput.addClass("ignoreValidate");
			$paymentConfigTr.hide();
		}
		deliveryFee = $parent.metadata().deliveryFee;
		$deliveryFee.text(orderUnitCurrencyFormat(deliveryFee));
		$paymentFee.text(orderUnitCurrencyFormat(paymentFee));
		$orderAmount.text(orderUnitCurrencyFormat(floatAdd(floatAdd(productTotalPrice, deliveryFee), paymentFee)));
	});
	
	// 根据支付方式修改订单总金额
	$paymentConfigInput.click( function() {
		var $this = $(this);
		var $parent = $this.parent();
		var paymentFeeTypeChecked = $parent.metadata().paymentFeeType;
		var paymentFeeChecked = $parent.metadata().paymentFee;
		if (paymentFeeTypeChecked == "scale") {
			paymentFee = floatMul(floatAdd(productTotalPrice, deliveryFee), floatDiv(paymentFeeChecked, 100));
		} else {
			paymentFee = paymentFeeChecked;
		}
		$paymentFee.text(orderUnitCurrencyFormat(paymentFee));
		$orderAmount.text(orderUnitCurrencyFormat(floatAdd(productTotalPrice, floatAdd(deliveryFee, paymentFee))));
	});
	
	// 表单验证
	$orderInfoForm.validate({
		ignore: ".ignoreValidate",
		invalidHandler: function(form, validator) {
			$.each(validator.invalid, function(key, value){
				$.tip(value);
				return false;
			});
		},
		errorPlacement:function(error, element) {},
		submitHandler: function(form) {
			$orderInfoForm.find(":submit").attr("disabled", true);
			form.submit();
		}
	});
	
});
</script>
</head>
<body class="orderInfo">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body">
		<div class="blank"></div>
		<div class="orderInfoDetail">
			<form id="orderInfoForm" action="order!save.action" method="post">
				<@s.token />
				<table class="orderInfoTable">
					<tr>
						<th>收货信息</th>
						<td>
							<ul>
								<#list loginMember.receiverSet as list>
									<li>
										<label>
											<input type="radio" name="receiver.id" class="{required: true, messages: {required: '请选择收货地址！'}}" value="${list.id}"<#if list.isDefault> checked</#if> />
											<strong>收货人：</strong>${list.name}    
											<#if (list.mobile != null)!>
												<strong>手机：</strong>${list.mobile}    
											<#else>
												<strong>电话：</strong>${list.phone}    
											</#if>
											<strong>收货地址：</strong>${list.address}
										</label>
									</li>
								</#list>
								<li>
									<label>
										<input type="radio" name="receiver.id" class="{required: true, messages: {required: '请选择收货地址！'}}" value=""<#if (loginMember.receiverSet == null || loginMember.receiverSet?size == 0)!> checked</#if> />
										填写收货地址
									</label>
									<div class="blank"></div>
									<table id="otherReceiverTable" class="otherReceiverTable">
										<tr>
											<th>
												收货人姓名:
											</th>
											<td>
												<input type="text" name="receiver.name" class="formText {required: true, messages: {required: '请填写收货人姓名！'}}" />
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												地区:
											</th>
											<td>
												<input type="text" name="receiver.areaPath" class="areaSelect hidden {required: true, messages: {required: '请选择地区！'}, messagePosition: '#areaMessagePosition'}" />
												<span id="areaMessagePosition"></span>
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												地址:
											</th>
											<td>
												<input type="text" name="receiver.address" class="formText {required: true, messages: {required: '请填写地址！'}}" />
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												电话:
											</th>
											<td>
												<input type="text" name="receiver.phone" class="formText {requiredOne: '#shipMobile', phone: true, messages: {requiredOne: '电话、手机必须填写其中一项！'}}" />
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												手机:
											</th>
											<td>
												<input type="text" id="shipMobile" name="receiver.mobile" class="formText {mobile: true, messages: {mobile: '手机格式错误！'}}" />
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												邮编:
											</th>
											<td>
												<input type="text" name="receiver.zipCode" class="formText {required: true, zipCode: true, messages: {required: '请填写邮编！', zipCode: '邮编格式错误！'}}" />
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												是否保存:
											</th>
											<td>
												<label><input type="radio" name="isSaveReceiver" value="true" checked />是</label>  
												<label><input type="radio" name="isSaveReceiver" value="false" />否</label>
											</td>
										</tr>
									</table>
								</li>
							</ul>
						</td>
					</tr>
					<tr>
						<th>配送方式</th>
						<td>
							<table class="deliveryTypeTable">
								<#list allDeliveryType as list>
									<tr>
										<th>
											<label class="{deliveryFee: '${list.getDeliveryFee(totalWeightGram)}', deliveryMethod: '${list.deliveryMethod}'}">
												<input type="radio" name="deliveryType.id" class="{required: true, messages: {required: '请选择配送方式！'}}" value="${list.id}" />
												${list.name}
											</label>
										</th>
										<td>
											<strong class="red">+ ${(list.getDeliveryFee(totalWeightGram)?string(orderCurrencyFormat))!}</strong>
											${(list.description)!}
										</td>
									</tr>
								</#list>
							</table>
						</td>
					</tr>
					<tr class="paymentConfigTr">
						<th>支付方式</th>
						<td>
							<table class="paymentConfigTable">
								<#list allPaymentConfig as list>
									<tr>
										<th>
											<label class="{paymentFeeType: '${list.paymentFeeType}', paymentFee: '${list.paymentFee}'}">
												<input type="radio" name="paymentConfig.id" class="{required: true, messages: {required: '请选择支付方式！'}}" value="${list.id}" />
												${list.name}
											</label>
										</th>
										<td>
											<#if list.paymentFeeType == "scale" && list.paymentFee != 0>
												[支付费率：${list.paymentFee}%]
											<#elseif list.paymentFeeType == "fixed" && list.paymentFee != 0>
												[支付费用：${list.paymentFee?string(orderUnitCurrencyFormat)}]
											</#if>
											<p>${list.description}</p>
										</td>
									</tr>
								</#list>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2"> </td>
					</tr>
					<tr>
						<th>附言</th>
						<td>
							<input type="text" name="memo" class="formText" />
						</td>
					</tr>
					<tr>
						<td colspan="2"> </td>
					</tr>
				</table>
				<div class="blank"></div>
				<table class="cartItemTable">
					<tr>
						<th>商品</th>
						<th>销售价格</th>
						<#if (loginMember.memberRank.preferentialScale != 100)!>
							<th>优惠价格</th>
						</#if>
						<th>商品重量</th>
						<th>小计</th>
						<th>数量</th>
					</tr>
					<#list cartItemSet as list>
						<tr>
							<td class="productName">
								<a href="${base}${list.product.htmlFilePath}" target="_blank">
									<img src="${base}${(list.product.productImageList[0].thumbnailProductImagePath)!systemConfig.defaultThumbnailProductImagePath}" /> ${list.name}
								</a>
							</td>
							<#if (loginMember.memberRank.preferentialScale != 100)!>
								<td class="priceTd">
									<span class="lineThrough">${list.product.price?string(priceCurrencyFormat)}</span>
								</td>
								<td class="priceTd">
									${list.preferentialPrice?string(priceCurrencyFormat)}
								</td>
							<#else>
								<td class="priceTd">
									${list.product.price?string(priceCurrencyFormat)}
								</td>
							</#if>
							<td>
								${list.product.weight} ${action.getText("WeightUnit." + list.product.weightUnit)}
							</td>
							<td>
								<span class="subtotalPrice">${list.subtotalPrice?string(orderCurrencyFormat)}</span>
							</td>
							<td>
								${list.quantity}
							</td>
						</tr>
					</#list>
					<tr>
						<td class="info" colspan="<#if (loginMember.memberRank.preferentialScale != 100)!>6<#else>5</#if>">
							商品共计：<span class="red">${totalQuantity}</span> 件    
							<#if systemConfig.pointType != "disable">
								积分：<span id="totalPoint" class="red">${totalPoint}</span>    
							</#if>
							商品总金额：<span id="productTotalPrice" class="red">${productTotalPrice?string(orderUnitCurrencyFormat)}</span>    
							配送费用：<span id="deliveryFee" class="red">${0?string(orderUnitCurrencyFormat)}</span>    
							支付手续费：<span id="paymentFee" class="red">${0?string(orderUnitCurrencyFormat)}</span>    
							订单总金额：<span id="orderAmount" class="red">${(productTotalPrice)?string(orderUnitCurrencyFormat)}</span>
						</td>
					</tr>
				</table>
				<div class="blank"></div>
				<a class="backCartItem" href="${base}/shop/cart_item!list.action"><span class="icon"> </span>返回购物车</a>
				<input type="submit" class="formButton" value="去结算" />
				<div class="clearfix"></div>
			</form>
		</div>
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>