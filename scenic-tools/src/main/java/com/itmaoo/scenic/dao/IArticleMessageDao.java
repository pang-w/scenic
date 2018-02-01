package com.itmaoo.scenic.dao;

import java.util.List;

import com.itmaoo.scenic.entity.po.ArticleMessagePo;
import com.itmaoo.scenic.entity.query.ArticleMessageQuery;

public interface IArticleMessageDao extends IBaseDao<ArticleMessagePo>{
	List<ArticleMessagePo> selectByUuid(ArticleMessageQuery messageQuery);

	int updateById(ArticleMessagePo proPo);

}
