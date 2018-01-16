package com.itmaoo.scenic.action.base;

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
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IProductDao;
import com.itmaoo.scenic.dao.ITagDao;
import com.itmaoo.scenic.dao.IUserDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.PagingData;
import com.itmaoo.scenic.entity.dto.ProductDto;
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
import com.itmaoo.scenic.wechat.SignConfig;
import com.itmaoo.scenic.wechat.WechatUtil;

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
	public ModelAndView blog(HttpServletRequest request, @PathVariable("useranme") String viewusername, ModelMap map) {

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
		ArticleQuery aq = new ArticleQuery();
		aq.setUuid(uuid);
		aq.setIsPublished(true); 
		ArticlePo a = articleDao.selectSingle(aq);
		if (a != null) {
			// 获取标签
			TagQuery tagQuery = new TagQuery();
			tagQuery.setArticleUuid(uuid);
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
			productQuery.setArticleUuid(uuid);
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
			map.put("wechatSign", WechatUtil.getSign("/a/"+uuid));
			ModelAndView mv = new ModelAndView("iukiss/article");
			return mv;
		}
		return null;

	}
	@RequestMapping("/")
	@ResponseBody
	public ModelAndView indexNull(HttpServletRequest request, ModelMap map) {
		return index(request, map);
		}
	@RequestMapping("/index.html")
	@ResponseBody
	public ModelAndView index(HttpServletRequest request, ModelMap map) {
		ArticleQuery indexArticleQuery = new ArticleQuery();
		indexArticleQuery.setIsPublished(true);
		indexArticleQuery.setPageSize(5);
		List<ArticlePo> indexArticlesPo = articleDao.selectList(indexArticleQuery);
		List<ArticleDto> indexArticlesDto = Lists.newArrayList();
		if (indexArticlesPo != null) {
			for (ArticlePo articlePo : indexArticlesPo) {
				ArticleDto articlePoToDto = makeupTagAndProductForArticle(articlePo);
				indexArticlesDto.add(articlePoToDto);
			}
		}

		map.addAttribute("baseDomain", baseDomain);
		map.addAttribute("imgDomain", imgDomain);
		map.addAttribute("indexArticles", indexArticlesDto);

		ModelAndView mv = new ModelAndView("iukiss/index");
		return mv;

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

	@RequestMapping("/action/edit/article/{articleUuid}")
	@ResponseBody
	public ModelAndView createArticle(@PathVariable("articleUuid") String articleUuid, ModelMap map) {
		while (articleUuid.endsWith("/")) {
			articleUuid = articleUuid.substring(0, articleUuid.length() - 1);
		}
		ArticleQuery aq = new ArticleQuery();
		aq.setUuid(articleUuid);
		ArticlePo articlePo = articleDao.selectSingle(aq);
		ArticleDto editArticle = null;
		if (articlePo == null) {
			editArticle = new ArticleDto();
			editArticle.setUuid(articleUuid);
			editArticle.setContent("");
			editArticle.setTitle("");
		} else {
			editArticle = EntityUtil.articlePoToDto(articlePo);
			TagQuery tagQuery = new TagQuery();
			tagQuery.setArticleUuid(articlePo.getUuid());
			List<TagPo> tags = tagDao.selectList(tagQuery);
			List<TagDto> tagsDto = Lists.newArrayList();
			if (tags != null) {
				for (TagPo tag : tags) {
					tagsDto.add(EntityUtil.tagPoToDto(tag));
				}
			}
			ProductQuery productQuery = new ProductQuery();
			productQuery.setArticleUuid(articlePo.getUuid());
			List<ProductPo> productDb = productDao.selectList(productQuery);
			List<ProductDto> productsDto = Lists.newArrayList();
			if (productDb != null) {
				for (ProductPo p : productDb) {
					productsDto.add(EntityUtil.productPoToDto(p));
				}
			}
			editArticle.setTags(tagsDto);
			editArticle.setProducts(productsDto);
		}

		map.addAttribute("editArticle", editArticle);

		map.addAttribute("imgDomain", imgDomain);
		map.addAttribute("articleUuid", articleUuid);

		ModelAndView mv = new ModelAndView("iukiss/editor");
		return mv;

	}

}
