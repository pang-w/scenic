package com.itmaoo.scenic.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseActiom;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.ISignatureLikeDao;
import com.itmaoo.scenic.dao.IUserDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.query.SignatureLikeQuery;
import com.itmaoo.scenic.entity.query.UserQuery;
import com.itmaoo.scenic.entity.support.EditPageDto;
import com.itmaoo.scenic.entity.support.EntityUtil;
@Controller
@RequestMapping(value = "/action/page/edit/")
public class EditPageAction extends BaseActiom{
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IArticleDao articleDao;
	
	@Autowired
	private ISignatureLikeDao signatureLikeDao;
	
	
	@ResponseBody
	@RequestMapping("all")
	public ResponseData all(HttpServletRequest request) {
	
		/** top user **/
		UserQuery recQuery = new UserQuery();
		recQuery.setUsername("ITMAOO");
		UserPo recUser = userDao.selectSingle(recQuery);
		SignatureLikeQuery squery = new SignatureLikeQuery();
		squery.setBelikedUser(recUser.getUsername());
		Integer count = signatureLikeDao.countByLikedUser(squery);
		
		UserDto userData = EntityUtil.userPoToDto(recUser);
		userData.setSignatureLikedCount(count);
		
		
		
		/** articles **/
		ArticleQuery query = new ArticleQuery();
		query.setPageIndex(1);
		query.setPageSize(5);
		List<ArticlePo> articlesPo = articleDao.selectList(query);
		List<ArticleDto> articlesDto = Lists.newArrayList();
		if(articlesPo!=null){
			for(ArticlePo articlePo:articlesPo){
				articlesDto.add(EntityUtil.articlePoToDto(articlePo));
			}
		}
		
		/** edit article**/
		//String articleUuid = requstArticle.getUuid();
		ArticleDto editArticle = new ArticleDto();
		/*if(articleUuid!=null){
			while (articleUuid.endsWith("/")) {
				articleUuid = articleUuid.substring(0, articleUuid.length() - 1);
			}
			ArticleQuery aq = new ArticleQuery();
			aq.setUuid(articleUuid);
			ArticlePo a = articleDao.selectSingle(aq);
			if (a != null) {
				editArticle = EntityUtil.articlePoToDto(a);
			}
		}*/
		
		
		EditPageDto editDto = new EditPageDto();
		editDto.setTopUser(userData);
		editDto.setArticles(articlesDto);
		editDto.setEditArticle(editArticle);
		if(getLogedUser(request)!=null){
			editDto.setArticleMenu(articlesDto);
			editDto.setAttentionMenu(articlesDto);
			//indexDto.setImageMenu(imageMenu);
			//indexDto.setProductMenu(productMenu);
		}
		
		ResponseData rd = new ResponseData();
		rd.setData(editDto);
		return rd;

	}
	
	@ResponseBody
	@RequestMapping("loadArticle/{uuid}")
	public ResponseData loadArticle(HttpServletRequest request, @PathVariable("uuid") String articleUuid) {
	
		ArticleDto editArticle = new ArticleDto();
		if(articleUuid!=null){
			while (articleUuid.endsWith("/")) {
				articleUuid = articleUuid.substring(0, articleUuid.length() - 1);
			}
			ArticleQuery aq = new ArticleQuery();
			aq.setUuid(articleUuid);
			ArticlePo a = articleDao.selectSingle(aq);
			if (a != null) {
				editArticle = EntityUtil.articlePoToDto(a);
			}
		}
		ResponseData rd = new ResponseData();
		rd.setData(editArticle);
		return rd;

	}
	
	
}
