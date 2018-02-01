package com.itmaoo.scenic.robot.entity.po;

public class LinkWindow {
	private Integer start;
	private Integer end;
	private String chengyu;
	
	public LinkWindow(Integer index, String key) {
		this.start=index;
		this.end = index;
		this.chengyu = key;
	}
	
	public void extrForwordOne(){
		end = end+1;
	}
	public void extrBackOne(){
		end = end+1;
	}
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	

	public String getChengyu() {
		return chengyu;
	}

	public void setChengyu(String chengyu) {
		this.chengyu = chengyu;
	}

	public boolean doLink(Integer index, String key) {
		if(index == start-1){
			start = start-1;
			chengyu = key + chengyu;
			return true;
		}
		if(index == end+1){
			end = end+1;
			chengyu = chengyu + key;
			return true;
		}
		return false;
	}
	
	
}
