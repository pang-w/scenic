package com.itmaoo.scenic.dao;

import com.itmaoo.scenic.entity.po.ArticleTagLinkPo;

public interface IArticleTagLinkDao extends IBaseDao<ArticleTagLinkPo>{
	
	int disableTagsByArticleUuid(String uuid);

	int enableTagById(Integer id);
}
