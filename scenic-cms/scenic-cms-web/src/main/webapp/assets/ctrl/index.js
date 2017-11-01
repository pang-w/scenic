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
	function logout() {
		var data = {
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("user/auth/logout", null,function(response) {
			location.href = "/index.html";
		});
	}
	function checkLogged() {
		var data = {
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status=="0000"){
						changeToLogged(response.data.username);
					}},
				function(response) {
					if(response.status=="4004"){
					//changeToLogged(response.data.username);
					}
				}
		);
	}
	function login() {
		var data = {
			"username" : $("#iukusername").val(),
			"password" : $("#iukpassword").val()
		};
		support.ajax("user/auth/login", data, function(response) {
			window.location = "/i/" + response.data.username;
			layer.msg(response.msg);
		});
	}
	function changeToLogged(username) {
		
		window.location = "/i/" + username;

	}
});