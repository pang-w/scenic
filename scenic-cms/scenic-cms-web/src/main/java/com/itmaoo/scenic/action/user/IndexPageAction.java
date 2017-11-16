package com.itmaoo.scenic.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
	private IImageDao imageDao;

	@Autowired
	private IProductDao productDao;

	@Autowired
	private ISignatureLikeDao signatureLikeDao;
	@Value("${base.img.domain}")
	private String imgDomain;

	@ResponseBody
	@RequestMapping("all")
	public ResponseData all(HttpServletRequest request) {

		/** top user **/
		UserDto userData = (UserDto) topUser(request).getData();

		/** articles **/
		ArticleQuery query = new ArticleQuery();
		query.setIsPublished(true);
		query.setPageIndex(1);
		query.setPageSize(5);
		List<ArticlePo> articlesPo = articleDao.selectList(query);
		List<ArticleDto> articlesDto = Lists.newArrayList();
		if (articlesPo != null) {
			for (ArticlePo articlePo : articlesPo) {
				articlesDto.add(EntityUtil.articlePoToDto(articlePo));
			}
		}
		/** Products **/
		ProductQuery proQuery = new ProductQuery();
		// proQuery.setUsername(loggedUser.getUsername());
		List<ProductPo> productsPo = productDao.selectList(proQuery);
		List<ProductDto> products = Lists.newArrayList();
		if (products != null) {
			for (ProductPo pPo : productsPo) {
				ProductDto proPoToDto = EntityUtil.productPoToDto(pPo);
				products.add(proPoToDto);
			}
		}

		IndexPageDto indexDto = new IndexPageDto();
		indexDto.setTopUser(userData);
		indexDto.setArticles(articlesDto);
		indexDto.setProducts(products);

		UserDto loggedUser = getLogedUser(request);

		if (loggedUser != null) {
			/** user articles **/
			List<ArticleDto> userArticlesDto = Lists.newArrayList();
			if (loggedUser != null) {
				ArticleQuery suerArticleQuery = new ArticleQuery();
				suerArticleQuery.setUsername(loggedUser.getUsername());
				suerArticleQuery.setPageIndex(1);
				suerArticleQuery.setPageSize(5);
				List<ArticlePo> userArticlesPo = articleDao.selectList(suerArticleQuery);
				if (articlesPo != null) {
					for (ArticlePo articlePo : userArticlesPo) {
						userArticlesDto.add(EntityUtil.articlePoToDto(articlePo));
					}
				}
			}

			/** Images **/
			ImageQuery iQuery = new ImageQuery();
			iQuery.setUsername(loggedUser.getUsername());
			List<ImagePo> imageMenu = imageDao.selectList(iQuery);
			List<ImageDto> imagesDto = Lists.newArrayList();
			if (imageMenu != null) {
				for (ImagePo imagePo : imageMenu) {
					ImageDto imagePoToDto = EntityUtil.imagePoToDto(imagePo);
					if (!imgDomain.endsWith("/")) {
						imagePoToDto.setUrl(imgDomain + "/" + imagePoToDto.getBaseUri());
					} else {
						imagePoToDto.setUrl(imgDomain + imagePoToDto.getBaseUri());
					}
					imagesDto.add(imagePoToDto);
				}
			}

			/** User Product **/
			ProductQuery uProQuery = new ProductQuery();
			uProQuery.setUsername(loggedUser.getUsername());
			List<ProductPo> uProductsPo = productDao.selectList(uProQuery);
			List<ProductDto> productMenu = Lists.newArrayList();
			if (productMenu != null) {
				for (ProductPo pPo : uProductsPo) {
					ProductDto proPoToDto = EntityUtil.productPoToDto(pPo);
					productMenu.add(proPoToDto);
				}
			}
			indexDto.setArticleMenu(userArticlesDto);
			// indexDto.setAttentionMenu(articlesDto);
			indexDto.setImageMenu(imagesDto);
			indexDto.setProductMenu(productMenu);
			indexDto.setLoggedUser(loggedUser);
		}

		ResponseData rd = new ResponseData();
		rd.setData(indexDto);
		return rd;

	}

	@ResponseBody
	@RequestMapping("topUser")
	public ResponseData topUser(HttpServletRequest request) {
		ResponseData rd = new ResponseData();

		SignatureLikeQuery query = new SignatureLikeQuery();
		UserQuery recQuery = new UserQuery();
		recQuery.setUsername("ITMAOO");
		UserPo recUser = userDao.selectSingle(recQuery);
		query.setBelikedUser(recUser.getUsername());
		Integer count = signatureLikeDao.countByLikedUser(query);
		UserDto userData = EntityUtil.userPoToDto(recUser);
		userData.setSignatureLikedCount(count);
		rd.setData(userData);
		return rd;

	}
}
