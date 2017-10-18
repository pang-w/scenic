<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>系统设置 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$().ready(function() {

	var $isLoginFailureLockInput = $("input[name='systemConfig.isLoginFailureLock']");
	var $loginFailureLockCountTr = $("#loginFailureLockCountTr");
	var $loginFailureLockTimeTr = $("#loginFailureLockTimeTr");

	var $smtpFromMail = $("input[name='systemConfig.smtpFromMail']");
	var $smtpHost = $("input[name='systemConfig.smtpHost']");
	var $smtpPort = $("input[name='systemConfig.smtpPort']");
	var $smtpUsername = $("input[name='systemConfig.smtpUsername']");
	var $smtpPassword = $("input[name='systemConfig.smtpPassword']");
	var $showSmtpTestWindow = $("#showSmtpTestWindow");
	var $pointType = $("input[name='systemConfig.pointType']");
	var $pointScaleTr = $(".pointScaleTr");
	var $pointScale = $("input[name='systemConfig.pointScale']");
	
	$isLoginFailureLockInput.click( function() {
		$this = $(this);
		if($this.val() == "true") {
			$loginFailureLockCountTr.show();
			$loginFailureLockTimeTr.show();
		} else {
			$loginFailureLockCountTr.hide();
			$loginFailureLockTimeTr.hide();
		}
	});
	
	// SMTP测试窗口
	$showSmtpTestWindow.click( function() {
		var windowHtml = '<form id="smtpTestForm" action="mail!ajaxSendSmtpTest.action"><input type="hidden" name="smtpFromMail" class="{required: true, email: true, messages: {required: \'请填写发件人邮箱!\', email: \'发件人邮箱格式错误!\'}}" value="' + $smtpFromMail.val() + '" /><input type="hidden" name="smtpHost" value="' + $smtpHost.val() + '" class="{required: true, messages: {required: \'请填写SMTP服务器地址!\'}}" /><input type="hidden" name="smtpPort" value="' + $smtpPort.val() + '" class="{required: true, digits: true, messages: {required: \'请填写SMTP服务器端口!\', digits: \'SMTP服务器端口必须为零或正整数!\'}}" /><input type="hidden" name="smtpUsername" value="' + $smtpUsername.val() + '" class="{required: true, messages: {required: \'请填写SMTP用户名!\'}}" /><input type="hidden" name="smtpPassword" value="' + $smtpPassword.val() + '" class="{required: true, messages: {required: \'请填写SMTP密码!\'}}" />收件人邮箱：<input type="text" name="smtpToMail" class="formText {required: true, email: true, messages: {required: \'请填写收件人邮箱!\', email: \'收件人邮箱格式错误!\'}}" /><div class="blank"></div><p id="smtpTestStatus"></p><div class="blank"></div><input type="submit" class="formButton" value="测试发送" /></form>';
		$.window("邮箱配置测试", windowHtml);
	});
	
	// 提交测试窗口
	$("#smtpTestForm").livequery("submit", function() {
		
		// 表单验证
		$("#smtpTestForm").validate({
			invalidHandler: function(form, validator) {
				$.each(validator.invalid, function(key, value){
					$.tip(value);
					return false;
				});
			},
			errorPlacement:function(error, element) {},
			submitHandler: function(form) {
				$("#submitButton").attr("disabled", true);
				$("#smtpTestForm").ajaxSubmit({
					dataType: "json",
					beforeSubmit: function(data) {
						$("#smtpTestStatus").html('<span class="loadingIcon">&nbsp;</span>正在发送测试邮件，请稍后...');
					},
					success: function(data) {
						$("#smtpTestStatus").empty();
						$("#submitButton").attr("disabled", false);
						$.closeWindow();
						$.message(data.status, data.message);
					}
				});
			}
		});
	});
	
	// 根据积分获取方式显示/隐藏“积分换算比率”
	$pointType.click( function() {
		$this = $(this);
		if($this.val() == "orderAmount") {
			$pointScale.removeClass("ignoreValidate");
			$pointScaleTr.show();
		} else {
			$pointScale.val("0");
			$pointScale.addClass("ignoreValidate");
			$pointScaleTr.hide();
		}
	});
	
})
</script>
<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>编辑个人资料</h1>
		</div>
		<form id="inputForm" class="validate" class="form" action="system_config!update.action" enctype="multipart/form-data" method="post">
			<div class="blank"></div>
			<ul class="tab">
				<li>
					<input type="button" value="基本设置" hidefocus="true" />
				</li>
				<li>
					<input type="button" value="显示设置" hidefocus="true" />
				</li>
				<li>
					<input type="button" value="安全设置" hidefocus="true" />
				</li>
				<li>
					<input type="button" value="邮件设置" hidefocus="true" />
				</li>
				<li>
					<input type="button" value="其它设置" hidefocus="true" />
				</li>
			</ul>
			<table class="inputTable tabContent">
				<tr>
					<th>
						网店名称:
					</th>
					<td>
						<input type="text" name="systemConfig.shopName" class="formText {required: true}" value="${(systemConfig.shopName)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						网店网址:
					</th>
					<td>
						<input type="text" name="systemConfig.shopUrl" class="formText {required: true, url: true}" title="必须以http://开头" value="${(systemConfig.shopUrl)!"http://"}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						网店Logo:
					</th>
					<td>
						<input type="file" name="shopLogo" />&nbsp;&nbsp;&nbsp;<a href="${base}${(systemConfig.shopLogo)!}" class="imagePreview" target="_blank">查看</a>
					</td>
				</tr>
				<tr>
					<th>
						热门搜索关键词:
					</th>
					<td>
						<input type="text" name="systemConfig.hotSearch" class="formText" value="${(systemConfig.hotSearch)!}" title="页面显示的热门搜索关键字,多个关键字请以(${stack.findValue("@net.shopxx.bean.SystemConfig@HOT_SEARCH_SEPARATOR")})分隔" />
					</td>
				</tr>
				<tr>
					<th>
						联系地址:
					</th>
					<td>
						<input type="text" name="systemConfig.address" class="formText" value="${(systemConfig.address)!}" />
					</td>
				</tr>
				<tr>
					<th>
						服务电话:
					</th>
					<td>
						<input type="text" name="systemConfig.phone" class="formText {phone: true}" value="${(systemConfig.phone)!}" />
					</td>
				</tr>
				<tr>
					<th>
						邮编:
					</th>
					<td>
						<input type="text" name="systemConfig.zipCode" class="formText {zipCode: true}" value="${(systemConfig.zipCode)!}" />
					</td>
				</tr>
				<tr>
					<th>
						E-mail:
					</th>
					<td>
						<input type="text" name="systemConfig.email" class="formText {email: true}" value="${(systemConfig.email)!}" />
					</td>
				</tr>
				<tr>
					<th>
						备案号:
					</th>
					<td>
						<input type="text" name="systemConfig.certtext" class="formText" value="${(systemConfig.certtext)!}" title="填写您在工信部备案管理网站申请的备案编号" />
					</td>
				</tr>
				<tr>
					<th>
						首页页面关键词:
					</th>
					<td>
						<input type="text" name="systemConfig.metaKeywords" class="formText" value="${(systemConfig.metaKeywords)!}" title="多个关键字请以(,)分隔" />
					</td>
				</tr>
				<tr>
					<th>
						首页页面描述:
					</th>
					<td>
						<textarea name="systemConfig.metaDescription" class="formTextarea">${(systemConfig.metaDescription)!}</textarea>
					</td>
				</tr>
			</table>
			<table class="inputTable tabContent">
				<tr>
					<th>
						商品图片（大）:
					</th>
					<td>
						<span class="fieldTitle">宽度:</span>
						<input type="text" name="systemConfig.bigProductImageWidth" class="formText {required: true, digits: true}" value="${(systemConfig.bigProductImageWidth)!}" style="width: 50px;" title="单位:像素, 只允许输入零或正整数" />
						<label class="requireField">*</label>
						<span class="fieldTitle">高度:</span>
						<input type="text" name="systemConfig.bigProductImageHeight" class="formText {required: true, digits: true}" value="${(systemConfig.bigProductImageHeight)!}" style="width: 50px;" title="单位:像素, 只允许输入零或正整数" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						商品图片（小）:
					</th>
					<td>
						<span class="fieldTitle">宽度:</span>
						<input type="text" name="systemConfig.smallProductImageWidth" class="formText {required: true, digits: true}" value="${(systemConfig.smallProductImageWidth)!}" style="width: 50px;" title="单位:像素, 只允许输入零或正整数" />
						<label class="requireField">*</label>
						<span class="fieldTitle">高度:</span>
						<input type="text" name="systemConfig.smallProductImageHeight" class="formText {required: true, digits: true}" value="${(systemConfig.smallProductImageHeight)!}" style="width: 50px;" title="单位:像素, 只允许输入零或正整数" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						商品缩略图:
					</th>
					<td>
						<span class="fieldTitle">宽度:</span>
						<input type="text" name="systemConfig.thumbnailProductImageWidth" class="formText {required: true, digits: true}" value="${(systemConfig.thumbnailProductImageWidth)!}" style="width: 50px;" title="单位:像素, 只允许输入零或正整数" />
						<label class="requireField">*</label>
						<span class="fieldTitle">高度:</span>
						<input type="text" name="systemConfig.thumbnailProductImageHeight" class="formText {required: true, digits: true}" value="${(systemConfig.thumbnailProductImageHeight)!}" style="width: 50px;" title="单位:像素, 只允许输入零或正整数" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						默认商品图片（大）:
					</th>
					<td>
						<input type="file" name="defaultBigProductImage" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}${(systemConfig.defaultBigProductImagePath)!}" class="imagePreview" target="_blank">查看</a>
					</td>
				</tr>
				<tr>
					<th>
						默认商品图片（小）:
					</th>
					<td>
						<input type="file" name="defaultSmallProductImage" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}${(systemConfig.defaultSmallProductImagePath)!}" class="imagePreview" target="_blank">查看</a>
					</td>
				</tr>
				<tr>
					<th>
						默认缩略图:
					</th>
					<td>
						<input type="file" name="defaultThumbnailProductImage" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}${(systemConfig.defaultThumbnailProductImagePath)!}" class="imagePreview" target="_blank">查看</a>
					</td>
				</tr>
				<tr>
					<th>
						水印图片:
					</th>
					<td>
						<input type="file" name="watermarkImage" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}${(systemConfig.watermarkImagePath)!}" class="imagePreview" target="_blank">查看</a>
					</td>
				</tr>
				<tr>
					<th>
						水印位置:
					</th>
					<td>
						<#list allWatermarkPosition as list>
							<label><input type="radio" name="systemConfig.watermarkPosition" value="${list}" <#if (systemConfig.watermarkPosition == list)!> checked="true"</#if> />${action.getText("WatermarkPosition." + list)}&nbsp;</label>
						</#list>
					</td>
				</tr>
				<tr>
					<th>
						水印透明度:
					</th>
					<td>
						<input type="text" name="systemConfig.watermarkAlpha" class="formText {required: true, digits: true}" value="${(systemConfig.watermarkAlpha)!}" title="取值范围:0-100,  0代表完全透明" />
						<label class="requireField">*</label>
					</td>
				</tr>
			</table>
			<table class="inputTable tabContent">
				<tr>
					<th>
						是否开放注册:
					</th>
					<td>
						<label><input type="radio" name="systemConfig.isRegister" value="true"<#if systemConfig.isRegister == true> checked</#if> />是</label>
						<label><input type="radio" name="systemConfig.isRegister" value="false"<#if systemConfig.isRegister == false> checked</#if> />否</label>
					</td>
				</tr>
				<tr>
					<th>
						是否自动锁定账号:
					</th>
					<td>
						<label><input type="radio" name="systemConfig.isLoginFailureLock" value="true"<#if systemConfig.isLoginFailureLock == true> checked</#if> />是</label>
						<label><input type="radio" name="systemConfig.isLoginFailureLock" value="false"<#if systemConfig.isLoginFailureLock == false> checked</#if> />否</label>
					</td>
				</tr>
				<tr id="loginFailureLockCountTr"<#if systemConfig.isLoginFailureLock == false> class="hidden"</#if>>
					<th>
						连续登录失败最大次数:
					</th>
					<td>
						<input type="text" name="systemConfig.loginFailureLockCount" class="formText {required: true, positiveInteger: true}" value="${(systemConfig.loginFailureLockCount)!}" title="只允许输入正整数,当连续登录失败次数超过设定值时,系统将自动锁定该账号" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr id="loginFailureLockTimeTr"<#if systemConfig.isLoginFailureLock == false> class="hidden"</#if>>
					<th>
						自动解锁时间:
					</th>
					<td>
						<input type="text" name="systemConfig.loginFailureLockTime" class="formText {required: true, digits: true}" value="${(systemConfig.loginFailureLockTime)!}" title="只允许输入零或正整数,账号锁定后,自动解除锁定的时间,单位:分钟,0表示永久锁定" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						允许上传图片文件扩展名:
					</th>
					<td>
						<input type="text" name="systemConfig.allowedUploadImageExtension" class="formText" value="${(systemConfig.allowedUploadImageExtension)!}" title="为空表示不允许上传图片文件,多个扩展名请以(${stack.findValue("@net.shopxx.bean.SystemConfig@EXTENSION_SEPARATOR")})分隔" />
					</td>
				</tr>
				<tr>
					<th>
						允许上传媒体文件扩展名:
					</th>
					<td>
						<input type="text" name="systemConfig.allowedUploadMediaExtension" class="formText" value="${(systemConfig.allowedUploadMediaExtension)!}" title="为空表示不允许上传媒体文件,多个扩展名请以(${stack.findValue("@net.shopxx.bean.SystemConfig@EXTENSION_SEPARATOR")})分隔" />
					</td>
				</tr>
				<tr>
					<th>
						允许上传其它文件扩展名:
					</th>
					<td>
						<input type="text" name="systemConfig.allowedUploadFileExtension" class="formText" value="${(systemConfig.allowedUploadFileExtension)!}" title="为空表示不允许上传其它文件,多个扩展名请以(${stack.findValue("@net.shopxx.bean.SystemConfig@EXTENSION_SEPARATOR")})分隔" />
					</td>
				</tr>
				<tr>
					<th>
						文件上传最大值:
					</th>
					<td>
						<select name="systemConfig.uploadLimit">
							<option value="512" <#if systemConfig.uploadLimit == 512>selected="selected" </#if>>
								512K
							</option>
							<option value="1024" <#if systemConfig.uploadLimit == 1024>selected="selected" </#if>>
								1M
							</option>
							<option value="2048" <#if systemConfig.uploadLimit == 2048>selected="selected" </#if>>
								2M
							</option>
							<option value="3072" <#if systemConfig.uploadLimit == 3072>selected="selected" </#if>>
								3M
							</option>
							<option value="5120" <#if systemConfig.uploadLimit == 5120>selected="selected" </#if>>
								5M
							</option>
							<option value="0" <#if systemConfig.uploadLimit == 0>selected="selected" </#if>>
								无限制
							</option>
						</select>
						<label class="requireField">*</label>
					</td>
				</tr>
			</table>
			<table class="inputTable tabContent">
				<tr>
					<th>
						发件人邮箱:
					</th>
					<td>
						<input type="text" name="systemConfig.smtpFromMail" class="formText {email: true}" value="${(systemConfig.smtpFromMail)!}" />
					</td>
				</tr>
				<tr>
					<th>
						SMTP服务器地址:
					</th>
					<td>
						<input type="text" name="systemConfig.smtpHost" class="formText" value="${(systemConfig.smtpHost)!}" />
					</td>
				</tr>
				<tr>
					<th>
						SMTP服务器端口:
					</th>
					<td>
						<input type="text" name="systemConfig.smtpPort" class="formText {digits: true}" value="${(systemConfig.smtpPort)!}" title="默认端口为25" />
					</td>
				</tr>
				<tr>
					<th>
						SMTP用户名:
					</th>
					<td>
						<input type="text" name="systemConfig.smtpUsername" class="formText" value="${(systemConfig.smtpUsername)!}" />
					</td>
				</tr>
				<tr>
					<th>
						SMTP密码:
					</th>
					<td>
						<input type="password" name="systemConfig.smtpPassword" class="formText" title="留空则不进行密码修改" />
					</td>
				</tr>
				<tr>
					<th>
						邮箱配置测试:
					</th>
					<td>
						<input type="button" id="showSmtpTestWindow" class="formButtonL" value="邮箱配置测试" />
					</td>
				</tr>
			</table>
			<table class="inputTable tabContent">
				<tr>
					<th>
						货币种类:
					</th>
					<td>
						<select id="currencyType" name="systemConfig.currencyType" class="{required: true}">
							<option value="">请选择...</option>
							<#list allCurrencyType as list>
								<option value="${list}"<#if (list == systemConfig.currencyType)!> selected </#if>>
								${action.getText("CurrencyType." + list)}
								</option>
							</#list>
						</select>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						货币符号:
					</th>
					<td>
						<input type="text" name="systemConfig.currencySign" class="formText {required: true}" value="${systemConfig.currencySign}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						货币单位:
					</th>
					<td>
						<input type="text" name="systemConfig.currencyUnit" class="formText {required: true}" value="${systemConfig.currencyUnit}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						商品价格精确位数:
					</th>
					<td>
						<select name="systemConfig.priceScale">
							<option value="0" <#if systemConfig.priceScale == 0>selected="selected" </#if>>
								无小数位
							</option>
							<option value="1" <#if systemConfig.priceScale == 1>selected="selected" </#if>>
								1位小数
							</option>
							<option value="2" <#if systemConfig.priceScale == 2>selected="selected" </#if>>
								2位小数
							</option>
							<option value="3" <#if systemConfig.priceScale == 3>selected="selected" </#if>>
								3位小数
							</option>
							<option value="4" <#if systemConfig.priceScale == 4>selected="selected" </#if>>
								4位小数
							</option>
						</select>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						商品价格精确方式:
					</th>
					<td>
						<select name="systemConfig.priceRoundType">
							<#list allRoundType as list>
								<option value="${list}" <#if systemConfig.priceRoundType == list>selected="selected" </#if>>
									${action.getText("RoundType." + list)}
								</option>
							</#list>
						</select>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						 订单金额精确位数:
					</th>
					<td>
						<select name="systemConfig.orderScale">
							<option value="0" <#if systemConfig.orderScale == 0>selected="selected" </#if>>
								无小数位
							</option>
							<option value="1" <#if systemConfig.orderScale == 1>selected="selected" </#if>>
								1位小数
							</option>
							<option value="2" <#if systemConfig.orderScale == 2>selected="selected" </#if>>
								2位小数
							</option>
							<option value="3" <#if systemConfig.orderScale == 3>selected="selected" </#if>>
								3位小数
							</option>
							<option value="4" <#if systemConfig.orderScale == 4>selected="selected" </#if>>
								4位小数
							</option>
						</select>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						订单金额精确方式:
					</th>
					<td>
						<select name="systemConfig.orderRoundType">
							<#list allRoundType as list>
								<option value="${list}" <#if systemConfig.orderRoundType == list>selected="selected" </#if>>
									${action.getText("RoundType." + list)}
								</option>
							</#list>
						</select>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						商品库存报警数量:
					</th>
					<td>
						<input type="text" name="systemConfig.storeAlertCount" class="formText {required: true, digits: true}" value="${systemConfig.storeAlertCount}" title="只允许输入零或正整数" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						库存预占时间点:
					</th>
					<td>
						<label title="设置库存预先扣除（占用）的时间点，而系统实际扣除库存点是发货操作" >
						<select name="systemConfig.storeFreezeTime" class="requireField" >
							<#list allStoreFreezeTime as list>
								<option value="${list}" <#if systemConfig.storeFreezeTime == list>selected="selected" </#if>>
									${action.getText("StoreFreezeTime." + list)}
								</option>
							</#list>
						</select>
						</label>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						积分获取方式:
					</th>
					<td>
						<#list allPointType as list>
							<label>
								<input type="radio" name="systemConfig.pointType" value="${list}" <#if systemConfig.pointType == list> checked="true"</#if> />${action.getText("PointType." + list)}&nbsp;
							</label>
						</#list>
					</td>
				</tr>
				<tr class="pointScaleTr<#if systemConfig.pointType != "orderAmount"> hidden</#if>">
					<th>
						积分换算比率:
					</th>
					<td>
						<input type="text" name="systemConfig.pointScale" class="formText {required: true, min: 0}<#if systemConfig.pointType != "orderAmount"> ignoreValidate</#if>" value="${systemConfig.pointScale}" title="每消费1元可获得积分数" />
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
			</div>
		</form>
	</div>
</body>
</html>