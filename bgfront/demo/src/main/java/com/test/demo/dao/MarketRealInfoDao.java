package com.test.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.demo.bean.MarketRealInfoBean;

/**
 * 交易数据dao
 * @author ASUS
 * 创建时间  2017年8月27日 下午3:54:32
 *
 */
public interface MarketRealInfoDao {

	
	
	/**
	 * 根据bean 查询交易信息
	 * @author ASUS
	 * 创建时间  2017年8月27日 下午4:00:24
	 * @param bean
	 * @return
	 */
	public Map  getRealInfo() throws Exception;
	
	/**
	 * 批量插入数据
	 * @author ASUS
	 * 创建时间  2017年8月27日 下午4:42:31
	 * @param list
	 */
	public void insertRealInfo(@Param("result")List<MarketRealInfoBean> list) throws Exception;
	
	

}
