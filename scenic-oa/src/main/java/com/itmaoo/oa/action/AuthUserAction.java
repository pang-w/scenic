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

import com.google.common.collect.Lists;
import com.itmaoo.oa.dao.IUserDao;
import com.itmaoo.oa.entity.PagingData;
import com.itmaoo.oa.entity.po.UserPo;
import com.itmaoo.oa.entity.query.BaseQuery;
import com.itmaoo.oa.entity.query.UserQuery;
import com.itmaoo.oa.entity.vo.ProductVo;
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
  @RequestMapping("addUser")
  public ResponseData regest(HttpServletRequest request, @RequestBody UserVo userDto) {
    ResponseData rd = new ResponseData();
    UserVo logedUser = getLogedUser(request);
    if (logedUser != null) {
      UserQuery query = new UserQuery();
      query.setUsername(userDto.getUsername());
      UserPo userDb = userDao.selectByUsername(query);
      UserVo userRegested = new UserVo();

      if (StringUtils.isEmpty(userDto.getUsername())) {
        rd.setStatus("5003");
        rd.setMsg("用户名不能为空");
        return rd;
      }
      if (userDb == null) {

        UserPo userPo = new UserPo();
        userPo.setCreateDate(new Date());
        userPo.setInvalid(false);
        userPo.setLastLoggedDate(null);
        userPo.setPassword("abc123");
        userPo.setUsername(userDto.getUsername());
        userPo.setNickname(userDto.getNickname());
        userPo.setRecCode(logedUser.getUsername());
        userDao.insert(userPo);

        userRegested.setUsername(userDto.getUsername());
        rd.setData(userRegested);
        if (getLogedUser(request) == null) {
          userDto.setPassword(null);
          request.getSession().setAttribute("loggedUser", userDto);
        }

      } else {
        rd.setStatus("5001");
        rd.setMsg("用户名已被注册，请尝试其他用户名。");
      }
    } else {
      rd.setStatus("4001");
      rd.setMsg("未登录");
    }
    return rd;

  }

  @ResponseBody
  @RequestMapping("list")
  public ResponseData list(HttpServletRequest request,@RequestBody UserVo userDto) {
    ResponseData rd = new ResponseData();

    UserVo logedUser = getLogedUser(request);
    if (logedUser != null) {
      
      UserQuery query = new UserQuery();
      if(logedUser.getUsername()!="admin"){
        query.setRecCode(logedUser.getUsername());
      }
      query.setUsername(userDto.getUsername());
      List<UserPo> userPo = userDao.selectList(query);
      List<UserVo> userVo = Lists.newArrayList();
      if(userPo!=null){
        for(UserPo u:userPo){
          userVo.add(EntityUtil.userPoToVo(u));
        }
      }
      PagingData<UserVo> pagingData = new PagingData<>();
      pagingData.setDataList(userVo);
      pagingData.setPageIndex(query.getPageIndex());// 设置当前页
      pagingData.setPageSize(query.getPageSize());// 设置一页多少条数据
      int count = userDao.selectListCount(query);
      pagingData.setTotalCount(count);// 设置总数量
      pagingData.setTotalPage(count, query.getPageSize());// 设置总共多少页
      rd.setData(pagingData);
    } else {
      rd.setStatus("4001");
      rd.setMsg("未登录");
    }
    return rd;

  }

  @ResponseBody
  @RequestMapping("update")
  public ResponseData update(HttpServletRequest request, @RequestBody UserVo userDto) {
    ResponseData rd = new ResponseData();

    UserVo logedUser = getLogedUser(request);
    if (logedUser != null) {
      UserPo userPo = new UserPo();
      userPo.setUsername(logedUser.getUsername());
      userPo.setNickname(userDto.getNickname());
      userPo.setSignature(userDto.getSignature());
      userPo.setLastModifyDate(new Date());
      userDao.update(userPo);
    } else {
      rd.setStatus("4001");
      rd.setMsg("未登录");
    }
    return rd;

  }
  @ResponseBody
  @RequestMapping("deleteUser")
  public ResponseData deleteUser(HttpServletRequest request, @RequestBody UserVo userDto) {
    ResponseData rd = new ResponseData();

    UserVo logedUser = getLogedUser(request);
    if (logedUser != null) {
      userDao.deleteByUniqueKey(userDto.getUsername());
    } else {
      rd.setStatus("4001");
      rd.setMsg("未登录");
    }
    return rd;

  }


  @ResponseBody
  @RequestMapping("changePwd")
  public ResponseData changepassword(HttpServletRequest request, @RequestBody UserVo userDto) {
    ResponseData rd = new ResponseData();
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
      rd.setStatus("3001");
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
