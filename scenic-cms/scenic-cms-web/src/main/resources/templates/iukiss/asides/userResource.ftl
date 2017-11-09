				<aside class="col-md-4 sidebar" >
					<!-- start tag cloud widget -->
					<div id="loggedInfo" class="widget">
						<h4 class="title">我的资源</h4>
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class='btn-block panel-heading panel-title'
									id="collapseArticleAction">我的文章</div>
								<div id="collapseArticle" class="panel-collapse collapse in">
									<ul class="list-group panel-body">
										<#list articlePager.list as articleEntity>
											<li class="list-group-item"><a onclick="support.layerImage('http://iukiss.oss-cn-shanghai.aliyuncs.com/bg/head_bg.jpg')" >${articleEntity.title}</a>
												<span class="btn">商品</span>
											</li>
										</#list>
										<li class="list-group-item" ng-repeat="a in resouceMenu.article">
											<a href="${baseDomain}/">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-success">
								<div class='btn-block panel-heading panel-title'
									id="collapseAttentionAction">我的收藏</div>
								<div id="collapseAttention" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in articleMenu">
											<a href="${baseDomain}/">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-info">
								<div class='btn-block panel-heading panel-title'
									id="collapseProductAction">我的商品</div>
								<div id="collapseProduct" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in articleMenu">
											<a href="${baseDomain}/">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel panel-warning">
								<div class='btn-block panel-heading panel-title'
									id="collapseImageAction">我的图片</div>
								<div id="collapseImage" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<li class="list-group-item" ng-repeat="a in articleMenu">
											<a href="${baseDomain}/">{{ a.title }}</a>
										</li>
									</ul>
								</div>
							</div>

						</div>
					</div>							

				
					<div id="loggedInfo" class="widget">
						<h4 class="title">我的资源</h4>
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class='btn-block panel-heading panel-title' id="collapseOneAction">我的文章</div>
								<div id="collapseOne" class="panel-collapse collapse in">
									<ul class="list-group panel-body">
										<#list imagePager.list as imageEntity>
											<li class="list-group-item"><a onclick="support.layerImage('http://iukiss.oss-cn-shanghai.aliyuncs.com/bg/head_bg.jpg')" >${imageEntity.description}</a>
												<span class="btn">商品</span>
											</li>
										</#list>
									</ul>
								</div>
							</div>
							<div class="panel panel-success">
								<div class='btn-block panel-heading panel-title' id="collapseTwoAction">我的商品</div>
								<div id="collapseTwo" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<#list imagePager.list as imageEntity>
											<li class="list-group-item"><a onclick="support.layerImage('http://iukiss.oss-cn-shanghai.aliyuncs.com/bg/head_bg.jpg')" >${imageEntity.description}</a>
												<span class="btn">商品</span>
											</li>
										</#list>
									</ul>
								</div>
							</div>
							
							<div class="panel panel-default">
								<div class='btn-block panel-heading panel-title' id="collapseThreeAction">我的图片</div>
								<div id="collapseThree" class="panel-collapse collapse in">
									<ul class="list-group panel-body">
										<#list imagePager.list as imageEntity>
											<li class="list-group-item"><a onclick="support.layerImage('http://iukiss.oss-cn-shanghai.aliyuncs.com/bg/head_bg.jpg')" >${imageEntity.description}</a>
												<span class="btn">商品</span>
											</li>
										</#list>
									</ul>
								</div>
							</div>
							
							<div class="panel panel-warning">
								<div class='btn-block panel-heading panel-title' id="collapseFourAction">我的收藏</div>
								<div id="collapseFour" class="panel-collapse collapse">
									<ul class="list-group panel-body">
										<#list imagePager.list as imageEntity>
											<li class="list-group-item"><a onclick="support.layerImage('http://iukiss.oss-cn-shanghai.aliyuncs.com/bg/head_bg.jpg')" >${imageEntity.description}</a>
												<span class="btn">商品</span>
											</li>
										</#list>
									</ul>
								</div>
							</div>
						</div>
					</div>