package com.itmaoo.scenic.action.page;

import java.util.Date;
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
import com.itmaoo.scenic.dao.IArticleLikeDao;
import com.itmaoo.scenic.dao.IArticleMessageDao;
import com.itmaoo.scenic.dao.IMessageLikeDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ArticleLikeDto;
import com.itmaoo.scenic.entity.dto.ArticleMessageDto;
import com.itmaoo.scenic.entity.dto.MessageLikeDto;
import com.itmaoo.scenic.entity.dto.PagingData;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticleLikePo;
import com.itmaoo.scenic.entity.po.ArticleMessagePo;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.MessageLikePo;
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
	@Autowired
	private IArticleLikeDao articleLikeDao;
	@Autowired
	private IMessageLikeDao messageLikeDao;
	
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
	@RequestMapping("likeArticle")
	@ResponseBody
	public ResponseData likeArticle(HttpServletRequest request, @RequestBody ArticleLikeDto articleLikeDto) {
		UserDto user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		} else {
			ArticleLikePo entity = new ArticleLikePo();
			entity.setArticleUuid(articleLikeDto.getArticleUuid());
			entity.setActionUser(user.getUsername());
			entity.setCreateDate(new Date());
			articleLikeDao.insert(entity);
		}
		rd.setData(user);
		return rd;    

	}
	@RequestMapping("addMessage")
	@ResponseBody
	public ResponseData addMessage(HttpServletRequest request, @RequestBody ArticleMessageDto articleMessageDto) {

		UserDto user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		} else {
			ArticleMessagePo entity = new ArticleMessagePo();
			entity.setArticleUuid(articleMessageDto.getArticleUuid());
			entity.setActionUser(user.getUsername());
			entity.setMessage(articleMessageDto.getMessage());
			entity.setCreateDate(new Date());
			articleMessageDao.insert(entity);
		}
		rd.setData(user);
		return rd;    

	}
	@RequestMapping("likeMessage")
	@ResponseBody
	public ResponseData addMessage(HttpServletRequest request, @RequestBody MessageLikeDto messageLikeDto) {

		UserDto user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		} else {
			MessageLikePo entity = new MessageLikePo();
			entity.setActionUser(user.getUsername());
			entity.setMessageId(messageLikeDto.getMessageId());
			entity.setCreateDate(new Date());
			entity.setLastModifyDate(new Date());
			messageLikeDao.insert(entity);
		}
		rd.setData(user);
		return rd;    

	}
}
