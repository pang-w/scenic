package com.itmaoo.scenic.dao;

import com.itmaoo.scenic.entity.po.ArticleLikePo;
import com.itmaoo.scenic.entity.query.ArticleLikeQuery;

public interface IArticleLikeDao extends IBaseDao<ArticleLikePo>{
	
	public Integer countByLikedUser(ArticleLikeQuery belikeduser);

}
