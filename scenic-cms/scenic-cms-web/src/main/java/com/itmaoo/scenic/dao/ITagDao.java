package com.itmaoo.scenic.dao;

import java.util.List;

import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.po.TagPo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.ProductQuery;
import com.itmaoo.scenic.entity.query.TagQuery;
import com.itmaoo.scenic.entity.query.UserQuery;

public interface ITagDao extends IBaseDao<TagPo>{

	TagPo selectSingleByValue(TagQuery tagQuery);
	List<TagPo> selectListByArticleUuid(TagQuery tagQuery);
	
	
}
