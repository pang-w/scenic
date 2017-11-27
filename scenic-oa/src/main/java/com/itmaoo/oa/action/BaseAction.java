package com.itmaoo.oa.action;

import javax.servlet.http.HttpServletRequest;

import com.itmaoo.oa.dao.IUserDao;
import com.itmaoo.oa.entity.vo.UserVo;

public class BaseAction {


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


}
