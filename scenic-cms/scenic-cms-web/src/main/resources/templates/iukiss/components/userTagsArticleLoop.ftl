			<main class="col-md-8 main-content">
				<article id="108" class="post">
					<aside class="col-md-4 sidebar">
						<i  class="fa fa-user fa-5x"></i>
					</aside>
					<div class="row">
						<div>
							<p>{{topUser.signature}}</p>
						</div>
						<div class="pull-right">
							<input id="signatureLikedUsername" type="hidden" value="{{topUser.username}}"/>
							<span class="author"><a href="/i/{{topUser.username}}">{{topUser.nickname||topUser.username}}</a></span> •
							<time class="post-date">{{topUser.createDate}}</time>
								| {{topUser.signatureLikedCount}}赞<span  class='btn' id="likeSignatureBtn" ><i class="fa fa-heart"></i> 点赞</span> 
						</div>
					</div>
				</article>
				<article id="108" class="post" ng-repeat="atc in articles" >
					<div class="post-head">
						<h1 class="post-title">
							<a href="../../../article/{{atc.uuid}}.html">{{atc.title}}</a>
						</h1>
						<div class="post-meta">
							<span class="author">作者：<a href="../../../i/{{atc.username}}">{{atc.username}}</a></span> •
							<time class="post-date" datetime="{{atc.lastmodify}}" title="{{atc.lastmodify}}">{{atc.lastmodify}}</time>
						</div>
					</div>
					<div class="post-content">
						<p>{{atc.description}}</p>
					</div>

					<footer class="post-footer clearfix">
						<div class="pull-left tag-list">
							<i class="fa fa-folder-open-o"></i>

						</div>
						<div class="pull-right share"></div>
					</footer>
				</article>
				
				<nav class="pagination" role="navigation">
					<span class="page-number">第 ${pager.currentPage} 页 ⁄ 共 ${pager.totalPage} 页</span> <a
						class="older-posts" href="http://www.iukiss.com/i/articles"><i
						class="fa fa-angle-right"></i></a>
				</nav>
			</main>