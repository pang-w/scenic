					<!-- start resource widget -->
					<div id="loggedInfo" class="widget">
						<h4 class="title">我的资源</h4>
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class='btn-block panel-heading panel-title' id="collapseArticleAction">我的文章</div>
								<div id="collapseArticle" class="panel-collapse collapse">
									<ul class="list-group panel-body" >
										<li class="list-group-item" ng-repeat="a in articleMenu">
											<a href="/article/{{a.uuid}}.html" target="_blank">{{ a.title }}</a>
											<a href="/action/edit/article/{{a.uuid}}">
        											<i class="pull-right fa fa-edit"></i>
        									</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-success">
								<div class='btn-block panel-heading panel-title' id="collapseAttentionAction">我的收藏</div>
								<div id="collapseAttention" class="panel-collapse collapse">
									<ul class="list-group panel-body" >
										<li class="list-group-item" ng-repeat="a in attentionMenu">
											<a href="/article/{{a.uuid}}.html" target="_blank">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-info">
								<div class='btn-block panel-heading panel-title' id="collapseProductAction">我的商品
								<a onclick="support.layerAddProduct()">
									<i class="pull-right fa fa-edit"></i>
								</a>
								</div>
								<div id="collapseProduct" class="panel-collapse collapse">
									<ul class="list-group panel-body" >
										<li class="list-group-item" ng-repeat="a in productMenu">
											<a href="/i/product/{{a.name}}">{{ a.name }}</a>
											<a class='btn pull-right' ng-click="ngEditProduct(a.id)" >
												<i class="pull-right fa fa-edit"></i>
											</a>
										</li>
									</ul>
								</div>
							</div>
							
							<div class="panel panel-warning">
								<div class='btn-block panel-heading panel-title' id="collapseImageAction">我的图片</div>
								<div id="collapseImage" class="panel-collapse collapse">
									<ul class="list-group panel-body" >
										<li class="list-group-item" ng-repeat="a in imageMenu">
											<a ng-click='ngShowImg(a.url)'>{{ a.imageName }}</a>
											
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- end resource widget -->