package com.itmaoo.oa.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.itmaoo.oa.dao.IProductDao;
import com.itmaoo.oa.dao.IUserDao;
import com.itmaoo.oa.entity.po.ProductPo;
import com.itmaoo.oa.entity.po.UserPo;
import com.itmaoo.oa.entity.query.ProductQuery;
import com.itmaoo.oa.entity.query.UserQuery;
import com.itmaoo.oa.entity.vo.UserVo;
import com.itmaoo.oa.support.EntityUtil;

public class BaseAction {
  @Autowired
  private IUserDao userDao;

  public boolean islogged(HttpServletRequest req) {
    if (getLogedUser(req) != null) {
      return true;
    }
    return false;
  }

  public UserVo getLogedUser(HttpServletRequest request) {
    UserVo userPo = (UserVo) request.getSession().getAttribute("loggedUser");
    return userPo;
  }

  public void resetLogedUser(HttpServletRequest request, UserVo loggedUser) {

    UserQuery uquery = new UserQuery();
    uquery.setUsername(loggedUser.getUsername());
    UserPo userDb = userDao.selectByUsername(uquery);
    UserVo userInfo = EntityUtil.userPoToDto(userDb);

    request.getSession().setAttribute("loggedUser", userInfo);
  }

  public IUserDao getUserDao() {
    return userDao;
  }

}
