package com.itmaoo.scenic.action.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
public class HtmlAction {
	// @Autowired
	// private IHtmlBuildService htmlservice;

	@RequestMapping("/action/admin/pages/buildIndex")
	public void buildIndexHtml() {
		// htmlservice.indexBuildHtml();
		try {
			// 创建一个合适的Configration对象
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

			configuration.setDirectoryForTemplateLoading(new File("/home/mario/git/scenic/scenic-cms/scenic-cms-web/src/main/resources/templates"));

			// configuration.setDirectoryForTemplateLoading(new File("/template"));
			configuration.setDefaultEncoding("UTF-8"); // 这个一定要设置，不然在生成的页面中 会乱码
			// 获取或创建一个模版。
			Template template = configuration.getTemplate("indexFreeMarker.ftl");

			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("name", "王老师");
			map.put("host", "http://blog.didispace.com");

			Writer writer;

			writer = new OutputStreamWriter(new FileOutputStream("src/main/webapp/indexmao2.html"), "UTF-8");
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

		System.out.println("恭喜，生成成功~~");

	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String loginUser() {
		return "Amdin";

	}

	@RequestMapping(value = "/helloFreeMarker", method = RequestMethod.GET)
	public ModelAndView indexBuildHtml(ModelMap map) {
		ModelAndView mv = new ModelAndView("indexFreeMarker");
		map.addAttribute("name", "王老师");
		map.addAttribute("host", "http://blog.didispace.com");
		return mv;

	}
	public static void main(String[] args) {
		System.out.println(HtmlAction.class.getClassLoader().getResource("/template"));
		System.out.println(new File("/template/").isDirectory());
		System.out.println(new File("/template/").getAbsolutePath());
		System.out.println(new File("/template/").getPath());
		new HtmlAction().buildIndexHtml();
	}

}
