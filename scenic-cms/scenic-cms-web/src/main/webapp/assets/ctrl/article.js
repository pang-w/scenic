jQuery(function($) {

	$("#likeArticleBtn").click(function() {
		var data = {
				"articleUuid" : $("#articleUuid").val()
			};
		support.ajax("user/article/likeArticle", data,function(response) {
			if (response.status == "0000") {
				location.reload();
			}
		});
	});
	$("#likeMessageBtn").click(function() {
		var data = {
				"articleUuid" : $("#articleUuid").val()
			};
		support.ajax("user/article/likeMessage", data,function(response) {
			if (response.status == "0000") {
				location.reload();
			}
		});
	});
	
	$("#addMessageBtn").click(function() {
		var data = {
				"articleUuid" : $("#articleUuid").val(),
				"message" : $("#articleMessage").val()
			};
		support.ajax("user/article/addMessage", data,function(response) {
			if (response.status == "0000") {
				layer.msg(response.msg);
			}
		});
	});
	$("#collapseAddMessageAction").click(function () {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						checkLogged();
						$('#collapseAddMessage').slideToggle("slow");
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
});







