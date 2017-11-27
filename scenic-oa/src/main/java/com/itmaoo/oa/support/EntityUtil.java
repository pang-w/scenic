package com.itmaoo.oa.support;

import java.util.Date;

import com.itmaoo.oa.entity.po.ProductPo;
import com.itmaoo.oa.entity.po.UserPo;
import com.itmaoo.oa.entity.vo.ProductVo;
import com.itmaoo.oa.entity.vo.UserVo;


public class EntityUtil {
	public static UserVo userPoToVo(UserPo userPo) {

		UserVo userVo = new UserVo();
		userVo.setNickname(userPo.getNickname());
		userVo.setTelphone(userPo.getTelphone());
		userVo.setCreateDate(CommonUtil.formatDate(userPo.getCreateDate()));
		userVo.setEmail(userPo.getEmail());
		userVo.setInvalid(userPo.getInvalid());
		userVo.setIsMale(userPo.getIsMale());
		userVo.setLastLoggedDate(CommonUtil.formatDate(userPo.getLastLoggedDate()));
		userVo.setPassword(userPo.getPassword());
		userVo.setUsername(userPo.getUsername());
		userVo.setIuCode(userPo.getIuCode());
		userVo.setRecCode(userPo.getRecCode());
		userVo.setSignature(userPo.getSignature());
		userVo.setSignatureLikedCount(userPo.getSignatureLikedCount());
		return userVo;

	}

	public static UserPo userVooToPo(UserVo userVo) {

		UserPo userPo = new UserPo();
		userPo.setTelphone(userVo.getTelphone());
		userPo.setEmail(userVo.getEmail());
		userPo.setInvalid(userPo.getInvalid());
		userPo.setIsMale(userVo.getIsMale());
		userPo.setLastLoggedDate(userPo.getLastLoggedDate());
		userPo.setPassword(userVo.getPassword());
		userPo.setUsername(userVo.getUsername());
		userPo.setIuCode(userPo.getIuCode());
		userPo.setRecCode(userVo.getRecCode());
		userPo.setSignature(userVo.getSignature());
		
		return userPo;

	}

	public static ProductPo productVoToPo(ProductVo productVo) {
		ProductPo proPo = new ProductPo();
		proPo.setId(productVo.getId());
		proPo.setDescription(productVo.getDescription());
		proPo.setLastModifyDate(new Date());
		
		proPo.setAge(productVo.getAge());
		proPo.setCaseId(productVo.getCaseId());
		proPo.setDoctor(productVo.getDoctor());
		proPo.setName(productVo.getName());
		proPo.setDepartment(productVo.getDepartment());
		//
		proPo.setReportDate(new Date());
		proPo.setSendDate(new Date());
		proPo.setSex(productVo.getSex());
		proPo.setTestResault(productVo.getTestResault());
		proPo.setTestType(productVo.getTestType());
		return proPo;
	}

	public static ProductVo productPoToVo(ProductPo productPo) {
	  ProductVo proVo = new ProductVo();
	  proVo.setId(productPo.getId());
	//  proVo.setCreateDate(new Date());
	  proVo.setDescription(productPo.getDescription());
	 // proVo.setLastModifyDate(new Date());
    
	  proVo.setAge(productPo.getAge());
	  proVo.setCaseId(productPo.getCaseId());
	  proVo.setDoctor(productPo.getDoctor());
	  proVo.setName(productPo.getName());
	  proVo.setDepartment(productPo.getDepartment());
    //
	//  proVo.setReportDate(reportDate);
	  proVo.setSendDate(CommonUtil.formatDate(productPo.getSendDate()));
	  proVo.setReportDate(CommonUtil.formatDate(productPo.getReportDate()));
	  proVo.setSex(productPo.getSex());
	  proVo.setTestResault(productPo.getTestResault());
	  proVo.setTestType(productPo.getTestType());
		return proVo;
	}

}
