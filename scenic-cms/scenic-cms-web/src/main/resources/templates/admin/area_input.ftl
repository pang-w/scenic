<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑地区 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/assets/admin/css/input.css" rel="stylesheet" type="text/css" />
<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加地区<#else>编辑地区</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>area!save.action<#else>area!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden" name="parentId" value="${parentId}" />
			<table class="inputTable">
				<tr>
					<th>
						上级地区:
					</th>
					<td>
						<#if parent??>${(parent.name)!}<#else>一级地区</#if>
					</td>
				</tr>
				<tr>
					<th>
						地区名称:
					</th>
					<td>
						<input type="text" name="area.name" class="formText {required: true, remote: 'area!checkName.action?oldValue=${(area.name?url)!}&parentId=${(parent.id)!}', messages: {remote: '此地区名称已存在!'}}" value="${(area.name)!}" />
						<label class="requireField">*</label>
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