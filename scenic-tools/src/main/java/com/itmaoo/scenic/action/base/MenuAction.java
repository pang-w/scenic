package com.itmaoo.scenic.action.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IImageDao;
import com.itmaoo.scenic.dao.IProductDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ImageDto;
import com.itmaoo.scenic.entity.dto.PagingData;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.ImagePo;
import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.query.ImageQuery;
import com.itmaoo.scenic.entity.query.ProductQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;
import com.itmaoo.scenic.support.ErrorCodes;

@Controller
@RequestMapping(value = "/action/menu/")
public class MenuAction extends BaseAction {
	@Autowired
	private IArticleDao articleDao;

	@Autowired
	private IImageDao imageDao;

	@Autowired
	private IProductDao productDao;

	@Value("${base.img.domain}")
	private String imgDomain;

	@ResponseBody
	@RequestMapping("article")
	public ResponseData article(HttpServletRequest request, @RequestBody ArticleDto articleRequest) {
		ResponseData rd = new ResponseData();

		// 登录用户 可浏览的信息
		UserDto loggedUser = getLogedUser(request);
		PagingData<ArticleDto> userArticlesPagingData = new PagingData<>();
		if (loggedUser != null) {

			ArticleQuery suerArticleQuery = new ArticleQuery();
			suerArticleQuery.setUsername(loggedUser.getUsername());
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
		} else {
			rd.setStatus(ErrorCodes.INGNOR);
			rd.setMsg("未登录");
		}
		return rd;
	}

	@ResponseBody
	@RequestMapping("image")
	public ResponseData image(HttpServletRequest request, @RequestBody ImageDto imageRequest) {
		ResponseData rd = new ResponseData();

		// 登录用户 可浏览的信息
		UserDto loggedUser = getLogedUser(request);
		PagingData<ImageDto> userImagePagingData = new PagingData<>();
		if (loggedUser != null) {
			/** Images **/
			ImageQuery iQuery = new ImageQuery();
			iQuery.setUsername(loggedUser.getUsername());
			if (imageRequest == null || imageRequest.getPageIndex() == null) {
				iQuery.setPageIndex(1);
			} else {
				iQuery.setPageIndex(imageRequest.getPageIndex());
			}
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

			userImagePagingData.setDataList(imagesDto);
			userImagePagingData.setPageIndex(iQuery.getPageIndex());// 设置当前页
			userImagePagingData.setPageSize(iQuery.getPageSize());// 设置一页多少条数据
			int count = imageDao.selectListCount(iQuery);
			userImagePagingData.setTotalCount(count);// 设置总数量
			userImagePagingData.setTotalPage(count, iQuery.getPageSize());// 设置总共多少页

			rd.setData(userImagePagingData);
		} else {
			rd.setStatus(ErrorCodes.INGNOR);
			rd.setMsg("未登录");
		}
		return rd;

	}

	@ResponseBody
	@RequestMapping("product")
	public ResponseData product(HttpServletRequest request, @RequestBody ProductDto imageRequest) {
		ResponseData rd = new ResponseData();

		// 登录用户 可浏览的信息
		UserDto loggedUser = getLogedUser(request);
		PagingData<ProductDto> userImagePagingData = new PagingData<>();
		if (loggedUser != null) {
			/** User Product **/
			ProductQuery uProQuery = new ProductQuery();
			uProQuery.setUsername(loggedUser.getUsername());
			if (imageRequest == null || imageRequest.getPageIndex() == null) {
				uProQuery.setPageIndex(1);
			} else {
				uProQuery.setPageIndex(imageRequest.getPageIndex());
			}
			List<ProductPo> uProductsPo = productDao.selectList(uProQuery);
			List<ProductDto> productMenu = Lists.newArrayList();
			if (uProductsPo != null) {
				for (ProductPo pPo : uProductsPo) {
					ProductDto proPoToDto = EntityUtil.productPoToDto(pPo);

					productMenu.add(proPoToDto);
				}
			}

			userImagePagingData.setDataList(productMenu);
			userImagePagingData.setPageIndex(uProQuery.getPageIndex());// 设置当前页
			userImagePagingData.setPageSize(uProQuery.getPageSize());// 设置一页多少条数据
			int count = imageDao.selectListCount(uProQuery);
			userImagePagingData.setTotalCount(count);// 设置总数量
			userImagePagingData.setTotalPage(count, uProQuery.getPageSize());// 设置总共多少页

			rd.setData(userImagePagingData);
		} else {
			rd.setStatus(ErrorCodes.INGNOR);
			rd.setMsg("未登录");
		}
		return rd;

	}

}
