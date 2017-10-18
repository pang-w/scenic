package com.itmaoo.scenic.action.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginAction {
	//@RequestMapping(value = "/action/user/loginUser",method = RequestMethod.GET)
	//@RequestMapping("/action/user/loginUser")
	public String loginUser() {
		return "Amdin";

	}
}
