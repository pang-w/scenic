package com.itmaoo.scenic.action.base;

import javax.servlet.http.HttpServletRequest;

import com.itmaoo.scenic.entity.dto.UserDto;

public class BaseAction {
	public boolean islogged(HttpServletRequest req){
		if(getLogedUser(req)!=null){
			return true;
		}
		return false;
	}
	public UserDto getLogedUser(HttpServletRequest request){
		UserDto userPo = (UserDto) request.getSession().getAttribute("loggedUser");
		return userPo;
	}

}
