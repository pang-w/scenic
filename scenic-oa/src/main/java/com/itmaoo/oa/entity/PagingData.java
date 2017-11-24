package com.itmaoo.oa.entity;

import java.util.List;

public class PagingData<T> extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 当前页码 **/
	private Integer pageIndex;
	/** 分页步长**/
	private Integer pageSize;
	/** 总记录数 **/
	private Integer totalCount;
	/** 总页数 **/
	private Integer totalPage;
	/** 数据列表 **/
	private List<T> dataList;

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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalCount, int pageSize) {
		this.totalPage = (totalCount + pageSize - 1) / pageSize;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
