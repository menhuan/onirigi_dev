package com.test.demo.job;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import com.alibaba.fastjson.JSON;
import com.test.demo.bean.proxy.ProxyBean;
import com.test.demo.redis.RedisDao;
import com.test.demo.service.proxy.ProxyPageParser;
import com.test.demo.service.proxy.ProxyService;
import com.test.demo.util.DateUtil;
import com.test.demo.util.RestRequestClient;

import static com.test.demo.service.proxy.impl.ProxyPool.*;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static com.test.demo.redis.RedisKeyUtil.*;
import static com.test.demo.service.proxy.impl.ProxyPool.*;

import static com.test.demo.util.ConstantsUtil.*;

/**
 * 代理job
 * @author dell
 */
@Component
public class ProxyJob {
	
	/**
	 * 日志文件
	 */
	private static final Logger logger =LoggerFactory.getLogger(ProxyJob.class);
	
	/**
	 * 代理service
	 */
	@Autowired
	private ProxyService   proxyService;
	
	@Autowired
	private RedisDao  redisDao ;
	
	
	/**
	 * 执行代理
	 * 1. 首先去 查看redis中的 查询 是否在列表中 有 数据  没有数据需要初始化代理ip   不使用代理 开启一个线程 异步操作
	 * 2. 从redis 中取出一个ip  根据时间去查询 是否要 更新
	 *    需要更新的话用 ip池里面的数据 去 代理页面抓取ip 然后 放入到池子中
	 * 3.   如果是空的话 那么 是用本地ip 去采集
	 * 4. 否则 应用代理ip 去采集  
	 */
	public void runProxy() throws Exception{
		
		String  time = redisDao.get(REDIS_PROXY_IP_TIME);
		
		if(!NULL.equals(time) && StringUtils.isNotEmpty(time) && DateUtil.compareTime(time) ) {
			ProxyBean  proxy   =proxyService.getProxyBean(REDIS_PROXY_IP_LIST);
			List<ProxyBean>  beans = new ArrayList();
			
			try {
				beans = proxyService.getProxyBeans(proxy);
			} catch (Exception e) {
				logger.error("代理ip爬取超时",e);
				this.dealWithExce(proxy);
			}
			
			if(beans != null) {
				//执行入库redis中
				List<String> values  =	JSON.parseArray(JSON.toJSONString(beans), String.class);
				proxyService.addProxyBeans(REDIS_PROXY_IP_LIST, values);
				String  keyResult = DateUtil.addDays(time, PLUS_DAYS) ;
				redisDao.set(REDIS_PROXY_IP_TIME, keyResult);
			}
			
		}
		if(StringUtils.isEmpty(time)) {
			long lastDays = -1l;
			String nowDay = DateUtil.createyyyyMMdd(lastDays);
			redisDao.set(REDIS_PROXY_IP_TIME, nowDay);
		}
			
	}
	
	/**
	 * 代理发生异常时 处理
	 * @author ASUS
	 * 创建时间  2017年11月26日 下午5:26:34
	 * @param bean  代理bean
	 * @throws Exception 
	 */
	public void dealWithExce(ProxyBean bean) throws Exception{
		int	 failureTimes = bean.getFailureTimes()+FAIL_TIMES;
		bean.setFailureTimes(failureTimes);
		proxyService.addProxyBean(REDIS_PROXY_IP_LIST, JSON.toJSONString(bean));
		this.runProxy();
	}
	
}
