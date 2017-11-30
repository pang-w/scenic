jQuery(function($) {
	//Load User Resource
	
	loadIndexAll();
});

function loadIndexAll(){
	var app = angular.module('iukissApp', []);
	app.controller('iukissCtrl', function($scope,$http) {
		
		$http.post("/action/page/index/all",{})
	    .then(function (result) {
	    	if (result.data.status == "0000") {
	    		$scope.topUser = result.data.data.topUser;
	    		$scope.loggedUser = result.data.data.loggedUser;
	            $scope.products = result.data.data.products;
	            
		        $scope.asideTags = result.data.data.asideTags;
		        $scope.userTags = result.data.data.userTags;
		        $scope.linkedProduct = result.data.data.linkedProducts;
			}
	    
	    });
	});
}





