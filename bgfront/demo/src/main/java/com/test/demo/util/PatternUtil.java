package com.test.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.test.demo.base.BaseCofig;

/**
 * 正则匹配 
 * @author ASUS
 * 创建时间  2017年10月21日 下午9:18:25
 *
 */
public class PatternUtil {

	
	/**
	 * 正则匹配
	 * @author ASUS
	 * 创建时间  2017年10月21日 下午9:21:10
	 * @param parContent  匹配规则  
	 * @param content 要匹配的内容
	 * @return
	 */
	public static Matcher  patternMatcher (String parRule , String content) {
		 Pattern  pattern = Pattern.compile(parRule,Pattern.DOTALL);
         Matcher  matcher = pattern.matcher(content);
         return matcher;
	}
}
