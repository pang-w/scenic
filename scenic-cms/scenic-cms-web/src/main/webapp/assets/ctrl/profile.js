
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
	
   
    
    
});
