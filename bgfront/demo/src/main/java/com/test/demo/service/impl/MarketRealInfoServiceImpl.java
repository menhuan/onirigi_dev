package com.test.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.bean.MarketRealInfoBean;
import com.test.demo.dao.MarketRealInfoDao;
import com.test.demo.service.MarketRealInfoService;

/**
 * 执行定时任务6小时获取交易数据service
 * @author ASUS
 * 创建时间  2017年8月27日 下午8:58:36
 *
 */
@Service
public class MarketRealInfoServiceImpl implements MarketRealInfoService{

	@Autowired
	private MarketRealInfoDao  marketRealInfoDao;
	
	/**
	 * 查询bean中的信息
	 *
	 * @author ASUS
	 * 创建时间  2017年8月27日 下午5:25:16
	 */
	@Override
	public Map getRealInfo()  throws Exception{
		return marketRealInfoDao.getRealInfo();
	}

	/**
	 * 批量插入数据
	 * @author ASUS
	 * 创建时间  2017年8月27日 下午5:25:26
	 */
	@Override
	public void insertRealInfo(List<MarketRealInfoBean> list) throws Exception{
		marketRealInfoDao.insertRealInfo(list);
	}

	
	
}
