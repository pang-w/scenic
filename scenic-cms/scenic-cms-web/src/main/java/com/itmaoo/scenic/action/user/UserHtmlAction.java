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
import com.itmaoo.scenic.entity.dto.PagerDto;
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

		ArticleQuery aq = new ArticleQuery();
		aq.setUsername(viewusername);
		List<ArticlePo> articles = articleDao.selectList(aq);
		List<ArticleDto> aticleDtos = EntityUtil.articlePoToDto(articles);
		PagerDto pager = new PagerDto();
		pager.setCurrentPage(1);
		pager.setTotalPage(aticleDtos.size() / 10 + 1);
		pager.setList(aticleDtos);

		map.addAttribute("baseDomain", baseDomain);
		map.addAttribute("imgDomain", imgDomain);
		map.addAttribute("pager", pager);
		map.addAttribute("author", userPoToDto);

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
		//aq.setUsername(tag);
		List<ArticlePo> articles = articleDao.selectList(aq);
		List<ArticleDto> aticleDtos = EntityUtil.articlePoToDto(articles);
		PagerDto pager = new PagerDto();
		pager.setCurrentPage(1);
		pager.setTotalPage(aticleDtos.size() / 10 + 1);
		pager.setList(aticleDtos);
		map.addAttribute("pager", pager);
		map.addAttribute("author", author);
		map.addAttribute("articles",aticleDtos);
		if (loggedUser != null) {
			map.addAttribute("loggedUser", loggedUser);
			if (loggedUser.getUsername().equals(tag)) {
				ModelAndView mv = new ModelAndView("iukiss/tags");
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("iukiss/tags");
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView("iukiss/tags");
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
