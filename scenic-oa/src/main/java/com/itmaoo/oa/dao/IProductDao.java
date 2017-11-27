package com.itmaoo.oa.dao;

import com.itmaoo.oa.entity.po.ProductPo;
import com.itmaoo.oa.entity.query.ProductQuery;



public interface IProductDao extends IBaseDao<ProductPo>{
	ProductPo selectById(ProductQuery productQuery);

	int updateByCaseId(ProductPo proPo);

	ProductPo selectSingleByCaseId(String caseId);

  int selectListCount(ProductQuery query);

  int deleteByCaseId(String caseId);


}
