package com.test.demo.service;

import java.util.List;

import com.test.demo.bean.NewsBean;

/**
 * 头条service
 * @author ASUS
 * 创建时间  2017年8月21日 下午10:01:28
 *
 */
public interface ToutiaoService {

	/**
	 * 说
	 * @author ASUS
	 * 创建时间  2017年8月21日 下午10:01:57
	 * @return
	 */
	public String say() ;
	
	
	/**
	 * 得到最新的bean
	 * @author ASUS
	 * 创建时间  2017年8月21日 下午10:02:46
	 * @param userId
	 * @param offert
	 * @param limit
	 * @return
	 */
	public List<NewsBean> getLateNewsBeans( int userId,int offset,int limit);
}
