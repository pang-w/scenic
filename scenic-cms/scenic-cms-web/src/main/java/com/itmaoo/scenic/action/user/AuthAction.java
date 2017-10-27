package com.itmaoo.scenic.action.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmaoo.scenic.dao.IAdminDao;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.entity.dto.Artilcle;
import com.itmaoo.scenic.entity.dto.BaseQuery;
import com.itmaoo.scenic.entity.po.Admin;
import com.itmaoo.scenic.entity.po.Article;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping(value = "/action/user/")
public class AuthAction {
	@Autowired
	private IAdminDao adminDao;
	@Autowired
	private IArticleDao articleDao;
	//@RequestMapping(value = "/action/user/loginUser",method = RequestMethod.GET)
	@RequestMapping("uploadImg")
	public String loginUser() {
		
		List<Admin> list = adminDao.selectList(new BaseQuery());
		
		System.out.println(list.size());
		
		return "User";

	}
	@RequestMapping("addArticle")
	public String addArticle(@RequestBody Artilcle article) {
		Article entity = new Article();
		entity.setContent(checkTextDanger(article.getContent()));
		//entity.setContent("wer");
		articleDao.insert(entity);
		
		try {
			// 创建一个合适的Configration对象
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

			configuration.setDirectoryForTemplateLoading(new File("/home/mario/git/scenic/scenic-cms/scenic-cms-web/src/main/resources/templates"));

			// configuration.setDirectoryForTemplateLoading(new File("/template"));
			configuration.setDefaultEncoding("UTF-8"); // 这个一定要设置，不然在生成的页面中 会乱码
			// 获取或创建一个模版。
			Template template = configuration.getTemplate("iukiss/article.ftl");

			Writer writer = new OutputStreamWriter(new FileOutputStream("src/main/webapp/article/a1.html"), "UTF-8");
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
		return "User";

	}
	public Map<String, Object> getCommonData() {
		Map<String, Object> commonData = new HashMap<String, Object>();
		
		commonData.put("content", "http://192.168.1.137:8080");
		return commonData;
	}
	public String checkTextDanger(String checkText)
    {
		String newText = checkText.trim();  //去掉头尾空格
        newText = newText.replace("\n", "<br>");
        newText = newText.replace("\t", "&nbsp&nbsp&nbsp&nbsp");
          //textBox里的换行是用\n来表示的，如果要在HTML里显示换行要用<br>
      //  newText = newText.replace("<", "&lt");  //置换 <
      //  newText = newText.replace(">", "&gt");
      //  newText = newText.replace(".", "。");//置换 >
       // newText = newText.replace("'", "''");
          //如果是用存储过程存储数据，这行不用加，如果你用的是SQL语句来存数据，这行要加上，功能为置换 ‘
        return newText;
    }
}
