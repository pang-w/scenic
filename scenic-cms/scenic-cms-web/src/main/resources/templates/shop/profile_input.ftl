<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>个人信息 </title>
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

	// 地区选择菜单
	$(".areaSelect").lSelect({
		url: "${base}/shop/area!ajaxChildrenArea.action"// Json数据获取url
	});

});
</script>
</head>
<body class="memberCenter">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body profileInput">
		<div class="bodyLeft">
			<#include "/WEB-INF/template/shop/member_menu.ftl">
		</div>
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">
					个人信息
				</div>
				<div class="middle">
					<div class="blank"></div>
					<form id="inputForm" class="validate" action="profile!update.action" method="post">
						<table class="inputTable">
							<tr>
								<th>
									E-mail:
								</th>
								<td>
									<input type="text" name="member.email" class="formText {required: true, messages: {required: '请输入E-mail地址！'}, email: true}" class="{required: true, messages: {required: '请选择收货地址！'}}" value="${(member.email)!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<#list enabledMemberAttributeList as list>
								<tr>
									<th>
										${list.name}:
									</th>
									<td>
										<#if list.attributeType == "text">
											<input type="text" name="${list.id}" class="formText<#if list.isRequired> {required: true}</#if>" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "number">
											<input type="text" name="${list.id}" class="formText {<#if list.isRequired>required: true, </#if>number: true}" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "alphaint">
											<input type="text" name="${list.id}" class="formText {<#if list.isRequired>required: true, </#if>lettersonly: true}" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "email">
											<input type="text" name="${list.id}" class="formText {<#if list.isRequired>required: true, </#if>email: true}" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "select">
											<select name="${list.id}"<#if list.isRequired> class="{required: true}"</#if>>
												<option value="">请选择...</option>
												<#list list.attributeOptionList as attributeOptionList>
													<option value="${attributeOptionList}"<#if (member.memberAttributeMap.get(list)[0] == attributeOptionList)!> selected</#if>>${attributeOptionList}</option>
												</#list>
											</select>
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "checkbox">
											<#list list.attributeOptionList as attributeOptionList>
												<label><input type="checkbox" name="${list.id}"<#if list.isRequired> class="{required: true, messagePosition: '#${list.id}MessagePosition'}"</#if> value="${attributeOptionList}"<#if (member.memberAttributeMap.get(list).contains(attributeOptionList))!> checked</#if> />${attributeOptionList}</label>
											</#list>
											<span id="${list.id}MessagePosition"></span>
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "name">
											<input type="text" name="${list.id}" class="formText <#if list.isRequired>{required: true}</#if>" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "gender">
											<label><input type="radio" name="${list.id}"<#if list.isRequired> class="{required: true, messagePosition: '#${list.id}MessagePosition'}"</#if> value="male" <#if (member.memberAttributeMap.get(list)[0] == "male")!> checked</#if> />${action.getText("Gender.male")}</label>
											<label><input type="radio" name="${list.id}"<#if list.isRequired> class="{required: true, messagePosition: '#${list.id}MessagePosition'}"</#if> value="female" <#if (member.memberAttributeMap.get(list)[0] == "female")!> checked</#if> />${action.getText("Gender.female")}</label>
											<span id="${list.id}MessagePosition"></span>
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "date">
											<input type="text" name="${list.id}" class="formText datePicker {<#if list.isRequired>required: true, </#if>dateISO: true}" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "area">
											<input type="text" name="${list.id}" class="formText areaSelect<#if list.isRequired> {required: true}</#if>" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "address">
											<input type="text" name="${list.id}" class="formText <#if list.isRequired>{required: true}</#if>" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "zipCode">
											<input type="text" name="${list.id}" class="formText {<#if list.isRequired>required: true, </#if>zipCode: true}" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "mobile">
											<input type="text" name="${list.id}" class="formText {<#if list.isRequired>required: true, </#if>mobile: true}" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "phone">
											<input type="text" name="${list.id}" class="formText {<#if list.isRequired>required: true, </#if>phone: true}" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "qq">
											<input type="text" name="${list.id}" class="formText <#if list.isRequired>{required: true}</#if>" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "msn">
											<input type="text" name="${list.id}" class="formText <#if list.isRequired>{required: true}</#if>" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "wangwang">
											<input type="text" name="${list.id}" class="formText <#if list.isRequired>{required: true}</#if>" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										<#elseif list.attributeType == "skype">
											<input type="text" name="${list.id}" class="formText <#if list.isRequired>{required: true}</#if>" value="${(member.memberAttributeMap.get(list)[0])!}" />
											<#if list.isRequired><label class="requireField">*</label></#if>
										</#if>
									</td>
								</tr>
							</#list>
							<tr>
								<th>
									 
								</th>
								<td>
									<input type="submit" class="submitButton" value="提 交" hidefocus="true" />
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