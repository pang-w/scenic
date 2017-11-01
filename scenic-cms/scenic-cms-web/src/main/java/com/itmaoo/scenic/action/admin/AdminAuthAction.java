package com.itmaoo.scenic.action.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmaoo.scenic.dao.IAdminDao;
import com.itmaoo.scenic.entity.po.Admin;
import com.itmaoo.scenic.entity.query.BaseQuery;

@Controller
public class AdminAuthAction {
	@Autowired
	private IAdminDao adminDao;
	//@RequestMapping(value = "/action/user/loginUser",method = RequestMethod.GET)
	@RequestMapping("/admin/login")
	public String loginUser() {
		
		List<Admin> list = adminDao.selectList(new BaseQuery());
		
		System.out.println(list.size());
		
		return "Amdin";

	}
}
