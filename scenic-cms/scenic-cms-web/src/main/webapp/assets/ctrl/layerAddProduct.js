jQuery(function($) {


	$("#doLayerAddProduction").click(function() {
		var data = {
			"name" : $("#proName").val(),
			"imgUrl" : $("#proImgUrl").val(),
			"linkTo" : $("#proLinkTo").val(),
			"price" : $("#proPrice").val(),
			"description" : $("#proDesc").val()
		};
		support.ajax("user/product/add", data, function(response) {
			window.parent.location.reload();
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}, function(response) {
			layer.msg(response.msg);
		});
	});


});