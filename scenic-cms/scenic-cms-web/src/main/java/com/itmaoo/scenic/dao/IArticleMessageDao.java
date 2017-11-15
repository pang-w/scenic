package com.itmaoo.scenic.dao;

import com.itmaoo.scenic.entity.po.ArticleMessagePo;
import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.query.ArticleMessageQuery;

public interface IArticleMessageDao extends IBaseDao<ArticleMessagePo>{
	ProductPo selectById(ArticleMessageQuery messageQuery);

	int updateById(ArticleMessagePo proPo);

}
