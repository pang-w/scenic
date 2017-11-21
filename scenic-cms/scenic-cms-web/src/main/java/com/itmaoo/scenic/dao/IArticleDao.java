package com.itmaoo.scenic.dao;

import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.query.ArticleQuery;

public interface IArticleDao extends IBaseDao<ArticlePo>{
	int updatePublishStatus(ArticlePo articlePo);
	
	ArticlePo selectSingleById(ArticleQuery articleQuery);
}
