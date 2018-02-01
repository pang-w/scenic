package com.itmaoo.scenic.action.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.ISignatureLikeDao;
import com.itmaoo.scenic.dao.ITagDao;
import com.itmaoo.scenic.dao.IUserDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.PagingData;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.TagDto;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.TagPo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.query.SignatureLikeQuery;
import com.itmaoo.scenic.entity.query.TagQuery;
import com.itmaoo.scenic.entity.query.UserQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;
import com.itmaoo.scenic.entity.support.IndexPageDto;

@Controller
@RequestMapping(value = "/action/page/index/")
public class IndexPageAction extends BaseAction {
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IArticleDao articleDao;

	@Autowired
	private ITagDao tagDao;

	@Autowired
	private ISignatureLikeDao signatureLikeDao;
	@Value("${base.img.domain}")
	private String imgDomain;

	@ResponseBody
	@RequestMapping("all")
	public ResponseData all(HttpServletRequest request, @RequestBody ArticleDto articleRequest) {

		/** top user **/
		UserDto userData = (UserDto) topUser(request).getData();
		
		/** Tags (asideTagsDto) **/
		TagQuery asideTagQuery = new TagQuery();
		// asideTagQuery.setUsername(username);
		// asideTagQuery.setPageIndex(1);
		asideTagQuery.setPageSize(20);
		List<TagPo> asideTagsPo = tagDao.selectList(asideTagQuery);
		List<TagDto> asideTagsDto = EntityUtil.tagPoToDtoList(asideTagsPo);

		// Set public properties
		IndexPageDto indexDto = new IndexPageDto();
		indexDto.setTopUser(userData);
		indexDto.setAsideTags(asideTagsDto);

		// 登录用户 可浏览的信息
		UserDto loggedUser = getLogedUser(request);
		if (loggedUser != null) {
			/** Tags (asideTagsDto) **/
			TagQuery userTagQuery = new TagQuery();
			userTagQuery.setUsername(loggedUser.getUsername());
			asideTagQuery.setPageIndex(1);
			asideTagQuery.setPageSize(10);
			List<TagPo> userTagsPo = tagDao.selectList(userTagQuery);
			List<TagDto> userTagsDto = EntityUtil.tagPoToDtoList(userTagsPo);

			indexDto.setLoggedUser(loggedUser);
			indexDto.setUserTags(userTagsDto);
		}
		ResponseData rd = new ResponseData();
		rd.setData(indexDto);
		return rd;

	}

	@ResponseBody
	@RequestMapping("article")
	public ResponseData publicArticle(HttpServletRequest request, @RequestBody ArticleDto articleRequest) {
		ResponseData rd = new ResponseData();

		// 登录用户 可浏览的信息
		PagingData<ArticleDto> indexArticlesPagingData = new PagingData<>();

		ArticleQuery indexArticleQuery = new ArticleQuery();
		indexArticleQuery.setIsPublished(true);
		if (articleRequest == null || articleRequest.getPageIndex() == null) {
			indexArticleQuery.setPageIndex(1);
		} else {
			indexArticleQuery.setPageIndex(articleRequest.getPageIndex());
		}
		indexArticleQuery.setPageSize(5);
		List<ArticlePo> indexArticlesPo = articleDao.selectList(indexArticleQuery);
		List<ArticleDto> indexArticlesDto = Lists.newArrayList();
		if (indexArticlesPo != null) {
			for (ArticlePo articlePo : indexArticlesPo) {
				ArticleDto articlePoToDto = makeupTagAndProductForArticle(articlePo);
				indexArticlesDto.add(articlePoToDto);
			}
		}
		indexArticlesPagingData.setDataList(indexArticlesDto);
		indexArticlesPagingData.setPageIndex(indexArticleQuery.getPageIndex());// 设置当前页
		indexArticlesPagingData.setPageSize(indexArticleQuery.getPageSize());// 设置一页多少条数据
		int count = articleDao.selectListCount(indexArticleQuery);
		indexArticlesPagingData.setTotalCount(count);// 设置总数量
		indexArticlesPagingData.setTotalPage(count, indexArticleQuery.getPageSize());// 设置总共多少页

		rd.setData(indexArticlesPagingData);
		return rd;

	}

	@ResponseBody
	@RequestMapping("topUser")
	public ResponseData topUser(HttpServletRequest request) {
		ResponseData rd = new ResponseData();

		SignatureLikeQuery query = new SignatureLikeQuery();
		UserQuery recQuery = new UserQuery();
		recQuery.setUsername("ITMAOO");
		UserPo recUser = userDao.selectByUsername(recQuery);
		query.setBelikedUser(recUser.getUsername());
		Integer count = signatureLikeDao.countByLikedUser(query);
		UserDto userData = EntityUtil.userPoToDto(recUser);
		userData.setSignatureLikedCount(count);
		rd.setData(userData);
		return rd;

	}
}
