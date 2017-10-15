package com.test.demo.async.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.test.demo.bean.EventBean;
import com.test.demo.redis.RedisDao;
import com.test.demo.redis.RedisKeyUtil;

/**
 * 消费者
 * @author ASUS
 * 创建时间  2017年9月17日 下午5:43:34
 *
 */
@Service
public class EventProducer {

	@Autowired
	private RedisDao  redisDao;
	
	/**
	 * 消费事件
	 * @author ASUS
	 * 创建时间  2017年9月17日 下午5:44:42
	 * @param bean
	 * @return
	 */
	public boolean fireEvent(EventBean bean) {
		
		try {
			String json =JSON.toJSONString(bean);
			String key = RedisKeyUtil.getEventQueueKey();
			redisDao.lpush(key, json);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
