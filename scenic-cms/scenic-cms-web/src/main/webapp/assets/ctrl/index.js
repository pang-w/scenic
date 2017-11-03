jQuery(function($) {
	$("#createArticle").click(function() {
		var uuid = support.uuid(8,16);
		window.location = "/action/edit/article/" + uuid;
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






