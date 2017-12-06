jQuery(function($) {
	var app = angular.module('iukissApp', []);
	app.controller('iukissCtrl', function($scope,$location) {
		$scope.editPro = urlParam();
		
		
		
		
		$("#doLayerEditProduction").click(function() {

			support.ajax("page/product/update", $scope.editPro, function(response) {
				window.parent.location.reload();
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}, function(response) {
				layer.msg(response.msg);
			});
		});
	});

	


});