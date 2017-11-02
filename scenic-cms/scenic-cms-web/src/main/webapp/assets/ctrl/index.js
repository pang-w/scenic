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
	// SLiding codes
	$("#userMenutoggle > li > div").click(function () {
	    if (false == $(this).next().is(':visible')) {
	        $('#userMenutoggle ul').slideUp();
	    }

	    var $currIcon=$(this).find("span.the-btn");

	    $("span.the-btn").not($currIcon).addClass('fa-plus').removeClass('fa-minus');

	    $currIcon.toggleClass('fa-minus fa-plus');

	    $(this).next().slideToggle();

	    $("#userMenutoggle > li > div").removeClass("active");
	    $(this).addClass('active');

	});
	$("#collapseOneAction").click(function () {
		$('#collapseOne').slideToggle("slow");
	});
	$("#collapseTwoAction").click(function () {
		$('#collapseTwo').slideToggle("slow");
	});
	$("#collapseThreeAction").click(function () {
		$('#collapseThree').slideToggle("slow");
	});
	$("#collapseFourAction").click(function () {
		$('#collapseFour').slideToggle("slow");
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