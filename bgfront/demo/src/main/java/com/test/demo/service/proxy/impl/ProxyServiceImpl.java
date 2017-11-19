package com.test.demo.service.proxy.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.test.demo.bean.proxy.ProxyBean;
import com.test.demo.redis.RedisDao;
import com.test.demo.service.proxy.ProxyService;


/**
 * 代理类 service
 * @author ASUS
 * 创建时间  2017年11月17日 下午9:21:41
 *
 */
@Service
public class ProxyServiceImpl implements ProxyService {

	/**
	 * 失败的次数
	 */
	private int FAIL_TIMES= 3 ; 
	
	/**
	 * 失败的概率
	 */
	private double PROBABILITY = 0.6 ;
	
	@Autowired
	private RedisDao  redisDao;
	
	/**
	 * 得到代理bean
	 * @author ASUS
	 * 创建时间  2017年11月19日 下午4:38:00
	 */
	@Override
	public ProxyBean getProxyBean(String key) throws Exception {

		String bean  = redisDao.brpop(key);
		ProxyBean proxy = null  ;
		if(StringUtils.isNotBlank(bean)) {
			proxy = JSON.parseObject(bean, ProxyBean.class);
			boolean isRemove = isRemove(proxy);
			if(!isRemove) {
				redisDao.lpush(key, JSON.toJSONString(proxy));
			}
		}
			
		return proxy;
	}

	
	/**
	 * 是否移除bean 从redis 中
	 * @author ASUS
	 * 创建时间  2017年11月17日 下午9:28:41
	 * @param proxy
	 * @return 
	 * @throws Exception
	 */
	private boolean isRemove(ProxyBean proxy) throws Exception{
		boolean result = true ;
		if (proxy.getFailureTimes() >= FAIL_TIMES && PROBABILITY <= (Double.valueOf(proxy.getFailureTimes()) /
				(Double.valueOf(proxy.getFailureTimes()+proxy.getSuccessFulTimes()))) ) {
			result = false ;
		}
		return  result;
	}

	/**
	 * 插入到redis中
	 * @author ASUS
	 * 创建时间  2017年11月19日 下午4:38:15
	 */
	@Override
	public void addProxyBeans(String key, List<String> values) throws Exception {
		redisDao.lpushList(key, values);
	}

	
}
