package com.itmaoo.oa.support;

/**
 * 
 * 生成编号的工具类
 * 
 * @author zyt
 * @date 2016年9月13日 上午11:36:32
 */
public class GenerateNumUtil {

	/**
	 * 生成唯一编号（YYYYMMDDHHmmSS+6位序列）
	 * 
	 * @param seq
	 *            6位序列
	 * @return 唯一编号（YYYYMMDDHHmmSS+6位序列）
	 * @author zyt
	 * @date 2016年9月13日 上午11:46:09
	 */
	public static String createNum(String seq) {
		StringBuilder num = new StringBuilder();
		num.append(PublicUtil.getNowTimeTo("yyyyMMddHHmmss"));
		num.append(seq);// 调用数据库查询六位序列
		return num.toString();
	}

	/**
	 * 生成唯一编号（XX+YYMMDDHHmmSS+6位序列）
	 * 
	 * @param type
	 *            编号类型（XX）
	 * @param seq
	 *            6位序列
	 * @return 唯一编号（XX+YYMMDDHHmmSS+6位序列）
	 * @author zyt
	 * @date 2016年9月13日 上午11:42:20
	 */
	public static String createNum(String type, String seq) {
		StringBuilder num = new StringBuilder();
		num.append(type);
		num.append(PublicUtil.getNowTimeTo("yyMMddHHmmss"));// 追加年月日
		// YYYYMMDD
		num.append(seq);// 调用数据库查询六位序列
		return num.toString();
	}
}
