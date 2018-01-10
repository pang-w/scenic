package com.itmaoo.scenic.dao;

import com.itmaoo.scenic.entity.po.SignatureLikePo;
import com.itmaoo.scenic.entity.query.SignatureLikeQuery;

public interface ISignatureLikeDao extends IBaseDao<SignatureLikePo>{
	
	public Integer countByLikedUser(SignatureLikeQuery belikeduser);

}
