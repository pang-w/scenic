package com.itmaoo.oa.dao;

import com.itmaoo.oa.entity.po.UserPo;
import com.itmaoo.oa.entity.query.UserQuery;

public interface IUserDao extends IBaseDao<UserPo>{

	UserPo selectSingleByIucode(UserQuery iucode);
	UserPo selectSingleLogin(UserQuery iucode);
	UserPo selectByUsername(UserQuery iucode);
	
	int update(UserPo iucode);
	int updatePassword(UserPo userPo);
	
}
