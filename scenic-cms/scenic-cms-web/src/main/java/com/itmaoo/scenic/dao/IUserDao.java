package com.itmaoo.scenic.dao;

import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.UserQuery;

public interface IUserDao extends IBaseDao<UserPo>{

	UserPo selectSingleByIucode(UserQuery iucode);
	UserPo selectSingleLogin(UserQuery iucode);
	UserPo selectByUsername(UserQuery iucode);
	
	int update(UserPo iucode);
	int updatePassword(UserPo userPo);
	int updatePayImgUrl(UserPo userPo);
	
}
