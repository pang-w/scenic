jQuery(function($) {
	$("#createArticle").click(function() {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status=="0000"){
						var uuid = support.uuid(8,16);
						window.location = "/action/edit/article/" + uuid;
					}else{
						layer.msg(response.msg);
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
		
	});
	$("#loginAction").click(function() {
		login();
	});
	$("#logoutAction").click(function() {
		logout();
	});
	$("#layerLoginAction").click(function() {
		support.layerLogin();
	});
	$("#likeSignatureBtn").click(function() {
		var data = {
				"belikedUser" : $("#signatureLikedUsername").text()
			};
		support.ajax("user/signature/likeSignature", data,function(response) {
			if (response.status == "0000") {
				location.reload();
			}
		});

		
	});
	
	$("#collapseArticleAction").click(function () {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						checkLogged();
						$('#collapseArticle').slideToggle("slow");
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
	$("#collapseAttentionAction").click(function () {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						checkLogged();
						$('#collapseAttention').slideToggle("slow");
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
	$("#collapseProductAction").click(function () {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						checkLogged();
						$('#collapseProduct').slideToggle("slow");
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
	$("#collapseImageAction").click(function () {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						checkLogged();
						$('#collapseImage').slideToggle("slow");
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
	
	function logout() {
		var data = {
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("user/auth/logout", null,function(response) {
			location.href = "/index.html";
		});
	}
	function login() {
		var data = {
			"username" : $("#iukusername").val(),
			"password" : $("#iukpassword").val()
		};
		support.ajax("user/auth/login", data, function(response) {
			checkLogged();
		});
	}
});






