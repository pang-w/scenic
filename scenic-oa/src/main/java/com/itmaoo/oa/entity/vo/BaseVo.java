package com.itmaoo.oa.entity.vo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;


public class BaseVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String createDate;
	private String lastModifyDate;
	
	private Boolean deleteAble;
	private Boolean updatgeAble;

	private Integer pageIndex;
	
	
  public Integer getPageIndex() {
    return pageIndex;
  }

  public void setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }

  public Boolean getDeleteAble() {
    return deleteAble;
  }

  public void setDeleteAble(Boolean deleteAble) {
    this.deleteAble = deleteAble;
  }

  public Boolean getUpdatgeAble() {
    return updatgeAble;
  }

  public void setUpdatgeAble(Boolean updatgeAble) {
    this.updatgeAble = updatgeAble;
  }

  public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}



	public String getLastModifyDate() {
		return lastModifyDate;
	}



	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}



	public String toJsonString() {
		return JSONObject.toJSONString(this);
	}

}