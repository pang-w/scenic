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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itmaoo.scenic.action.base.BaseActiom;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.entity.dto.Artilcle;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.query.BaseQuery;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping(value = "/action/edit/")
public class EditAction extends BaseActiom {
	@Autowired
	private IArticleDao articleDao;

	@RequestMapping("article/{articleUuid}")
	@ResponseBody
	public ModelAndView createArticle(@PathVariable("articleUuid") String articleUuid, ModelMap map) {
		while(articleUuid.endsWith("/")){
			articleUuid = articleUuid.substring(0, articleUuid.length()-1);
		}
		ArticlePo entity = new ArticlePo();
		entity.setLastModifyDate(new Date());
		entity.setUuid(articleUuid);
		ArticleQuery aq = new ArticleQuery();
		aq.setUuid(articleUuid);
		ArticlePo a = articleDao.selectSingle(aq);
		if (a != null) {
			map.addAttribute("content", a.getContent());
		} else {

		}
		ModelAndView mv = new ModelAndView("iukiss/editor");
		map.addAttribute("baseDomain", "http://localhost:8080");
		map.addAttribute("articleUuid", articleUuid);
		return mv;

	}

	@RequestMapping("article/save")
	@ResponseBody
	public ResponseData updateArticle(@RequestBody Artilcle article) {
		ArticlePo entity = new ArticlePo();
		entity.setContent(checkTextDanger(article.getContent()));
		entity.setLastModifyDate(new Date());
		entity.setUuid(article.getUuid());
		
		ArticleQuery aq = new ArticleQuery();
		aq.setUuid(article.getUuid());
		ArticlePo a = articleDao.selectSingle(aq);
		if (a != null) {
			articleDao.updateByUniqueKey(entity);
			// check authorization and load article for update
		} else {
			articleDao.insert(entity);
		}

		article.setContent(entity.getContent());
		ResponseData rd = new ResponseData();
		rd.setData(article);
		return rd;
	}

	@RequestMapping("article/publish")
	@ResponseBody
	public ResponseData publishArticle(@RequestBody Artilcle article) {

		
		ArticleQuery aq = new ArticleQuery();
		aq.setUuid(article.getUuid());
		ArticlePo a = articleDao.selectSingle(aq);

		article.setContent(a.getContent());
		buildArticleHtml(a);
		
		ResponseData rd = new ResponseData();
		rd.setData(article);
		return rd;

	}

	private void buildArticleHtml(ArticlePo entity) {
		try {
			// 创建一个合适的Configration对象
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

			configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

			// configuration.setDirectoryForTemplateLoading(new
			// File("/template"));
			configuration.setDefaultEncoding("UTF-8"); // 这个一定要设置，不然在生成的页面中 会乱码
			// 获取或创建一个模版。
			Template template = configuration.getTemplate("iukiss/article.ftl");

			Writer writer = new OutputStreamWriter(
					new FileOutputStream("src/main/webapp/article/" + entity.getUuid() + ".html"), "UTF-8");
			Map<String, Object> commonData = new HashMap<String, Object>();

			commonData.put("content", entity.getContent());

			template.process(commonData, writer);
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
