package com.itmaoo.scenic.entity.dto;


/**
 * 实体类 - 导航
 */

public class Navigation extends BaseEntity {

	private static final long serialVersionUID = -7635757647887646795L;
	public Navigation (String name,Position position, String url, Boolean isVisible,Boolean isBlankTarget,Integer orderList){
		
		
		this.name = name;// 名称
		this.position = position;// 位置
		this.url = url;// 链接地址;
		this.isVisible = isVisible;// 是否显示
		this.isBlankTarget =isBlankTarget;// 是否在新窗口中打开
		this.orderList = orderList;// 排序
	}
	// 导航位置:顶部、中间、底部
	public enum Position {
		top, middle, bottom
	}

	private String name;// 名称
	private Position position;// 位置
	private String url;// 链接地址;
	private Boolean isVisible;// 是否显示
	private Boolean isBlankTarget;// 是否在新窗口中打开
	private Integer orderList;// 排序

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getIsBlankTarget() {
		return isBlankTarget;
	}

	public void setIsBlankTarget(Boolean isBlankTarget) {
		this.isBlankTarget = isBlankTarget;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

}