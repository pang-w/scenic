package com.itmaoo.scenic.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itmaoo.scenic.entity.dto.UserDto;


public class UserCtl {
	@RequestMapping(value = "do", method = RequestMethod.POST)
	public void getLogin(@RequestBody UserDto userModel) {
		if(userModel.getUsername()=="admin"){
			System.out.println("OK");
		}else{
			System.out.println("Failed");
		}

	}

}
