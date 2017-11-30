package com.itmaoo.scenic.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IProductDao;
import com.itmaoo.scenic.dao.ITagDao;
import com.itmaoo.scenic.dao.IUserDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.TagDto;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.po.TagPo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.query.ProductQuery;
import com.itmaoo.scenic.entity.query.TagQuery;
import com.itmaoo.scenic.entity.query.UserQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;
import com.itmaoo.scenic.entity.support.IndexPageDto;
/**
 * 
 * @author mario
 *
 */
@Controller
@RequestMapping(value = "/action/page/edit/")
public class EditPageAction extends BaseAction {
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IArticleDao articleDao;

	@Autowired
	private IProductDao productDao;
	@Autowired
	private ITagDao tagDao;
	
	@Value("${base.img.domain}")
	private String imgDomain;

	@ResponseBody
	@RequestMapping("{articleUuid}")
	public ResponseData all(HttpServletRequest request, @PathVariable("articleUuid") String articleUuid) {

		/** top user **/
		UserDto userData = (UserDto) topUser(request).getData();

		/** Products (productsDto)**/
		ProductQuery proQuery = new ProductQuery();
		// proQuery.setUsername(loggedUser.getUsername());
		List<ProductPo> productsPo = productDao.selectList(proQuery);
		List<ProductDto> publicProductsDto = Lists.newArrayList();
		if (productsPo != null) {
			for (ProductPo pPo : productsPo) {
				ProductDto proPoToDto = EntityUtil.productPoToDto(pPo);
				publicProductsDto.add(proPoToDto);
			}
		}
		/** Tags (asideTagsDto)**/
		TagQuery asideTagQuery = new TagQuery();
		//asideTagQuery.setUsername(username);
		asideTagQuery.setPageIndex(1);
		asideTagQuery.setPageSize(20);
		List<TagPo> asideTagsPo = tagDao.selectList(asideTagQuery);
		List<TagDto> asideTagsDto = EntityUtil.tagPoToDtoList(asideTagsPo);
		
		/** Products (productsDto)**/
		ProductQuery linkProQuery = new ProductQuery();
		linkProQuery.setArticleUuid(articleUuid);
		List<ProductPo> linkedProductsPo = productDao.selectList(linkProQuery);
		List<ProductDto> linkedProductsDto = Lists.newArrayList();
		if (linkedProductsPo != null) {
			for (ProductPo pPo : linkedProductsPo) {
				ProductDto proPoToDto = EntityUtil.productPoToDto(pPo);
				linkedProductsDto.add(proPoToDto);
			}
		}


		//Set public properties
		IndexPageDto indexDto = new IndexPageDto();
		indexDto.setTopUser(userData);
		indexDto.setAsideTags(asideTagsDto);
		indexDto.setLinkedProducts(linkedProductsDto);

		//登录用户 可浏览的信息
		UserDto loggedUser = getLogedUser(request);
		if (loggedUser != null) {
			/** Tags (asideTagsDto)**/
			TagQuery userTagQuery = new TagQuery();
			userTagQuery.setUsername(loggedUser.getUsername());
			userTagQuery.setPageIndex(1);
			userTagQuery.setPageSize(20);
			List<TagPo> userTagsPo = tagDao.selectList(userTagQuery);
			TagQuery defaultTagQuery = new TagQuery();
			defaultTagQuery.setPageIndex(1);
			defaultTagQuery.setPageSize(5);
			defaultTagQuery.setType("default");
			List<TagPo> defaultTagsPo = tagDao.selectList(defaultTagQuery);
			//添加默认Tag
			
			List<TagDto> userTagsDto = EntityUtil.tagPoToDtoList(defaultTagsPo);
			userTagsDto.addAll(EntityUtil.tagPoToDtoList(userTagsPo));
			indexDto.setUserTags(userTagsDto);
			
			/** user articles **/
			List<ArticleDto> userArticlesDto = Lists.newArrayList();
			if (loggedUser != null) {
				ArticleQuery suerArticleQuery = new ArticleQuery();
				suerArticleQuery.setUsername(loggedUser.getUsername());
				suerArticleQuery.setPageIndex(1);
				suerArticleQuery.setPageSize(5);
				List<ArticlePo> userArticlesPo = articleDao.selectList(suerArticleQuery);
				ArticleDto editArticle = new ArticleDto();
				if (userArticlesPo != null) {
					for (ArticlePo articlePo : userArticlesPo) {
						ArticleDto articlePoToDto = makeupTagAndProductForArticle(articlePo);
						userArticlesDto.add(articlePoToDto);
						if (articlePo.getUuid().equals(articleUuid)) {
							editArticle = articlePoToDto;
							indexDto.setEditArticle(editArticle);
						}
					}
				}
			}
		}
		
		ResponseData rd = new ResponseData();
		rd.setData(indexDto);
		return rd;

	}

	

	@ResponseBody
	@RequestMapping("topUser")
	public ResponseData topUser(HttpServletRequest request) {
		UserQuery userQuery = new UserQuery();
		userQuery.setUsername("ITMAOO");
		UserPo recUser = userDao.selectByUsername(userQuery);
		UserDto userData = EntityUtil.userPoToDto(recUser);
		
		ResponseData rd = new ResponseData();
		rd.setData(userData);
		return rd;

	}
}
