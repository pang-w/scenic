package com.itmaoo.scenic.action.user;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.dao.ITagDao;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.TagDto;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.TagPo;
import com.itmaoo.scenic.entity.query.TagQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;

@Controller
@RequestMapping(value = "/action/user/tag/")
public class TagUserAction extends BaseAction {
	
	@Autowired
	private ITagDao tagDao;

	@RequestMapping("save")
	@ResponseBody
	public ResponseData saveTag(HttpServletRequest request, @RequestBody TagDto tageDto) {

		UserDto user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		} else {
			TagPo entity = new TagPo();
			entity.setValue(tageDto.getValue());
			entity.setCreateBy(tageDto.getCreateBy());
			entity.setCreateDate(new Date());
			entity.setLastModifyDate(new Date());
			tagDao.insert(entity);
		}
		rd.setData(user);
		return rd;    
	}
	@RequestMapping("select")
	@ResponseBody
	public ResponseData addMessage(HttpServletRequest request, @RequestBody TagDto tageDto) {

		UserDto user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		} else {
			TagQuery query = new TagQuery();
			query.setValue(tageDto.getValue());
			query.setCreateBy(tageDto.getCreateBy());
			List<TagDto> tagsDto = Lists.newArrayList();
			
			List<TagPo> tagsPo = tagDao.selectList(query);
			if(tagsPo!=null){
				for(TagPo tagPo:tagsPo){
					tagsDto.add(EntityUtil.tagPoToDto(tagPo));
				}
			}
			rd.setData(tagsDto);
		}
		return rd;    

	}
	
}
