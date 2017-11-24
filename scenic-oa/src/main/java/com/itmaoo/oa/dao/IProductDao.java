package com.itmaoo.oa.dao;

import com.itmaoo.oa.entity.po.ProductPo;
import com.itmaoo.oa.entity.query.ProductQuery;



public interface IProductDao extends IBaseDao<ProductPo>{
	ProductPo selectById(ProductQuery productQuery);

	int updateById(ProductPo proPo);

	ProductPo selectSingleByCaseId(String caseId);

}
