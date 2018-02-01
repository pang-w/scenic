jQuery(function($) {
	$("#createArticle").click(function() {
		var uuid = support.uuid(8,16);
		window.location = "/action/edit/article/" + uuid;
	});
	
	function logout() {
		support.ajax("user/auth/logout", null,function(response) {
			location.href = "/index.html";
		});
	}
	
});
