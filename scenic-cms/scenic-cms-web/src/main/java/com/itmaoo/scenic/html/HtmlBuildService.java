package com.itmaoo.scenic.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itmaoo.scenic.support.HtmlConfig;


import freemarker.template.Configuration;
import freemarker.template.Template;

public class HtmlBuildService implements IHtmlBuildService{
	@Resource
	
	@Override
	public void buildHtml(String templateFilePath, String htmlFilePath, Map<String, Object> data) {

	}

	@Override
	public void baseJavascriptBuildHtml() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	@RequestMapping(value = "/helloFreeMarker",method = RequestMethod.GET)
	public ModelAndView indexBuildHtml(ModelMap map) {
		        ModelAndView mv = new ModelAndView("indexFreeMarker");
		        map.addAttribute("name","王老师");
		        map.addAttribute("host", "http://blog.didispace.com");
		        return mv;
		
	}

	@Override
	public void loginBuildHtml() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorPageBuildHtml() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorPageAccessDeniedBuildHtml() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorPage500BuildHtml() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorPage404BuildHtml() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorPage403BuildHtml() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void indexBuildHtml() {
		// TODO Auto-generated method stub
		
	}

}
