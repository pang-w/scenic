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
				"belikedUser" : $("#signatureLikedUsername").val()
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
					if(response.status=="0000"){
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
					if(response.status=="0000"){
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
					if(response.status=="0000"){
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
					if(response.status=="0000"){
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
	checkLogged();
});
function checkLogged() {
	support.ajax("user/auth/checkLogged", null,
			function(response) {
			//	loadIndexAll();
				if(response.status=="0000"){
					$("#loggedDropdown").slideDown();
					$("#loginLayerOutBtn").slideUp();
					$("#loggedUesername").text(response.data.username);
				}else{
					$("#loggedDropdown").slideUp();
					$("#loginLayerOutBtn").slideDown();
				}
			},
			function(response) {
				$("#loggedDropdown").slideUp();
				$("#loginLayerOutBtn").slideDown();
				if(response.status=="4004"){
				//changeToLogged(response.data.username);
				}
			}
	);
}







