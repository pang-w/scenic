package com.itmaoo.scenic.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmaoo.scenic.action.base.BaseActiom;
import com.itmaoo.scenic.dao.IAdminDao;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.Admin;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.BaseQuery;

@Controller
@RequestMapping(value = "/action/user/auth")
public class AuthAction extends BaseActiom {
	@Autowired
	private IAdminDao adminDao;

	@ResponseBody
	@RequestMapping("login")
	public ResponseData loginUser(HttpServletRequest request, @RequestBody UserDto userDto) {

		List<Admin> list = adminDao.selectList(new BaseQuery());

		System.out.println(list.size());
		UserDto user = new UserDto();
		user.setUsername(userDto.getUsername());
		ResponseData rd = new ResponseData();
		rd.setData(user);
		request.getSession().setAttribute("loggedUser", user);
		return rd;

	}

	@ResponseBody
	@RequestMapping("logout")
	public ResponseData logout(HttpServletRequest request ) {
		ResponseData rd = new ResponseData();
		request.getSession().setAttribute("loggedUser", null);
		return rd;

	}

	@ResponseBody
	@RequestMapping("checkLogged")
	public ResponseData checkLogged(HttpServletRequest request) {
		UserDto user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		} else {
			request.getSession().setAttribute("loggedUser", user);
		}
		rd.setData(user);
		return rd;
	}

}
