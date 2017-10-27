package com.itmaoo.scenic.action.base;

import com.itmaoo.scenic.entity.po.UserPo;

public class BaseActiom {
	public UserPo getLogedUser(){
		UserPo userPo= new UserPo();
		userPo.setUsername("test");
		return userPo;
	}

}
