package com.itmaoo.oa.support;

import java.util.Date;

import com.itmaoo.oa.entity.po.ProductPo;
import com.itmaoo.oa.entity.po.UserPo;
import com.itmaoo.oa.entity.vo.ProductVo;
import com.itmaoo.oa.entity.vo.UserVo;


public class EntityUtil {
	public static UserVo userPoToDto(UserPo userPo) {

		UserVo userDto = new UserVo();
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

	public static UserPo userDtoToPo(UserVo userDto) {

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

	public static ProductPo productDtoToPo(ProductVo productDto) {
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

	public static ProductVo productPoToDto(ProductPo productPo) {
	  ProductVo productDto = new ProductVo();
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

}
