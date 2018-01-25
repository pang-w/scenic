package com.itmaoo.scenic.action.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IArticleLikeDao;
import com.itmaoo.scenic.dao.IArticleMessageDao;
import com.itmaoo.scenic.dao.IMessageLikeDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.PagingData;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.query.ArticleQuery;

@Controller
@RequestMapping(value = "/action/wechat/")
public class WechatAction extends BaseAction {
	@Autowired
	private IArticleMessageDao articleMessageDao;
	@Autowired
	private IArticleDao articleDao;
	@Autowired
	private IArticleLikeDao articleLikeDao;
	@Autowired
	private IMessageLikeDao messageLikeDao;
	
	@ResponseBody
	@RequestMapping(value ="search")
	public ResponseData publicArticle(HttpServletRequest request, @RequestBody ArticleDto articleRequest) {
		ResponseData rd = new ResponseData();

		PagingData<ArticleDto> userArticlesPagingData = new PagingData<>();

		ArticleQuery suerArticleQuery = new ArticleQuery();
		suerArticleQuery.setIsPublished(true);
		if (articleRequest == null || articleRequest.getPageIndex() == null) {
			suerArticleQuery.setPageIndex(1);
		} else {
			suerArticleQuery.setPageIndex(articleRequest.getPageIndex());
		}
		suerArticleQuery.setPageSize(3);
		suerArticleQuery.setTitle(articleRequest.getTitle());
		suerArticleQuery.setContent(articleRequest.getContent());
		suerArticleQuery.setUsername(articleRequest.getUsername());
		List<ArticlePo> userArticlesPo = articleDao.searchList(suerArticleQuery);
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
	@RequestMapping("addArticle")
	public ResponseData addArticle(HttpServletRequest request, @RequestBody ArticleDto articleRequest) {
		ResponseData rd = new ResponseData();

		PagingData<ArticleDto> userArticlesPagingData = new PagingData<>();

		ArticleQuery suerArticleQuery = new ArticleQuery();
		suerArticleQuery.setIsPublished(true);
		if (articleRequest == null || articleRequest.getPageIndex() == null) {
			suerArticleQuery.setPageIndex(1);
		} else {
			suerArticleQuery.setPageIndex(articleRequest.getPageIndex());
		}
		suerArticleQuery.setPageSize(5);
		suerArticleQuery.setTitle(articleRequest.getTitle());
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
	

}
