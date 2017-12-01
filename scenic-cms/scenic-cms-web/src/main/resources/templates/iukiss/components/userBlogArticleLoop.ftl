			<main class="col-md-8 main-content">
				<article id="108" class="post">
					<aside class="col-md-4 sidebar">
						<i  class="fa fa-user fa-5x"></i>
						<input id="blogerName" type="hidden" value="${bloger.username}"/>
					</aside>
					<div class="row">
						<div>
							<p>${bloger.signature}</p>
						</div>
						<div class="pull-right">
							<input id="signatureLikedUsername" type="hidden" value="${bloger.username}"/>
							<span class="author"><a href="../../../i/${bloger.username}">${bloger.nickname}</a></span> •
							<time class="post-date" >${bloger.createDate}</time>
								| ${bloger.signatureLikedCount}赞<span class='btn' id="likeSignatureBtn" ><i class="fa fa-heart"></i> 点赞</span> 
						</div>
					</div>
				</article>
				<article id="108" class="post" ng-repeat="atc in articles.dataList">
					<div class="post-head">
						<h1 class="post-title">
							<a href="../../../article/{{atc.uuid}}.html">{{atc.title}}</a>
						</h1>
						<div class="post-meta">
							<span class="author">作者：<a href="../../../i/{{atc.username}}">{{atc.username}}</a></span>
							•
							<time class="post-date" datetime="{{atc.lastmodify}}"
								title="{{atc.lastmodify}}">{{atc.lastmodify}}</time>
						</div>
						<div class="col-sm-4"></div>
					</div>

					<div class="post-content">
						<p>{{atc.description}}</p>
					</div>

					<footer class="post-footer clearfix">
						<div class="pull-left tag-list">
							<div class="widget">
								<div class="content tag-cloud">
									<i class="fa fa-tag"></i>标签 
									<a href="../tags/{{ut.value}}"	ng-repeat="ut in atc.tags">{{ut.value}}</a>
								</div>
							</div>
						</div>
						<div class="pull-right share"></div>
					</footer>
				</article>
				
				<nav class="pagination" role="navigation">

					<span ng-if="articles.pageIndex!=1" class="btn btn-default" class="older-posts" id="pagePrevious" ng-click="searchArticlePrevious()">
					<i class="fa fa-angle-left"></i></span>
					
					<span class="page-number">第 <span id="articlePageIndex">{{articles.pageIndex}}</span> 页 ⁄ 共 
					<span id="totalPage">{{articles.totalPage}}</span> 页</span>
					
					<span  ng-if="articles.pageIndex!=articles.totalPage" class="btn btn-default" class="older-posts" id="pageNext" ng-click="searchArticleNext()">
					<i class="fa fa-angle-right"></i></span>
				</nav>
			</main>