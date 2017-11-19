package com.test.demo.service.proxy;

import java.util.List;

import com.test.demo.bean.proxy.ProxyBean;

/**
 * 代理service
 * @author ASUS
 * 创建时间  2017年11月17日 下午9:16:41
 *
 */
public interface ProxyService {


	/**
	 * 根据redis key 取出一个redis 中的内容
	 * @author ASUS
	 * 创建时间  2017年11月17日 下午9:17:47
	 * @param key redis中的key
	 * @return
	 * @throws Exception
	 */
	public ProxyBean getProxyBean(String key) throws Exception ;

	
	/**
	 * 根据key插入到redis中
	 * @author ASUS
	 * 创建时间  2017年11月19日 下午4:37:27
	 * @param key
	 * @throws Exception
	 */
	public void addProxyBeans(String key ,List<String> values) throws Exception ;
}
