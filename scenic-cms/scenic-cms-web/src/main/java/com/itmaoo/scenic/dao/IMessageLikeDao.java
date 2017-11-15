package com.itmaoo.scenic.dao;

import com.itmaoo.scenic.entity.po.MessageLikePo;
import com.itmaoo.scenic.entity.query.MessageLikeQuery;

public interface IMessageLikeDao extends IBaseDao<MessageLikePo>{
	
	public Integer countByLikedUser(MessageLikeQuery belikeduser);

}
