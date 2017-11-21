package com.itmaoo.scenic.entity.support;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ArticleMessageDto;
import com.itmaoo.scenic.entity.dto.ImageDto;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.TagDto;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticleMessagePo;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.ImagePo;
import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.po.TagPo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.support.CommonUtil;

public class EntityUtil {
	public static UserDto userPoToDto(UserPo userPo) {

		UserDto userDto = new UserDto();
		userDto.setNickname(userPo.getNickname());
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
		userDto.setSignatureLikedCount(userPo.getSignatureLikedCount());
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

	public static ProductPo productDtoToPo(ProductDto productDto) {
		ProductPo proPo = new ProductPo();
		proPo.setId(productDto.getId());
		proPo.setCreateDate(new Date());
		proPo.setDescription(productDto.getDescription());
		proPo.setImgUrl(productDto.getImgUrl());
		proPo.setLastModifyDate(new Date());
		proPo.setName(productDto.getName());
		proPo.setPrice(productDto.getPrice());
		proPo.setLinkTo(productDto.getLinkTo());
		return proPo;
	}

	public static ProductDto productPoToDto(ProductPo productPo) {
		ProductDto productDto = new ProductDto();
		productDto.setId(productPo.getId());
		productDto.setCreateDate(CommonUtil.formatDate(productPo.getCreateDate()));
		productDto.setDescription(productPo.getDescription());
		productDto.setImgUrl(productPo.getImgUrl());
		productDto.setLastModifyDate(CommonUtil.formatDate(productPo.getLastModifyDate()));
		productDto.setName(productPo.getName());
		productDto.setPrice(productPo.getPrice());
		productDto.setLinkTo(productPo.getLinkTo());
		return productDto;
	}

	public static ArticleMessageDto articleMessagePoToDto(ArticleMessagePo a) {
		ArticleMessageDto dto = new ArticleMessageDto();
		dto.setLikedCount(a.getLikedCount());
		dto.setActionUser(a.getActionUser());
		dto.setArticleUuid(a.getArticleUuid());
		dto.setCreateDate(CommonUtil.formatDate(a.getCreateDate()));
		dto.setId(a.getId());
		dto.setLastModifyDate(CommonUtil.formatDate(a.getLastModifyDate()));
		dto.setMessage(a.getMessage());
		return dto;
	}

	public static TagDto tagPoToDto(TagPo tagPo) {
		TagDto tagDto = new TagDto();
		tagDto.setCreateBy(tagPo.getCreateBy());
		tagDto.setCreateDate(CommonUtil.formatDate(tagPo.getCreateDate()));
		tagDto.setDescription(tagPo.getDescription());
		tagDto.setType(tagPo.getType());
		tagDto.setId(tagPo.getId());
		tagDto.setLastModifyDate(CommonUtil.formatDate(tagPo.getLastModifyDate()));
		tagDto.setParentId(tagPo.getParentId());
		tagDto.setValue(tagPo.getValue());
		return tagDto;
	}

	public static List<TagDto> tagPoToDtoList(List<TagPo> tagsPo) {
		if(tagsPo==null){
			return null;
		}
		List<TagDto> tagDto = Lists.newArrayList();
		for(TagPo tag:tagsPo){
			tagDto.add(tagPoToDto(tag));
		}
		return tagDto;
	}
}
