package com.itmaoo.scenic.dao;

import com.itmaoo.scenic.entity.po.ArticleProductLinkPo;

public interface IArticleProductLinkDao extends IBaseDao<ArticleProductLinkPo>{

	int disableTagsByArticleUuid(String uuid);

	int enableTagById(Integer id);
	

}
