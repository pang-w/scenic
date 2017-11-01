package com.itmaoo.scenic.action.base;

import javax.servlet.http.HttpServletRequest;

import com.itmaoo.scenic.entity.dto.UserDto;

public class BaseActiom {
	public boolean islogged(HttpServletRequest req){
		if(getLogedUser(req)!=null){
			return true;
		}
		return false;
	}
	public UserDto getLogedUser(HttpServletRequest req){
		UserDto userPo = (UserDto) req.getSession().getAttribute("loggedUser");
		return userPo;
	}

}
