package com.itmaoo.scenic.action.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.dao.IProductDao;
import com.itmaoo.scenic.dao.ITagDao;
import com.itmaoo.scenic.dao.IUserDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.TagDto;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.po.TagPo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.ProductQuery;
import com.itmaoo.scenic.entity.query.TagQuery;
import com.itmaoo.scenic.entity.query.UserQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;

public class BaseAction {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ITagDao tagDao;
	@Autowired
	private IProductDao productDao;
	
	public boolean islogged(HttpServletRequest req){
		if(getLogedUser(req)!=null){
			return true;
		}
		return false;
	}
	public UserDto getLogedUser(HttpServletRequest request){
		UserDto userPo = (UserDto) request.getSession().getAttribute("loggedUser");
		return userPo;
	}
	public void resetLogedUser(HttpServletRequest request,UserDto loggedUser){
		
		UserQuery uquery = new UserQuery();
		uquery.setUsername(loggedUser.getUsername());
		UserPo userDb = userDao.selectByUsername(uquery);
		UserDto userInfo = EntityUtil.userPoToDto(userDb);
		
		request.getSession().setAttribute("loggedUser", userInfo);
	}
	public IUserDao getUserDao() {
		return userDao;
	}
	public ArticleDto makeupTagAndProductForArticle(ArticlePo articlePo) {
		TagQuery tagQuery = new TagQuery();
		tagQuery.setArticleUuid(articlePo.getUuid());
		tagQuery.setEffected(true); 
		List<TagPo> tags = tagDao.selectList(tagQuery);
		List<TagDto> tagsDto = Lists.newArrayList();
		if (tags != null) {
			for (TagPo tag : tags) {
				tagsDto.add(EntityUtil.tagPoToDto(tag));
			}
		}
		ProductQuery productQuery = new ProductQuery();
		productQuery.setArticleUuid(articlePo.getUuid());
		productQuery.setEffected(true);
		List<ProductPo> productDb = productDao.selectList(productQuery);
		List<ProductDto> productsDto = Lists.newArrayList();
		if (productDb != null) {
			for (ProductPo p : productDb) {
				productsDto.add(EntityUtil.productPoToDto(p));
			}
		}
		ArticleDto articlePoToDto = EntityUtil.articlePoToDto(articlePo);
		articlePoToDto.setProducts(productsDto);
		articlePoToDto.setTags(tagsDto);
		return articlePoToDto;
	}

}
