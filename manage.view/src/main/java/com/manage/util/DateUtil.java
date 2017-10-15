package com.manage.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by ASUS on 2017/9/24.
 */
public class DateUtil {

    /**
     * 静态转换
     */
   public  static  SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd ");

   public  static  SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

   public  static  SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd  HH:mm");

   public static   DateTimeFormatter yyyyMMddHHmmssJava8 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 将 日期类型转换为Date
     * @param content
     * @return
     */
   public static Date StringToDate(String  content){

       ZoneId zoneId=ZoneId.systemDefault();
       LocalDateTime  localDateTime    = LocalDateTime.parse(content,yyyyMMddHHmmssJava8);
       ZonedDateTime  zonedDateTime  =localDateTime.atZone(zoneId);

       return Date.from(zonedDateTime.toInstant())  ;
   }


}
