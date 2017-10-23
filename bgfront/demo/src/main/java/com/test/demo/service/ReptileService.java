package com.test.demo.service;

/**
 * 爬虫service
 * @author ASUS
 * 创建时间  2017年10月19日 下午7:54:31
 *
 */
public interface ReptileService {

	
	/**
	 * 爬取首页
	 * @author ASUS
	 * 创建时间  2017年10月19日 下午8:01:01
	 * @throws Exception
	 */
	public void reptileIndex() throws Exception; 

	/**
	 * 爬取首页中链接的数据
	 * @author ASUS
	 * 创建时间  2017年10月23日 下午8:50:21
	 * @throws Exception
	 */
	public void reptileHtml() throws Exception;

}
