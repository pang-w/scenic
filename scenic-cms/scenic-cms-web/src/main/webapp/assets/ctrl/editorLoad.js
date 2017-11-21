
loadEditAll();

function loadEditAll(){
	var app = angular.module('iukissApp', []);
	app.controller('iukissCtrl', function($scope,$http) {
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
		$scope.selectTag = function(value){
			if($scope.selectedTags==null){
				$scope.selectedTags = [];
				$scope.selectedTags.push({"value":value});
			}else{
				var added = false;
				for (var int = 0; int < $scope.selectedTags.length; int++) {
					if($scope.selectedTags[int].value==value){
						var added = true;
					}
				}
				if(!added){
					$scope.selectedTags.push({"value":value});
				}
			}
		};
		$scope.refreshUserTag = function(){
			$scope.userTags = [];
		};
		$("#saveArticle").click(function() {
			support.ajax("user/auth/checkLogged", null,
					function(response) {
						if(response.status!="0000"){
							layer.msg(response.msg);
						}else{
							var data = {
									"title" : $("#articleTitle").val(),
									"content" : $("#summernote").summernote('code'),
									"uuid" : $("#articleUuid").val(),
									"selectedTags": $scope.selectedTags,
									"selectedProducts": $scope.selectedProducts
								};
								support.ajax("edit/article/save", data, function(response) {
									layer.msg(response.msg);
								});
						}
					},
					function(response) {
						layer.msg(response.msg);
					}
			);
			
		});

		$('#addSelfTagBtn').bind('keypress',function(event){ 
	         if(event.keyCode == 13) {  
	        	 var value = $('#addSelfTagBtn').val()
		         $scope.selectTag(value);
	        	 $scope.$apply();
	         }  
	     });
		$scope.deselectTag = function(value){
			var tmpTags = [];
			var added = false;
			for (var int = 0; int < $scope.selectedTags.length; int++) {
				if($scope.selectedTags[int].value!=value){
					tmpTags.push($scope.selectedTags[int]);
				}
			}
			$scope.selectedTags = tmpTags;
		};
		$scope.selectProduct = function(pro){
			if($scope.selectedProducts==null){
				$scope.selectedProducts = [];
				$scope.selectedProducts.push(pro);
			}else{
				var added = false;
				for (var int = 0; int < $scope.selectedProducts.length; int++) {
					if($scope.selectedProducts[int].id==pro.id){
						var added = true;
					}
				}
				if(!added){
					$scope.selectedProducts.push(pro);
				}
			}
		};
		$scope.deselectProduct = function(pro){
			var tmpProducts = [];
			var added = false;
			for (var int = 0; int < $scope.selectedProducts.length; int++) {
				if($scope.selectedProducts[int].id!=pro.id){
					tmpProducts.push($scope.selectedProducts[int]);
				}
			}
			$scope.selectedProducts = tmpProducts;
		};

		$scope.insertProduct =function (pro){
			$scope.selectProduct(pro);
			var range = $('#summernote').summernote('createRange');
			var imageElement = document.createElement("img");
			imageElement.setAttribute("src",pro.imgUrl);
			imageElement.setAttribute("alt",pro.description);
			
			var labelElement = document.createElement("div");
			
			var a = document.createElement("a");
			a.setAttribute("href",pro.linkTo);
			a.setAttribute("target","_blank");
			a.setAttribute("class","title");
			a.innerText = pro.name;
			labelElement.appendChild(a);
			var priceElement = document.createElement("div");
			priceElement.setAttribute("class","title");
			priceElement.innerText = "价格："+pro.price;
			
			
			var asideElement = document.createElement("aside");
			asideElement.setAttribute("style","text-align: center");
			asideElement.appendChild(imageElement);
			asideElement.appendChild(labelElement);
			asideElement.appendChild(priceElement);
			
			var node = document.createElement('article');
			node.appendChild(asideElement);
			node.setAttribute("class","post");
			
			$('#summernote').summernote('insertNode',node);
		};
		$scope.insertImage =function (img){
			var range = $('#summernote').summernote('createRange');
			$('#summernote').summernote('insertImage',
					img.url, function($image) {
						$image.css('maxWidth', '80%');
						$image.css('text-align', 'center');
					});
		};
		

		$http.post("/action/page/edit/"+$("#articleUuid").val(),null)
	    .then(function (result) {
	    	if (result.data.status == "0000") {
	    		$scope.topUser = result.data.data.topUser;
	            $scope.articles = result.data.data.articles;
	            
		        $scope.articleMenu = result.data.data.articleMenu;
		        $scope.imageMenu = result.data.data.imageMenu;
		        $scope.productMenu = result.data.data.productMenu;
		        $scope.attentionMenu = result.data.data.attentionMenu;
		        
		        $scope.asideTags = result.data.data.asideTags;
		        $scope.userTags = result.data.data.userTags;
		        $scope.linkedProduct = result.data.data.linkedProducts;
		        if(result.data.data.editArticle!=null){
		        	$scope.selectedTags = result.data.data.editArticle.tags;
		        	$scope.selectedProducts = result.data.data.editArticle.products;
		        }
			}
	    
	    });
	});
}
function checkLogged() {
	support.ajax("user/auth/checkLogged", null,
			function(response) {
			loadEditAll();
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



