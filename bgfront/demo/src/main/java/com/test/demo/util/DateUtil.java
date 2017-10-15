package com.test.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author ASUS
 * 创建时间  2017年8月27日 下午10:31:56
 *
 */
public class DateUtil {

	/** 格式化年月日 yyyy-MM-dd HH:mm:ss**/
	private static SimpleDateFormat ymdhmsDfformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	/**
	 * 根据yyyy-MM-dd HH:mm:ss 返回Date类型数据
	 * @author ASUS
	 * 创建时间  2017年8月27日 下午10:34:00
	 * @throws Exception 
	 */
	public static Date getDateFromYYMMDDHHmmss(String dateString) throws Exception{
		Date date=ymdhmsDfformat.parse(dateString);
		return date;
	}
}
