package com.itmaoo.scenic.dao;

import java.util.List;

import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.query.ProductQuery;

public interface IProductDao extends IBaseDao<ProductPo>{
	ProductPo selectById(ProductQuery productQuery);
	List<ProductPo> selectListByArticleUuid(ProductQuery productQuery);
	

	int updateById(ProductPo proPo);

}
