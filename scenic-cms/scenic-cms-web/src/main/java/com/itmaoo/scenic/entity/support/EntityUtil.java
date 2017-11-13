package com.itmaoo.scenic.entity.support;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ImageDto;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.ImagePo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.support.CommonUtil;

public class EntityUtil {
	public static UserDto userPoToDto(UserPo userPo) {

		UserDto userDto = new UserDto();
		userDto.setTelphone(userPo.getTelphone());
		userDto.setCreateDate(CommonUtil.formatDate(userPo.getCreateDate()));
		userDto.setEmail(userPo.getEmail());
		userDto.setInvalid(userPo.getInvalid());
		userDto.setIsMale(userPo.getIsMale());
		userDto.setLastLoggedDate(CommonUtil.formatDate(userPo.getLastLoggedDate()));
		userDto.setPassword(userPo.getPassword());
		userDto.setUsername(userPo.getUsername());
		userDto.setIuCode(userPo.getIuCode());
		userDto.setRecCode(userPo.getRecCode());
		userDto.setSignature(userPo.getSignature());
		return userDto;

	}

	public static UserPo userDtoToPo(UserDto userDto) {

		UserPo userPo = new UserPo();
		userPo.setTelphone(userDto.getTelphone());
		userPo.setEmail(userDto.getEmail());
		userPo.setInvalid(userPo.getInvalid());
		userPo.setIsMale(userDto.getIsMale());
		userPo.setLastLoggedDate(userPo.getLastLoggedDate());
		userPo.setPassword(userDto.getPassword());
		userPo.setUsername(userDto.getUsername());
		userPo.setIuCode(userPo.getIuCode());
		userPo.setRecCode(userDto.getRecCode());
		userPo.setSignature(userDto.getSignature());
		return userPo;

	}
	public static ArticlePo articleDtoToPo(ArticleDto articleDto) {

		ArticlePo articlePo = new ArticlePo();
		
		articlePo.setContent(articleDto.getContent());
		articlePo.setDescription(articleDto.getDescription());
		//articlePo.setKeyword(articleDto.get);
		articlePo.setTitle(articleDto.getTitle());
		articlePo.setUsername(articleDto.getUsername());
		articlePo.setUuid(articleDto.getUuid());

		return articlePo;

	}
	public static ArticleDto articlePoToDto(ArticlePo articlePo) {

		ArticleDto articleDto = new ArticleDto();
		
		articleDto.setContent(articlePo.getContent());
		articleDto.setDescription(articlePo.getDescription());
		//articlePo.setKeyword(articlePo.get);
		articleDto.setTitle(articlePo.getTitle());
		articleDto.setUsername(articlePo.getUsername());
		articleDto.setUuid(articlePo.getUuid());
		
		articleDto.setCreateDate(CommonUtil.formatDate(articlePo.getCreateDate()));
		articleDto.setLastmodify(CommonUtil.formatDate(articlePo.getLastModifyDate()));

		return articleDto;

	}
	public static  List<ArticleDto> articlePoToDto(List<ArticlePo> articles) {
		if(articles==null){
			return null;
		}
		List<ArticleDto> results = Lists.newArrayList();
		for(ArticlePo apo:articles){
			results.add(articlePoToDto(apo));
		}
		return results;
	}

	public static ImageDto imagePoToDto(ImagePo imagePo) {
		ImageDto imageDto = new ImageDto();
		imageDto.setBaseNum(imagePo.getBaseNum());
		imageDto.setBaseUri(imagePo.getBaseUri());
		imageDto.setCreateDate(imagePo.getCreateDate());
		imageDto.setDescription(imagePo.getDesc());
		imageDto.setLinkTo(imagePo.getLinkTo());
		imageDto.setUsername(imagePo.getUsername());
		imageDto.setImageName(imagePo.getImagename());
		return imageDto;
	}
}
