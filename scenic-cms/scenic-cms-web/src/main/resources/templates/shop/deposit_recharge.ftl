<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>预存款充值</title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/member_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
<script type="text/javascript">
$().ready( function() {
	
	var $depositRechargeForm = $("#depositRechargeForm");
	
	// 表单验证
	$depositRechargeForm.validate({
		ignore: ".ignoreValidate",
		invalidHandler: function(form, validator) {
			$.each(validator.invalid, function(key, value){
				$.tip(value);
				return false;
			});
		},
		errorPlacement:function(error, element) {},
		submitHandler: function(form) {
			$depositRechargeForm.find(":submit").attr("disabled", true);
			form.submit();
		}
	});
	
});
</script>
</head>
<body class="memberCenter">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body depositRecharge">
		<div class="bodyLeft">
			<#include "/WEB-INF/template/shop/member_menu.ftl">
		</div>
		<div class="bodyRight  bodyCenter">
			<div class="memberCenterDetail">
				<div class="top">
					预存款充值
				</div>
				<div class="middle">
					<div class="blank"></div>
					<form id="depositRechargeForm" action="payment!confirm.action" method="post">
						<input type="hidden" name="paymentType" value="recharge" />
						<table class="inputTable">
							<tr>
								<th>
									充值金额:
								</th>
								<td>
									<input type="text" name="amountPayable" class="formText {required: true, positive: true, messages: {required: '请填写充值金额！', positive: '充值金额必须大于0！'}}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									支付方式:
								</th>
								<td>
									<table class="paymentConfigTable">
										<#list nonDepositOfflinePaymentConfigList as list>
											<tr>
												<td class="nameTd">
													<label>
														<input type="radio" name="paymentConfig.id" class="{required: true, messages: {required: '请选择支付方式！'}}" value="${list.id}" />
														${list.name}
													</label>
												</td>
												<td>
													<#if list.paymentFeeType == "scale" && list.paymentFee != 0>
														[支付手续费费率：${list.paymentFee}%]
													<#elseif list.paymentFeeType == "fixed" && list.paymentFee != 0>
														[支付手续费：${list.paymentFee?string(orderUnitCurrencyFormat)}]
													</#if>
													<p>${list.description}</p>
												</td>
											</tr>
										</#list>
									</table>
								</td>
							</tr>
							<tr>
								<th>
									 
								</th>
								<td>
									<input type="submit" class="submitButton" value="立刻充值" hidefocus="true" />
								</td>
							</tr>
						</table>
					</form>
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