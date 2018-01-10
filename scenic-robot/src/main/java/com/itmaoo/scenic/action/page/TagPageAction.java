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
import com.itmaoo.scenic.dao.IArticleMessageDao;
import com.itmaoo.scenic.dao.ITagDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ArticleMessageDto;
import com.itmaoo.scenic.entity.dto.PagingData;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.TagDto;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticleMessagePo;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.TagPo;
import com.itmaoo.scenic.entity.query.ArticleMessageQuery;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.query.TagQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;

@Controller
@RequestMapping(value = "/action/page/tag/")
public class TagPageAction extends BaseAction {
	@Autowired
	private IArticleMessageDao articleMessageDao;
	@Autowired
	private IArticleDao articleDao;
	@Autowired
	private ITagDao tagDao;
	
	@ResponseBody
	@RequestMapping("article")
	public ResponseData publicArticle(HttpServletRequest request, @RequestBody ArticleDto articleRequest) {
		ResponseData rd = new ResponseData();

		// 登录用户 可浏览的信息
		PagingData<ArticleDto> userArticlesPagingData = new PagingData<>();

		ArticleQuery suerArticleQuery = new ArticleQuery();
		suerArticleQuery.setTag(articleRequest.getSearchTag());
		
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
	@RequestMapping("save")
	@ResponseBody
	public ResponseData saveTag(HttpServletRequest request, @RequestBody TagDto tageDto) {

		UserDto user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		} else {
			TagPo entity = new TagPo();
			entity.setValue(tageDto.getValue());
			entity.setCreateBy(tageDto.getCreateBy());
			entity.setCreateDate(new Date());
			entity.setLastModifyDate(new Date());
			tagDao.insert(entity);
		}
		rd.setData(user);
		return rd;
	}

	@RequestMapping("select")
	@ResponseBody
	public ResponseData addMessage(HttpServletRequest request, @RequestBody TagDto tageDto) {

		UserDto user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		} else {
			TagQuery query = new TagQuery();
			query.setValue(tageDto.getValue());
			query.setCreateBy(tageDto.getCreateBy());
			List<TagDto> tagsDto = Lists.newArrayList();

			List<TagPo> tagsPo = tagDao.selectList(query);
			if (tagsPo != null) {
				for (TagPo tagPo : tagsPo) {
					tagsDto.add(EntityUtil.tagPoToDto(tagPo));
				}
			}
			rd.setData(tagsDto);
		}
		return rd;

	}
}
