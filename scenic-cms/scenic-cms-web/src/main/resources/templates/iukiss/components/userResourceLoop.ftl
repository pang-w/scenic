					<!-- start tag cloud widget -->
					<div id="loggedInfo" class="widget">
						<h4 class="title">我的资源</h4>
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class='btn-block panel-heading panel-title' id="collapseArticleAction">我的文章</div>
								<div id="collapseArticle" class="panel-collapse collapse in">
									<ul class="list-group panel-body" >
										<li class="list-group-item" ng-repeat="a in articleMenu">
											<a href="/article/{{a.uuid}}.html">{{ a.title }}</a>
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
											<a href="/">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-info">
								<div class='btn-block panel-heading panel-title' id="collapseProductAction">我的商品</div>
								<div id="collapseProduct" class="panel-collapse collapse">
									<ul class="list-group panel-body" >
										<li class="list-group-item" ng-repeat="a in productMenu">
											<a href="/article/{{a.uuid}}.html">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-warning">
								<div class='btn-block panel-heading panel-title' id="collapseImageAction">我的图片</div>
								<div id="collapseImage" class="panel-collapse collapse">
									<ul class="list-group panel-body" >
										<li class="list-group-item" ng-repeat="a in imageMenu">
											<a href="/">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>