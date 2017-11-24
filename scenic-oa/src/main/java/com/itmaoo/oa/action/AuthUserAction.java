package com.itmaoo.oa.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmaoo.oa.dao.IUserDao;
import com.itmaoo.oa.entity.po.UserPo;
import com.itmaoo.oa.entity.query.UserQuery;
import com.itmaoo.oa.entity.vo.ResponseData;
import com.itmaoo.oa.entity.vo.UserVo;
import com.itmaoo.oa.support.CommonUtil;
import com.itmaoo.oa.support.EntityUtil;



@Controller
@RequestMapping(value = "/action/user/")
public class AuthUserAction extends BaseAction {

	@Autowired
	private IUserDao userDao;
	@ResponseBody
	@RequestMapping("regist")
	public ResponseData regest(HttpServletRequest request, @RequestBody UserVo userDto) {
		UserQuery query = new UserQuery();
		query.setUsername(userDto.getUsername());
		UserPo userDb = userDao.selectByUsername(query);
		UserVo userRegested = new UserVo();
		ResponseData rd = new ResponseData();
		
		if (StringUtils.isEmpty(userDto.getUsername())||
				userDto.getUsername().length()<5) {
			rd.setStatus("5003");
			rd.setMsg("用户名必须为5位以上的字符");
			return rd;
		}
		if (StringUtils.isEmpty(userDto.getNickname())||
				userDto.getPassword().length()<6) {
			rd.setStatus("5004");
			rd.setMsg("昵称不能为空");
			return rd;
		}
		if (StringUtils.isEmpty(userDto.getPassword())||
				userDto.getPassword().length()<6) {
			rd.setStatus("5004");
			rd.setMsg("密码必须为5位以上的字符");
			return rd;
		}else if(!userDto.getPassword().equals(userDto.getConfirmMassword())){
			rd.setStatus("5004");
			rd.setMsg("两次密码输入不一致");
		}
		if (StringUtils.isEmpty(userDto.getRecCode())||
				userDto.getRecCode().length()!=8) {
			//izHd6HDy
			rd.setStatus("5005");
			rd.setMsg("推荐码不正确");
			return rd;
		}
		if (StringUtils.isEmpty(userDto.getSignature())||
				userDto.getSignature().length()<10 ||
				userDto.getSignature().length()>80) {
			rd.setStatus("5006");
			rd.setMsg("签名10到80个字符");
			return rd;
		}
		if (userDb == null) {
			UserQuery recQuery = new UserQuery();
			recQuery.setIuCode(userDto.getRecCode());
			UserPo userRec = userDao.selectSingleByIucode(recQuery);
			if (userRec != null) {
				
				UserPo userPo = new UserPo();
				userPo.setTelphone(userDto.getTelphone());
				userPo.setCreateDate(new Date());
				userPo.setEmail(userDto.getEmail());
				userPo.setInvalid(false);
				userPo.setIsMale(userDto.getIsMale());
				userPo.setLastLoggedDate(null);
				userPo.setPassword(userDto.getPassword());
				userPo.setUsername(userDto.getUsername());
				userPo.setIuCode(CommonUtil.generateShortUuid());
				userPo.setRecCode(userDto.getRecCode());
				userPo.setSignature(userDto.getSignature());
				userDao.insert(userPo);

				userRegested.setUsername(userDto.getUsername());
				rd.setData(userRegested);
				if (getLogedUser(request) == null) {
					userDto.setPassword(null);
					request.getSession().setAttribute("loggedUser", userDto);
				}
			} else {
				rd.setStatus("5002");
				rd.setMsg("推荐码不存在");
			}

		} else {
			rd.setStatus("5001");
			rd.setMsg("用户名已被注册，请尝试其他用户名。");
		}
		return rd;

	}
	@ResponseBody
	@RequestMapping("update")
	public ResponseData update(HttpServletRequest request, @RequestBody UserVo userDto) {
		ResponseData rd = new ResponseData();
		if (StringUtils.isEmpty(userDto.getSignature())||
				userDto.getSignature().length()<10 ||
				userDto.getSignature().length()>80) {
			rd.setStatus("5006");
			rd.setMsg("签名10到80个字符");
			return rd;
		}
		UserVo logedUser = getLogedUser(request);
		if(logedUser!=null){
			UserPo userPo = new UserPo();
			userPo.setUsername(logedUser.getUsername());
			userPo.setNickname(userDto.getNickname());
			userPo.setSignature(userDto.getSignature());
			userPo.setLastModifyDate(new Date());
			userDao.update(userPo);
			resetLogedUser(request, logedUser);
		}else{
			rd.setStatus("4001");
			rd.setMsg("未登录");
		}
		return rd;

	}
	@ResponseBody
	@RequestMapping("changepassword")
	public ResponseData changepassword(HttpServletRequest request, @RequestBody UserVo userDto) {
		ResponseData rd = new ResponseData();
		if (StringUtils.isEmpty(userDto.getPassword())||
				userDto.getPassword().length()<5) {
			rd.setStatus("5004");
			rd.setMsg("密码必须为5位以上的字符");
			return rd;
		}
		if(!userDto.getPassword().equals(userDto.getConfirmMassword())){
			rd.setStatus("5004");
			rd.setMsg("密码不一致");
			return rd;
		}
		UserVo logedUser = getLogedUser(request);
		if(logedUser!=null){
			UserPo userPo = new UserPo();
			userPo.setUsername(logedUser.getUsername());
			userPo.setPassword(userDto.getPassword());
			userPo.setLastModifyDate(new Date());
			userDao.updatePassword(userPo);
		}else{
			rd.setStatus("4001");
			rd.setMsg("未登录");
		}
		return rd;

	}
	
	@ResponseBody
	@RequestMapping("login")
	public ResponseData loginUser(HttpServletRequest request, @RequestBody UserVo userDto) {
		
		
		ResponseData rd = new ResponseData();
		
		
		UserQuery recQuery = new UserQuery();
		recQuery.setUsername(userDto.getUsername());
		recQuery.setPassword(userDto.getPassword());
		
		UserPo loggedUser = userDao.selectSingleLogin(recQuery);
		if(loggedUser==null){
			rd.setStatus("3001");
			rd.setMsg("用户名与密码不匹配");
			return rd;
		}else{
			UserVo userInfo = EntityUtil.userPoToVo(loggedUser);
			userInfo.setPassword(null);
			request.getSession().setAttribute("loggedUser", userInfo);
			rd.setData(userInfo);
			return rd;
		}

	}

	@ResponseBody
	@RequestMapping("logout")
	public ResponseData logout(HttpServletRequest request) {
		ResponseData rd = new ResponseData();
		request.getSession().setAttribute("loggedUser", null);
		return rd;

	}

	@ResponseBody
	@RequestMapping("loginUser")
	public ResponseData checkLogged(HttpServletRequest request) {
		UserVo user = getLogedUser(request);
		ResponseData rd = new ResponseData();
		if (user == null) {
			rd.setStatus("3001");
			rd.setMsg("未登录");
		} else {
			request.getSession().setAttribute("loggedUser", user);
		}
		rd.setData(user);
		return rd;
	}

}
