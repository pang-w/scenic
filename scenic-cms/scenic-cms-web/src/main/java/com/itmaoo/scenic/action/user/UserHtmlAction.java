package com.itmaoo.scenic.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IArticleTagLinkDao;
import com.itmaoo.scenic.dao.IProductDao;
import com.itmaoo.scenic.dao.ITagDao;
import com.itmaoo.scenic.dao.IUserDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.PagingData;
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

@Controller
public class UserHtmlAction extends BaseAction {
	@Autowired
	private IArticleDao articleDao;
	@Autowired
	private ITagDao tagDao;


	@Autowired
	private IProductDao productDao;
	@Autowired
	private IUserDao userDao;
	@Value("${base.site.domain}")
	private String baseDomain;

	@Value("${base.img.domain}")
	private String imgDomain;

	@RequestMapping("/i/{useranme}")
	@ResponseBody
	public ModelAndView index(HttpServletRequest request, @PathVariable("useranme") String viewusername, ModelMap map) {

		UserQuery userQuery = new UserQuery();
		userQuery.setUsername(viewusername);
		UserPo userAuthor = getUserDao().selectByUsername(userQuery);

		if (userAuthor == null) {
			return null;
		}
		UserDto userPoToDto = EntityUtil.userPoToDto(userAuthor);

		map.addAttribute("baseDomain", baseDomain);
		map.addAttribute("imgDomain", imgDomain);
		map.addAttribute("bloger", userPoToDto);

		ModelAndView mv = new ModelAndView("iukiss/blog");
		return mv;

	}

	@RequestMapping("/a/{uuid}")
	@ResponseBody
	public ModelAndView article(HttpServletRequest request, @PathVariable("uuid") String uuid, ModelMap map) {

		// 取出数据库中的数生成永久地址页面，不要直接生成，保证下次生成的一致性

		ArticlePo articlePo = new ArticlePo();
		articlePo.setIsPublished(true);
		articlePo.setUuid(uuid);
		Integer count = articleDao.updatePublishStatus(articlePo);
		ArticleQuery aq = new ArticleQuery();
		aq.setUuid(uuid);
		ArticlePo a = articleDao.selectSingle(aq);
		if (a != null) {
			// 获取标签
			TagQuery tagQuery = new TagQuery();
			tagQuery.setArticleUuid(articlePo.getUuid());
			tagQuery.setEffected(true);
			List<TagPo> tags = tagDao.selectList(tagQuery);
			// 设置为null在展示文章时不显示标签整个节点
			List<TagDto> tagsDto = null;
			if (tags != null && !tags.isEmpty()) {
				tagsDto = Lists.newArrayList();
				for (TagPo tag : tags) {
					tagsDto.add(EntityUtil.tagPoToDto(tag));
				}
			}
			// 获取商品
			ProductQuery productQuery = new ProductQuery();
			productQuery.setArticleUuid(articlePo.getUuid());
			productQuery.setEffected(true);
			List<ProductPo> productDb = productDao.selectList(productQuery);
			List<ProductDto> productsDto = null;
			if (productDb != null && !productDb.isEmpty()) {
				productsDto = Lists.newArrayList();
				for (ProductPo p : productDb) {
					productsDto.add(EntityUtil.productPoToDto(p));
				}
			}
			ArticleDto articlePoToDto = EntityUtil.articlePoToDto(a);
			articlePoToDto.setProducts(productsDto);
			articlePoToDto.setTags(tagsDto);
			UserQuery uquery = new UserQuery();
			uquery.setUsername(a.getUsername());
			UserPo userDb = userDao.selectByUsername(uquery);
			UserDto userInfo = EntityUtil.userPoToDto(userDb);
			
			map.put("author", userInfo);
			map.put("article", articlePoToDto);
			map.put("imgDomain", imgDomain);
			map.put("articleUuid", uuid);

			ModelAndView mv = new ModelAndView("iukiss/article");
			return mv;
		}
		return null;

	}

	@RequestMapping("/tags/{tag}")
	@ResponseBody
	public ModelAndView tag(HttpServletRequest request, @PathVariable("tag") String tag, ModelMap map) {

		UserDto author = new UserDto();
		author.setUsername(tag);

		UserDto loggedUser = getLogedUser(request);

		ArticleQuery aq = new ArticleQuery();
		aq.setPageIndex(1);
		aq.setPageSize(5);
		aq.setTag(tag);
		List<ArticlePo> articles = articleDao.selectList(aq);
		List<ArticleDto> aticleDtos = EntityUtil.articlePoToDto(articles);
		if (articles != null) {
			for (ArticlePo articlePo : articles) {
				ArticleDto articlePoToDto = makeupTagAndProductForArticle(articlePo);
				aticleDtos.add(articlePoToDto);
			}
		}
		PagingData<ArticleDto> pager = new PagingData<ArticleDto>();
		pager.setDataList(aticleDtos);
		pager.setPageIndex(aq.getPageIndex());// 设置当前页
		pager.setPageSize(aq.getPageSize());// 设置一页多少条数据
		int count = articleDao.selectListCount(aq);
		pager.setTotalCount(count);// 设置总数量
		pager.setTotalPage(count, aq.getPageSize());// 设置总共多少页

		map.addAttribute("tag", tag);
		map.addAttribute("pager", pager);
		map.addAttribute("author", author);
		map.addAttribute("articles", aticleDtos);
		if (loggedUser != null) {
			map.addAttribute("loggedUser", loggedUser);
			if (loggedUser.getUsername().equals(tag)) {
				ModelAndView mv = new ModelAndView("iukiss/tag");
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("iukiss/tag");
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView("iukiss/tag");
			return mv;
		}

	}

	@RequestMapping("/product/add/")
	@ResponseBody
	public ModelAndView createProduct(HttpServletRequest request, ModelMap map) {

		request.getAttribute("imageUrl");

		ModelAndView mv = new ModelAndView("iukiss/index");
		return mv;

	}

}
