							

				
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