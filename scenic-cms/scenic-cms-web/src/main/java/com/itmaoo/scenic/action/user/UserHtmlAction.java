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

import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.PagingData;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.query.UserQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;

@Controller
public class UserHtmlAction extends BaseAction {
	@Autowired
	private IArticleDao articleDao;

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
		map.addAttribute("articles",aticleDtos);
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
