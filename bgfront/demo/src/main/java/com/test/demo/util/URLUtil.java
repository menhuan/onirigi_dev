package com.test.demo.util;

import java.net.URLEncoder;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import java.util.Map.Entry;

import com.test.demo.bean.common.SignBean;


/**
 * 访问url 参数组合
 * @author ASUS
 * 创建时间  2018年3月17日 下午6:26:13
 *
 */
public class URLUtil {

	
	/**
	 * 火币网 签名
	 * @author ASUS
	 * 创建时间  2018年3月17日 下午6:39:43
	 * @param treeMap
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String  getHuobiUrl(TreeMap<String, String> treeMap , SignBean bean) throws Exception{
		StringBuffer result =  new StringBuffer();
		StringBuffer  buffer = new StringBuffer();
		buffer.append(bean.getMethod()).append(bean.getApiName()).append(bean.getMethodName());
		for(Entry<String, String> entry : treeMap.entrySet() ) {
			entry.setValue(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
			buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		String content = StringUtils.substring(buffer.toString(), 0, buffer.length() -1);
		String  encrypContent = URLEncoder.encode(EncrypUtil.sha256HMAC(content),"UTF-8");
		
		
		for(Entry<String, String> entry : treeMap.entrySet() ) {
			result.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		result.append("Signature=").append(encrypContent);
		return result.toString();
		
	}
	
	
}


