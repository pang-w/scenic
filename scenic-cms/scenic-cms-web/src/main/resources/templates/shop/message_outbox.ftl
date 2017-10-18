<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发件箱 </title>
<meta name="Author" content="足衣男鞋" />
<meta name="Copyright" content="足衣" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/assets/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/shop/css/member_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/assets/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/assets/shop/js/register.js"></script>
<script type="text/javascript">
$().ready( function() {
	
	// 删除
	$(".deleteMessage").click( function() {
		if (confirm("您确定要删除吗？") == false) {
			return false;
		}
	});
	
	// 显示消息内容
	$(".showMessageContent").click( function() {
		var $this = $(this);
		var $showMessageContentIcon = $this.prev("span");
		var $messageContentTr = $this.parent().parent().next(".messageContentTr");
		if ($showMessageContentIcon.hasClass("downIcon")) {
			$messageContentTr.show();
			$showMessageContentIcon.removeClass("downIcon").addClass("upIcon");
		} else {
			$messageContentTr.hide();
			$showMessageContentIcon.removeClass("upIcon").addClass("downIcon");
		}
	});
	
});
</script>
<style type="text/css">
<!--

.messageOutbox .messageContentTr {
	display: none;
	background-color: #fafafa;
}

.messageOutbox .messageContentTr td {
	padding-left: 30px;
}

-->
</style>
</head>
<body class="memberCenter">
	<#include "header.ftl">
	<div class="body messageOutbox">
		<div class="bodyLeft">
			<#include "member_menu.ftl">
		</div>
		<div class="bodyRight bodyCenter">
			<div class="memberCenterDetail">
				<div class="top">
					发件箱
				</div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<th>标题</th>
							<th>收件人</th>
							<th>时间</th>
							<th>操作</th>
						</tr>
						<#list pager.list as list>
							<tr>
								<td>
									<span class="downIcon"> </span><a class="showMessageContent {id: '${list.id}'}" href="javascript: void(0);">${list.title}</a>
								</td>
								<td>
									${(list.toMember.username)!"<span class=\"green\">管理员</span>"}
								</td>
								<td>
									${list.createDate?string("yyyy-MM-dd HH:mm")}
								</td>
								<td>
									<a href="${base}/shop/message!delete.action?id=${list.id}" class="deleteMessage">删除</a>
								</td>
							</tr>
							<tr class="messageContentTr">
								<td colspan="4">
									${list.content}
								</td>
							</tr>
						</#list>
					</table>
					<div class="blank"></div>
         			<link href="${base}/assets/shop/css/pager.css" rel="stylesheet" type="text/css" />
         			<#import "pager.ftl" as p>
         			<@p.pager pager = pager baseUrl = "/shop/message!outbox.action" />
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