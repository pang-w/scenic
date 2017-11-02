			<main class="col-md-8 main-content">
				<article id="108" class="post">
					<aside class="col-md-4 sidebar">
						<img alt="user" width="90%"
							src="http://iukiss.oss-cn-shanghai.aliyuncs.com/bg/head_bg.jpg">
					</aside>
					<div class="row">
						<div>
							<p>人生没有彩排每天都是现场直播，人生没有彩排每天都是现场直播，人生没有彩排每天都是现场直播。</p>
						</div>
						<div class="pull-right">
							<span class="author">作者：<a
								href="http://www.iukiss.com/author/wangsai/">ITMAOO</a></span> •
							<time class="post-date" datetime="2017年10月25日星期三下午4点57分"
								title="2017年10月25日星期三下午4点57分">2017-8-9</time>
							<a onclick=""><i class="fa fa-heart"></i> 点赞</a> 108赞
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