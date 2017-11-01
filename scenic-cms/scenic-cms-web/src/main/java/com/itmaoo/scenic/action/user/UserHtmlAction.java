package com.itmaoo.scenic.action.user;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseActiom;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.entity.dto.ArtilcleDto;
import com.itmaoo.scenic.entity.dto.PagerDto;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.query.ArticleQuery;

@Controller
public class UserHtmlAction extends BaseActiom{
	@Autowired
	private IArticleDao articleDao;
	
	@RequestMapping("/i/{useranme}")
	@ResponseBody
	public ModelAndView index(HttpServletRequest request,@PathVariable("useranme") String viewuseranme, ModelMap map) {
		UserDto userDto = getLogedUser(request);
		
		ArticleQuery aq = new ArticleQuery();
		aq.setUsername(viewuseranme);
		List<ArticlePo> articles = articleDao.selectList(aq);
		List<ArtilcleDto> aticleDtos = toArticleDto(articles);
		PagerDto pager = new PagerDto();
		pager.setCurrentPage(1);
		pager.setTotalPage(aticleDtos.size()/10 + 1);
		pager.setList(aticleDtos);
		map.addAttribute("baseDomain", "http://localhost:8080");
		map.addAttribute("imgDomain", "http://img.iukiss.com");
		map.addAttribute("pager", pager);
		if(userDto!=null){
			map.addAttribute("user", userDto);
			if(userDto.getUsername().equals(viewuseranme)){
				ModelAndView mv = new ModelAndView("iukiss/index");
				return mv;
			}else{
				ModelAndView mv = new ModelAndView("iukiss/index");
				return mv;
			}
		}else{
			ModelAndView mv = new ModelAndView("iukiss/index");
			return mv;
		}

	}

	private List<ArtilcleDto> toArticleDto(List<ArticlePo> articles) {
		if(articles==null){
			return null;
		}
		List<ArtilcleDto> results = Lists.newArrayList();
		for(ArticlePo apo:articles){
			ArtilcleDto dto = new ArtilcleDto();
			dto.setContent(apo.getContent());
			dto.setCreateDate(new Date().toLocaleString());
			dto.setLastmodify(new Date().toString());
			dto.setTitle(apo.getTitle());
			dto.setUuid(apo.getUuid());
			dto.setUsername(apo.getUsername());
			dto.setDescription("aqwer");
			results.add(dto);
		}
		return results;
	}
}



