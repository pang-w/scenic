package com.itmaoo.scenic.action.page;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.UserDto;

@Controller
@RequestMapping(value = "/action/page/profile/")
public class ProfilePageAction extends BaseAction {
	
	@ResponseBody
	@RequestMapping("user")
	public ResponseData publicProduct(HttpServletRequest request, @RequestBody ProductDto proRequest) {
		UserDto user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		} else {
			rd.setData(user);
		}
		return rd;

	}

}
