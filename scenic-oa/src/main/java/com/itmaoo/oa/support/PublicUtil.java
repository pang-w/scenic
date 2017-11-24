package com.itmaoo.oa.support;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共工具类
 * 
 * 
 */

public class PublicUtil {
	
	private static final String YYYY_MM_DD = "yyyy-MM-dd";
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	final static Logger LOGGER = LoggerFactory.getLogger(PublicUtil.class);

	/**
	 * 获取当前系统时间
	 * 
	 * @param strFormat
	 *            格式
	 * @return 当前时间字符串
	 */
	public static String getNowTimeTo(String strFormat) {
		if (strFormat == null || StringUtils.isEmpty(strFormat)) {
			strFormat = YYYY_MM_DD_HH_MM_SS;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		return dateFormat.format(new Date());
	}

	/**
	 * 获取当前系统日期
	 * 
	 * 
	 * @return 当前日期字符串（yyyy-MM-dd）
	 */
	public static String getNowDate() {
		return getNowTimeTo(YYYY_MM_DD);
	}

	/**
	 * 格式化时间为yyyyMMdd
	 * 
	 * @param 时间
	 * @return 格式化时候的时间（String类型）
	 **/
	public static String formatDate(String strFormat, Date date) {
		if(date==null){
			return null;
		}
		if (strFormat == null || StringUtils.isEmpty(strFormat)) {
			strFormat = YYYY_MM_DD_HH_MM_SS;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		return dateFormat.format(date);
	}
	/**
	 * 格式化时间为yyyyMMdd
	 * 
	 * @param 时间
	 * @return 格式化时候的时间（String类型）
	 **/
	public static Date parseDate(String date) {
		if(StringUtils.isEmpty(date)){
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.error("字符串转化时间失败");
		}
		return null;
	}

	/**
	 * 获取报文头中的账户类型
	 * 
	 * @param 客户类型
	 * @param 客户业务类型
	 * @return 账户类型
	 */
	public static int getAccType(Integer cusType, Integer cusBiz) {
		int accType = 0;
		if (cusType != null && cusType == 1) {
			if (cusBiz != null && cusBiz == 1) {
				accType = 1;// 个人投资人
			}
			if (cusBiz != null && cusBiz == 2) {
				accType = 2;// 个人借款人
			}
		}

		if (cusType != null && cusType == 2) {
			if (cusBiz != null && cusBiz == 1) {
				accType = 3;// 商户投资人
			}
			if (cusBiz != null && cusBiz == 2) {
				accType = 4;// 商户借款人
			}
		}
		return accType;
	}

	/**
	 * 计算减日期
	 * 
	 * @param num
	 * @return
	 */
	public static String getPrevDate(int num, String date) {
		SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String result = "";
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - num);// 让日期减
			Date paramDate = calendar.getTime();
			result = formatDate(YYYY_MM_DD, paramDate);
		} catch (Exception e) {
			result = formatDate(YYYY_MM_DD, new Date());
		}
		return result;
	}

	/**
	 * 计算加日期 yyyy-MM-dd
	 * 
	 * @param num
	 * @return
	 */
	public static String getNextDate(int num, String date) {
		SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String result = "";
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(date));
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + num);// 让日期加
			Date paramDate = calendar.getTime();
			result = formatDate(YYYY_MM_DD, paramDate);
		} catch (Exception e) {
			result = formatDate(YYYY_MM_DD, new Date());
		}
		return result;
	}

	/**
	 * 格式化 手机号
	 * 
	 * @param argMobile
	 * @return
	 */
	public static String getShortMobile(String argMobile) {
		String strResult = "";
		if (null != argMobile && argMobile.length() == 11) {
			strResult = argMobile.substring(0, 3) + "****"
					+ argMobile.substring(argMobile.length() - 4, argMobile.length());
		} else {
			strResult = argMobile;
		}
		return strResult;
	}

	public static String formatIdNum(String idNum) {
		if (null != idNum && idNum.length() == 18) {
			String head = idNum.substring(0, 3);
			String body = "";
			String foot = idNum.substring(14, 18);

			for (int i = 0; i < 11; i++) {
				body += "*";
			}
			foot = idNum.substring(14, 18);
			idNum = head + body + foot;
		}
		return idNum;
	}

	/**
	 * 银行卡打码 前6后4中间*号代替
	 * 
	 * @param cardNum
	 * @return
	 */
	public static String HideBankCard(String cardNum) {
		String strResult = "";
		int len = 0;
		if (cardNum.length() > 10) {
			len = cardNum.length() - 10;
		}

		try {

			String param = "";
			for (int i = 0; i < len; i++) {
				param = param + "*";
			}
			if (cardNum.length() > 10) {
				strResult = cardNum.substring(0, 6) + param + cardNum.substring(cardNum.length() - 4, cardNum.length());
			} else {
				strResult = cardNum;
			}
		} catch (Exception ex) {

		}
		return strResult;
	}

	// 计算天差
	public static int countDiffDays(String date1, String date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		Date sdate = sdf.parse(date2);
		Date bdate = sdf.parse(date1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		if (between_days < 0) {
			return -1;
		}
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算加日期 yyyy-MM-dd
	 * 
	 * @param num
	 * @return
	 */
	public static String datePlusDate(int num, String date) {
		SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
		String result = "";
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(date));
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + num);// 让日期加
			Date paramDate = calendar.getTime();
			result = formatDate(YYYY_MM_DD, paramDate);
		} catch (Exception e) {
			result = formatDate(YYYY_MM_DD, new Date());
		}
		return result;
	}

	/**
	 * 计算减日期
	 * 
	 * @param num
	 * @return
	 */
	public static String getPrevDate2(int num, String date) {
		SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
		String result = "";
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - num);// 让日期减
			Date paramDate = calendar.getTime();
			result = formatDate(YYYY_MM_DD, paramDate);
		} catch (Exception e) {
			result = formatDate(YYYY_MM_DD, new Date());
		}
		return result;
	}

	public static boolean matches(String value, String checkKey) {
		Pattern pattern = Pattern.compile(checkKey);
		Matcher matcher = pattern.matcher((CharSequence) value);
		return matcher.matches();
	}

	/**
	 * 与当前时间作比较 比当前时间小 true 比当前时间大 false
	 */
	public static boolean parseTime(String time) throws ParseException {
		// 系统当前时间
		SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
		Date dateNow = df.parse(df.format(new Date()));
		Date checkdate = df.parse(time);
		return dateNow.getTime() > checkdate.getTime();
	}

	/**
	 * 格式化金额保留2位（直接舍位）
	 * 
	 * @param amt
	 * @return
	 * @author: qytong
	 * @date:2016年7月24日 下午12:09:29
	 */
	public static String formatBigDecimal(BigDecimal amt) {
		String amount = "0.00";
		if (amt == null) {
			return amount;
		}
		return amt.setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
	}
	/**
	 * 格式化金额保留2位（直接舍位）
	 * 
	 * @param amt
	 * @return
	 * @author: qytong
	 * @date:2016年7月24日 下午12:09:29
	 */
	
}
