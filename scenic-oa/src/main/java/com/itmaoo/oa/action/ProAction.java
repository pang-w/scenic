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
import com.itmaoo.oa.dao.IProductDao;
import com.itmaoo.oa.dao.IUserDao;
import com.itmaoo.oa.entity.PagingData;
import com.itmaoo.oa.entity.po.ProductPo;
import com.itmaoo.oa.entity.po.UserPo;
import com.itmaoo.oa.entity.query.ProductQuery;
import com.itmaoo.oa.entity.query.UserQuery;
import com.itmaoo.oa.entity.vo.ProductVo;
import com.itmaoo.oa.entity.vo.ResponseData;
import com.itmaoo.oa.entity.vo.UserVo;
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
    if (StringUtils.isEmpty(proVo.getName())) {
      rd.setStatus("5006");
      rd.setMsg("姓名不能为空");
      return rd;
    }
    if (StringUtils.isEmpty(proVo.getCaseId())) {
      rd.setStatus("5006");
      rd.setMsg("手机号不能为空");
      return rd;
    }
    ProductPo prductPo = proDao.selectSingleByCaseId(proVo.getCaseId());
    if (prductPo != null) {
      rd.setStatus("5006");
      rd.setMsg("手机号:"+proVo.getCaseId()+"姓名："+prductPo.getName()+" 已报名");
      return rd;
    }
  //  UserVo logedUser = getLogedUser(request);
   // if (logedUser != null) {
      ProductPo poForSave = EntityUtil.productVoToPo(proVo);
      int count = proDao.insert(poForSave);
      if (count != 1) {
        rd.setStatus("5006");
        rd.setMsg("保存失败");
        return rd;
      }
   // } else {
     // rd.setStatus("3001");
    //  rd.setMsg("未登录");
  //  }
    return rd;

  }

  @ResponseBody
  @RequestMapping("update")
  public ResponseData update(HttpServletRequest request, @RequestBody ProductVo proVo) {
    ResponseData rd = new ResponseData();
    if (StringUtils.isEmpty(proVo.getCaseId())) {
      rd.setStatus("5006");
      rd.setMsg("人员唯一编号不能为空");
      return rd;
    }

    UserVo logedUser = getLogedUser(request);
    if (logedUser != null) {
      ProductPo prductPo = proDao.selectSingleByCaseId(proVo.getCaseId());
      if (prductPo == null) {
        rd.setStatus("5006");
        rd.setMsg("不存在的人员编号");
        return rd;
      }
      ProductPo poForSave = EntityUtil.productVoToPo(proVo);
      int count = proDao.updateByCaseId(poForSave);
      if (count != 1) {
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
  public ResponseData list(HttpServletRequest request, @RequestBody ProductVo productVo) {

    ResponseData rd = new ResponseData();
    UserVo logedUser = getLogedUser(request);
    if (logedUser != null) {

      ProductQuery query = new ProductQuery();
      query.setId(productVo.getId());
      query.setDescription(productVo.getDescription());
      // query.setLastModifyDate(new Date());

      query.setAge(productVo.getAge());
      query.setCaseId(productVo.getCaseId());
      query.setDoctor(productVo.getDoctor());
      query.setName(productVo.getName());
      query.setDepartment(productVo.getDepartment());
      //
      query.setReportDateStart(productVo.getReportDateStart());
      query.setReportDateEnd(productVo.getReportDateEnd());
      query.setSendDateStart(productVo.getSendDateStart());
      query.setSendDateEnd(productVo.getSendDateEnd());
      query.setSex(productVo.getSex());
      query.setTestResault(productVo.getTestResault());
      query.setTestType(productVo.getTestType());
      if (productVo.getPageIndex() != null) {
        query.setPageIndex(productVo.getPageIndex());
      }
      List<ProductPo> proPos = proDao.selectList(query);
      List<ProductVo> proVos = Lists.newArrayList();
      if (proPos != null) {
        for (ProductPo po : proPos) {
          proVos.add(EntityUtil.productPoToVo(po));
        }
      }

      PagingData<ProductVo> pagingData = new PagingData<>();
      pagingData.setDataList(proVos);
      pagingData.setPageIndex(query.getPageIndex());// 设置当前页
      pagingData.setPageSize(query.getPageSize());// 设置一页多少条数据
      int count = proDao.selectListCount(query);

      pagingData.setTotalCount(count);// 设置总数量
      pagingData.setTotalPage(count, query.getPageSize());// 设置总共多少页

      rd.setData(pagingData);
    } else {
      rd.setStatus("3001");
      rd.setMsg("未登录");
    }
    return rd;

  }

  @ResponseBody
  @RequestMapping("remove")
  public ResponseData remove(HttpServletRequest request, @RequestBody ProductVo productVo) {

    ResponseData rd = new ResponseData();
    UserVo logedUser = getLogedUser(request);
    if (logedUser != null) {

      ProductQuery query = new ProductQuery();

      query.setCaseId(productVo.getCaseId());

      Integer count = proDao.deleteByCaseId(productVo.getCaseId());
      if (count < 1) {
        rd.setStatus("5001");
        rd.setMsg("删除失败");
        return rd;
      }
    } else {
      rd.setStatus("3001");
      rd.setMsg("未登录");
    }
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
