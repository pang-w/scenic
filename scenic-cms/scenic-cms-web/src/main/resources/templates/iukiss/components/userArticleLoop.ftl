			<main class="col-md-8 main-content">
				<article id="108" class="post">
					<div class="post-head">
						<h1 class="post-title">
							ABC 的用户空间
						</h1>
						<div class="post-meta">
							<span class="author">2017-11-26 1:10:23</span> 
						</div>
					</div>
				</article>
				<#list pager.list as articleEntity>
				<article id="108" class="post">
					<div class="post-head">
						<h1 class="post-title">
							<a href="${baseDomain}/article/${articleEntity.uuid}.html">${articleEntity.title}</a>
						</h1>
						<div class="post-meta">
							<span class="author">作者：<a href="${baseDomain}/i/${articleEntity.username}">${articleEntity.username}</a></span> •
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