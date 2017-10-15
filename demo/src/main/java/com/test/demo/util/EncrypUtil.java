package com.test.demo.util;

import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密工具类
 * @author ASUS
 * 创建时间  2017年8月26日 下午1:35:06
 *
 */
public class EncrypUtil {

	/**
	 * 日志组件
	 */
	private static Logger logger =LoggerFactory.getLogger(EncrypUtil.class);
	
	/**
	 * 使用MD5加密
	 * @author ASUS
	 * 创建时间  2017年8月26日 下午1:35:43
	 * @param key
	 * @return
	 */
	public static String MD5encrpy(String key) {
		 char hexDigits[] = {
	                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
	        };
		 
		 try {
			 byte[] btInput=key.getBytes();
			 
			 //获得md5摘要算法的MessageDigest 对象
			 MessageDigest  mdInstDigest =MessageDigest.getInstance("MD5");
			 //使用指定的字节更新摘要
			 mdInstDigest.update(btInput);
			 //获得密文
			 byte[] md=mdInstDigest.digest();
			 //把密文转换成十六进制的字符串形式
			 int  j=md.length;
			 char str[]= new char[j*2];
			 int k=0;
			 for(int i=0;i<j;i++) {
				 byte byte0=md[i];
				 str[k++]=hexDigits[byte0>>>4 & 0xf];
				 str[k++]=hexDigits[byte0 & 0xf];
			 }
			
			 return new String(str);
			 
		} catch (Exception e) {
			logger.error("md5加密出现错误。。。",e);
			return null;
		}
	}
}
