package com.itmaoo.scenic.dao;

import java.util.List;

import com.itmaoo.scenic.entity.dto.BaseQuery;

public interface IBaseDao<T> {

	int insert(T entity);

	int updateByUniqueKey(T entity);

	List<T> selectList(BaseQuery query);
	
	List<T> pagingSelectList(BaseQuery query);
	
	List<T> select4Update(BaseQuery query);

	T selectSingle(BaseQuery query);
	
	int selectCount(BaseQuery query);

	int selectSequence();
	
	int deleteByUniqueKey(String key);
	
}
