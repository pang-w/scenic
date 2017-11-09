package com.itmaoo.scenic.action.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmaoo.scenic.action.base.BaseActiom;
import com.itmaoo.scenic.dao.ISignatureLikeDao;
import com.itmaoo.scenic.dao.IUserDao;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.SignatureLikeDto;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.SignatureLikePo;

@Controller
@RequestMapping(value = "/action/user/signature/")
public class SignatureUserAction extends BaseActiom{
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private ISignatureLikeDao signatureLikeDao;

	@RequestMapping("likeSignature")
	@ResponseBody
	public ResponseData likeSignature(HttpServletRequest request, @RequestBody SignatureLikeDto signatureLikeDto) {

		UserDto user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		} else {
			SignatureLikePo entity = new SignatureLikePo();
			entity.setBelikedUser(signatureLikeDto.getBelikedUser());
			entity.setActionUser(user.getUsername());
			entity.setCreateDate(new Date());
			signatureLikeDao.insert(entity);
		}
		rd.setData(user);
		return rd;    

	}
	
}
