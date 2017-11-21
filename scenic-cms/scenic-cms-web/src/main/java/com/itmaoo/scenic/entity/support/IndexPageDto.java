package com.itmaoo.scenic.entity.support;

import java.util.List;

import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ImageDto;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.TagDto;
import com.itmaoo.scenic.entity.dto.UserDto;

public class IndexPageDto {
	private UserDto topUser;
	private UserDto loggedUser;
	private List<ArticleDto> articles;
	private List<ProductDto> products;
	private List<ArticleDto> articleMenu;
	private List<ImageDto> imageMenu;
	private List<ProductDto> productMenu;
	private List<ArticleDto> attentionMenu;
	
	private List<TagDto> userTags;
	private List<TagDto> asideTags;
	
	private List<ProductDto> linkedProducts;
	private List<TagDto> selectedTags;
	
	private ArticleDto editArticle;
	
	
	public ArticleDto getEditArticle() {
		return editArticle;
	}
	public void setEditArticle(ArticleDto editArticle) {
		this.editArticle = editArticle;
	}
	public List<ProductDto> getLinkedProducts() {
		return linkedProducts;
	}
	public void setLinkedProducts(List<ProductDto> linkedProducts) {
		this.linkedProducts = linkedProducts;
	}
	public List<TagDto> getUserTags() {
		return userTags;
	}
	public void setUserTags(List<TagDto> userTags) {
		this.userTags = userTags;
	}
	
	public List<TagDto> getAsideTags() {
		return asideTags;
	}
	public void setAsideTags(List<TagDto> asideTags) {
		this.asideTags = asideTags;
	}
	public List<TagDto> getSelectedTags() {
		return selectedTags;
	}
	public void setSelectedTags(List<TagDto> selectedTags) {
		this.selectedTags = selectedTags;
	}
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	public UserDto getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(UserDto loggedUser) {
		this.loggedUser = loggedUser;
	}
	public UserDto getTopUser() {
		return topUser;
	}
	public void setTopUser(UserDto topUser) {
		this.topUser = topUser;
	}
	public List<ArticleDto> getArticles() {
		return articles;
	}
	public void setArticles(List<ArticleDto> articles) {
		this.articles = articles;
	}
	public List<ArticleDto> getArticleMenu() {
		return articleMenu;
	}
	public void setArticleMenu(List<ArticleDto> articleMenu) {
		this.articleMenu = articleMenu;
	}
	public List<ImageDto> getImageMenu() {
		return imageMenu;
	}
	public void setImageMenu(List<ImageDto> imageMenu) {
		this.imageMenu = imageMenu;
	}
	public List<ProductDto> getProductMenu() {
		return productMenu;
	}
	public void setProductMenu(List<ProductDto> productMenu) {
		this.productMenu = productMenu;
	}
	public List<ArticleDto> getAttentionMenu() {
		return attentionMenu;
	}
	public void setAttentionMenu(List<ArticleDto> attentionMenu) {
		this.attentionMenu = attentionMenu;
	}
	
	

}
