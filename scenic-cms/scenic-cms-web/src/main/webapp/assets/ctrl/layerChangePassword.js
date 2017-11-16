
jQuery(document).ready(function() {
	$('#doLayerChangePassword').click(function(){
    	var data = {
    			"password" : $("#iukpassword").val(),
    			"confirmMassword" : $("#confirmMassword").val()
    			
    		};
    		support.ajax("user/auth/changepassword", data, function(response) {
    			layer.msg(response.msg);
    		}, function(response) {
    			layer.msg(response.msg);
    		});
    });
	doLayerChangePassword
   
    
    
});
