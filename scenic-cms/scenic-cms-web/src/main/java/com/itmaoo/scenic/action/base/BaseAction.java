package com.itmaoo.scenic.action.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.itmaoo.scenic.dao.IUserDao;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.UserQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;

public class BaseAction {
	@Autowired
	private IUserDao userDao;
	
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
	public void resetLogedUser(HttpServletRequest request,UserDto loggedUser){
		
		UserQuery uquery = new UserQuery();
		uquery.setUsername(loggedUser.getUsername());
		UserPo userDb = userDao.selectByUsername(uquery);
		UserDto userInfo = EntityUtil.userPoToDto(userDb);
		
		request.getSession().setAttribute("loggedUser", userInfo);
	}
	public IUserDao getUserDao() {
		return userDao;
	}

}
