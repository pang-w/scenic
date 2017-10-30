jQuery(function($) {
	$("#createArticle").click(function() {
		var uuid = support.uuid(8,16);
		window.location = "./action/edit/article/" + uuid;
	});
	
});