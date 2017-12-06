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
	            
		        $scope.asideTags = result.data.data.asideTags;
		        $scope.userTags = result.data.data.userTags;
		        $scope.linkedProduct = result.data.data.linkedProducts;
			}
	    
	    });
		$scope.searchProductPrevious = function(){
			var page = $("#productPageIndex").html();
			support.ajax("page/product/product", {"pageIndex":(parseInt(page) - 1)}, function (result) {
			     $scope.products = result.data;
			     $scope.$apply();
		    });
		};
		$scope.searchProductNext = function(){
			var page = $("#productPageIndex").html();
			support.ajax("page/product/product", {"pageIndex":(parseInt(page) + 1)}, function (result) {
			     $scope.products = result.data;
			     $scope.$apply();
		    });
		};
		support.ajax("page/product/product", {}, function (result) {
		     $scope.products = result.data;
	    });
		
		
		//menu load start
		$scope.searchMenuArticlePrevious = function(){
			var page = $("#menuArticlePageIndex").html();
			support.ajax("menu/article", {"pageIndex":(parseInt(page) - 1)}, function (result) {
			     $scope.articleMenu = result.data;
			     $scope.$apply();
		    });
		};
		$scope.searchMenuArticleNext = function(){
			var page = $("#menuArticlePageIndex").html();
			support.ajax("menu/article", {"pageIndex":(parseInt(page) + 1)}, function (result) {
			     $scope.articleMenu = result.data;
			     $scope.$apply();
		    });
		};
		$scope.searchMenuImagePrevious = function(){
			var page = $("#menuImagePageIndex").html();
			support.ajax("menu/image", {"pageIndex":(parseInt(page) - 1)}, function (result) {
			     $scope.imageMenu = result.data;
			     $scope.$apply();
		    });
		};
		$scope.searchMenuImageNext = function(){
			var page = $("#menuImagePageIndex").html();
			support.ajax("menu/image", {"pageIndex":(parseInt(page) + 1)}, function (result) {
			     $scope.imageMenu = result.data;
			     $scope.$apply();
		    });
		};
		$scope.searchMenuProductPrevious = function(){
			var page = $("#menuProductPageIndex").html();
			support.ajax("menu/product", {"pageIndex":(parseInt(page) - 1)}, function (result) {
			     $scope.productMenu = result.data;
			     $scope.$apply();
		    });
		};
		$scope.searchMenuProductNext = function(){
			var page = $("#menuProductPageIndex").html();
			support.ajax("menu/product", {"pageIndex":(parseInt(page) + 1)}, function (result) {
			     $scope.productMenu = result.data;
			     $scope.$apply();
		    });
		};
		support.ajax("menu/article", {}, function (result) {
		     $scope.articleMenu = result.data;
		     $scope.$apply();
	    });
		support.ajax("menu/image", {}, function (result) {
		     $scope.imageMenu = result.data;
		     $scope.$apply();
	    });
		support.ajax("menu/product", {}, function (result) {
		     $scope.productMenu = result.data;
		     $scope.$apply();
	    });
		$scope.layerViewProduct = function (data) {
			layer.open({
				  type:  2//Page层类型
				  ,area: ['500px', '450px']
				  ,title: '查看商品'
				  ,shade: 0.6 //遮罩透明度
				  ,maxmin: false //允许全屏最小化
				  ,anim: 1 //0-6的动画形式，-1不开启
				  ,content: '/layerViewProduct.html' + praseParam(data)
				});
		};
		$scope.ngEditProduct = function(pid){
			support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status=="0000"){
						support.layerEditProduct(pid);
					}else{
						layer.msg(response.msg);
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
			);
		};

		$scope.ngShowImg = function(url){
			//alert("Item Id 是 ");
		    support.layerImage(url);
		};
		//menu load end
	});
}





