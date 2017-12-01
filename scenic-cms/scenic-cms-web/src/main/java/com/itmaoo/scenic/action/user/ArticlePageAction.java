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
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IArticleMessageDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ArticleMessageDto;
import com.itmaoo.scenic.entity.dto.PagingData;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.po.ArticleMessagePo;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.query.ArticleMessageQuery;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;

@Controller
@RequestMapping(value = "/action/page/article/")
public class ArticlePageAction extends BaseAction {
	@Autowired
	private IArticleMessageDao articleMessageDao;
	@Autowired
	private IArticleDao articleDao;
	
	@ResponseBody
	@RequestMapping("article")
	public ResponseData publicArticle(HttpServletRequest request, @RequestBody ArticleDto articleRequest) {
		ResponseData rd = new ResponseData();

		// 登录用户 可浏览的信息
		PagingData<ArticleDto> userArticlesPagingData = new PagingData<>();

		ArticleQuery suerArticleQuery = new ArticleQuery();
		suerArticleQuery.setIsPublished(true);
		if (articleRequest == null || articleRequest.getPageIndex() == null) {
			suerArticleQuery.setPageIndex(1);
		} else {
			suerArticleQuery.setPageIndex(articleRequest.getPageIndex());
		}
		suerArticleQuery.setPageSize(5);
		List<ArticlePo> userArticlesPo = articleDao.selectList(suerArticleQuery);
		List<ArticleDto> userArticlesDto = Lists.newArrayList();
		if (userArticlesPo != null) {
			for (ArticlePo articlePo : userArticlesPo) {
				ArticleDto articlePoToDto = makeupTagAndProductForArticle(articlePo);
				userArticlesDto.add(articlePoToDto);
			}
		}
		userArticlesPagingData.setDataList(userArticlesDto);
		userArticlesPagingData.setPageIndex(suerArticleQuery.getPageIndex());// 设置当前页
		userArticlesPagingData.setPageSize(suerArticleQuery.getPageSize());// 设置一页多少条数据
		int count = articleDao.selectListCount(suerArticleQuery);
		userArticlesPagingData.setTotalCount(count);// 设置总数量
		userArticlesPagingData.setTotalPage(count, suerArticleQuery.getPageSize());// 设置总共多少页

		rd.setData(userArticlesPagingData);
		return rd;

	}
	@ResponseBody
	@RequestMapping("message")
	public ResponseData messages(HttpServletRequest request, @RequestBody ArticleDto articleDto) {
		ResponseData rd = new ResponseData();

		ArticleMessageQuery query = new ArticleMessageQuery();
		query.setArticleUuid(articleDto.getUuid());
		if(articleDto.getPageIndex()!=null){
			query.setPageIndex(articleDto.getPageIndex());
		}
		List<ArticleMessagePo> articleMessagesPo = articleMessageDao.selectList(query);

		List<ArticleMessageDto> massagesDto = Lists.newArrayList();
		if (articleMessagesPo != null) {
			for (ArticleMessagePo a : articleMessagesPo) {
				massagesDto.add(EntityUtil.articleMessagePoToDto(a));
			}
		}
		PagingData<ArticleMessageDto> articleMessagePagingData = new PagingData<>();
		articleMessagePagingData.setDataList(massagesDto);
		articleMessagePagingData.setPageIndex(query.getPageIndex());// 设置当前页
		articleMessagePagingData.setPageSize(query.getPageSize());// 设置一页多少条数据
		int count = articleMessageDao.selectListCount(query);
		articleMessagePagingData.setTotalCount(count);// 设置总数量
		articleMessagePagingData.setTotalPage(count, query.getPageSize());// 设置总共多少页
		rd.setData(articleMessagePagingData);
		return rd;

	}
}
