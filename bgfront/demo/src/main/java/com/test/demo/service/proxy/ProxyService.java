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
	
	/**
	 * 根据key 插入书库到redis中
	 * @author ASUS
	 * 创建时间  2017年11月26日 下午6:15:08
	 * @param key
	 * @param values
	 * @throws Exception
	 */
	public void addProxyBean(String key ,String values) throws Exception  ;
	
	/**
	 * 根据代理或者直连的方式采集内容
	 * @author ASUS
	 * 创建时间  2017年11月26日 下午5:13:01
	 * @throws IllegalAccessException 
	 */
	public List<ProxyBean> getProxyBeans (ProxyBean bean) throws Exception ;
}
