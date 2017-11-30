package com.itmaoo.scenic.entity.query;

import com.itmaoo.scenic.entity.dto.BaseEntity;

/**
 * 
 * @author mario
 *
 */
public class BaseQuery extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 当前页码 **/
	protected Integer pageIndex;
	/** 分页步长 **/
	protected Integer pageSize;
	/** 开始条目 **/
	protected Integer startIndex;
	/** 截止条目 **/
	protected Integer endIndex;

	/** 排序字段 **/
	protected String sorter;
	
	/** 排序方式  asc/desc **/
	protected String ascOrDesc;
	
	protected Integer groupSize;
	
	public BaseQuery() {
		pageIndex = 1;
		pageSize = 5;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartIndex() {
		this.startIndex = (pageIndex - 1) * pageSize + 1;
		return startIndex;
	}

	public Integer getEndIndex() {
		this.endIndex = pageIndex * pageSize;
		return endIndex;
	}

	public String getSorter() {
		return sorter;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	public String getAscOrDesc() {
		return ascOrDesc;
	}

	public void setAscOrDesc(String ascOrDesc) {
		this.ascOrDesc = ascOrDesc;
	}

	public Integer getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(Integer groupSize) {
		this.groupSize = groupSize;
	}
	
}
