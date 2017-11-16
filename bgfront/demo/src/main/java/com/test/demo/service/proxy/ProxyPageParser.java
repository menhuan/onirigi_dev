package com.test.demo.service.proxy;

import java.util.List;

import com.test.demo.bean.proxy.Proxy;
import com.test.demo.service.Parser;

/**
 * 代理类
 * @author dell
 */
public interface ProxyPageParser extends Parser {

	/**
	 * 是否开启匿名代理
	 */
	static final boolean  isOpenProxy =true;
	
	/**
	 * 解析某个网站的连接
	 * @param content
	 * @return
	 */
	List<Proxy> parse(String html ) ;
	
}
