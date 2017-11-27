package com.test.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import static com.test.demo.util.ConstantsUtil.*;
/**
 * 日期工具类
 * @author ASUS
 * 创建时间  2017年8月27日 下午10:31:56
 *
 */
public class DateUtil {

	/** 格式化年月日 yyyy-MM-dd HH:mm:ss**/
	private static SimpleDateFormat ymdhmsDfformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/** 格式化年月日 yyyy-MM-dd**/
    public  static  SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    /** 格式化年月日 yyyy-MM-dd  HH:mm:ss**/
    public  static  SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

    /** 格式化年月日 yyyy-MM-dd  HH:mm**/
    public  static  SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd  HH:mm");

    /** 格式化年月日 yyyy-MM-dd HH:mm:ss**/
    public static   DateTimeFormatter yyyyMMddHHmmssJava8 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**格式化时间为 yyyy-MM-dd 00:00:00    */
    public static   DateTimeFormatter yyyyMMdd000000 = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
	
    /**格式化时间为 yyyy-MM-dd*/
    public static   DateTimeFormatter yyyyMMddD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
	
	/**
	 * 用现在的时间 跟 传输过来的时间加一天来做比较 
	 * @author ASUS
	 * 创建时间  2017年11月20日 下午9:13:49
	 * @param time  yyyy-mm-dd
	 * @return  result
	 */
	@SuppressWarnings("static-access")
	public static boolean compareTime(String time ) {
		boolean result  = false;
		LocalDate  localDate = LocalDate.parse(time , yyyyMMddD);
	//	LocalDateTime  localDateTime = LocalDateTime.parse(time, yyyyMMddD).plusDays(PLUS_DAYS);
		LocalDate toDayLast = localDate.now();
		result = localDate.isBefore(toDayLast);
		
		return result ;
	}
	
	/**
	 * 时间对比  增加天数
	 * @author ASUS
	 * 创建时间  2017年11月25日 下午9:48:44
	 * @param time
	 * @param days
	 * @return
	 */
	public static boolean compareTime(String time ,long days ) {
		boolean result  = false;

		LocalDateTime  localDateTime = LocalDateTime.parse(time, yyyyMMddD).plusDays(days);
		LocalDateTime toDayLast = localDateTime.now();
		result = toDayLast.isBefore(localDateTime);
		
		return result ;
	}
	
	/**
	 * 给指定日期增加天数  日期格式yyyy-MM-dd
	 * @author ASUS
	 * 创建时间  2017年11月26日 上午11:15:33
	 * @param time  时间
	 * @param days  增加的天数
	 * @return
	 */
	public static String addDays(String time , long days) {
		LocalDate localDate = LocalDate.parse(time, yyyyMMddD).plusDays(days);
		String result = localDate.format(yyyyMMddD);
		return result;
	}
	
	/**
	 * 创建时间 
	 * @author ASUS
	 * 创建时间  2017年11月26日 上午11:22:17
	 * @param time
	 * @param days
	 * @return
	 */
	public static String createyyyyMMdd(long days){
		LocalDateTime  localDateTime = LocalDateTime.now().plusDays(days);
		String result = localDateTime.format(yyyyMMddD);
		return result;
	}
	
	
}
