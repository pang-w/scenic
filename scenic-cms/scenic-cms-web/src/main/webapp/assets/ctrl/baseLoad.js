jQuery(function($) {
	//Load User Resource
	$("#collapseArticleAction").click(function () {
		checkMenu();
		$('#collapseArticle').slideToggle("slow");
	});
	$("#collapseAttentionAction").click(function () {
		$('#collapseAttention').slideToggle("slow");
	});
	$("#collapseProductAction").click(function () {
		$('#collapseProduct').slideToggle("slow");
	});
	$("#collapseImageAction").click(function () {
		$('#collapseImage').slideToggle("slow");
	});
	checkLogged();
	checkMenu();
});
function checkMenu(){
	var app = angular.module('iukissApp', []);
	app.controller('iukissCtrl', function($scope,$http) {
		$http.post("/action/user/article/menuList",null)
	    .then(function (result) {
	    	$scope.imagePrefixUrl = "http://img.iukiss.com/";
	    	if (result.data.status == "0000") {
		        $scope.articleMenu = result.data.data;
			} 
	    	
	    });
	});
}
function layerLogged(){
	location.href = '/index.html';
}
function checkLogged() {
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
				checkMenu();
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







