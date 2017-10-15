package com.test.demo.async.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.test.demo.async.EventType;
import com.test.demo.async.IEventHandler;
import com.test.demo.bean.EventBean;
import com.test.demo.redis.RedisDao;
import com.test.demo.redis.RedisKeyUtil;


/**
 * 事件的消费者
 * @author ASUS
 * 创建时间  2017年9月16日 下午4:01:07
 *
 */
@Service
public class EventConsumer implements InitializingBean,ApplicationContextAware {
	
	/**
	 * 日志文件
	 */
	private static final Logger logger=LoggerFactory.getLogger(EventConsumer.class);
	
	private  ApplicationContext  applicationContext;
	
	private Map<EventType, List<IEventHandler>> config= new HashMap<EventType, List<IEventHandler>>();
	
	@Autowired
	private RedisDao  redisDao;
	
	
	

	/**
	 * 得到容器	
	 * @author ASUS
	 * 创建时间  2017年9月17日 上午10:16:43
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			this.applicationContext =applicationContext;
	}

	/**
	 * 初始化这个bean的时候执行该方法拿到类型
	 * @author ASUS
	 * 创建时间  2017年9月17日 上午10:16:58
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		/**实现接口的类实例*/
		Map<String,IEventHandler > beans=applicationContext.getBeansOfType(IEventHandler.class);
		
		if(beans != null) {
			for(Map.Entry<String, IEventHandler>  eventEntry:beans.entrySet()) {
				List<EventType> eventHandlers = eventEntry.getValue().getSupportEventTypes();
				
				if(eventHandlers != null) {
					for(EventType type:eventHandlers) {
						if(!config.containsKey(type)) {
							config.put(type, new ArrayList<IEventHandler>());
						}
						
						config.get(type).add(eventEntry.getValue());
					}
				}
			}
			
		}
		
		
		Thread thread =new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true) {
					String key =RedisKeyUtil.getEventQueueKey();
					
					String message=redisDao.brpop(0, key);
					
				
					if(message.equals(key)) {
						continue;
					}
					
					EventBean  bean=JSON.parseObject(message,EventBean.class);
					
					if(!config.containsKey(bean.getType())) {
						logger.error("不能识别的事件");
						continue;
					}
					
					for(IEventHandler  iEventHandler:config.get(bean.getType())) {
						iEventHandler.doHandle(bean);
					}
					
				}
				
			}
		});
		thread.start();
		
	}

	

}
