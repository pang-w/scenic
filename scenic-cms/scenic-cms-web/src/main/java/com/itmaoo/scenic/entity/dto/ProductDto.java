package com.itmaoo.scenic.entity.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.commons.lang.StringUtils;




/**
 * 实体类 - 商品
 **/

public class ProductDto extends BaseEntity {

	private static final long serialVersionUID = 4858058186018438872L;
	
	// 重量单位（克、千克、吨）
	public enum WeightUnit {
		g, kg, t
	}

	public static final int MAX_BEST_PRODUCT_LIST_COUNT = 20;// 精品商品列表最大商品数
	public static final int MAX_NEW_PRODUCT_LIST_COUNT = 20;// 新品商品列表最大商品数
	public static final int MAX_HOT_PRODUCT_LIST_COUNT = 20;// 热销商品列表最大商品数
	public static final int DEFAULT_PRODUCT_LIST_PAGE_SIZE = 12;// 商品列表默认每页显示数
	
	private String productSn;// 货号
	private String name;// 商品名称
	private BigDecimal price;// 商品价格
	private BigDecimal marketPrice;// 市场价格
	private Double weight;// 商品重量
	private WeightUnit weightUnit;// 重量单位
	private Integer store;// 商品库存数量
	private Integer freezeStore;// 被占用库存数
	private Integer point;// 积分
	private Boolean isMarketable;// 是否上架
	private Boolean isBest;// 是否为精品商品
	private Boolean isNew;// 是否为新品商品
	private Boolean isHot;// 是否为热销商品
	private String description;// 描述
	private String metaKeywords;// 页面关键词
	private String metaDescription;// 页面描述
	private String htmlFilePath;// HTML静态文件路径
	private String productImageListStore;// 商品图片路径存储
	private String ownerId;// 是否管理员上传
	private String productOutURL;// 产品的外部链接
	private String productOutImagePath;// 商品外部图片路径存储
	private Boolean isMemberProduct;
	public String getProductSn() {
		return productSn;
	}
	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public WeightUnit getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(WeightUnit weightUnit) {
		this.weightUnit = weightUnit;
	}
	public Integer getStore() {
		return store;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public Integer getFreezeStore() {
		return freezeStore;
	}
	public void setFreezeStore(Integer freezeStore) {
		this.freezeStore = freezeStore;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Boolean getIsMarketable() {
		return isMarketable;
	}
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
	public Boolean getIsBest() {
		return isBest;
	}
	public void setIsBest(Boolean isBest) {
		this.isBest = isBest;
	}
	public Boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	public Boolean getIsHot() {
		return isHot;
	}
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMetaKeywords() {
		return metaKeywords;
	}
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public String getHtmlFilePath() {
		return htmlFilePath;
	}
	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}
	public String getProductImageListStore() {
		return productImageListStore;
	}
	public void setProductImageListStore(String productImageListStore) {
		this.productImageListStore = productImageListStore;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getProductOutURL() {
		return productOutURL;
	}
	public void setProductOutURL(String productOutURL) {
		this.productOutURL = productOutURL;
	}
	public String getProductOutImagePath() {
		return productOutImagePath;
	}
	public void setProductOutImagePath(String productOutImagePath) {
		this.productOutImagePath = productOutImagePath;
	}
	public Boolean getIsMemberProduct() {
		return isMemberProduct;
	}
	public void setIsMemberProduct(Boolean isMemberProduct) {
		this.isMemberProduct = isMemberProduct;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	

}