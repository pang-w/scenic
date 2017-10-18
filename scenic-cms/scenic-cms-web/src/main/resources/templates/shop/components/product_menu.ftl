<div class="productCategory">
            	  <div class="top">商品分类</div>
            	  <div class="middle clearfix">
            		<ul class="menu">
            			<#list rootProductCategoryList as list>
            				<li class="mainCategory">
								<a href="${base}/shop/product!list.action?id=${list.id}">${list.name}</a>
							</li>
							<#if (list.children != null && list.children?size > 0)>
								<#list list.children as list>
									<li>
										<a href="${base}/shop/product!list.action?id=${list.id}"><span class="icon"> </span>${list.name}</a>
										<#if (list.children != null && list.children?size > 0)>
										<ul>
										<#list list.children as list>
										<li>
										<a href="${base}/shop/product!list.action?id=${list.id}"><span class="icon"> </span>${list.name}</a>
										</li>
										</#list>
										</ul>
										</#if>
									</li>
									<#if list_index + 1 == 5>
										<#break />
									</#if>
								</#list>
							</#if>
							<#if list_index + 1 == 3>
								<#break />
							</#if>
            			</#list>
			</ul>
            	</div>
		<div class="bottom"></div>
