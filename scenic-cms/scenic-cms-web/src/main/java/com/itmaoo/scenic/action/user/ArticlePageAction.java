package com.itmaoo.scenic.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.dao.IArticleMessageDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ArticleMessageDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticleMessagePo;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.ArticleMessageQuery;
import com.itmaoo.scenic.entity.query.SignatureLikeQuery;
import com.itmaoo.scenic.entity.query.UserQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;

@Controller
@RequestMapping(value = "/action/page/article/")
public class ArticlePageAction extends BaseAction{
	@Autowired
	private IArticleMessageDao articleMessageDao;
	@ResponseBody
	@RequestMapping("message")
	public ResponseData messages(HttpServletRequest request, @RequestBody ArticleDto articleDto) {
		ResponseData rd = new ResponseData();

		UserDto loggedUser = getLogedUser(request);
		
		//if (loggedUser != null) {
			ArticleMessageQuery query = new ArticleMessageQuery();
		    List<ArticleMessagePo> articleMessagesPo = articleMessageDao.selectList(query);
		    List<ArticleMessageDto> massagesDto = Lists.newArrayList();
		    if(articleMessagesPo!=null){
		    	for(ArticleMessagePo a:articleMessagesPo){
		    		massagesDto.add(EntityUtil.articleMessagePoToDto(a));
		    	}
		    }
		    rd.setData(massagesDto);
		/*} else {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		}*/
		return rd;

	}
}
