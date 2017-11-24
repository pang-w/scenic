package com.itmaoo.oa.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmaoo.oa.dao.IProductDao;
import com.itmaoo.oa.dao.IUserDao;
import com.itmaoo.oa.entity.po.ProductPo;
import com.itmaoo.oa.entity.po.UserPo;
import com.itmaoo.oa.entity.query.ProductQuery;
import com.itmaoo.oa.entity.query.UserQuery;
import com.itmaoo.oa.entity.vo.ProductVo;
import com.itmaoo.oa.entity.vo.ResponseData;
import com.itmaoo.oa.entity.vo.UserVo;
import com.itmaoo.oa.support.CommonUtil;
import com.itmaoo.oa.support.EntityUtil;

@Controller
@RequestMapping(value = "/action/pro/")
public class ProAction extends BaseAction {

  @Autowired
  private IUserDao userDao;
  @Autowired
  private IProductDao proDao;

  @ResponseBody
  @RequestMapping("add")
  public ResponseData regest(HttpServletRequest request, @RequestBody ProductVo proVo) {
    
    ResponseData rd = new ResponseData();
    if (StringUtils.isEmpty(proVo.getCaseId())) {
      rd.setStatus("5006");
      rd.setMsg("病理唯一编号不能为空");
      return rd;
    }
   /* ProductPo prductPo = proDao.selectSingleByCaseId(proVo.getCaseId());
    if(prductPo!=null){
      rd.setStatus("5006");
      rd.setMsg("病理唯一编号已存在");
      return rd;
    }*/
    UserVo logedUser = getLogedUser(request);
    if (logedUser != null) {
      ProductPo poForSave = EntityUtil.productVoToPo(proVo);
      int count = proDao.insert(poForSave);
      if(count!=1){
        rd.setStatus("5006");
        rd.setMsg("保存失败");
        return rd;
      }
    } else {
      rd.setStatus("3001");
      rd.setMsg("未登录");
    }
    return rd;

  }

  @ResponseBody
  @RequestMapping("update")
  public ResponseData update(HttpServletRequest request, @RequestBody UserVo userDto) {
    ResponseData rd = new ResponseData();
    if (StringUtils.isEmpty(userDto.getSignature()) || userDto.getSignature().length() < 10
        || userDto.getSignature().length() > 80) {
      rd.setStatus("5006");
      rd.setMsg("签名10到80个字符");
      return rd;
    }
    UserVo logedUser = getLogedUser(request);
    if (logedUser != null) {
      UserPo userPo = new UserPo();
      userPo.setUsername(logedUser.getUsername());
      userPo.setNickname(userDto.getNickname());
      userPo.setSignature(userDto.getSignature());
      userPo.setLastModifyDate(new Date());
      userDao.update(userPo);
      resetLogedUser(request, logedUser);
    } else {
      rd.setStatus("4001");
      rd.setMsg("未登录");
    }
    return rd;

  }

  @ResponseBody
  @RequestMapping("changepassword")
  public ResponseData changepassword(HttpServletRequest request, @RequestBody UserVo userDto) {
    ResponseData rd = new ResponseData();
    if (StringUtils.isEmpty(userDto.getPassword()) || userDto.getPassword().length() < 5) {
      rd.setStatus("5004");
      rd.setMsg("密码必须为5位以上的字符");
      return rd;
    }
    if (!userDto.getPassword().equals(userDto.getConfirmMassword())) {
      rd.setStatus("5004");
      rd.setMsg("密码不一致");
      return rd;
    }
    UserVo logedUser = getLogedUser(request);
    if (logedUser != null) {
      UserPo userPo = new UserPo();
      userPo.setUsername(logedUser.getUsername());
      userPo.setPassword(userDto.getPassword());
      userPo.setLastModifyDate(new Date());
      userDao.updatePassword(userPo);
    } else {
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
    if (loggedUser == null) {
      rd.setStatus("5021");
      rd.setMsg("用户名与密码不匹配");
      return rd;
    } else {
      UserVo userInfo = EntityUtil.userPoToVo(loggedUser);
      userInfo.setPassword(null);
      request.getSession().setAttribute("loggedUser", userInfo);
      rd.setData(userInfo);
      return rd;
    }

  }
  @ResponseBody
  @RequestMapping("list")
  public ResponseData list(HttpServletRequest request, @RequestBody ProductVo proVo) {
    
    ResponseData rd = new ResponseData();
  /*  if (StringUtils.isEmpty(proVo.getCaseId())) {
      rd.setStatus("5006");
      rd.setMsg("病理唯一编号不能为空");
      return rd;
    }*/
   /* ProductPo prductPo = proDao.selectSingleByCaseId(proVo.getCaseId());
    if(prductPo!=null){
      rd.setStatus("5006");
      rd.setMsg("病理唯一编号已存在");
      return rd;
    }*/
    UserVo logedUser = getLogedUser(request);
   // if (logedUser != null) {
      List<ProductPo> count = proDao.selectList(new ProductQuery());
      rd.setData(count);
   // } else {
  //    rd.setStatus("3001");
  //    rd.setMsg("未登录");
  //  }
    return rd;

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
      rd.setStatus("4004");
      rd.setMsg("未登录");
    } else {
      request.getSession().setAttribute("loggedUser", user);
    }
    rd.setData(user);
    return rd;
  }

}
