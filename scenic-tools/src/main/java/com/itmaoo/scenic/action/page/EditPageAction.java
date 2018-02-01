package com.itmaoo.scenic.action.page;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IArticleProductLinkDao;
import com.itmaoo.scenic.dao.IArticleTagLinkDao;
import com.itmaoo.scenic.dao.IProductDao;
import com.itmaoo.scenic.dao.ITagDao;
import com.itmaoo.scenic.dao.IUserDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ProductDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.TagDto;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.ArticleProductLinkPo;
import com.itmaoo.scenic.entity.po.ArticleTagLinkPo;
import com.itmaoo.scenic.entity.po.ProductPo;
import com.itmaoo.scenic.entity.po.TagPo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.ArticleProductLinkQuery;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.query.ArticleTagLinkQuery;
import com.itmaoo.scenic.entity.query.ProductQuery;
import com.itmaoo.scenic.entity.query.TagQuery;
import com.itmaoo.scenic.entity.query.UserQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;
import com.itmaoo.scenic.entity.support.IndexPageDto;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 
 * @author mario
 *
 */
@Controller
@RequestMapping(value = "/action/page/edit/")
public class EditPageAction extends BaseAction {
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IArticleDao articleDao;

	@Autowired
	private IProductDao productDao;
	@Autowired
	private ITagDao tagDao;

	@Autowired
	private IArticleTagLinkDao articleTagLinkDao;

	@Autowired
	private IArticleProductLinkDao articleProductLinkDao;
	@Value("${base.img.domain}")
	private String imgDomain;

	@ResponseBody
	@RequestMapping("{articleUuid}")
	public ResponseData all(HttpServletRequest request, @PathVariable("articleUuid") String articleUuid) {

		/** top user **/
		UserDto userData = (UserDto) topUser(request).getData();

		/** Products (productsDto)**/
		ProductQuery proQuery = new ProductQuery();
		// proQuery.setUsername(loggedUser.getUsername());
		List<ProductPo> productsPo = productDao.selectList(proQuery);
		List<ProductDto> publicProductsDto = Lists.newArrayList();
		if (productsPo != null) {
			for (ProductPo pPo : productsPo) {
				ProductDto proPoToDto = EntityUtil.productPoToDto(pPo);
				publicProductsDto.add(proPoToDto);
			}
		}
		/** Tags (asideTagsDto)**/
		TagQuery asideTagQuery = new TagQuery();
		//asideTagQuery.setUsername(username);
		asideTagQuery.setPageIndex(1);
		asideTagQuery.setPageSize(20);
		List<TagPo> asideTagsPo = tagDao.selectList(asideTagQuery);
		List<TagDto> asideTagsDto = EntityUtil.tagPoToDtoList(asideTagsPo);
		
		/** Products (productsDto)**/
		ProductQuery linkProQuery = new ProductQuery();
		linkProQuery.setArticleUuid(articleUuid);
		List<ProductPo> linkedProductsPo = productDao.selectList(linkProQuery);
		List<ProductDto> linkedProductsDto = Lists.newArrayList();
		if (linkedProductsPo != null) {
			for (ProductPo pPo : linkedProductsPo) {
				ProductDto proPoToDto = EntityUtil.productPoToDto(pPo);
				linkedProductsDto.add(proPoToDto);
			}
		}


		//Set public properties
		IndexPageDto indexDto = new IndexPageDto();
		indexDto.setTopUser(userData);
		indexDto.setAsideTags(asideTagsDto);
		indexDto.setLinkedProducts(linkedProductsDto);

		//登录用户 可浏览的信息
		UserDto loggedUser = getLogedUser(request);
		if (loggedUser != null) {
			/** Tags (asideTagsDto)**/
			TagQuery userTagQuery = new TagQuery();
			userTagQuery.setUsername(loggedUser.getUsername());
			userTagQuery.setPageIndex(1);
			userTagQuery.setPageSize(20);
			List<TagPo> userTagsPo = tagDao.selectList(userTagQuery);
			TagQuery defaultTagQuery = new TagQuery();
			defaultTagQuery.setPageIndex(1);
			defaultTagQuery.setPageSize(5);
			defaultTagQuery.setType("default");
			List<TagPo> defaultTagsPo = tagDao.selectList(defaultTagQuery);
			//添加默认Tag
			
			List<TagDto> userTagsDto = EntityUtil.tagPoToDtoList(defaultTagsPo);
			userTagsDto.addAll(EntityUtil.tagPoToDtoList(userTagsPo));
			indexDto.setUserTags(userTagsDto);
			
			/** user articles **/
			List<ArticleDto> userArticlesDto = Lists.newArrayList();
			if (loggedUser != null) {
				ArticleQuery suerArticleQuery = new ArticleQuery();
				suerArticleQuery.setUsername(loggedUser.getUsername());
				suerArticleQuery.setPageIndex(1);
				suerArticleQuery.setPageSize(5);
				List<ArticlePo> userArticlesPo = articleDao.selectList(suerArticleQuery);
				ArticleDto editArticle = new ArticleDto();
				if (userArticlesPo != null) {
					for (ArticlePo articlePo : userArticlesPo) {
						ArticleDto articlePoToDto = makeupTagAndProductForArticle(articlePo);
						userArticlesDto.add(articlePoToDto);
						if (articlePo.getUuid().equals(articleUuid)) {
							editArticle = articlePoToDto;
							indexDto.setEditArticle(editArticle);
						}
					}
				}
			}
		}
		
		ResponseData rd = new ResponseData();
		rd.setData(indexDto);
		return rd;

	}

	@RequestMapping("article/save")
	@ResponseBody
	public ResponseData saveArticle(HttpServletRequest request, @RequestBody ArticleDto article) {
		ResponseData rd = new ResponseData();
		UserDto loggeduser = getLogedUser(request);
		if (loggeduser != null) {

			List<TagDto> selectedTags = article.getSelectedTags();
			articleTagLinkDao.disableTagsByArticleUuid(article.getUuid());
			if (selectedTags != null) {
				for (TagDto st : selectedTags) {
					TagQuery tagQuery = new TagQuery();
					tagQuery.setValue(st.getValue());
					TagPo ssv = tagDao.selectSingleByValue(tagQuery);
					if (ssv == null) {
						TagPo tagPo = new TagPo();
						tagPo.setCreateBy(loggeduser.getUsername());
						tagPo.setCreateDate(new Date());
						tagPo.setLastModifyDate(new Date());
						tagPo.setValue(st.getValue());
						tagPo.setType("USER");
						tagDao.insert(tagPo);
						ssv = tagDao.selectSingleByValue(tagQuery);
					}
					ArticleTagLinkQuery atlq = new ArticleTagLinkQuery();
					atlq.setArticleUuid(article.getUuid());
					atlq.setTagId(ssv.getId());
					ArticleTagLinkPo articleTagLinkPo = articleTagLinkDao.selectSingle(atlq);
					if (articleTagLinkPo == null) {
						ArticleTagLinkPo atlp = new ArticleTagLinkPo();
						atlp.setArticleUuid(article.getUuid());
						atlp.setCreateBy(loggeduser.getUsername());
						atlp.setCreateDate(new Date());
						atlp.setLastModifyDate(new Date());
						atlp.setEffected(true);
						atlp.setTagId(ssv.getId());
						articleTagLinkDao.insert(atlp);
					} else {
						articleTagLinkDao.enableTagById(articleTagLinkPo.getId());
					}
				}
			}

			List<ProductDto> selectedProducts = article.getSelectedProducts();
			articleProductLinkDao.disableTagsByArticleUuid(article.getUuid());
			if (selectedProducts != null) {
				for (ProductDto sp : selectedProducts) {
					ProductQuery productQuery = new ProductQuery();
					productQuery.setId(sp.getId());
					ProductPo ssv = productDao.selectById(productQuery);
					if (ssv != null) {
						ArticleProductLinkQuery atlq = new ArticleProductLinkQuery();
						atlq.setArticleUuid(article.getUuid());
						atlq.setProductId(ssv.getId());
						ArticleProductLinkPo articleProLinkPo = articleProductLinkDao.selectSingle(atlq);
						if (articleProLinkPo == null) {
							ArticleProductLinkPo aplp = new ArticleProductLinkPo();
							aplp.setArticleUuid(article.getUuid());
							aplp.setEffected(true);
							aplp.setProductId(ssv.getId());
							aplp.setCreateDate(new Date());
							aplp.setLastModifyDate(new Date());
							articleProductLinkDao.insert(aplp);
						} else {
							articleProductLinkDao.enableTagById(articleProLinkPo.getId());
						}
					}

				}
			}
			ArticlePo entity = new ArticlePo();
			entity.setContent(checkTextDanger(article.getContent()));
			entity.setLastModifyDate(new Date());
			entity.setUuid(article.getUuid());
			entity.setTitle(article.getTitle());
			entity.setUsername(loggeduser.getUsername());
			
			entity.setDefaultImageUrl(getDefaultImageUrl(entity.getContent()));
			entity.setDescription(getDesc(entity.getContent()));

			ArticleQuery aq = new ArticleQuery();
			aq.setUuid(article.getUuid());
			ArticlePo a = articleDao.selectSingle(aq);
			if (a != null) {
				articleDao.updateByUniqueKey(entity);
				// check authorization and load article for update
			} else {
				entity.setCreateDate(new Date());
				articleDao.insert(entity);
			}
			article.setContent(entity.getContent());
			rd.setData(article);
		} else {
			rd.setStatus("4004");
			rd.setMsg("未登录");
		}
		return rd;
	}

	

	private String getDefaultImageUrl(String content) {
		Pattern pattern = Pattern.compile("<img[^<>]*>");
		Matcher matcher = pattern.matcher(content);
		String imageHtml = "";
		if(matcher.find()){
			imageHtml = matcher.group();
			Pattern patternSrc = Pattern.compile("src=\"([^\"]*)\"");
			Matcher matcherSrc = patternSrc.matcher(imageHtml);
			if(matcherSrc.find()){
				return matcherSrc.group(1);
			}
		}

		return null;
	}

	@RequestMapping("article/publish")
	@ResponseBody
	public ResponseData publishArticle(HttpServletRequest request, @RequestBody ArticleDto article) {
		// 取出数据库中的数生成永久地址页面，不要直接生成，保证下次生成的一致性

		ArticlePo articlePo = new ArticlePo();
		articlePo.setIsPublished(true);
		articlePo.setUuid(article.getUuid());
		Integer count = articleDao.updatePublishStatus(articlePo);
		System.out.println(count);
		ArticleQuery aq = new ArticleQuery();
		aq.setUuid(article.getUuid());
		ArticlePo a = articleDao.selectSingle(aq);
		// 获取标签
		TagQuery tagQuery = new TagQuery();
		tagQuery.setArticleUuid(articlePo.getUuid());
		tagQuery.setEffected(true);
		List<TagPo> tags = tagDao.selectList(tagQuery);
		//设置为null在展示文章时不显示标签整个节点
		List<TagDto> tagsDto = null;
		if (tags != null && !tags.isEmpty()) {
			tagsDto = Lists.newArrayList();
			for (TagPo tag : tags) {
				tagsDto.add(EntityUtil.tagPoToDto(tag));
			}
		}
		// 获取商品
		ProductQuery productQuery = new ProductQuery();
		productQuery.setArticleUuid(articlePo.getUuid());
		productQuery.setEffected(true);
		List<ProductPo> productDb = productDao.selectList(productQuery);
		List<ProductDto> productsDto = null;
		if (productDb != null&& !productDb.isEmpty()) {
			productsDto = Lists.newArrayList();
			for (ProductPo p : productDb) {
				productsDto.add(EntityUtil.productPoToDto(p));
			}
		}
		ArticleDto articlePoToDto = EntityUtil.articlePoToDto(a);
		articlePoToDto.setProducts(productsDto);
		articlePoToDto.setTags(tagsDto);

	//	buildArticleHtml(articlePoToDto, request);

		ResponseData rd = new ResponseData();
		rd.setData(article);
		return rd;

	}

	@RequestMapping("article/unpublish")
	@ResponseBody
	public ResponseData unpublishArticle(HttpServletRequest request, @RequestBody ArticleDto article) {
		// 取出数据库中的数生成永久地址页面，保证下次生成的一致性

		ArticlePo articlePo = new ArticlePo();
		articlePo.setIsPublished(false);
		articlePo.setUuid(article.getUuid());
		Integer count = articleDao.updatePublishStatus(articlePo);
		ResponseData rd = new ResponseData();
		if (count != 1) {
			rd.setStatus("5001");
			rd.setMsg("更改失败");
		}
		rd.setData(article);
		return rd;

	}

	public String checkTextDanger(String checkText) {
		String newText = checkText.trim(); // 去掉头尾空格
		newText = newText.replace("\n", "<br>");
		newText = newText.replace("\t", "&nbsp&nbsp&nbsp&nbsp");
		// textBox里的换行是用\n来表示的，如果要在HTML里显示换行要用<br>
		// newText = newText.replace("<", "&lt"); //置换 <
		// newText = newText.replace(">", "&gt");
		// newText = newText.replace(".", "。");//置换 >
		// newText = newText.replace("'", "''");
		// 如果是用存储过程存储数据，这行不用加，如果你用的是SQL语句来存数据，这行要加上，功能为置换 ‘
		return newText;
	}
	public static String getDesc(String content) {
        content = content.replaceAll("<[^<>]*>", "");
		String strNoBlank = content.replaceAll("\\s{2,}"," ");
		if (strNoBlank.length() > 128) {
			strNoBlank = strNoBlank.substring(0, 124) + "...";
		}
		return strNoBlank;
	}
	public static void main(String[] args) {
		String tt = "<img src=\"http://img.iukiss.com/img/user/itmaoo/iukiss.png\" style=\"max-width: 80%; text-align: center;\">asdf";
		String s = EditPageAction.getDesc(tt);
		System.out.println(s);
	}
	

	@ResponseBody
	@RequestMapping("topUser")
	public ResponseData topUser(HttpServletRequest request) {
		UserQuery userQuery = new UserQuery();
		userQuery.setUsername("ITMAOO");
		UserPo recUser = userDao.selectByUsername(userQuery);
		UserDto userData = EntityUtil.userPoToDto(recUser);
		
		ResponseData rd = new ResponseData();
		rd.setData(userData);
		return rd;

	}
}
