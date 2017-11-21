jQuery(function($) {
	//Load User Resource
	checkLogged();
	loadIndexAll();
});



function loadTopUser(){
	var app = angular.module('iukissApp', []);
	app.controller('iukissCtrl', function($scope,$http) {
		$http.post("/action/page/index/topUser",null)
	    .then(function (result) {
	    	scope.topUser = result.data.data.topUser;
	    	if (result.data.status == "0000") {
		        $scope.topUser = result.data.data;
			}
	    	
	    });
	});
}

function loadIndexAll(){
	var app = angular.module('iukissApp', []);
	app.controller('iukissCtrl', function($scope,$http) {
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
		$http.post("/action/page/index/all",null)
	    .then(function (result) {
	    	if (result.data.status == "0000") {
	    		$scope.topUser = result.data.data.topUser;
	    		$scope.loggedUser = result.data.data.loggedUser;
	            $scope.articles = result.data.data.articles;
	            $scope.products = result.data.data.products;
	            
		        $scope.articleMenu = result.data.data.articleMenu;
		        $scope.imageMenu = result.data.data.imageMenu;
		        $scope.productMenu = result.data.data.productMenu;
		        $scope.attentionMenu = result.data.data.attentionMenu;
		        
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
				loadIndexAll();
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



