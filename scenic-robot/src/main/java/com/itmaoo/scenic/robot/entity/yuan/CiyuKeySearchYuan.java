package com.itmaoo.scenic.robot.entity.yuan;

import com.itmaoo.scenic.robot.entity.po.Chengyu;
import com.itmaoo.scenic.robot.entity.po.Ciyu;

import java.util.List;

public class CiyuKeySearchYuan {
	private String key;
	private List<Ciyu> chengyuList;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Ciyu> getChengyuList() {
		return chengyuList;
	}
	public void setChengyuList(List<Ciyu> chengyuList) {
		this.chengyuList = chengyuList;
	}
	
	
}
