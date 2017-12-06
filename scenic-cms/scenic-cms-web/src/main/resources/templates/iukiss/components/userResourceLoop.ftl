					<!-- start resource widget -->
					<div id="loggedInfo" class="widget">
						<h4 class="title">我的资源</h4>
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class='btn-block panel-heading panel-title'
									id="collapseArticleAction">我的文章</div>
								<div id="collapseArticle" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in articleMenu.dataList">
											<a href="/a/{{a.uuid}}" target="_blank">{{a.title }}</a>
											<a href="/action/edit/article/{{a.uuid}}"> <i class="pull-right fa fa-edit"></i></a>
										</li>
										<li class="list-group-item">

												<span ng-if="articleMenu.pageIndex!=1" class="btn" class="older-posts" id="pagePrevious" ng-click="searchMenuArticlePrevious()">
												<i class="fa fa-angle-left"></i></span>
												
												<span class="page-number">第 <span id="menuArticlePageIndex">{{articleMenu.pageIndex}}</span> 页 ⁄ 共 
												<span id="totalPage">{{articleMenu.totalPage}}</span> 页</span>
												
												<span  ng-if="articleMenu.pageIndex!=articleMenu.totalPage" class="btn" class="older-posts" id="pageNext" ng-click="searchMenuArticleNext()">
												<i class="fa fa-angle-right"></i></span>
											
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-success">
								<div class='btn-block panel-heading panel-title'
									id="collapseAttentionAction">我的收藏</div>
								<div id="collapseAttention" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in attentionMenu">
											<a href="/a/{{a.uuid}}" target="_blank">{{a.title }}</a>
										</li>
										
									</ul>
								</div>
							</div>
							<div class="panel panel-info">
								<div class='btn-block panel-heading panel-title' id="collapseProductAction">
									我的商品 <a onclick="support.layerAddProduct()"> <i class="pull-right fa fa-edit"></i></a>
								</div>
								<div id="collapseProduct" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in productMenu.dataList">
											<a ng-click="layerViewProduct(a)">{{ a.name }}</a> <a
											class='btn pull-right' ng-click="ngEditProduct(a.id)"> <i
												class="pull-right fa fa-edit"></i>
											</a>
										</li>
										<li class="list-group-item">
												<span ng-if="productMenu.pageIndex!=1" class="btn" class="older-posts" id="pagePrevious" ng-click="searchMenuProductPrevious()">
												<i class="fa fa-angle-left"></i></span>
												
												<span class="page-number">第 <span id="menuProductPageIndex">{{productMenu.pageIndex}}</span> 页 ⁄ 共 
												<span id="totalPage">{{productMenu.totalPage}}</span> 页</span>
												
												<span  ng-if="productMenu.pageIndex!=productMenu.totalPage" class="btn" class="older-posts" id="pageNext" ng-click="searchMenuProductNext()">
												<i class="fa fa-angle-right"></i></span>
										</li>
									</ul>
								</div>
							</div>

							<div class="panel panel-warning">
								<div class='btn-block panel-heading panel-title'
									id="collapseImageAction">我的图片</div>
								<div id="collapseImage" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in imageMenu.dataList"><a
											ng-click='ngShowImg(a.url)'>{{ a.imageName }}</a></li>
										<li class="list-group-item">
												<span ng-if="imageMenu.pageIndex!=1" class="btn" class="older-posts" id="pagePrevious" ng-click="searchMenuImagePrevious()">
												<i class="fa fa-angle-left"></i></span>
												
												<span class="page-number">第 <span id="menuImagePageIndex">{{imageMenu.pageIndex}}</span> 页 ⁄ 共 
												<span id="totalPage">{{imageMenu.totalPage}}</span> 页</span>
												
												<span  ng-if="imageMenu.pageIndex!=imageMenu.totalPage" class="btn" class="older-posts" id="pageNext" ng-click="searchMenuImageNext()">
												<i class="fa fa-angle-right"></i></span>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- end resource widget -->