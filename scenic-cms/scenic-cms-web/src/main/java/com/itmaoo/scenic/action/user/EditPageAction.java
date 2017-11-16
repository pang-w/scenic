package com.itmaoo.scenic.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IImageDao;
import com.itmaoo.scenic.dao.IProductDao;
import com.itmaoo.scenic.dao.ISignatureLikeDao;
import com.itmaoo.scenic.dao.IUserDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ImageDto;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.ImagePo;
import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.query.ImageQuery;
import com.itmaoo.scenic.entity.query.ProductQuery;
import com.itmaoo.scenic.entity.query.SignatureLikeQuery;
import com.itmaoo.scenic.entity.query.UserQuery;
import com.itmaoo.scenic.entity.support.EditPageDto;
import com.itmaoo.scenic.entity.support.EntityUtil;
@Controller
@RequestMapping(value = "/action/page/edit/")
public class EditPageAction extends BaseAction{
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IArticleDao articleDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private ISignatureLikeDao signatureLikeDao;
	@Value("${base.site.domain}")
	private String baseDomain;

	@Value("${base.img.domain}")
	private String imgDomain;
	@Autowired
	private IImageDao imageDao;
	
	
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
