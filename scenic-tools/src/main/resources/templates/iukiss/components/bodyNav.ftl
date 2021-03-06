<!-- start navigation -->
	<nav class="main-navigation">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="navbar-header">
                        <span class="nav-toggle-button collapsed" data-toggle="collapse" data-target="#main-menu">
                        <span class="sr-only">Toggle navigation</span>
                        <i class="fa fa-bars"></i>
                        </span>
					</div>
					<div class="collapse navbar-collapse" id="main-menu">
						<ul class="nav navbar-nav">
							<li><a href="/">IUKISS</a></li>
							<li><a href="/articles.html">文章</a></li>
							<li><a href="/products.html">商品</a></li>
						</ul>

						<ul class="nav navbar-nav">
							<li class="active"><a href="/tags/游记">游记 <span
									class="sr-only">(current)</span></a></li>
							<li><a href="/tags/装修">装修</a></li>
							<li><a href="/tags/办事流程">办事流程</a></li>
							<li><a href="/tags/IT技能">IT技能</a></li>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							<li><a href="/register.html">注册</a></li>
							<li id="loginLayerOutBtn"><a onclick="support.layerLogin()">登录</a></li>
							<li id="loggedDropdown" class="dropdown">
								<a	id="loggedUesername" class="dropdown-toggle" data-toggle="dropdown"> <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="/profile.html">个人资料</a></li>
									<li data-filter-camera-type="Zed"><a
											onclick="support.layerChangePassword()">修改密码</a></li>
									<li data-filter-camera-type="Bravo"><a
											onclick="support.logout()">退出</a></li>
								</ul></li>
						</ul>
						<!-- <form class="navbar-form navbar-right">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-default">搜</button>
						</form> -->
					</div>
				</div>
			</div>
		</div>
	</nav>
	<!-- end navigation -->