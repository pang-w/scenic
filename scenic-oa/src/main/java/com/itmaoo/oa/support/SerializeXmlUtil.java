package com.itmaoo.oa.support;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * xml及对象互转工具类 （XStream）
 * 
 * @author wangxiushan(2015-01-13)
 * 
 */
public class SerializeXmlUtil {

	/**
	 * 将对象转化成XML字符串
	 * 
	 * @param Obj
	 *            转化对象
	 * @return xml字符串
	 * @throws Exception
	 */
	public static String serializeToXml(Object Obj) throws Exception {
		// 指定编码解析器,直接用jaxp dom来解释
		XStream xstream = new XStream(new DomDriver("utf-8"));
		// 注解生效
		xstream.processAnnotations(Obj.getClass());
		// 去掉xml中节点的class属性
		xstream.aliasSystemAttribute(null, "class");
		// 将对象序列化成XML字符串
		return xstream.toXML(Obj);
	}

	/**
	 * 将对象转化成XML字符串
	 * 
	 * @param Obj
	 *            转化对象
	 * @param cls
	 *            需修改类
	 * @param newName
	 *            新节点名称
	 * @return
	 * @throws Exception
	 */
	public static <T> String serializeToXml(Object Obj, Class<T> cls,
			String newName) throws Exception {
		// 指定编码解析器,直接用jaxp dom来解释
		XStream xstream = new XStream(new DomDriver("utf-8"));
		// 注解生效
		xstream.processAnnotations(Obj.getClass());
		// 改变节点名称
		xstream.alias(newName, cls);
		// 去掉xml中节点的class属性
		xstream.aliasSystemAttribute(null, "class");
		// 将对象序列化成XML字符串
		return xstream.toXML(Obj);
	}

	/**
	 * 将XML字符串转化成对象
	 * 
	 * @param xmlStr
	 *            xml字符串
	 * @param cls
	 *            转化实体类
	 * @return 转化后对象
	 * @throws Exception
	 */
	public static <T> Object deserializeToObj(String xmlStr, Class<T> cls)
			throws Exception {
		// 创建Xstream的DOM解释器
		XStream xstream = new XStream(new DomDriver());
		// 装载转化实体类
		xstream.processAnnotations(cls);
		// 将XML字符串转化成实体类对象
		return xstream.fromXML(xmlStr);
	}

}
