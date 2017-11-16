			<main class="col-md-8 main-content">
				<article id="108" class="post">
					<aside class="col-md-4 sidebar">
						<i  class="fa fa-user fa-5x"></i>
					</aside>
					<div class="row">
						<div>
							<p>{{loggedUser.signature}}</p>
						</div>
						<div class="pull-right">
							<input id="signatureLikedUsername" type="hidden" value="{{loggedUser.username}}"/>
							<span class="author"><a href="/i/{{loggedUser.username}}">{{loggedUser.nickname||loggedUser.username}}</a></span> •
							<time class="post-date" datetime="2017年10月25日星期三下午4点57分"
								title="2017年10月25日星期三下午4点57分">{{loggedUser.createDate}}</time>
								| {{loggedUser.signatureLikedCount}}赞<span  class='btn' id="likeSignatureBtn" ><i class="fa fa-heart"></i> 点赞</span> 
						</div>
					</div>
				</article>
				<#list pager.list as articleEntity>
				<article id="108" class="post">
					<div class="post-head">
						<h1 class="post-title">
							<a href="../../../article/${articleEntity.uuid}.html">${articleEntity.title}</a>
						</h1>
						<div class="post-meta">
							<span class="author">作者：<a href="../../../i/${articleEntity.username}">${articleEntity.username}</a></span> •
							<time class="post-date" datetime="${articleEntity.createDate}" title="${articleEntity.createDate}">${articleEntity.createDate}</time>
						</div>
					</div>
					<div class="post-content">
						<p>${articleEntity.description}</p>
					</div>

					<footer class="post-footer clearfix">
						<div class="pull-left tag-list">
							<i class="fa fa-folder-open-o"></i>
						</div>
						<div class="pull-right share"></div>
					</footer>
				</article>
				</#list>
				<nav class="pagination" role="navigation">
					<span class="page-number">第 ${pager.currentPage} 页 ⁄ 共 ${pager.totalPage} 页</span> <a
						class="older-posts" href="http://www.iukiss.com/i/articles"><i
						class="fa fa-angle-right"></i></a>
				</nav>
			</main>