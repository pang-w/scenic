package com.itmaoo.scenic.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.dao.IArticleMessageDao;
import com.itmaoo.scenic.dao.IProductDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ArticleMessageDto;
import com.itmaoo.scenic.entity.dto.PagingData;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.po.ArticleMessagePo;
import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.query.ArticleMessageQuery;
import com.itmaoo.scenic.entity.query.ProductQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;

@Controller
@RequestMapping(value = "/action/page/product/")
public class ProductPageAction extends BaseAction {
	@Autowired
	private IArticleMessageDao articleMessageDao;
	@Autowired
	private IProductDao productDao;
	
	@ResponseBody
	@RequestMapping("product")
	public ResponseData publicProduct(HttpServletRequest request, @RequestBody ProductDto proRequest) {
		ResponseData rd = new ResponseData();

		PagingData<ProductDto> publicArticlesPagingData = new PagingData<>();

		
		
		ProductQuery proQuery = new ProductQuery();
		if (proRequest == null || proRequest.getPageIndex() == null) {
			proQuery.setPageIndex(1);
		} else {
			proQuery.setPageIndex(proRequest.getPageIndex());
		}
		// proQuery.setUsername(loggedUser.getUsername());
		List<ProductPo> productsPo = productDao.selectList(proQuery);
		List<ProductDto> publicProductsDto = Lists.newArrayList();
		if (productsPo != null) {
			for (ProductPo pPo : productsPo) {
				ProductDto proPoToDto = EntityUtil.productPoToDto(pPo);
				publicProductsDto.add(proPoToDto);
			}
		}
		publicArticlesPagingData.setDataList(publicProductsDto);
		publicArticlesPagingData.setPageIndex(proQuery.getPageIndex());// 设置当前页
		publicArticlesPagingData.setPageSize(proQuery.getPageSize());// 设置一页多少条数据
		int count = productDao.selectListCount(proQuery);
		publicArticlesPagingData.setTotalCount(count);// 设置总数量
		publicArticlesPagingData.setTotalPage(count, proQuery.getPageSize());// 设置总共多少页

		rd.setData(publicArticlesPagingData);
		return rd;

	}
	@ResponseBody
	@RequestMapping("message")
	public ResponseData messages(HttpServletRequest request, @RequestBody ArticleDto articleDto) {
		ResponseData rd = new ResponseData();

		ArticleMessageQuery query = new ArticleMessageQuery();
		query.setArticleUuid(articleDto.getUuid());
		List<ArticleMessagePo> articleMessagesPo = articleMessageDao.selectByUuid(query);

		List<ArticleMessageDto> massagesDto = Lists.newArrayList();
		if (articleMessagesPo != null) {
			for (ArticleMessagePo a : articleMessagesPo) {
				massagesDto.add(EntityUtil.articleMessagePoToDto(a));
			}
		}
		rd.setData(massagesDto);
		return rd;

	}
}
