package com.test.demo.service;

import java.util.List;
import java.util.Map;

import com.test.demo.bean.MarketRealInfoBean;

/**
 * service
 * @author ASUS
 * 创建时间  2017年8月27日 下午5:01:06
 *
 */
public interface MarketRealInfoService {
	/**
	 * 根据bean 查询交易信息
	 * @author ASUS
	 * 创建时间  2017年8月27日 下午4:00:24
	 * @param bean
	 * @return
	 */
	public Map  getRealInfo()throws Exception;
	
	/**
	 * 批量插入数据
	 * @author ASUS
	 * 创建时间  2017年8月27日 下午4:42:31
	 * @param list
	 * @throws Exception 
	 */
	public void insertRealInfo(List<MarketRealInfoBean> list) throws Exception;
	
}
