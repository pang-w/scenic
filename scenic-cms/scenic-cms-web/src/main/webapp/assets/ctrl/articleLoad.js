jQuery(function($) {
	$("#likeArticleBtn").click(function() {
		var data = {
				"articleUuid" : $("#articleUuid").val()
			};
		support.ajax("user/article/likeArticle", data,function(response) {
			if (response.status == "0000") {
				location.reload();
			}
		});
	});
	
	$("#addMessageBtn").click(function() {
		var data = {
				"articleUuid" : $("#articleUuid").val(),
				"message" : $("#articleMessage").val()
			};
		support.ajax("user/article/addMessage", data,function(response) {
			if (response.status == "0000") {
				layer.msg(response.msg);
			}
		});
	});
	$("#collapseAddMessageAction").click(function () {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						checkLogged();
						$('#collapseAddMessage').slideToggle("slow");
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
	//Load User Resource
	
	loadIndexAll();
});

function loadIndexAll(){
	var app = angular.module('iukissApp', []);
	app.controller('iukissCtrl', function($scope,$http) {
		
		$scope.likeMessage = function(messageId){
			var data = {
					"messageId" : messageId
				};
			support.ajax("user/article/likeMessage", data,function(response) {
				if (response.status == "0000") {
					location.reload();
				}
			});
		};
		$http.post("/action/page/index/all",{})
	    .then(function (result) {
	    	if (result.data.status == "0000") {
	    		$scope.topUser = result.data.data.topUser;
	    		$scope.loggedUser = result.data.data.loggedUser;
	            
		        $scope.asideTags = result.data.data.asideTags;
		        $scope.linkedProduct = result.data.data.linkedProducts;
			}
	    
	    });
		$scope.searchMessagePrevious = function(){
			var page = $("#messagePageIndex").html();
			var uuid =  $("#articleUuid").val()
			support.ajax("page/article/message", {"uuid":uuid,"pageIndex":(parseInt(page) - 1)}, function (result) {
			     $scope.articleMsg = result.data;
			     $scope.$apply();
		    });
		};
		$scope.searchMessageNext = function(){
			var page = $("#messagePageIndex").html();
			var uuid = $("#articleUuid").val();
			support.ajax("page/article/message", {"uuid":uuid,"pageIndex":(parseInt(page) + 1)}, function (result) {
			     $scope.articleMsg = result.data;
			     $scope.$apply();
		    });
		};
		var data = {"uuid" : $("#articleUuid").val()};
		support.ajax("page/article/message", data,function(response) {
			if (response.status == "0000") {
				$scope.articleMsg = response.data
				$scope.$apply();
			}},
			function(response) {
				
		});
	});
}





