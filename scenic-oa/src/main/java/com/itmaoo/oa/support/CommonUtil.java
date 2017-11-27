package com.itmaoo.oa.support;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static String formatDate(Date date){
		if(date==null){
			return null;
		}
		SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日"); 
		return time.format(date); 
	}

}