package com.itmaoo.scenic.action.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmaoo.scenic.action.base.BaseActiom;
import com.itmaoo.scenic.dao.IProductDao;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.support.EntityUtil;

@Controller
@RequestMapping(value = "/action/user/product/")
public class ProductUserAction extends BaseActiom {
	@Autowired
	private IProductDao proDao;


	@RequestMapping("add")
	@ResponseBody
	public ResponseData add(HttpServletRequest request, @RequestBody ProductDto productDto) {

		ResponseData rd = new ResponseData();

		UserDto user = getLogedUser(request);
		if (user == null) {
			rd.setMsg("未登录");
			rd.setStatus("4000");
		} else {
			ProductPo proPo = EntityUtil.productDtoToPo(productDto); 
			proPo.setUsername(user.getUsername());
			proDao.insert(proPo);
			

		}

		return rd;

	}

}
