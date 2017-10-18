package com.itmaoo.scenic.html;

import java.util.Map;

public interface IHtmlBuildService {
	
	/**
	 * 根据Freemarker模板文件路径、Map数据生成HTML静态文件
	 * 
	 * @param templateFilePath
	 *            Freemarker模板文件路径
	 *            
	 * @param htmlFilePath
	 *            生成HTML静态文件存放路径
	 * 
	 * @param data
	 *            Map数据
	 * 
	 */
	public void buildHtml(String templateFilePath, String htmlFilePath, Map<String, Object> data);
	
	/**
	 * 生成baseJavascript文件
	 * 
	 */
	public void baseJavascriptBuildHtml();
	
	/**
	 * 生成首页HTML静态文件
	 * 
	 */
	public void indexBuildHtml();
	
	/**
	 * 生成登录HTML静态文件
	 * 
	 */
	public void loginBuildHtml();
	
	/**
	 * 错误页HTML静态文件
	 */
	public void errorPageBuildHtml();
	
	/**
	 * 权限错误页HTML静态文件
	 */
	public void errorPageAccessDeniedBuildHtml();
	
	/**
	 * 错误页500 HTML静态文件
	 */
	public void errorPage500BuildHtml();
	
	/**
	 * 错误页404 HTML静态文件
	 */
	public void errorPage404BuildHtml();
	
	/**
	 * 错误页403 HTML静态文件
	 */
	public void errorPage403BuildHtml();
	
}