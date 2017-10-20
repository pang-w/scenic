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
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itmaoo.scenic.support.SystemConfigUtil;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.ResourceBundleModel;
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
			Template template = configuration.getTemplate("shop/index.ftl");

			Writer writer = new OutputStreamWriter(new FileOutputStream("src/main/webapp/index.html"), "UTF-8");
			
			template.process(getCommonData(), writer);
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
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexBuildHtml2(ModelMap map) {
		ModelAndView mv = new ModelAndView("shop/index");
		map.addAttribute("base", "http://localhost:8080");
		map.addAttribute("topNavigationList", new ArrayList<>());
		Map<String, Object> commonData = getCommonData();
		for(Map.Entry<String, Object> entry:commonData.entrySet()){
			map.put(entry.getKey(), entry.getValue());
		}
		
		return mv;

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
	public Map<String, Object> getCommonData() {
		Map<String, Object> commonData = new HashMap<String, Object>();
		
		commonData.put("base", "http://192.168.1.137:8080");
		commonData.put("baseOOS", "http://localhost:8080");
		commonData.put("systemConfig", SystemConfigUtil.getSystemConfig());
		commonData.put("priceCurrencyFormat", "#0");
		commonData.put("priceUnitCurrencyFormat", "#0");
		commonData.put("orderCurrencyFormat", "#0");
		commonData.put("orderUnitCurrencyFormat", "#0");
		commonData.put("topNavigationList", new ArrayList<>());
		commonData.put("middleNavigationList", new ArrayList<>());
		commonData.put("bottomNavigationList", new ArrayList<>());
		commonData.put("friendLinkList",new ArrayList<>());
		commonData.put("pictureFriendLinkList", new ArrayList<>());
		commonData.put("textFriendLinkList", new ArrayList<>());
		commonData.put("rootProductCategoryList", new ArrayList<>());
		commonData.put("footer", null);
		return commonData;
	}

}
