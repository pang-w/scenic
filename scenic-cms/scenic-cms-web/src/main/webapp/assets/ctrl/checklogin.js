jQuery(function($) {
	
	checkLogged();
	function checkLogged() {
		var data = {
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("user/auth/checkLogged", null,
				function(response) {
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
	
});
