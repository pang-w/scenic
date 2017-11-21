package com.itmaoo.scenic.action.user;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseAction;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IArticleProductLinkDao;
import com.itmaoo.scenic.dao.IArticleTagLinkDao;
import com.itmaoo.scenic.dao.IProductDao;
import com.itmaoo.scenic.dao.ITagDao;
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
import com.itmaoo.scenic.entity.query.BaseQuery;
import com.itmaoo.scenic.entity.query.ProductQuery;
import com.itmaoo.scenic.entity.query.TagQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping(value = "/action/edit/")
public class EditAction extends BaseAction {
	@Autowired
	private IArticleDao articleDao;
	
	@Autowired
	private ITagDao tagDao;
	
	@Autowired
	private IArticleTagLinkDao articleTagLinkDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private IArticleProductLinkDao articleProductLinkDao;

	@Value("${base.img.domain}")
	private String imgDomain;

	@RequestMapping("article/{articleUuid}")
	@ResponseBody
	public ModelAndView createArticle(@PathVariable("articleUuid") String articleUuid, ModelMap map) {
		while (articleUuid.endsWith("/")) {
			articleUuid = articleUuid.substring(0, articleUuid.length() - 1);
		}
		ArticleQuery aq = new ArticleQuery();
		aq.setUuid(articleUuid);
		ArticlePo articlePo = articleDao.selectSingle(aq);
		ArticleDto editArticle = null;
		if (articlePo == null) {
			editArticle = new ArticleDto();
			editArticle.setUuid(articleUuid);
			editArticle.setContent("");
			editArticle.setTitle("");
		}else{
			editArticle = EntityUtil.articlePoToDto(articlePo);
			TagQuery tagQuery = new TagQuery();
			tagQuery.setArticleUuid(articlePo.getUuid());
			List<TagPo> tags = tagDao.selectList(tagQuery);
			List<TagDto> tagsDto = Lists.newArrayList(); 
			if(tags!=null){
				for(TagPo tag:tags){
					tagsDto.add(EntityUtil.tagPoToDto(tag));
				}
			}
			ProductQuery productQuery = new ProductQuery();
			productQuery.setArticleUuid(articlePo.getUuid());
			List<ProductPo> productDb = productDao.selectList(productQuery);
			List<ProductDto> productsDto = Lists.newArrayList(); 
			if(productDb!=null){
				for( ProductPo p:productDb){
					productsDto.add(EntityUtil.productPoToDto(p));
				}
			}
			editArticle.setTags(tagsDto);
			editArticle.setProducts(productsDto);
		}
		
		map.addAttribute("editArticle", editArticle);

		map.addAttribute("imgDomain", imgDomain);
		map.addAttribute("articleUuid", articleUuid);
		
		ModelAndView mv = new ModelAndView("iukiss/editor");
		return mv;

	}

	@RequestMapping("article/save")
	@ResponseBody
	public ResponseData saveArticle(HttpServletRequest  request,@RequestBody ArticleDto article) {
		ResponseData rd = new ResponseData();
		UserDto loggeduser = getLogedUser(request);
		if(loggeduser!=null){
			
			List<TagDto> selectedTags = article.getSelectedTags();
			if(selectedTags!=null){
			for(TagDto st:selectedTags){
				TagQuery tagQuery = new TagQuery();
				tagQuery.setValue(st.getValue());
				TagPo ssv = tagDao.selectSingleByValue(tagQuery);
				if(ssv==null){
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
				if(articleTagLinkPo==null){
					ArticleTagLinkPo atlp = new ArticleTagLinkPo();
					atlp.setArticleUuid(article.getUuid());
					atlp.setCreateBy(loggeduser.getUsername());
					atlp.setCreateDate(new Date());
					atlp.setLastModifyDate(new Date());
					atlp.setTagId(ssv.getId());
					articleTagLinkDao.insert(atlp);
				}
				
				
			}
			}
			
			List<ProductDto> selectedProducts = article.getSelectedProducts();
			if(selectedProducts!=null){
			for( ProductDto sp:selectedProducts){
				ProductQuery productQuery = new ProductQuery();
				productQuery.setId(sp.getId());
				ProductPo ssv = productDao.selectById(productQuery);
				if(ssv!=null){
					ArticleProductLinkQuery atlq = new ArticleProductLinkQuery();
					atlq.setArticleUuid(article.getUuid());
					atlq.setProductId(ssv.getId());
					ArticleProductLinkPo articleTagLinkPo = articleProductLinkDao.selectSingle(atlq);
					if(articleTagLinkPo==null){
						ArticleProductLinkPo aplp = new ArticleProductLinkPo();
						aplp.setArticleUuid(article.getUuid());
						aplp.setEffected(true);
						aplp.setProductId(ssv.getId());
						aplp.setCreateDate(new Date());
						aplp.setLastModifyDate(new Date());
						articleProductLinkDao.insert(aplp);
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
		}else{
			rd.setStatus("4004");
			rd.setMsg("未登录");
		}
		return rd;
	}

	private String getDesc(String content) {
		String tmpString =content.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");//去掉所有中英文符号
    	char[] carr = tmpString.toCharArray();
    	for(int i = 0; i<tmpString.length();i++){
    		if(carr[i] < 0xFF){
    			carr[i] = ' ' ;//过滤掉非汉字内容
    		}
    	}
    	String desc =  String.copyValueOf(carr).trim();
    	Pattern p = Pattern.compile("\\s{2,}|\t");
    	Matcher m = p.matcher(desc);
    	String strNoBlank = m.replaceAll(" ");
    	if(strNoBlank.length()>128){
    		strNoBlank = strNoBlank.substring(0, 124) + "...";
    	}
    	return strNoBlank;
    	
	}

	@RequestMapping("article/publish")
	@ResponseBody
	public ResponseData publishArticle(HttpServletRequest request, @RequestBody ArticleDto article) {
		//取出数据库中的数生成永久地址页面，不要直接生成，保证下次生成的一致性
		
		ArticlePo articlePo = new ArticlePo();
		articlePo.setIsPublished(true);
		articlePo.setUuid(article.getUuid());
		Integer count = articleDao.updatePublishStatus(articlePo);
		System.out.println(count);
		ArticleQuery aq = new ArticleQuery();
		aq.setUuid(article.getUuid());
		ArticlePo a = articleDao.selectSingle(aq);
		//获取标签
		TagQuery tagQuery = new TagQuery();
		tagQuery.setArticleUuid(articlePo.getUuid());
		List<TagPo> tags = tagDao.selectList(tagQuery);
		List<TagDto> tagsDto = Lists.newArrayList(); 
		if(tags!=null){
			for(TagPo tag:tags){
				tagsDto.add(EntityUtil.tagPoToDto(tag));
			}
		}
		//获取商品
		ProductQuery productQuery = new ProductQuery();
		productQuery.setArticleUuid(articlePo.getUuid());
		List<ProductPo> productDb = productDao.selectList(productQuery);
		List<ProductDto> productsDto = Lists.newArrayList(); 
		if(productDb!=null){
			for( ProductPo p:productDb){
				productsDto.add(EntityUtil.productPoToDto(p));
			}
		}
		ArticleDto articlePoToDto = EntityUtil.articlePoToDto(a);
		articlePoToDto.setProducts(productsDto);
		articlePoToDto.setTags(tagsDto);
		
		buildArticleHtml(articlePoToDto,request);

		ResponseData rd = new ResponseData();
		rd.setData(article);
		return rd;

	}
	@RequestMapping("article/unpublish")
	@ResponseBody
	public ResponseData unpublishArticle(HttpServletRequest request, @RequestBody ArticleDto article) {
		//取出数据库中的数生成永久地址页面，保证下次生成的一致性
		
		ArticlePo articlePo = new ArticlePo();
		articlePo.setIsPublished(false);
		articlePo.setUuid(article.getUuid());
		Integer count = articleDao.updatePublishStatus(articlePo);
		ResponseData rd = new ResponseData();
		if(count!=1){
			rd.setStatus("5001");
			rd.setMsg("更改失败");
		}
		rd.setData(article);
		return rd;

	}

	private void buildArticleHtml(ArticleDto entity, HttpServletRequest request) {
		try {
			// 创建一个合适的Configration对象
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

			configuration.//setClassForTemplateLoading(this.getClass(),"templates");
				setDirectoryForTemplateLoading(new File(this.getClass().getClassLoader().getResource("").getPath()+"templates"));

			// configuration.setDirectoryForTemplateLoading(new
			// File("/template"));
			configuration.setDefaultEncoding("UTF-8"); // 这个一定要设置，不然在生成的页面中 会乱码
			//项目部署路径
			String path = request.getSession().getServletContext().getRealPath("/");

			// 获取或创建一个模版。
			Template template = configuration.getTemplate("iukiss/article.ftl");


			Writer writer = new OutputStreamWriter(
					new FileOutputStream(path +"article/" + entity.getUuid() + ".html"), "UTF-8");
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("article", entity);
			map.put("imgDomain", imgDomain);
			map.put("articleUuid", entity.getUuid());

			template.process(map, writer);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
