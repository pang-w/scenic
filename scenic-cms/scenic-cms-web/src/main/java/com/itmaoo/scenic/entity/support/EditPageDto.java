package com.itmaoo.scenic.entity.support;

import java.util.List;

import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ImageDto;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.UserDto;

public class EditPageDto {
	private UserDto topUser;
	private List<ArticleDto> articles;
	private List<ArticleDto> articleMenu;
	private List<ImageDto> imageMenu;
	private List<ProductDto> productMenu;
	private List<ArticleDto> attentionMenu;
	private ArticleDto editArticle;
	
	
	public ArticleDto getEditArticle() {
		return editArticle;
	}
	public void setEditArticle(ArticleDto editArticle) {
		this.editArticle = editArticle;
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
