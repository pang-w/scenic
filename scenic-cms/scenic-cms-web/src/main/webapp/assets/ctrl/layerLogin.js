jQuery(function($) {

	$("#doLayerLogin").click(function() {
		var data = {
			"username" : $("#iukusername").val(),
			"password" : $("#iukpassword").val()
		};
		support.ajax("user/auth/login", data, function(response) {
			window.parent.changeToLogged(data.username);
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}, function(response) {
			layer.msg(response.msg);
		});
	});

});