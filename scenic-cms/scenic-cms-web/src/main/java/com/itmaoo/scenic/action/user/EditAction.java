package com.itmaoo.scenic.action.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itmaoo.scenic.action.base.BaseActiom;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.query.ArticleQuery;
import com.itmaoo.scenic.entity.support.EntityUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping(value = "/action/edit/")
public class EditAction extends BaseActiom {
	@Autowired
	private IArticleDao articleDao;

	@Value("${base.site.domain}")
	private String baseDomain;

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
		ArticlePo a = articleDao.selectSingle(aq);
		if (a == null) {
			a = new ArticlePo();
			a.setUuid(articleUuid);
			a.setContent("在这里写点事.....");
			a.setTitle("填一个标题.....");
		}
		ArticleDto editArticle = EntityUtil.articlePoToDto(a);
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
    	if(desc.length()>128){
    		desc = desc.substring(0, 124) + "...";
    	}
    	return desc;
    	
	}

	@RequestMapping("article/publish")
	@ResponseBody
	public ResponseData publishArticle(HttpServletRequest request, @RequestBody ArticleDto article) {

		ArticleQuery aq = new ArticleQuery();
		aq.setUuid(article.getUuid());
		ArticlePo a = articleDao.selectSingle(aq);

		article.setContent(a.getContent());
		buildArticleHtml(a,request);

		ResponseData rd = new ResponseData();
		rd.setData(article);
		return rd;

	}

	private void buildArticleHtml(ArticlePo entity, HttpServletRequest request) {
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
