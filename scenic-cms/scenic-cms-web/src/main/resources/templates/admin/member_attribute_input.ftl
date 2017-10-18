<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑会员注册项  - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$().ready(function() {

	var $memberAttributeType = $("#memberAttributeType");
	var $memberAttributeTypeTr = $("#memberAttributeTypeTr");
	var $addAndRemoveTr = $("#addAndRemoveTr");

	// 显示选项内容
	$memberAttributeType.change(function() {
		memberAttributeChange();
	})
	
	// 增加选项内容输入框
	$("#addImage").click( function() {
		addAttributeOptionTr();
	})
	
	// 减少选项内容输入框
	$("#removeImage").click( function() {
		removeAttributeOptionTr();
	})
	
	// 删除选项内容输入框
	$(".deleteImage").livequery("click", function() {
		if($(".attributeOptionTr").length > 1) {
			$(this).parent().parent().remove();
		} else {
			alert("请至少保留一个选项!");
		}
	});

	function memberAttributeChange() {
		$addAndRemoveTr.hide();
		$(".attributeOptionTr").remove();
		if($memberAttributeType.val() == "select" || $memberAttributeType.val() == "checkbox") {
			addAttributeOptionTr();
			$addAndRemoveTr.show();
		}
	}
	
	function addAttributeOptionTr() {
		var attributeOptionTrHtml = '<tr class="attributeOptionTr"><th>选项内容:</th><td><input type="text" name="attributeOptionList" class="formText attributeOption {required: true}" />&nbsp;<img src="${base}/template/admin/images/input_delete_icon.gif" class="deleteImage" alt="删除" /></td></tr>';
		if($(".attributeOptionTr").length > 0) {
			$(".attributeOptionTr:last").after(attributeOptionTrHtml);
		} else {
			$memberAttributeTypeTr.after(attributeOptionTrHtml);
		}
	}

	function removeAttributeOptionTr() {
		if($(".attributeOptionTr").length > 1) {
			$(".attributeOptionTr:last").remove();
		} else {
			alert("请至少保留一个选项!");
		}
	}

})
</script>
<style type="text/css">
<!--

.deleteImage, #addImage, #removeImage {
	cursor: pointer;
}

-->
</style>
<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加会员注册项<#else>编辑会员注册项</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>member_attribute!save.action<#else>member_attribute!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<table class="inputTable">
				<tr>
					<th>
						注册项名称:
					</th>
					<td>
						<input type="text" name="memberAttribute.name" class="formText {required: true, remote: 'member_attribute!checkName.action?oldValue=${(memberAttribute.name?url)!}', messages: {remote: '注册项名称已存在!'}}" value="${(memberAttribute.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr id="memberAttributeTypeTr">
					<th>
						注册项类型:
					</th>
					<td>
						<select id="memberAttributeType" name="memberAttribute.attributeType" class="{required: true}">
							<option value="">请选择...</option>
							<#list allAttributeType as list>
								<option value="${list}"<#if (list == memberAttribute.attributeType)!> selected</#if>>
									${action.getText("AttributeType." + list)}
								</option>
							</#list>
						</select>
						<label class="requireField">*</label>
					</td>
				</tr>
				<#if (memberAttribute.attributeType == "select" || memberAttribute.attributeType == "checkbox")!>
					<#list memberAttribute.attributeOptionList as list>
						<tr class="attributeOptionTr">
							<th>选项内容:</th>
							<td>
								<input type="text" name="attributeOptionList" class="formText attributeOption {required: true}" value="${list}" />
								&nbsp;<img src="${base}/template/admin/images/input_delete_icon.gif" class="deleteImage" alt="删除" />
							</td>
						</tr>
					</#list>
					<tr id="addAndRemoveTr">
				<#else>
					<tr id="addAndRemoveTr" style="display: none;">
				</#if>
					<td class="label">
						&nbsp;
					</td>
					<td>
						<img src="${base}/template/admin/images/input_add_icon.gif" id="addImage" alt="增加选项" />&nbsp;&nbsp;&nbsp;&nbsp;
						<img src="${base}/template/admin/images/input_remove_icon.gif" id="removeImage" alt="减少选项" />
					</td>
				</tr>
				<tr>
					<th>
						排序:
					</th>
					<td>
						<input type="text" name="memberAttribute.orderList" class="formText {required: true, digits: true}" value="${(memberAttribute.orderList)!50}" title="只允许输入零或正整数" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						是否必填:
					</th>
					<td>
						<label><input type="radio" name="memberAttribute.isRequired" value="true"<#if (memberAttribute.isRequired == true)!> checked</#if> />是</label>
						<label><input type="radio" name="memberAttribute.isRequired" value="false"<#if (isAdd || memberAttribute.isRequired == false)!> checked</#if> />否</label>
					</td>
				</tr>
				<tr>
					<th>
						是否启用:
					</th>
					<td>
						<label><input type="radio" name="memberAttribute.isEnabled" value="true"<#if (isAdd || memberAttribute.isEnabled == true)!> checked</#if> />是</label>
						<label><input type="radio" name="memberAttribute.isEnabled" value="false"<#if (memberAttribute.isEnabled == false)!> checked</#if> />否</label>
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