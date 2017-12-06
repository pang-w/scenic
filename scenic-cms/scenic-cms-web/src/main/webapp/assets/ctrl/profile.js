
jQuery(document).ready(function() {
	$('#updateProfileBtn').click(function(){
    	var data = {
    			"nickname" : $("#user-nick-name").val(),
    			"signature" : $("#user-signature").val()
    		};
    		support.ajax("user/auth/update", data, function(response) {
    			layer.msg(response.msg);
    		}, function(response) {
    			layer.msg(response.msg);
    		});
    });
	
   
	$("#user-pay-ali").change(function() {
		
		support.fileUploadAjax("image/savePayImg", "ALI",this.files[0],function(response) {
			if (response.status == "0000") {
				location.reload();
			}
		});

		
	});
	$("#user-pay-weixin").change(function() {
		
		support.fileUploadAjax("image/savePayImg", "WX",this.files[0],function(response) {
			if (response.status == "0000") {
				location.reload();
			}
		});

		
	});
    
});
